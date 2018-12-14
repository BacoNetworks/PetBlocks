package com.github.shynixn.petblocks.core.logic.persistence.entity

import com.github.shynixn.petblocks.api.persistence.entity.GuiIcon
import com.github.shynixn.petblocks.api.persistence.entity.GuiItem

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
class GuiItemEntity : GuiItem {
    /**
     * Position in the inventory which cannot be scrolled.
     */
    override var positionFixed: Int = -1
    /**
     * Should this icon always be hidden?
     */
    override var hidden: Boolean = false

    /**
     * Should this icon be hidden when the pet is spawned?
     */
    override var hiddenWhenPetIsSpawned: Boolean = false
    /**
     * Gets a nullable script
     * which can be used for defining the action of the gui item.
     */
    override var script: String? = null
    /**
     * Icon of the gui item.
     */
    override val icon: GuiIcon = GuiIconEntity()

    /** Returns the position in the inventory. */
    override var position: Int = 0
}