package org.sagebionetworks;

import java.io.BufferedReader;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.UserGroupHeaderResponsePage;

public class ClearPreviews {

	
	/**
	 * Throw away program that will be used to clear a specific set of previews
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createSynapseClient("JayClearingPreviews");		
		
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
