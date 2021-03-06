package com.github.shynixn.petblocks.api.business.service

import java.io.Reader
import java.io.Writer

/**
 * Created by Shynixn 2019.
 * <p>
 * Version 1.2
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2019 by Shynixn
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
interface YamlSerializationService {
    /**
     * Serializes the given [instance] to a key value pair map.
     */
    fun serialize(instance: Any): Map<String, Any?>

    /**
     * Serializes the given [instance] to the target [writer].
     */
    fun serialize(instance: Any, writer: Writer)

    /**
     * DeSerializes the given [dataSource] into a new instance of the given [targetObjectClass].
     */
    fun <R> deserialize(targetObjectClass: Any, dataSource: Map<String, Any?>): R

    /**
     * DeSerializes the given [reader] into a new instance of the given [targetObjectClass].
     */
    fun <R> deserialize(targetObjectClass: Any, reader: Reader): R
}