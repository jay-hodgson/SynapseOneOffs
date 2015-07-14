package org.sagebionetworks;

import java.io.File;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.UserProfile;
import org.sagebionetworks.repo.model.file.FileHandle;
import org.sagebionetworks.repo.model.file.S3FileHandle;

public class FileUploadTest {
	
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createStagingSynapseClient("storage location id test");

		try {
//			set in this case
//			FileHandle fileHandle = synapseClient.createFileHandle(new File("/Users/jayhodgson/SynapseWebClient/SynapseWebClient/pom.xml"), "text/xml", "syn3851543");

			FileHandle fileHandle = synapseClient.createFileHandle(new File("/Users/jayhodgson/SynapseWebClient/SynapseWebClient/pom.xml"), "text/xml", "syn3851543");
			
			System.out.println("stop.  filehandle storage location id=" + fileHandle.getStorageLocationId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
