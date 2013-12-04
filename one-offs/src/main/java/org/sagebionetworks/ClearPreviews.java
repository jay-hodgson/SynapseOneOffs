package org.sagebionetworks;

import java.io.BufferedReader;
import java.io.FileReader;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.client.SynapseClientImpl;
import org.sagebionetworks.repo.model.UserGroupHeaderResponsePage;

public class ClearPreviews {

	
	/**
	 * Throw away program that will be used to clear a specific set of previews
	 * @param args
	 */
	public static void main(String[] args) {
		String sessionToken = "xxxxxxx";
		String repoUrl = "https://repo-prod.prod.sagebase.org/repo/v1";
		String authUrl = "https://repo-prod.prod.sagebase.org/auth/v1";
		String fileEndpointUrl = "https://repo-prod.prod.sagebase.org/file/v1";
		
		SynapseClient synapseClient = new SynapseClientImpl();		
		synapseClient.setSessionToken(sessionToken);
		synapseClient.setRepositoryEndpoint(repoUrl);
		synapseClient.setAuthEndpoint(authUrl);
		synapseClient.setFileEndpoint(fileEndpointUrl);
		synapseClient.appendUserAgent("JayClearingPreviews");

		
		BufferedReader br = null;
		try {
			UserGroupHeaderResponsePage page = synapseClient.getUserGroupHeadersByPrefix("");
			System.out.println("Total Results: " + page.getTotalNumberOfResults());
//			br = new BufferedReader(new FileReader("/Users/jayhodgson/Documents/file-handles.csv"));
//			String line;
//			while ((line = br.readLine()) != null) {
//				System.out.println("Clearing " + line);
//				synapseClient.clearPreview(line);
//				Thread.sleep(1300);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (Throwable e) {}
		}
	}

}
