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
package org.takes.facets.auth;

import com.jcabi.matchers.XhtmlMatchers;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.takes.rq.RqFake;
import org.takes.rs.xe.RsXembly;
import org.takes.rs.xe.XeAppend;

/**
 * Test case for {@link XeLogoutLink}.
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id: e8f341bbe22a71e25b6e43a5b69ef36f5fa56b3f $
 * @since 0.8
 */
public final class XeLogoutLinkTest {

    /**
     * XeLogoutLink can create a correct link.
     * @throws IOException If some problem inside
     */
    @Test
    public void generatesCorrectLink() throws IOException {
        MatcherAssert.assertThat(
            IOUtils.toString(
                new RsXembly(
                    new XeAppend(
                        "root",
                        new XeLogoutLink(new RqFake())
                    )
                ).body()
            ),
            XhtmlMatchers.hasXPaths(
                "/root/links/link[@rel='takes:logout']"
            )
        );
    }

}