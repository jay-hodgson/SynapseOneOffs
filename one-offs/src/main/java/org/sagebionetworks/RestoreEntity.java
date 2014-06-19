package org.sagebionetworks;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.UserProfile;

public class RestoreEntity {
	
	/**
	 * Throw away program to attempt to change my email address (verified that it is currently not supported)
	 */
	public static void main(String[] args) {
		
		
		SynapseClient synapseClient = LoginUtils.createSynapseClient("JayRestoreProject");

		try {
			synapseClient.restoreFromTrash("syn2455683", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
