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
		SynapseClient adminSynapseClient = LoginUtils.createLocalSynapseClient("Jay-creating-access-requirements");
//		SynapseClient normalSynapseClient = LoginUtils.createStagingSynapseClient("Jay-PLFM-2538-validation");
//		String evalId = "2364167";
//		String projectId = "syn2313744";
		String teamId = "3318980";
		BufferedReader br = null;
		try {
			TermsOfUseAccessRequirement tou;
			RestrictableObjectDescriptor rod;
			////////////////evaluation
//			tou = new TermsOfUseAccessRequirement();
//			tou.setAccessType(ACCESS_TYPE.SUBMIT);
//			tou.setTermsOfUse("Must abide by some rules to submit to the challenge");
//			rod = new RestrictableObjectDescriptor();
//			rod.setId(evalId);
//			rod.setType(RestrictableObjectType.EVALUATION);
//			tou.setSubjectIds(Arrays.asList(new RestrictableObjectDescriptor[]{rod}));
//			tou = adminSynapseClient.createAccessRequirement(tou);
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
			tou = new TermsOfUseAccessRequirement();
			tou.setAccessType(ACCESS_TYPE.PARTICIPATE);
			String rules = "<div class=\"markdown\"><h1>Challenge Rules</h1><p>Quisque ac mattis eros. <strong>Donec accumsan</strong>, augue et pulvinar auctor, sem massa euismod nunc, eget laoreet nunc lacus elementum nibh. Mauris ornare diam vestibulum sapien semper interdum. Cras enim magna, luctus ac tincidunt eget, ullamcorper eu tellus. In non commodo urna. Curabitur at lorem a metus varius tempor. Sed et leo leo.Integer nec odio eu nisl tincidunt lobortis. Morbi eu dignissim elit. Donec vitae pellentesque nisi, ac commodo dolor. Donec dictum in augue vitae mattis. Suspendisse ac massa pharetra, porttitor felis quis, vestibulum ipsum. Duis pretium a lacus at tempus. Ut luctus et nisi non pellentesque. Nam et purus id turpis adipiscing cursus non eget arcu.Quisque vulputate arcu massa, et mattis purus adipiscing id. Ut dictum arcu eget facilisis vestibulum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla sed facilisis diam. Suspendisse luctus leo at tortor luctus dapibus. Quisque aliquam tellus eros, sed suscipit ante eleifend vitae. Aliquam ultrices convallis magna vel convallis. Pellentesque venenatis orci mi, sed ullamcorper ligula aliquet in. Donec sollicitudin vestibulum massa, volutpat malesuada est placerat eu. Ut interdum dui eget leo posuere semper. Vivamus aliquet congue elit sit amet dignissim. Quisque venenatis consequat neque, sed feugiat est volutpat eget.Nulla fringilla, enim eu consequat lobortis, quam nisl pellentesque tortor, id pretium ligula ligula ac lorem. Morbi non feugiat velit. Nam id arcu in turpis mollis rutrum. Ut sem neque, egestas nec urna cursus, varius scelerisque massa. Aenean porta sagittis dolor, a placerat lorem dignissim a. Sed ac massa dui. Morbi orci erat, commodo sit amet tempor molestie, pretium eu massa. Fusce pharetra, erat vitae accumsan scelerisque, turpis quam aliquam turpis, at laoreet justo quam non nibh. Proin mollis, ligula ac egestas tincidunt, ligula ante vehicula sapien, et congue risus ligula eu tellus. Vivamus lectus ipsum, pharetra sed sagittis sed, viverra sed lacus. Ut nisi risus, lacinia vel rutrum eu, viverra imperdiet odio. Suspendisse convallis est vitae auctor posuere.</p><p>augue et pulvinar auctor, sem massa euismod nunc, eget laoreet nunc lacus elementum nibh. Mauris ornare diam vestibulum sapien semper interdum. Cras enim magna, luctus ac tincidunt eget, ullamcorper eu tellus. In non commodo urna. Curabitur at lorem a metus varius tempor. Sed et leo leo.Integer nec odio eu nisl tincidunt lobortis. Morbi eu dignissim elit. Donec vitae pellentesque nisi, ac commodo dolor. Donec dictum in augue vitae mattis. Suspendisse ac massa pharetra, porttitor felis quis, vestibulum ipsum. Duis pretium a lacus at tempus. Ut luctus et nisi non pellentesque. Nam et purus id turpis adipiscing cursus non eget arcu.Quisque vulputate arcu massa, et mattis purus adipiscing id. Ut dictum arcu eget facilisis vestibulum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla sed facilisis diam. Suspendisse luctus leo at tortor luctus dapibus. Quisque aliquam tellus eros, sed suscipit ante eleifend vitae.</p></div>";
			tou.setTermsOfUse(rules);
			rod = new RestrictableObjectDescriptor();
			rod.setId(teamId);
			rod.setType(RestrictableObjectType.TEAM);
			tou.setSubjectIds(Arrays.asList(new RestrictableObjectDescriptor[]{rod}));
			tou = adminSynapseClient.createAccessRequirement(tou);

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
