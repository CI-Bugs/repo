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
package org.takes.tk;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.RsWithHeaders;

/**
 * Take that headers.
 *
 * <p>This take wraps all responses of another take, adding
 * headers to them, through {@link org.takes.rs.RsWithHeaders}.
 *
 * <p>The class is immutable and thread-safe.
 *
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id: 8f27b913bc8095c36566a2aae2fb7d6965f820ff $
 * @since 0.1
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class TkWithHeaders extends TkWrap {

    /**
     * Ctor.
     * @param take Original
     * @param headers Headers
     */
    public TkWithHeaders(final Take take, final String... headers) {
        this(take, Arrays.asList(headers));
    }

    /**
     * Ctor.
     * @param take Original
     * @param headers Headers
     */
    public TkWithHeaders(final Take take, final Collection<String> headers) {
        super(
            new Take() {
                @Override
                public Response act(final Request req) throws IOException {
                    return new RsWithHeaders(take.act(req), headers);
                }
            }
        );
    }

}