package org.sagebionetworks;

import java.io.BufferedReader;
import java.util.List;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.TeamMember;

public class AddToFavorites {

	
	/**
	 * Throw away program that will be used to clear a specific set of previews
	 * @param args
	 */
	public static void main(String[] args) {
		String teamId = "";
		
		SynapseClient synapseClient = LoginUtils.createLocalSynapseClient("JayAddToFavorites");		
		
		BufferedReader br = null;
		try {
			List<TeamMember> members = synapseClient.getTeamMembers(teamId, null, Long.MAX_VALUE, 0).getResults();
			
			
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
