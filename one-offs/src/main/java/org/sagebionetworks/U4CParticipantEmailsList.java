package org.sagebionetworks;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.FileEntity;
import org.sagebionetworks.repo.model.TeamMember;
import org.sagebionetworks.repo.model.UserProfile;
import org.sagebionetworks.repo.model.file.FileHandle;

import au.com.bytecode.opencsv.CSVWriter;

public class U4CParticipantEmailsList {
	
	/**
	 * Throw away program used in "Up For A Challenge" to communicate email addresses to dbGap to expedite data access.
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createSynapseClient("JayGetEmailForU4C");

		try {
			String teamId = "3325803";
			String targetEntityId = "syn4550116";
			org.sagebionetworks.reflection.model.PaginatedResults<TeamMember> members = synapseClient
					.getTeamMembers(teamId, "", 10000, 0);
			
			//gather user ids to ask for all user profiles in bulk
			List<Long> userIds = new ArrayList<Long>();
			for (TeamMember member : members.getResults()) {
				if (!member.getIsAdmin()) {
					userIds.add(Long.parseLong(member.getMember().getOwnerId()));
				}
			}
			List<UserProfile> profiles = synapseClient.listUserProfiles(userIds);
			
			//create the file
			File tempFile = File.createTempFile("NCI_U4C_Registered_List", ".csv");
			FileWriter fileWriter = new FileWriter(tempFile);
			CSVWriter writer = new CSVWriter(fileWriter, ',');
			String[] headers = new String[]{"id","userName","firstName","lastName","company","email"};
			writer.writeNext(headers);
			for (UserProfile userProfile : profiles) {
				String[] entries = new String[]{
					userProfile.getOwnerId(),
					userProfile.getUserName(),
					userProfile.getFirstName(),
					userProfile.getLastName(),
					userProfile.getCompany(),
					userProfile.getEmails().get(0)
				};
				writer.writeNext(entries);
			}
			writer.close();
			
			FileHandle handle = synapseClient.createFileHandle(tempFile, "text/csv", targetEntityId);
			//update the file entity
			FileEntity fileEntity = (FileEntity) synapseClient.getEntityById(targetEntityId);
			//update data file handle id
			fileEntity.setDataFileHandleId(handle.getId());
			fileEntity = (FileEntity)synapseClient.putEntity(fileEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
