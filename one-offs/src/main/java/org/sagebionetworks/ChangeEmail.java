package org.sagebionetworks;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.UserProfile;

public class ChangeEmail {
	
	/**
	 * Throw away program to attempt to change my email address (verified that it is currently not supported)
	 */
	public static void main(String[] args) {
		String newEmail = "blarg@jayhodgson.com";
		
		SynapseClient synapseClient = LoginUtils.createStagingSynapseClient("JayChangeEmail");

		try {
			UserProfile profile = synapseClient.getMyProfile();
			profile.getEmails().remove(0);
			profile.getEmails().add(newEmail);
			synapseClient.updateMyProfile(profile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
