package org.sagebionetworks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.ObjectType;
import org.sagebionetworks.repo.model.PaginatedResults;
import org.sagebionetworks.repo.model.dao.WikiPageKey;
import org.sagebionetworks.repo.model.file.S3FileHandle;
import org.sagebionetworks.repo.model.v2.wiki.V2WikiHeader;
import org.sagebionetworks.repo.model.v2.wiki.V2WikiPage;

public class UpdateParentWiki {

	
	/**
	 * Throw away program that will be used to clear a specific set of previews
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createSynapseClient("UpdateParentWiki");
		String ownerId = "syn2455683";
		ObjectType ownerType = ObjectType.ENTITY;
		
		try {
			PaginatedResults<V2WikiHeader> tree = synapseClient.getV2WikiHeaderTree(ownerId, ownerType);
			List<V2WikiHeader> pagelist = tree.getResults();
			//find all children of wiki id 64614
			List<V2WikiHeader> childList = new ArrayList<V2WikiHeader>();
			V2WikiHeader rootPage = null;
			for (V2WikiHeader v2WikiHeader : pagelist) {
				if (v2WikiHeader.getParentId() == null) {
					rootPage = v2WikiHeader;
				}
				if ("64614".equals(v2WikiHeader.getParentId())) {
					childList.add(v2WikiHeader);
				}
			}
			String rootWikiId = rootPage.getId();
			
			//now make all children of one page the children of another instead
			for (V2WikiHeader v2WikiHeader : childList) {
				V2WikiPage childPage = synapseClient.getV2WikiPage(new WikiPageKey(ownerId, ownerType, v2WikiHeader.getId()));
				childPage.setParentWikiId(rootWikiId);
				synapseClient.updateV2WikiPage(ownerId, ownerType, childPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
