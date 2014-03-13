package org.sagebionetworks;

import java.io.BufferedReader;
import java.util.Arrays;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.ACCESS_TYPE;
import org.sagebionetworks.repo.model.Folder;
import org.sagebionetworks.repo.model.Project;
import org.sagebionetworks.repo.model.RestrictableObjectDescriptor;
import org.sagebionetworks.repo.model.RestrictableObjectType;
import org.sagebionetworks.repo.model.TermsOfUseAccessRequirement;

public class CreateDataUseTestDataset {

	
	/**
	 * Throw away program that will be used to clear a specific set of previews
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createAdminStagingSynapseClient("JayCreateDataUseTestDataset");		
		
		BufferedReader br = null;
		try {
			Project project = new Project();
			project.setName("Demo Data-Use Project");
			project = synapseClient.createEntity(project);
			
			Folder folder1 = new Folder();
			folder1.setParentId(project.getId());
			folder1.setName("No Restrictions");
			folder1 = synapseClient.createEntity(folder1);
			
			Folder folder2 = new Folder();
			folder2.setParentId(folder1.getId());
			folder2.setName("Locked Down");
			folder2 = synapseClient.createEntity(folder2);
			
			synapseClient.createLockAccessRequirement(folder2.getId());
			
			Folder folder3 = new Folder();
			folder3.setParentId(project.getId());
			folder3.setName("Custom Restrictions");
			folder3 = synapseClient.createEntity(folder3);
			
			Folder folder4 = new Folder();
			folder4.setParentId(folder3.getId());
			folder4.setName("Additional Custom Restrictions");
			folder4 = synapseClient.createEntity(folder4);
			
			TermsOfUseAccessRequirement tou = new TermsOfUseAccessRequirement();
			tou.setAccessType(ACCESS_TYPE.DOWNLOAD);
			tou.setTermsOfUse("(this text is customizable)\nCustom Restriction\nThis folder has a set of conditions that you must agree to before download.");
			RestrictableObjectDescriptor rod = new RestrictableObjectDescriptor();
			rod.setId(folder3.getId());
			rod.setType(RestrictableObjectType.ENTITY);
			tou.setSubjectIds(Arrays.asList(new RestrictableObjectDescriptor[]{rod}));
			synapseClient.createAccessRequirement(tou);
			
			tou.setTermsOfUse("(this text is customizable)\nAdditional Custom Restriction\nThis folder has an additional set of conditions that you must agree to before download.");
			rod.setId(folder4.getId());
			tou.setSubjectIds(Arrays.asList(new RestrictableObjectDescriptor[]{rod}));
			synapseClient.createAccessRequirement(tou);
			
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
