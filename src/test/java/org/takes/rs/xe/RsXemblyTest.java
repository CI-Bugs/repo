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

import com.jcabi.matchers.XhtmlMatchers;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.xembly.Directive;
import org.xembly.Directives;

/**
 * Test case for {@link RsXembly}.
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id: 28dcb838e38e4dd3f6e776a8f0af3d3fa4378132 $
 * @since 0.1
 */
public final class RsXemblyTest {

    /**
     * RsXembly can build XML response.
     * @throws IOException If some problem inside
     */
    @Test
    public void buildsXmlResponse() throws IOException {
        MatcherAssert.assertThat(
            IOUtils.toString(
                new RsXembly(
                    new XeStylesheet("/a.xsl"),
                    new XeAppend(
                        "root",
                        new XeMillis(false),
                        new XeSource() {
                            @Override
                            public Iterable<Directive> toXembly() {
                                return new Directives().add("hey");
                            }
                        },
                        new XeMillis(true)
                    )
                ).body()
            ),
            XhtmlMatchers.hasXPaths(
                "/root/hey",
                "/root/millis",
                "/processing-instruction('xml-stylesheet')[contains(.,'/a')]"
            )
        );
    }

}
