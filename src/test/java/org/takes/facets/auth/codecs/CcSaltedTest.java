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

import java.io.IOException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.takes.facets.auth.Identity;

/**
 * Test case for {@link CcSalted}.
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id: 907faafc8c80b329b4aed9b25e7f798fbfb03091 $
 * @since 0.5
 */
public final class CcSaltedTest {

    /**
     * CcSalted can decode.
     * @throws IOException If some problem inside
     */
    @Test
    public void decodesInvalidData() throws IOException {
        MatcherAssert.assertThat(
            new CcSafe(new CcSalted(new CcPlain())).decode(
                " % tjw".getBytes()
            ),
            Matchers.equalTo(Identity.ANONYMOUS)
        );
        MatcherAssert.assertThat(
            new CcSafe(new CcSalted(new CcPlain())).decode(
                "75726E253".getBytes()
            ),
            Matchers.equalTo(Identity.ANONYMOUS)
        );
    }

}