package org.sagebionetworks;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.client.exceptions.SynapseException;
import org.sagebionetworks.repo.model.ACCESS_TYPE;
import org.sagebionetworks.repo.model.ACTAccessRequirement;
import org.sagebionetworks.repo.model.PostMessageContentAccessRequirement;
import org.sagebionetworks.repo.model.RestrictableObjectDescriptor;
import org.sagebionetworks.repo.model.RestrictableObjectType;
import org.sagebionetworks.repo.model.TermsOfUseAccessRequirement;
import org.sagebionetworks.repo.model.file.ExternalUploadDestination;
import org.sagebionetworks.repo.model.file.UploadDestination;
import org.sagebionetworks.repo.model.file.UploadType;
import org.sagebionetworks.repo.model.project.ExternalUploadDestinationSetting;
import org.sagebionetworks.repo.model.project.ProjectSetting;
import org.sagebionetworks.repo.model.project.UploadDestinationListSetting;
import org.sagebionetworks.repo.model.project.UploadDestinationSetting;

public class CreateProjectSettings {
	
	
	/**
	 * Throw away program that will be used to create project settings
	 * @param args
	 */
	public static void main(String[] args) {
		
		String projectId = "syn2758121";
		SynapseClient adminSynapseClient = LoginUtils.createAdminStagingSynapseClient("Jay-create-project-settings");
		try {
			UploadDestinationListSetting projectSetting = new UploadDestinationListSetting();
			List<UploadDestinationSetting> destinations = new ArrayList<UploadDestinationSetting>();
			ExternalUploadDestinationSetting setting = new ExternalUploadDestinationSetting();
			setting.setBanner("This is required!");
			setting.setUploadType(UploadType.SFTP);
			setting.setUrl("sftp://localhost/project-config-test/");
			destinations.add(setting);
			projectSetting.setDestinations(destinations);
			projectSetting.setSettingsType("upload");
			projectSetting.setProjectId(projectId);
			adminSynapseClient.createProjectSetting(projectSetting);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
