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
package com.ibm.sbt.services.client.connections.files.utils;


/**
 * @author Vimal Dhupar
 * Class used to retrieve translatable message values from the associated properties file. 
 */
public class Messages {
	
	private static final String BUNDLE_NAME = "com.ibm.sbt.services.client.connections.files.messages"; //$NON-NLS-1$
	
	static {
		// initialize resource bundle
		//NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static String InvalidArgument_1 = "Invalid Input parameter : FileEntry";
	public static String InvalidArgument_2 = "Invalid Input parameter : FileId";
	public static String InvalidArgument_3 = "Invalid Input parameter : UserId";
	public static String InvalidArgument_4 = "Invalid Input parameter : CollectionId";
	public static String InvalidArgument_5 = "Invalid Argument : File to be uploaded";
	public static String InvalidArgument_6 = "Invalid Argument : UserId";
	public static String UploadINFO_1 = "No File name specified. Checking for request Body.";
	public static String UploadINFO_2 = "No Request Body. Invalid call to upload.";
	public static String FileServiceException_1 = "Exception occurred in method";
	public static String NonceInfo_1 = "Nonce returned from Server : ";
	public static String PayloadInfo_1 = "Empty Payload Map provided.";
	public static String InvalidValue_1 = "Invalid Value : SubscriberId";
	public static String ProfilesException_1 = "Exception occurred in method while fetching User's Profile";
	public static String ProfileError_1 = "Profile Object : Data is null";
	public static String FileError_1 = "Error in method";
	
	private Messages() {
	}
}
