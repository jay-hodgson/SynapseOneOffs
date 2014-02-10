package org.sagebionetworks;

import java.io.File;
import java.util.List;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.ObjectType;
import org.sagebionetworks.repo.model.dao.WikiPageKey;
import org.sagebionetworks.repo.model.file.S3FileHandle;
import org.sagebionetworks.repo.model.v2.wiki.V2WikiPage;

public class CheckWikiEtag {

	
	/**
	 * Throw away program that will be used to clear a specific set of previews
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createLocalSynapseClient("JayClearingPreviews");
		String ownerId = "syn1681356";
		ObjectType ownerType = ObjectType.ENTITY;
		WikiPageKey key = new WikiPageKey(ownerId, ownerType, "1");
		try {
			V2WikiPage page = synapseClient.getV2WikiPage(key);
			System.out.println("ETAG before adding attachment: " + page.getEtag());
			
			File tempFile = new File("/Users/jayhodgson/Pictures/test.png");
			S3FileHandle fileHandle = synapseClient.createFileHandle(tempFile, "image/png");
			List<String> fileHandles = page.getAttachmentFileHandleIds();
			fileHandles.add(fileHandle.getId());
			synapseClient.updateV2WikiPage(ownerId, ownerType, page);
			
			page = synapseClient.getV2WikiPage(key);
			System.out.println("ETAG after adding attachment: " + page.getEtag());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
