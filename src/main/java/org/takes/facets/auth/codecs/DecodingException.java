/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.facets.auth.codecs;

/**
 * Decoding exception.
 *
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id: e8d812344cc21fcfe5304ec084abecd4fb0f9b6c $
 * @since 0.5
 */
public final class DecodingException extends RuntimeException {

    /**
     * Serialization marker.
     */
    private static final long serialVersionUID = 0x7529FA781EDA1479L;

    /**
     * Public ctor.
     * @param cause The cause of it
     */
    DecodingException(final String cause) {
        super(cause);
    }

    /**
     * Public ctor.
     * @param cause The cause of it
     */
    DecodingException(final Throwable cause) {
        super(cause);
    }

}
