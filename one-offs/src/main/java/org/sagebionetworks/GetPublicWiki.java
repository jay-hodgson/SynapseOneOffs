package org.sagebionetworks;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.ObjectType;
import org.sagebionetworks.repo.model.dao.WikiPageKey;
import org.sagebionetworks.repo.model.v2.wiki.V2WikiPage;
import org.sagebionetworks.repo.model.wiki.WikiPage;

public class GetPublicWiki {
	
	/**
	 * Throw away program to attempt to read a public wiki page
	 */
	public static void main(String[] args) {
		String entityId = "syn312572";

		SynapseClient synapseClient = LoginUtils.createAnonymousSynapseClient("JayPublicWikiTest");

		try {
			String rootId = null;
			// copy portal synapse client getV2RootWikiId
			V2WikiPage rootPage = synapseClient.getV2RootWikiPage(entityId, ObjectType.ENTITY);
			if (rootPage != null)
				rootId = rootPage.getId();

			WikiPageKey key = new WikiPageKey(entityId, ObjectType.ENTITY, rootId);
			WikiPage page = synapseClient.getV2WikiPageAsV1(key);
			System.out.println(page.getMarkdown());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
