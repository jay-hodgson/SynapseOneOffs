package org.sagebionetworks;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.client.exceptions.SynapseForbiddenException;
import org.sagebionetworks.repo.model.ACCESS_TYPE;
import org.sagebionetworks.repo.model.Folder;
import org.sagebionetworks.repo.model.Project;
import org.sagebionetworks.repo.model.RestrictableObjectDescriptor;
import org.sagebionetworks.repo.model.RestrictableObjectType;
import org.sagebionetworks.repo.model.TermsOfUseAccessRequirement;

public class CreateTCGAAccessRequirement {

	public static final String terms = "<div class=\"markdown\">\n" + 
			"<h6 class=\"text-danger\">The following TCGA data types should NOT be stored or shared on a platform other than the DCC and CGHub:</h6>\n" + 
			"<ul class=\"margin-10\">\n" + 
			"  <li>BAM files or any raw sequence data</li>\n" + 
			"  <li>VCFs</li>\n" + 
			"  <li>Certain information in MAFs, specifically\n" + 
			"    <ol><li>Germline calls</li>\n" + 
			"      <li>Unvalidated somatic calls from non-coding regions</li>\n" + 
			"      <li>All other calls can be included in the public MAF and shared</li>\n" + 
			"    </ol>\n" + 
			"  </li>\n" + 
			"  </ul>\n" + 
			"<span>\n" + 
			"Patient clinical data with absolute dates and other PHI information should not be shared or stored on ANY platform.\n" + 
			"The DCC has set up an internal workspace (Jamboree site) for the AWGs to share preliminary analyses that may contain the controlled-access data described above. If you are an active AWG member and would like access to this site, please send a request to the DCC team <br>(tcga-dcc-binf-l@list.nih.gov).\n" + 
			"  </span>\n" + 
			"</div>";
	
	static final String[] projects = new String[]{"syn1725886","syn2344890","syn2344108","syn2480680","syn2024473","syn1960986","syn2426653","syn1935546","syn2296810","syn2284679","syn2289118","syn1973664","syn2318326"};
	
	/**
	 * Throw away program that will be used to create access requirements
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createStagingSynapseClient("Jay-creating-upload-access-requirement");
//		String projectId = "syn2313744";
//		String teamId = "3318980";
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
			tou.setAccessType(ACCESS_TYPE.UPLOAD);
			tou.setTermsOfUse(terms);
			
			List<RestrictableObjectDescriptor> rods = new ArrayList<RestrictableObjectDescriptor>();
			for (int i = 0; i < projects.length; i++) {
				rod = new RestrictableObjectDescriptor();
				rod.setId(projects[i]);
				rod.setType(RestrictableObjectType.ENTITY);
				rods.add(rod);
			}
			tou.setSubjectIds(rods);
			tou = synapseClient.createAccessRequirement(tou);

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
