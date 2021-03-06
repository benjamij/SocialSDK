/*
 * � Copyright IBM Corp. 2012
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package com.ibm.sbt.service.core.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.sbt.service.basic.ProxyEndpointService;

/**
 * Ping Proxy Handler.
 * @author priand
 */
public class ProxyHandler extends AbstractServiceHandler {

    public static final String URL_PATH = "proxy";
	
    @Override
	public void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ProxyEndpointService p = new ProxyEndpointService() {
            @Override
            protected String getProxyUrlPath() {
                return URL_PATH;
            }
            @Override
            protected boolean isMethodAllowed(String method) {
                if(method.equalsIgnoreCase("get")|| method.equalsIgnoreCase("put")||method.equalsIgnoreCase("post")||method.equalsIgnoreCase("delete") ||
                        method.equalsIgnoreCase("OPTIONS") || method.equalsIgnoreCase("HEAD")) {
                    return true;
                }
                return false;
            }
        };
        p.service(request, response);
    }    
}