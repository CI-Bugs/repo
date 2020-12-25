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
package org.takes.rs.xe;

import java.io.IOException;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import org.xembly.Directive;

/**
 * Xembly source that could be empty of could return an encapsulated
 * other Xembly source.
 *
 * <p>The class is immutable and thread-safe.
 *
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id: 5bbbe59694f06ad46873977a2ec2cdf2fefafa65 $
 * @since 0.13
 */
@EqualsAndHashCode(callSuper = true)
public final class XeWhen extends XeWrap {

    /**
     * Ctor.
     * @param condition Condition
     * @param source Xembly source
     */
    @SuppressWarnings("PMD.CallSuperInConstructor")
    public XeWhen(final boolean condition, final XeSource source) {
        super(
            new XeSource() {
                @Override
                public Iterable<Directive> toXembly() throws IOException {
                    final Iterable<Directive> dirs;
                    if (condition) {
                        dirs = source.toXembly();
                    } else {
                        dirs = Collections.emptyList();
                    }
                    return dirs;
                }
            }
        );
    }

}