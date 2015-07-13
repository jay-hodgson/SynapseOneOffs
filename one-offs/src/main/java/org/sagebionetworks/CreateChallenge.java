package org.sagebionetworks;

import java.util.Date;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.evaluation.model.Evaluation;
import org.sagebionetworks.evaluation.model.EvaluationStatus;
import org.sagebionetworks.evaluation.model.SubmissionQuota;
import org.sagebionetworks.repo.model.Challenge;

public class CreateChallenge {
	
	
	/**
	 * Throw away program that will be used to create project settings
	 * @param args
	 */
	public static void main(String[] args) {
		
		SynapseClient synapseClient = LoginUtils.createStagingSynapseClient("Jay-create-challenge");
		try {
			Evaluation evaluation = new Evaluation();
			evaluation.setContentSource("syn3851543");
			evaluation.setName("Jay Staging notification testing");
			evaluation.setStatus(EvaluationStatus.OPEN);
			SubmissionQuota quota = new SubmissionQuota();
			quota.setFirstRoundStart(new Date());
			quota.setNumberOfRounds(1L);
			quota.setRoundDurationMillis(1000L*60L*60L*24L*30L); //about a month
			quota.setSubmissionLimit(1L);
			evaluation.setQuota(quota);
			evaluation.setSubmissionReceiptMessage("The submission receipt can also be customized per challenge");
			synapseClient.createEvaluation(evaluation);
			
			Challenge challenge = new Challenge();
			
			challenge.setParticipantTeamId("3327892");
			challenge.setProjectId("syn3851543");
			challenge = synapseClient.createChallenge(challenge);

			
//			
//			evaluation = new Evaluation();
//			evaluation.setContentSource(challengeProject);
//			evaluation.setName("Jay Staging test queue 2");
//			evaluation.setStatus(EvaluationStatus.OPEN);
//			evaluation.setSubmissionInstructionsMessage("Again, submit anything you want");
//			evaluation.setSubmissionReceiptMessage("The submission receipt can also be customized per challenge");
//			synapseClient.createEvaluation(evaluation);
//			PaginatedResults<Evaluation> t= synapseClient.getEvaluationByContentSource("syn2823193", 0, 100);
//			for (Evaluation eval : t.getResults()) {
//				System.out.println("evaluation id = " + eval.getId());
//				//update name of evaluation
//				eval.setName("Up For A Challenge");
//				synapseClient.updateEvaluation(eval);
//			}
//			Challenge challenge = synapseClient.getChallengeForProject("syn2823193");
//			Challenge challenge = new Challenge();
//			
//			challenge.setParticipantTeamId("3325803");
//			challenge.setProjectId("syn2823193");
//			challenge = synapseClient.createChallenge(challenge);
//			System.out.println("challenge id = " + challenge.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
