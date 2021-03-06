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

dojo.provide('sbt.pathUtil');
/**
 * Social Business Toolkit SDK - URL utilities
 */
define('sbt/pathUtil',['sbt/stringUtil'],function(stringUtil) {
	return {
		concat: function(path1,path2) {
			if(!path1) {
				return path2;
			}
			if(!path2) {
				return path1;
			}
			if(stringUtil.endsWith(path1,"/")) {
				path1 = path1.substring(0,path1.length-1);
			}
			if(stringUtil.startsWith(path2,"/")) {
				path2 = path2.substring(1);
			}
			return path1 + "/" + path2;
		},
		isAbsolute: function(url) {
			return url.indexOf("://")>=0;
		}
	}
});
