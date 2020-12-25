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
package org.takes.http;

import java.net.Socket;
import lombok.EqualsAndHashCode;

/**
 * Safe back-end.
 *
 * <p>The class is immutable and thread-safe.
 *
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id: 3dc08ec8f6c2b6c520e8e9b69f3c538fde2f6ee9 $
 * @since 0.1
 */
@EqualsAndHashCode(callSuper = true)
public final class BkSafe extends BkWrap {

    /**
     * Ctor.
     * @param back Original back
     */
    public BkSafe(final Back back) {
        super(new Back() {
            @Override
            @SuppressWarnings("PMD.AvoidCatchingThrowable")
            public void accept(final Socket socket) {
                try {
                    back.accept(socket);
                    // @checkstyle IllegalCatchCheck (1 line)
                } catch (final Throwable ex) {
                    assert ex != null;
                }
            }
        });
    }

}