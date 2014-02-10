package org.sagebionetworks;

import java.io.BufferedReader;

import org.sagebionetworks.client.SynapseClient;

public class CreateLockAccessRestriction {

	
	/**
	 * Throw away program that will be used to clear a specific set of previews
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createStagingSynapseClient("JayCreateLockAccessRequirement");		
		
		BufferedReader br = null;
		try {
			synapseClient.createLockAccessRequirement("syn2344944");
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
