package com.ibm.sbt.services.client.connections.communities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import com.ibm.commons.util.StringUtil;
import com.ibm.commons.xml.DOMUtil;
import com.ibm.commons.xml.NamespaceContext;
import com.ibm.commons.xml.XMLException;

public class Community {

	static final String					sourceClass		= Community.class.getName();
	static final Logger					logger			= Logger.getLogger(sourceClass);
	static final Map<String, String>	xpathMap;
	private final Map<String, String>	fieldsMap		= new HashMap<String, String>();
	// this holds the values which api consumer sets while creating updating profile Servicename reference
	private CommunityService			communityService;
	private Document					data;
	private String						communityUuid;

	static NamespaceContext	nameSpaceCtx = new NamespaceContext() {

		@Override
		public String getNamespaceURI(String prefix) {
			String uri;
			if (prefix.equals("h")) {
				uri = "http://www.w3.org/1999/xhtml";
			} else if (prefix.equals("a")) {
				uri = "http://www.w3.org/2005/Atom";
			} else if (prefix.equals("snx")) {
				uri = "http://www.ibm.com/xmlns/prod/sn";
			} else {
				uri = null;
			}
			return uri;
		}

		// Dummy implementation - not used!
		@Override
		public Iterator<String> getPrefixes(String val) {
			return null;
		}

		// Dummy implemenation - not used!
		@Override
		public String getPrefix(String uri) {
			return null;
		}

		@Override
		public Iterator<String> getPrefixes() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	static {
		xpathMap = new HashMap<String, String>();
		String[][] pairs = { { "communityUuid", "/a:entry/snx:communityUuid" },
			{ "title", "/a:entry/a:title" }, { "summary", "/a:entry/a:summary[@type='text']" },
			{ "logoUrl", "/a:entry/a:link[@rel='http://www.ibm.com/xmlns/prod/sn/logo']/@href" },
			{ "membersUrl", "/a:entry/a:link[@rel='http://www.ibm.com/xmlns/prod/sn/member-list']/@href" },
			{ "communityUrl", "/a:entry/a:link[@rel='self']/@href" },
			{ "tags", "/a:entry/a:category/@term" }, { "content", "/a:entry/a:content[@type='html']" } };
		for (String[] pair : pairs) {
			xpathMap.put(pair[0], pair[1]);
		}
	}

	// check
	public Community() {

	}

	public Community(CommunityService communityService, String communityUuid) {
		this.communityService = communityService;
		this.communityUuid = communityUuid;
	}

	public Community(String communityUuid) {
		this.communityUuid = communityUuid;
	}

	public Object getData() {
		return data;
	}

	public void setData(Document data) {
		this.data = data;
	}

	public static com.ibm.commons.xml.NamespaceContext getNameSpaceCtx() {
		return nameSpaceCtx;
	}

	public void setNameSpaceCtx(com.ibm.commons.xml.NamespaceContext nameSpaceCtx) {
		this.nameSpaceCtx = nameSpaceCtx;
	}

	public Map<String, String> getFieldsMap() {
		return fieldsMap;
	}

	public String getCommunityUuid() {
		return communityUuid;
	}

	public void setCommunityUuid(String communityUuid) {
		this.communityUuid = communityUuid;
	}

	public String getTitle() {

		if (StringUtil.isEmpty(fieldsMap.get("title"))) {
			return get("title");
		} else {
			return fieldsMap.get("title");
		}

	}

	public void setTitle(String title) {
		fieldsMap.put("title", title);
	}

	/**
	 * @return the content
	 */
	public String getContent() {

		if (StringUtil.isEmpty(fieldsMap.get("content"))) {
			return get("content");
		} else {
			return fieldsMap.get("content");
		}

	}

	/**
	 * @return the communityUrl
	 */
	public String getCommunityUrl() {
		return get("communityUrl");
	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return get("logoUrl");
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return get("summary");
	}

	/**
	 * @sets the content
	 */
	public void setContent(String content) {
		fieldsMap.put("content", content);
	}

	/**
	 * @sets the tag
	 */
	public void setAddedTags(String addedTags) {
		fieldsMap.put("addedTags", addedTags);
	}

	/**
	 * @return the array of Tags
	 * @throws XMLException
	 */
	public String[] getTags() throws XMLException {
		String xpQuery = getXPathQuery("tags");
		String[] categoryFields = DOMUtil.value(this.data, xpQuery, nameSpaceCtx).split(" ");
		String[] tags = new String[categoryFields.length - 1];
		for (int i = 1; i < categoryFields.length; i++) {// remove occurence of category term which has scheme
															// of "http://www.ibm.com/xmlns/prod/sn/type"
			tags[i - 1] = categoryFields[i];
		}
		return tags;
	}

	/**
	 * @sets the tag
	 */
	public void setDeletedTags(String deletedTags) {
		fieldsMap.put("deletedTags", deletedTags);
	}

	/**
	 * @return the membersUrl
	 */
	public String getMembersUrl() {
		return get("membersUrl");
	}

	/**
	 * @return value for specified field. Field names follow IBM Connections naming convention
	 */
	public String get(String fieldName) {
		String xpQuery = getXPathQuery(fieldName);
		return getFieldUsingXPath(xpQuery);
	}

	/**
	 * @return xpath query for specified field. Field names follow IBM Connections naming convention
	 */
	public String getXPathQuery(String fieldName) {
		return xpathMap.get(fieldName);
	}

	/**
	 * @return Execute xpath query on Community XML
	 */
	public String getFieldUsingXPath(String xpathQuery) {
		try {
			return DOMUtil.value(this.data, xpathQuery, nameSpaceCtx);
		} catch (XMLException e) {
			logger.log(Level.SEVERE, "Error executing xpath query on Community XML", e);
			return null;
		}
	}

	public void clearFieldsMap() {
		fieldsMap.clear();
	}

	public Object constructCreateRequestBody() {

		String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entry xmlns=\"http://www.w3.org/2005/Atom\" xmlns:app=\"http://www.w3.org/2007/app\" xmlns:snx=\"http://www.ibm.com/xmlns/prod/sn\">";

		Iterator<Map.Entry<String, String>> entries = fieldsMap.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> fieldMapPairs = entries.next();
			// title and content are mandatory fields , if not provided in the update request then retrieved
			// values will be used

			if (fieldMapPairs.getKey().equalsIgnoreCase("title")) {
				body += "<title type=\"text\">" + getTitle() + "</title>";
			}

			if (fieldMapPairs.getKey().equalsIgnoreCase("content")) {
				body += "<content type=\"html\">" + getContent() + "</content>";
			}

			if (fieldMapPairs.getKey().equalsIgnoreCase("addedTags")) {

				String[] originalTags;
				try {
					originalTags = getTags();
					for (String originalTag : originalTags) {// add original tags in the request
						body += "<category term=\"" + originalTag + "\"/>";
					}
					body += "<category term=\"" + fieldMapPairs.getValue() + "\"/>";

				} catch (XMLException e) {
					logger.log(Level.SEVERE, "Error creating request body", e);
				}
			}
			if (fieldMapPairs.getKey().equalsIgnoreCase("deletedTags")) {
				String[] originalTags;
				try {
					originalTags = getTags();
					for (int i = 0; i < originalTags.length; i++) {
						if (!fieldMapPairs.getValue().equalsIgnoreCase(originalTags[i])) {
							body += "<category term=\"" + originalTags[i] + "\"/>";
						}
					}
				} catch (XMLException e) {
					logger.log(Level.SEVERE, "Error creating request body", e);
				}
			}
		}// end while

		body += "<category term=\"community\" scheme=\"http://www.ibm.com/xmlns/prod/sn/type\"></category><snx:communityType xmlns:snx=\"http://www.ibm.com/xmlns/prod/sn\">public</snx:communityType></entry>";

		return body;

	}

	@Override
	public String toString() {
		try {
			return DOMUtil.getXMLString(data);
		} catch (XMLException e) {
			logger.log(Level.SEVERE, "Error converting xml to String", e);
			return "";
		}
	}

}
