package org.sagebionetworks;

import java.io.BufferedReader;
import java.util.ArrayList;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.AccessRequirement;
import org.sagebionetworks.repo.model.Entity;
import org.sagebionetworks.repo.model.RestrictableObjectDescriptor;
import org.sagebionetworks.repo.model.RestrictableObjectType;
import org.sagebionetworks.repo.model.TermsOfUseAccessRequirement;
import org.sagebionetworks.repo.model.VariableContentPaginatedResults;
import org.sagebionetworks.repo.model.attachment.AttachmentData;

public class UpdateAccessRequirement {

	static String newTermsOfUse = "<div class=\"markdown\" style=\"margin-left:20px; margin-right:20px;\">\n" + 
			"		<h3>Please read the full <a href=\"https://s3.amazonaws.com/static.synapse.org/DREAM9+Challenge+Rules.pdf\" class=\"link\" target=\"_blank\">DREAM9 Challenge Rules</a>.</h3>\n" + 
			"		<h3 style=\"margin-bottom:10px\">These rules cover:</h3>\n" + 
			"		<div class=\"row\" style=\"height: 90px;\">\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/person.png\" /><h5 style=\"padding-top: 25px; text-align:left;\">Who can participate</h5>\n" + 
			"			</div>\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/certificate.png\" /><h5 style=\"padding-top: 5px; text-align:left;\">What you need to submit to be evaluated as a best performer</h5>\n" + 
			"			</div>\n" + 
			"		</div>\n" + 
			"		<div class=\"row\" style=\"height: 110px;\">\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/people.png\" /><h5 style=\"padding-top: 5px; text-align:left;\">How to participate as an individual and on teams</h5>\n" + 
			"			</div>\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/anonymous-person.png\" style=\"margin-bottom: 20px;\" /><h5 style=\"text-align:left;\">Your right to participate anonymously <small>To participate anonymously, you must update your Synapse username to a pseudonym.</small></h5>\n" + 
			"			</div>\n" + 
			"		</div>\n" + 
			"		<div class=\"row\" style=\"height: 80px;\">\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/unlock.png\" /><h5 style=\"text-align:left;\">That DREAM Challenges are IP-free and open source</h5>\n" + 
			"			</div>\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/handshake.png\" style=\"margin-bottom: 20px;\" /><h5 style=\"text-align:left;\">How we will resolve disagreements with participants</h5>\n" + 
			"			</div>\n" + 
			"		</div>\n" + 
			"		<h5>By clicking \"Agree\" you confirm that you have read and accept the DREAM9 Challenge Rules</h5>\n" + 
			"		\n" + 
			"</div>";
	/**
	 * Throw away program that will be used to clear a specific set of previews
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createSynapseClient("JayUpdateAccessRequirement");		
		
		BufferedReader br = null;
		try {
//			Entity e = synapseClient.getEntityById("syn2874024");
//			e.setAttachments(new ArrayList<AttachmentData>());
			RestrictableObjectDescriptor subjectId = new RestrictableObjectDescriptor();
			subjectId.setId("3323870"); //a challenge team
			subjectId.setType(RestrictableObjectType.TEAM);
			VariableContentPaginatedResults<AccessRequirement> accessRequirements = synapseClient.getAccessRequirements(subjectId);
			TermsOfUseAccessRequirement challengeAR = null;
			for (AccessRequirement ar : accessRequirements.getResults()) {
				if (ar.getId().equals(3098037L)) {
					challengeAR = (TermsOfUseAccessRequirement)ar;
					break;
				}
			}
			
			challengeAR.setTermsOfUse(newTermsOfUse);
			synapseClient.updateAccessRequirement(challengeAR);
			
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
