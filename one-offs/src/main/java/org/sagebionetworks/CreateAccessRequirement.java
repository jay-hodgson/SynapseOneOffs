package org.sagebionetworks;

import java.io.BufferedReader;
import java.util.Arrays;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.client.exceptions.SynapseException;
import org.sagebionetworks.repo.model.ACCESS_TYPE;
import org.sagebionetworks.repo.model.ACTAccessRequirement;
import org.sagebionetworks.repo.model.PostMessageContentAccessRequirement;
import org.sagebionetworks.repo.model.RestrictableObjectDescriptor;
import org.sagebionetworks.repo.model.RestrictableObjectType;
import org.sagebionetworks.repo.model.TermsOfUseAccessRequirement;

public class CreateAccessRequirement {

	private static TermsOfUseAccessRequirement createNew(ACCESS_TYPE type, String text, SynapseClient synapseClient, String teamId) throws SynapseException {
		TermsOfUseAccessRequirement tou = new TermsOfUseAccessRequirement();
		tou.setAccessType(type);
		tou.setTermsOfUse(text);
		RestrictableObjectDescriptor rod = new RestrictableObjectDescriptor();
		rod.setId(teamId);
		rod.setType(RestrictableObjectType.TEAM);
		tou.setSubjectIds(Arrays.asList(new RestrictableObjectDescriptor[]{rod}));
		return synapseClient.createAccessRequirement(tou);
	}
	
