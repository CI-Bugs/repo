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
package org.takes.facets.hamcrest;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.takes.rs.RsEmpty;
import org.takes.rs.RsWithBody;
import org.takes.rs.RsWithHeader;
import org.takes.rs.RsWithHeaders;

/**
 * Test case for {@link HmRsHeader}.
 * @author Eugene Kondrashev (eugene.kondrashev@gmail.com)
 * @version $Id: 074524bd4e747cc4f57659f43d566428127b502b $
 * @since 0.23.3
 */
public final class HmRsHeaderTest {

    /**
     * HmRsHeader can test header available.
     * @throws Exception If some problem inside
     */
    @Test
    public void testsHeaderAvailable() throws Exception {
        MatcherAssert.assertThat(
            new RsWithHeader(
                new RsWithBody("<html>Hello</html>"),
                "content-encoding: gzip"
            ),
            new HmRsHeader(
                new EntryMatcher<String, String>(
                    "content-encoding", "gzip"
                )
            )
        );
    }

    /**
     * HmRsHeader can test header not available.
     * @throws Exception If some problem inside
     */
    @Test
    public void testsHeaderNotAvailable() throws Exception {
        MatcherAssert.assertThat(
            new RsWithBody("<html></html>"),
            new HmRsHeader(
                Matchers.not(
                    new EntryMatcher<String, String>(
                        "cache-control",
                        "no-cache, no-store"
                    )
                )
            )
        );
    }

    /**
     * HmRsHeader can test header name and value available.
     * @throws Exception If some problem inside
     */
    @Test
    public void testsHeaderNameAndValueAvailable() throws Exception {
        MatcherAssert.assertThat(
            new RsWithHeader("header1: value1"),
            new HmRsHeader("header1", "value1")
        );
    }

    /**
     * HmRsHeader can test header name and value not available.
     * @throws Exception If some problem inside
     */
    @Test
    public void testsHeaderNameAndValueNotAvailable() throws Exception {
        MatcherAssert.assertThat(
            new RsWithHeader("header2: value2"),
            Matchers.not(new HmRsHeader("header2", "value21"))
        );
    }

    /**
     * HmRsHeader can test headers available.
     * @throws Exception If some problem inside
     */
    @Test
    public void testsHeadersAvailable() throws Exception {
        MatcherAssert.assertThat(
            new RsWithHeaders(
                new RsEmpty(),
                "header3: value31", "header3: value32"
            ),
            new HmRsHeader("header3", Matchers.<String>iterableWithSize(2))
        );
    }

    /**
     * HmRsHeader can test headers not available.
     * @throws Exception If some problem inside
     */
    @Test
    public void testsHeadersNotAvailable() throws Exception {
        MatcherAssert.assertThat(
            new RsWithHeaders(new RsEmpty(), "header4: value4"),
            new HmRsHeader("header41", Matchers.<String>iterableWithSize(0))
        );
    }
}
