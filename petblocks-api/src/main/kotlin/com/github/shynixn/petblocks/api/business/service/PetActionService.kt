package com.github.shynixn.petblocks.api.business.service

import com.github.shynixn.petblocks.api.persistence.entity.AIBase

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
interface PetActionService {
    /**
     * Calls the pet to the given [player]. If the pet is not enabled, it will be enabled after calling.
     */
    fun <P> callPet(player: P)

    /**
     * Disables the pet of the given [player].
     */
    fun <P> disablePet(player: P)

    /**
     * Toggles the pet of the given [player]. If the pet is disabled it will be enabled and when enabled it will be
     * disabled.
     */
    fun <P> togglePet(player: P)

    /**
     * Applies the given ais to the pet of the given player.
     * Returns a pair of added and removed ais amount.
     */
    fun <P> applyAI(player: P, addAis: List<AIBase>, removeAis: List<AIBase>): Pair<Int, Int>

    /**
     * Renames the pet of the given [player] to the given [name].
     */
    fun <P> renamePet(player: P, name: String)

    /**
     * Launches the pet of the player.
     */
    fun <P> launchPet(player: P)

    /**
     * Sets the pet of the given [player] to the given skin.
     */
    fun <P> changePetSkin(player: P, skin: String)
}