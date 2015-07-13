package org.sagebionetworks;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.UserProfile;

public class GetEmails {
	
	/**
	 * Throw away program to attempt to change my email address (verified that it is currently not supported)
	 */
	public static void main(String[] args) {
		
		String[] principalIds = new String[]{"273959","273979","372127","1571073","1742861","1856606","1967913","1968150","1991111","2219083","2223263","2223334","2223415","2223484","2223826","2224141","3319344","3319715","3319761","3319804","3319877","3319912","3320545"};
		SynapseClient synapseClient = LoginUtils.createSynapseClient("GetEmails");

		try {
			for (int i = 0; i < principalIds.length; i++) {
				UserProfile up = synapseClient.getUserProfile(principalIds[i]);
				for (String email : up.getEmails()) {
					System.out.print(email + ",");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getDisplayName(UserProfile profile) {
		return getDisplayName(profile.getFirstName(), profile.getLastName(), profile.getUserName());
	}
	
	public static String getDisplayName(String firstName, String lastName, String userName) {
		StringBuilder sb = new StringBuilder();
		boolean hasDisplayName = false;
		if (firstName != null && firstName.length() > 0) {
			sb.append(firstName.trim());
			hasDisplayName = true;
		}
		if (lastName != null && lastName.length() > 0) {
			sb.append(" ");
			sb.append(lastName.trim());
			hasDisplayName = true;
		}
		
		sb.append(getUserName(userName, hasDisplayName));
		
		return sb.toString();
	}
	
	public static String getUserName(String userName, boolean inParens) {
		StringBuilder sb = new StringBuilder();
		
		if (userName != null && !isTemporaryUsername(userName)) {
			//if the name is filled in, then put the username in parens
			if (inParens)
				sb.append(" (");
			sb.append(userName);
			if (inParens)
				sb.append(")");
		}
		
		return sb.toString();
	}
	public static boolean isTemporaryUsername(String username){
		if(username == null) throw new IllegalArgumentException("UserName cannot be null");
		return username.startsWith("TEMPORARY-");
	}

}
