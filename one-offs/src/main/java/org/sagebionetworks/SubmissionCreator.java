package org.sagebionetworks;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.evaluation.model.Submission;
import org.sagebionetworks.repo.model.Entity;
import org.sagebionetworks.repo.model.Versionable;

public class SubmissionCreator {
	
	/**
	 * Throw away program used to write out random scores for an evaluation using the Synapse Java client
	 */
	public static void main(String[] args) {
		int submissionsToCreate = 50;
		String evalId = "1683425";
		String entityId = "syn1683434";
		
		SynapseClient synapseClient = LoginUtils.createLocalSynapseClient("JaysAutomatedSubmissions");		
		
		try {
			Entity submissionEntity = synapseClient.getEntityById(entityId);
			String eTag = submissionEntity.getEtag();
			Long versionNumber = null;
			if (submissionEntity instanceof Versionable) {
				versionNumber = ((Versionable)submissionEntity).getVersionNumber();
			}
			for (int i = 0; i < submissionsToCreate; i++) {
				Submission newSubmission= new Submission();
				newSubmission.setName("Submission " + (i+1));
				newSubmission.setEntityId(entityId);
				newSubmission.setSubmitterAlias("A Major");
				newSubmission.setEvaluationId(evalId);
				newSubmission.setUserId(synapseClient.getUserName());
				newSubmission.setVersionNumber(versionNumber);
				
				synapseClient.createSubmission(newSubmission, eTag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
