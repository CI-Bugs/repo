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
package org.takes.rq;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.EqualsAndHashCode;
import org.takes.Request;

/**
 * Request in which the body is a byte array.
 *
 * @author Dragan Bozanovic (bozanovicdr@gmail.com)
 * @version $Id: bea2c51fcc63f1f5a3c1974a2dd036f86cd5e069 $
 * @since 0.24
 */
@EqualsAndHashCode
public final class RqBytes implements Request {
    /**
     * Head.
     */
    private final transient List<String> hed;

    /**
     * Body.
     */
    private final transient byte[] bdy;

    /**
     * Ctor.
     * @param head Head
     * @param body Body
     */
    public RqBytes(final List<String> head, final byte[] body) {
        this.hed = Collections.unmodifiableList(head);
        this.bdy = Arrays.copyOf(body, body.length);
    }

    @Override
    public Iterable<String> head() {
        return this.hed;
    }

    @Override
    public InputStream body() {
        return new ByteArrayInputStream(this.bdy);
    }
}