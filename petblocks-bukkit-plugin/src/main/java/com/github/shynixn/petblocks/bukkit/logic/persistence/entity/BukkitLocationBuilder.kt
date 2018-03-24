package com.github.shynixn.petblocks.bukkit.logic.persistence.entity

import com.github.shynixn.petblocks.api.persistence.entity.IPosition
import com.github.shynixn.petblocks.core.logic.persistence.entity.LocationBuilder
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.util.EulerAngle
import org.bukkit.util.Vector
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
class BukkitLocationBuilder : LocationBuilder, ConfigurationSerializable {

    constructor() : super()
    constructor(worldName: String?, x: Double, y: Double, z: Double, yaw: Float, pitch: Float) : super(worldName, x, y, z, yaw, pitch)
    constructor(worldName: String?, x: Double, y: Double, z: Double, yaw: Double, pitch: Double) : super(worldName, x, y, z, yaw, pitch)
    constructor(items: MutableMap<String, Any>?) : super(items)


    /**
     * Copies the position to another [IPosition].
     */
    fun copy(): IPosition {
        return BukkitLocationBuilder(this.worldName, this.x, this.y, this.z, this.yaw, this.pitch)
    }

    /**
     * Converts this position to a [Location].
     */
    fun toLocation(): Location? {
        return if (Bukkit.getWorld(this.worldName) == null) null else Location(Bukkit.getWorld(this.worldName), this.x, this.y, this.z, this.yaw.toFloat(), this.pitch.toFloat())
    }

    /**
     * Converts this position to a [Vector].
     */
    fun toVector(): Vector {
        return Vector(this.x, this.y, this.z)
    }

    /**
     * Converts this position to an [EulerAngle].
     */
    fun toAngle(): EulerAngle {
        return EulerAngle(this.x, this.y, this.z)
    }

    /**
     * Sets the [world] of the builder.
     */
    fun setWorld(world: World): IPosition {
        this.worldName = world.name
        return this
    }

    /**
     * Returns the [world] of the builder.
     */
    fun getWorld(): World? {
        return Bukkit.getWorld(this.worldName)
    }

    /**
     * Serializes the location data to be stored to the filesystem
     *
     * @return serializedContent
     */
    override fun serialize(): Map<String, Any> {
        val map = LinkedHashMap<String, Any>()
        map["x"] = this.x
        map["y"] = this.y
        map["z"] = this.z
        map["yaw"] = this.yaw
        map["pitch"] = this.pitch
        map["worldname"] = this.worldName
        return map
    }
}