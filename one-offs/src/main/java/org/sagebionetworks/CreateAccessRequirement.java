package org.sagebionetworks;

import java.io.BufferedReader;
import java.util.Arrays;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.client.exceptions.SynapseForbiddenException;
import org.sagebionetworks.repo.model.ACCESS_TYPE;
import org.sagebionetworks.repo.model.Folder;
import org.sagebionetworks.repo.model.Project;
import org.sagebionetworks.repo.model.RestrictableObjectDescriptor;
import org.sagebionetworks.repo.model.RestrictableObjectType;
import org.sagebionetworks.repo.model.TermsOfUseAccessRequirement;

public class CreateAccessRequirement {

	
	/**
	 * Throw away program that will be used to create access requirements
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient adminSynapseClient = LoginUtils.createAdminStagingSynapseClient("Jay-PLFM-2538-validation");
		SynapseClient normalSynapseClient = LoginUtils.createStagingSynapseClient("Jay-PLFM-2538-validation");
		String evalId = "2364167";
		String projectId = "syn2313744";
		String teamId = "3319267";
		BufferedReader br = null;
		try {
			////////////////evaluation
			TermsOfUseAccessRequirement tou = new TermsOfUseAccessRequirement();
			tou.setAccessType(ACCESS_TYPE.SUBMIT);
			tou.setTermsOfUse("Must abide by some rules to submit to evaluation");
			RestrictableObjectDescriptor rod = new RestrictableObjectDescriptor();
			rod.setId(evalId);
			rod.setType(RestrictableObjectType.EVALUATION);
			tou.setSubjectIds(Arrays.asList(new RestrictableObjectDescriptor[]{rod}));
			tou = adminSynapseClient.createAccessRequirement(tou);
//
//			//try to update and delete as normal
//			try {
//				tou.setTermsOfUse("new terms");
//				normalSynapseClient.updateAccessRequirement(tou);
//				throw new Exception("Failed eval test!");
//			} catch (SynapseForbiddenException e) {
//				//expect failure
//				System.out.println("verified evaluation update");
//			}
//			
//			try {
//				normalSynapseClient.deleteAccessRequirement(tou.getId());
//				throw new Exception("Failed eval test!");
//			} catch (SynapseForbiddenException e) {
//				//expect failure
//				System.out.println("verified evaluation delete");
//			}
//			
//			//admin can update and delete
//			adminSynapseClient.updateAccessRequirement(tou);
//			adminSynapseClient.deleteAccessRequirement(tou.getId());
			
			
			//////////////team
//			tou = new TermsOfUseAccessRequirement();
//			tou.setAccessType(ACCESS_TYPE.PARTICIPATE);
//			tou.setTermsOfUse("Must abide by some rules to participate");
//			rod = new RestrictableObjectDescriptor();
//			rod.setId(teamId);
//			rod.setType(RestrictableObjectType.TEAM);
//			tou.setSubjectIds(Arrays.asList(new RestrictableObjectDescriptor[]{rod}));
//			tou = adminSynapseClient.createAccessRequirement(tou);

//			//try to update and delete as normal
//			try {
//				tou.setTermsOfUse("new terms");
//				normalSynapseClient.updateAccessRequirement(tou);
//				throw new Exception("Failed team test!");
//			} catch (SynapseForbiddenException e) {
//				//expect failure
//				System.out.println("verified team update");
//			}
//			
//			try {
//				normalSynapseClient.deleteAccessRequirement(tou.getId());
//				throw new Exception("Failed team test!");
//			} catch (SynapseForbiddenException e) {
//				//expect failure
//				System.out.println("verified team delete");
//			}
//			
//			//admin can update and delete
//			adminSynapseClient.updateAccessRequirement(tou);
//			adminSynapseClient.deleteAccessRequirement(tou.getId());
			
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
