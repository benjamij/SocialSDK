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

package com.ibm.commons.runtime.properties;

import java.io.InputStream;
import java.util.Properties;
import com.ibm.commons.runtime.Application;
import com.ibm.commons.util.StringUtil;
import com.ibm.commons.util.io.StreamUtil;



/**
 * SBT Properties factory.
 *
 * @author Philippe Riand
 */
public class WebResourcePropertiesFactory extends AbstractPropertiesFactory {

	public static final String DEFAULT_PROPERTIES = "/WEB-INF/sbt.properties";

	private final Properties properties;

	public WebResourcePropertiesFactory() {
		this(DEFAULT_PROPERTIES);
	}

	public WebResourcePropertiesFactory(String fileName) {
		this.properties = readProperiesFromWebResource(fileName);
	}

	@Override
	public String getProperty(String name) {
		return properties!=null ? properties.getProperty(name) : null;
	}

	public Properties readProperiesFromWebResource(String fileName) {
		if(StringUtil.isEmpty(fileName)) {
			return null;
		}
		// We read the resource
		Application app = Application.getUnchecked();
		if(app==null) {
			return null;
		}
		try {
			Properties properties = new Properties();
			InputStream is = app.getResourceAsStream(fileName);
			if(is!=null) {
				try {
					properties.load(is);
					return properties;
				} finally {
					StreamUtil.close(is);
				}
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}
		return null;
	}
}