	private static ACTAccessRequirement createNewACT(ACCESS_TYPE type, String text, SynapseClient synapseClient, String teamId) throws SynapseException {
		ACTAccessRequirement tou = new ACTAccessRequirement();
		tou.setAccessType(type);
		tou.setActContactInfo(text);
		RestrictableObjectDescriptor rod = new RestrictableObjectDescriptor();
		rod.setId(teamId);
		rod.setType(RestrictableObjectType.TEAM);
		tou.setSubjectIds(Arrays.asList(new RestrictableObjectDescriptor[]{rod}));
		return synapseClient.createAccessRequirement(tou);
	}

	
	private static PostMessageContentAccessRequirement createNewPostMessageAR(ACCESS_TYPE type, String url, SynapseClient synapseClient, String teamId) throws SynapseException {
		PostMessageContentAccessRequirement tou = new PostMessageContentAccessRequirement();
		tou.setAccessType(type);
		tou.setUrl(url);
		RestrictableObjectDescriptor rod = new RestrictableObjectDescriptor();
		rod.setId(teamId);
		rod.setType(RestrictableObjectType.TEAM);
		tou.setSubjectIds(Arrays.asList(new RestrictableObjectDescriptor[]{rod}));
		return synapseClient.createAccessRequirement(tou);
	}
	
	
	/**
	 * Throw away program that will be used to create access requirements
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient adminSynapseClient = LoginUtils.createStagingSynapseClient("Jay-create-ARs");
		BufferedReader br = null;
		
		try {
			String teamId = "3324043";
//			String[] removeUploadAR = {"syn1725886","syn2344890","syn2344108","syn2480680","syn2426653","syn2296810","syn2284679","syn2289118","syn1973664","syn2318326"};
//			HashSet<String> projectIds = new HashSet<String>();
//			for (String id : removeUploadAR) {
//				projectIds.add(id);
//			}
			
//			TermsOfUseAccessRequirement ar = (TermsOfUseAccessRequirement)adminSynapseClient.getAccessRequirement(3221179L);
//			ar.setTermsOfUse(tou);
//			adminSynapseClient.updateAccessRequirement(ar);
//			
//			RestrictableObjectDescriptor descriptor = new RestrictableObjectDescriptor();
//			descriptor.setType(RestrictableObjectType.TEAM);
//			descriptor.setId("3325264");
//			VariableContentPaginatedResults<AccessRequirement> t = adminSynapseClient.getAccessRequirements(descriptor);
//			for (AccessRequirement ar : t.getResults()) {
//				System.out.println("AR id: " + ar.getId());
//				List<RestrictableObjectDescriptor> subjects = ar.getSubjectIds();
//				//add new subject
//				RestrictableObjectDescriptor newDescriptor = new RestrictableObjectDescriptor();
//				newDescriptor.setType(RestrictableObjectType.TEAM);
//				newDescriptor.setId("3325264");
//				subjects.add(newDescriptor);
//				adminSynapseClient.updateAccessRequirement(ar);
//			}
			//delete 3221173, create a new one
			
			createNew(ACCESS_TYPE.PARTICIPATE, "<div class=\"markdown\">Access to the data requires agreement to the following terms. By clicking \"Accept\", you agree to\n" + 
					"these terms:<br><br><strong>Publication embargo</strong>: The use of Challenge data or results in a publication is under embargo and requires written consent from data producers and the Challenge organizers.<br><br></div>", adminSynapseClient, teamId);
			
//			createNew(ACCESS_TYPE.PARTICIPATE, "<div class=\"markdown\"> To speed up the dbGaP data access request process, please fill in your first name, last name, and current affiliation in <a target=\"_blank\" href=\"https://www.synapse.org/#!Profile:edit\" class=\"link\">your Synapse profile</a>."
//					+ "<br>If you registered your account with a different email address than the one you will use with your dbGaP registration, then you should also "
//					+ "<a target=\"_blank\" href=\"https://www.synapse.org/#!Profile:edit\" class=\"link\">update your Synapse email</a> to the same address."
//					+ "<br><br></div>", adminSynapseClient, teamId);


			//ACT access requirement id 3219058 is associated with entity syn3219045
			
//			ACTAccessApproval aa  =new ACTAccessApproval();
//			aa.setAccessorId("3325009");
//			aa.setApprovalStatus(ACTApprovalStatus.APPROVED);
//			aa.setRequirementId(3054486L);
//			adminSynapseClient.createAccessApproval(aa);
			
//			
//			createNew(ACCESS_TYPE.UPLOAD, "must abide by some rules before upload", adminSynapseClient, "syn2699915");
//			createNew(ACCESS_TYPE.DOWNLOAD, "And more rules!", adminSynapseClient, "syn3240921");
			//createNewACT(ACCESS_TYPE.DOWNLOAD, "And more rules!", adminSynapseClient, "syn3240921");
			
//			createNewPostMessageAR(ACCESS_TYPE.PARTICIPATE, "https://s3.amazonaws.com/static.synapse.org/jhodgson/Form.html", adminSynapseClient, "3325859");
//			createNew(ACCESS_TYPE.DOWNLOAD, "<h5>this is another AR</h5> with other text." 
//					, adminSynapseClient, "syn3063492");
			//PROD
//			createNewPostMessageAR(ACCESS_TYPE.PARTICIPATE, "https://www.projectdatasphere.org/projectdatasphere/html/registration/challenge", adminSynapseClient, "3324826");
			
//			createNewPostMessageAR(ACCESS_TYPE.PARTICIPATE, "https://mpmdev.ondemand.sas.com/projectdatasphere/html/registration/challenge", adminSynapseClient, "3325504");
//			createNewPostMessageAR(ACCESS_TYPE.PARTICIPATE, "http://l75068.na.sas.com:8080/projectdatasphere/html/registration/challenge", adminSynapseClient, "3325466");

			
//			createNew(ACCESS_TYPE.PARTICIPATE, "Different set of terms of use (AR)", adminSynapseClient, teamId);
//			createNewACT(ACCESS_TYPE.PARTICIPATE, "This contains ACT contact info.  No approval necessary", adminSynapseClient, teamId);
//			createNew(ACCESS_TYPE.UPLOAD, "Rules for upload.  Should always be shown on upload.", adminSynapseClient, uploadId);
//			createNew(ACCESS_TYPE.DOWNLOAD, "Rules for download.  Must agree once.  Should be marked as controlled.", adminSynapseClient, downloadId);
//			
//			createNew(ACCESS_TYPE.UPLOAD, "Rules for upload on entity that also has a download ar.  Always shown on upload.", adminSynapseClient, bothId);
//			createNew(ACCESS_TYPE.DOWNLOAD, "Rules for download on entity that also has an upload ar.  Must agree once.  Should be marked as controlled.", adminSynapseClient, bothId);
			
			
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
//			String rules = "<div class=\"markdown\"><h1>Challenge Rules</h1><p>Quisque ac mattis eros. <strong>Donec accumsan</strong>, augue et pulvinar auctor, sem massa euismod nunc, eget laoreet nunc lacus elementum nibh. Mauris ornare diam vestibulum sapien semper interdum. Cras enim magna, luctus ac tincidunt eget, ullamcorper eu tellus. In non commodo urna. Curabitur at lorem a metus varius tempor. Sed et leo leo.Integer nec odio eu nisl tincidunt lobortis. Morbi eu dignissim elit. Donec vitae pellentesque nisi, ac commodo dolor. Donec dictum in augue vitae mattis. Suspendisse ac massa pharetra, porttitor felis quis, vestibulum ipsum. Duis pretium a lacus at tempus. Ut luctus et nisi non pellentesque. Nam et purus id turpis adipiscing cursus non eget arcu.Quisque vulputate arcu massa, et mattis purus adipiscing id. Ut dictum arcu eget facilisis vestibulum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla sed facilisis diam. Suspendisse luctus leo at tortor luctus dapibus. Quisque aliquam tellus eros, sed suscipit ante eleifend vitae. Aliquam ultrices convallis magna vel convallis. Pellentesque venenatis orci mi, sed ullamcorper ligula aliquet in. Donec sollicitudin vestibulum massa, volutpat malesuada est placerat eu. Ut interdum dui eget leo posuere semper. Vivamus aliquet congue elit sit amet dignissim. Quisque venenatis consequat neque, sed feugiat est volutpat eget.Nulla fringilla, enim eu consequat lobortis, quam nisl pellentesque tortor, id pretium ligula ligula ac lorem. Morbi non feugiat velit. Nam id arcu in turpis mollis rutrum. Ut sem neque, egestas nec urna cursus, varius scelerisque massa. Aenean porta sagittis dolor, a placerat lorem dignissim a. Sed ac massa dui. Morbi orci erat, commodo sit amet tempor molestie, pretium eu massa. Fusce pharetra, erat vitae accumsan scelerisque, turpis quam aliquam turpis, at laoreet justo quam non nibh. Proin mollis, ligula ac egestas tincidunt, ligula ante vehicula sapien, et congue risus ligula eu tellus. Vivamus lectus ipsum, pharetra sed sagittis sed, viverra sed lacus. Ut nisi risus, lacinia vel rutrum eu, viverra imperdiet odio. Suspendisse convallis est vitae auctor posuere.</p><p>augue et pulvinar auctor, sem massa euismod nunc, eget laoreet nunc lacus elementum nibh. Mauris ornare diam vestibulum sapien semper interdum. Cras enim magna, luctus ac tincidunt eget, ullamcorper eu tellus. In non commodo urna. Curabitur at lorem a metus varius tempor. Sed et leo leo.Integer nec odio eu nisl tincidunt lobortis. Morbi eu dignissim elit. Donec vitae pellentesque nisi, ac commodo dolor. Donec dictum in augue vitae mattis. Suspendisse ac massa pharetra, porttitor felis quis, vestibulum ipsum. Duis pretium a lacus at tempus. Ut luctus et nisi non pellentesque. Nam et purus id turpis adipiscing cursus non eget arcu.Quisque vulputate arcu massa, et mattis purus adipiscing id. Ut dictum arcu eget facilisis vestibulum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla sed facilisis diam. Suspendisse luctus leo at tortor luctus dapibus. Quisque aliquam tellus eros, sed suscipit ante eleifend vitae.</p></div>";
//			tou.setTermsOfUse(rules);
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
