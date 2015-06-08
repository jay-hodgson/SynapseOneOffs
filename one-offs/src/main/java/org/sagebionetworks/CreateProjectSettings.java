package org.sagebionetworks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.mockito.cglib.core.CollectionUtils;
import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.file.UploadType;
import org.sagebionetworks.repo.model.project.ExternalS3StorageLocationSetting;
import org.sagebionetworks.repo.model.project.ExternalUploadDestinationSetting;
import org.sagebionetworks.repo.model.project.ProjectSetting;
import org.sagebionetworks.repo.model.project.ProjectSettingsType;
import org.sagebionetworks.repo.model.project.S3UploadDestinationSetting;
import org.sagebionetworks.repo.model.project.StorageLocationSetting;
import org.sagebionetworks.repo.model.project.UploadDestinationListSetting;
import org.sagebionetworks.repo.model.project.UploadDestinationSetting;
import org.sagebionetworks.schema.adapter.org.json.EntityFactory;

public class CreateProjectSettings {
	
	
	/**
	 * Throw away program that will be used to create project settings
	 * @param args
	 */
	public static void main(String[] args) {
		
		String projectId = "syn4230346";
		SynapseClient adminSynapseClient = LoginUtils.createStagingSynapseClient("Jay-create-project-settings");
		try {
//			//validate the ability to update the project setting
//			UploadDestinationListSetting currentSettings = (UploadDestinationListSetting)adminSynapseClient.getProjectSetting(projectId, ProjectSettingsType.upload);
//			List<UploadDestinationSetting> currentDestinations = currentSettings.getDestinations();
//			ExternalUploadDestinationSetting currentDestination = (ExternalUploadDestinationSetting)currentDestinations.get(0);
//			currentDestination.setBanner("This banner text is customizable (in the upload destination settings).");
//			currentDestination.setUrl("sftp://tcgaftps.nci.nih.gov/tcgapancan/tcgapancantestdir/synapse/jay");
//			adminSynapseClient.updateProjectSetting(currentSettings);
//			ProjectSetting settings = adminSynapseClient.getProjectSetting(projectId, ProjectSettingsType.upload);
			
//			List<StorageLocationSetting> mySettings = adminSynapseClient.getMyStorageLocationSettings();
//			for (StorageLocationSetting storageLocationSetting : mySettings) {
//				System.out.println("class = " + storageLocationSetting.getClass().getName());
//				System.out.println("storage location id = " + storageLocationSetting.getStorageLocationId());
//			}
			
			ExternalS3StorageLocationSetting externalSetting = new ExternalS3StorageLocationSetting();
			externalSetting.setUploadType(UploadType.S3);
			externalSetting.setBaseKey("jhodgson");
			externalSetting.setBucket("external.bucket.test");
			externalSetting = adminSynapseClient.createStorageLocationSetting(externalSetting);

			UploadDestinationListSetting projectSetting = new UploadDestinationListSetting();
			projectSetting.setLocations(Collections.singletonList(externalSetting.getStorageLocationId()));
			
			
////			setting.setUrl("sftp://fakeendpoint/test");
////			setting.setUrl("sftp://tcgaftps.nci.nih.gov/tcgapancantestdir/synapse");
//			setting.setUrl("sftp://ec2-54-245-147-42.us-west-2.compute.amazonaws.com/public/jay");
////			setting.setUrl("sftp://localhost/synapse/jays-project-settings-test");
			
			projectSetting.setSettingsType(ProjectSettingsType.upload);
			projectSetting.setProjectId(projectId);
			JSONObject object = EntityFactory.createJSONObjectForEntity(projectSetting);
			System.out.print(object.toString());
			adminSynapseClient.createProjectSetting(projectSetting);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
