/*
 * � Copyright IBM Corp. 2013
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at,
 * 
 * http,//www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package com.ibm.sbt.test.js.base;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.sbt.automation.core.test.BaseApiTest;
import com.ibm.sbt.automation.core.test.FlexibleTest;
import com.ibm.sbt.automation.core.test.pageobjects.JavaScriptPreviewPage;

/**
 * @author mwallace
 *  
 * @date 25 Mar 2013
 */
public class BaseServiceGetEntities extends FlexibleTest {
    
    static final String SNIPPET_ID = "Toolkit_Base_BaseServiceGetEntities";
    
    public BaseServiceGetEntities() {
        setAuthType(BaseApiTest.AuthType.NONE);
    }
    
    @Test
    public void testGetEntities() {
        JavaScriptPreviewPage previewPage = executeSnippet(SNIPPET_ID);
        List jsonList = previewPage.getJsonList();
        Assert.assertTrue(jsonList.size() > 0);
        Assert.assertEquals("createEntities", ((JsonJavaObject)jsonList.get(0)).getString("callback"));
        Assert.assertEquals("createEntity", ((JsonJavaObject)jsonList.get(1)).getString("callback"));
        Assert.assertEquals("response", ((JsonJavaObject)jsonList.get(jsonList.size() - 1)).getString("callback"));
    }

}
