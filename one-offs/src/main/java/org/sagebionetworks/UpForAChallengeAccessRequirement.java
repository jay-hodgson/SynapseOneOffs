package org.sagebionetworks;

import java.io.BufferedReader;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.RestrictableObjectDescriptor;
import org.sagebionetworks.repo.model.RestrictableObjectType;
import org.sagebionetworks.repo.model.TermsOfUseAccessRequirement;

public class UpForAChallengeAccessRequirement {

	
	/**
	 * Throw away program that will be used to edit Up For A Challenge access requirements
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient adminSynapseClient = LoginUtils.createSynapseClient("Jay-updating-up-for-a-challenge-ars");
		String teamId = "3325803";
		BufferedReader br = null;
		try {
			RestrictableObjectDescriptor rod = new RestrictableObjectDescriptor();
			rod.setId(teamId);
			rod.setType(RestrictableObjectType.TEAM);
			
			TermsOfUseAccessRequirement ar1 = (TermsOfUseAccessRequirement)adminSynapseClient.getAccessRequirement(4228528L);
			ar1.setTermsOfUse(U4C_RULES);
			adminSynapseClient.updateAccessRequirement(ar1);
			
			TermsOfUseAccessRequirement ar2 = (TermsOfUseAccessRequirement)adminSynapseClient.getAccessRequirement(4228529L);
			ar2.setTermsOfUse(DB_GAP_NOTE);
			adminSynapseClient.updateAccessRequirement(ar2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (Throwable e) {}
		}
	}

	private static final String U4C_RULES="<div class=\"markdown\" style=\"margin-left:20px; margin-right:20px;\">\n" + 
			"		<h3>Please read the full <a href=\"https://www.synapse.org/Portal/filehandle?entityId=syn4299368&preview=false&proxy=false\" class=\"link\" target=\"_blank\">Up For A Challenge Rules</a>.</h3>" +
			"		<h3>You can also view a summary of rules in the <a href=\"https://www.federalregister.gov/articles/2015/06/05/2015-13816/announcement-of-requirements-and-registration-for-up-for-a-challenge-u4c-stimulating-innovation-in\" class=\"link\" target=\"_blank\">Federal Register Notice</a>.</h3>\n" + 
			"		<h3 style=\"margin-bottom:10px\">These rules cover:</h3>\n" + 
			"		<div class=\"row\" style=\"height: 90px;\">\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/person.png\"><h5 style=\"padding-top: 25px; text-align:left;\">Who can participate</h5>\n" + 
			"			</div>\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/certificate.png\"><h5 style=\"padding-top: 5px; text-align:left;\">What you need to submit to be evaluated as a best performer</h5>\n" + 
			"			</div>\n" + 
			"		</div>\n" + 
			"		<div class=\"row\" style=\"height: 110px;\">\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/people.png\"><h5 style=\"padding-top: 5px; text-align:left;\">How to participate as an individual and on teams</h5>\n" + 
			"			</div>\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/handshake.png\" style=\"margin-bottom: 20px;\"><h5 style=\"text-align:left;\">How we will resolve disagreements with participants</h5>\n" + 
			"			</div>\n" + 
			"		</div>\n" + 
			"		<div class=\"row\" style=\"height: 80px;\">\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"				<img class=\"left\" src=\"https://s3.amazonaws.com/static.synapse.org/images/unlock.png\"><h5 style=\"text-align:left;\">This Challenge is IP-free and open source</h5>\n" + 
			"			</div>\n" + 
			"			<div class=\"col-xs-6\">\n" + 
			"			</div>\n" + 
			"		</div>\n" + 
			"		<h5>By clicking \"Accept\" you confirm that you have read and accept the Up For A Challenge Rules</h5>\n" + 
			"</div>";
	
	public static final String DB_GAP_NOTE="<div class=\"markdown\" style=\"margin-left:20px; margin-right:20px;\"><br>\n" + 
			"	<p>\n" + 
			"		To facilitate the dbGaP data access process for the <a href=\"TBD\">Datasets Available</a>, you should provide your first name, last name and current affiliation in <a target=\"_blank\" href=\"/#!Profile:edit\" class=\"link\">your Synapse profile</a> (click <em>Edit Profile</em>). \n" + 
			"	</p><br>\n" + 
			"	<p>\n" + 
			"		If you registered your account with a different email address than the one you will use with your dbGaP registration, then you should also update your <a target=\"_blank\" href=\"/#!Profile:edit\" class=\"link\">Synapse email</a> to the same address.  \n" + 
			"	</p><br>\n" + 
			"	<p>\n" + 
			"		Note that this information will be provided to the appropriate NCI Data Access Committees so they will anticipate your data access request application.\n" + 
			"	</p><br>\n" + 
			"	\n" + 
			"	<h5>Once you have updated your profile, select “Accept”.</h5>\n" + 
			"</div>";
}
