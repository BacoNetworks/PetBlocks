@file:Suppress("UNCHECKED_CAST")

package com.github.shynixn.petblocks.bukkit.logic.business.service

import com.github.shynixn.petblocks.api.business.enumeration.MaterialType
import com.github.shynixn.petblocks.api.business.enumeration.Version
import com.github.shynixn.petblocks.api.business.proxy.ItemStackProxy
import com.github.shynixn.petblocks.api.business.service.ItemService
import com.github.shynixn.petblocks.bukkit.logic.business.proxy.ItemStackProxyImpl
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.lang.reflect.Method
import java.util.*

/**
 * Created by Shynixn 2018.
 * <p>
 * Version 1.2
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2018 by Shynixn
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
class Item18R1ServiceImpl(private val version: Version) : ItemService {
    private val getMaterialFromIdMethod: Method = Material::class.java.getDeclaredMethod("getMaterial", Int::class.javaPrimitiveType)
    private val getItemInMainHandMethod: Method
    private var getItemInOffHandMethod: Method? = null

    /**
     * Initialize.
     */
    init {
        val inventoryClazz = Class.forName("org.bukkit.inventory.PlayerInventory")

        getItemInMainHandMethod = if (version.isVersionSameOrGreaterThan(Version.VERSION_1_9_R1)) {
            inventoryClazz.getDeclaredMethod("getItemInMainHand", ItemStack::class.java)
        } else {
            Class.forName("org.bukkit.entity.HumanEntity").getDeclaredMethod("getItemInHand", ItemStack::class.java)
        }

        if (version.isVersionSameOrGreaterThan(Version.VERSION_1_9_R1)) {
            getItemInOffHandMethod = inventoryClazz.getDeclaredMethod("getItemInOffHand", ItemStack::class.java)
        }
    }

    /**
     * Gets the itemstack in the hand of the player with optional offHand flag.
     */
    override fun <P, I> getItemInHand(player: P, offHand: Boolean): Optional<I> {
        if (player !is Player) {
            throw IllegalArgumentException("Player has to be a BukkitPlayer!")
        }

        return if (version.isVersionSameOrGreaterThan(Version.VERSION_1_9_R1)) {
            if (offHand) {
                Optional.ofNullable(getItemInOffHandMethod!!.invoke(player.inventory) as I)
            } else {
                Optional.ofNullable(getItemInMainHandMethod.invoke(player.inventory) as I)
            }
        } else {
            Optional.ofNullable(getItemInMainHandMethod.invoke(player) as I)
        }
    }

    /**
     * Creates a new itemstack from the given parameters.
     */
    override fun createItemStack(type: Any, dataValue: Int): ItemStackProxy {
        return ItemStackProxyImpl(getMaterialValue(type).name, dataValue)
    }

    /**
     * Gets if the given [itemStack] has got the given [type] and [dataValue].
     */
    override fun <I> hasItemStackProperties(itemStack: I, type: Any, dataValue: Int): Boolean {
        if (itemStack !is ItemStack) {
            throw IllegalArgumentException("ItemStack has to be a BukkitItemStack!")
        }

        val material = getMaterialValue(type)
        return material == itemStack.type && dataValue == itemStack.durability.toInt()
    }

    /**
     * Processing if there is any way the given [value] can be mapped to an material.
     */
    private fun getMaterialValue(value: Any): Material {
        if (value is Int) {
            return getMaterialFromIdMethod.invoke(null, value) as Material
        } else if (value is String && value.toIntOrNull() != null) {
            return getMaterialFromIdMethod.invoke(null, value.toInt()) as Material
        } else if (value is String) {
            return Material.getMaterial(value) ?: throw IllegalArgumentException("Material $value does not exist!")
        } else if (value is MaterialType) {
            return getMaterialValue(value.MinecraftNumericId)
        }

        throw  throw IllegalArgumentException("Material $value is not a string or int.")
    }
}