package org.sagebionetworks;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.ACCESS_TYPE;
import org.sagebionetworks.repo.model.RestrictableObjectDescriptor;
import org.sagebionetworks.repo.model.RestrictableObjectType;
import org.sagebionetworks.repo.model.TermsOfUseAccessRequirement;

public class CreateTCGAAccessRequirement {

	public static final String terms = "<div class=\"markdown\">\n" + 
			"<h6 class=\"text-danger\">This is for testing purposes only</h6>\n" + 
			"<ul class=\"margin-10\">\n" + 
			"  <li>This is an Upload access restriction</li>\n" + 
			" </ul>\n" + 
			"</div>";
	
	static final String[] projects = new String[]{"syn2245571"};
	
	/**
	 * Throw away program that will be used to create access requirements
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createSynapseClient("Jay-creating-upload-access-requirement");
		BufferedReader br = null;
		try {
			TermsOfUseAccessRequirement tou;
			RestrictableObjectDescriptor rod;

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
