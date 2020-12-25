/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.caelum.vraptor;

 import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WeldJunitRunner.class)
public class VRaptorTest {

	@Inject private VRaptor vRaptor;
	@Inject private MockStaticContentHandler handler;

	@Test(expected = ServletException.class)
	public void shoudlComplainIfNotInAServletEnviroment() throws Exception {
		ServletRequest request = mock(ServletRequest.class);
		ServletResponse response = mock(ServletResponse.class);
		vRaptor.doFilter(request, response, null);
	}

	@Test
	public void shouldDeferToContainerIfStaticFile() throws Exception{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		FilterChain chain = mock(FilterChain.class);
		vRaptor.doFilter(request, response, chain);
		assertThat(handler.isDeferProcessingToContainerCalled(), is(true));
	}
}