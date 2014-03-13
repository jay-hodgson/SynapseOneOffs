package org.sagebionetworks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.evaluation.model.Submission;
import org.sagebionetworks.evaluation.model.SubmissionBundle;
import org.sagebionetworks.evaluation.model.SubmissionStatus;
import org.sagebionetworks.evaluation.model.SubmissionStatusEnum;
import org.sagebionetworks.repo.model.PaginatedResults;
import org.sagebionetworks.repo.model.annotation.Annotations;
import org.sagebionetworks.repo.model.annotation.StringAnnotation;

public class RandomScoringHarness {
	
	/**
	 * Throw away program used to write out random scores for an evaluation using the Synapse Java client
	 */
	public static void main(String[] args) {
		String evalId = "2356590";
		
		SynapseClient synapseClient = LoginUtils.createSynapseClient("JaysRandomScoringHarness");		
		
		try {
			PaginatedResults<SubmissionBundle> submissions = synapseClient.getAllSubmissionBundles(evalId, 0, Integer.MAX_VALUE);
			System.out.println("Total Submissions: " + submissions.getTotalNumberOfResults());
			for (SubmissionBundle submissionBundle : submissions.getResults()) {
				Submission submission = submissionBundle.getSubmission();
				SubmissionStatus status = submissionBundle.getSubmissionStatus();
				Annotations annotations = status.getAnnotations();
				if (annotations == null) {
					annotations = new Annotations();
					status.setAnnotations(annotations);
				}
					
				List<StringAnnotation> stringAnnotations = annotations.getStringAnnos();
				if (stringAnnotations == null) {
					stringAnnotations = new ArrayList<StringAnnotation>();
					annotations.setStringAnnos(stringAnnotations);
				}
				
				//some of the entries should be scored, and some set to validated
				if (new Random().nextDouble() > .5) {
					//and half are scored
					StringAnnotation scoreAnnotation = findStringAnnotation("SCORE", stringAnnotations);
					scoreAnnotation.setValue(Double.toString(new Random().nextDouble()));
					status.setStatus(SubmissionStatusEnum.SCORED);
				} else {
					status.setStatus(SubmissionStatusEnum.VALIDATED);
					StringAnnotation scoreAnnotation = findStringAnnotation("SCORE", stringAnnotations);
					scoreAnnotation.setValue("0");
				}
				
				StringAnnotation userId = findStringAnnotation("USER_ID", stringAnnotations);
				userId.setValue(submission.getUserId());
				
				synapseClient.updateSubmissionStatus(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static StringAnnotation findStringAnnotation(String key, List<StringAnnotation> stringAnnotations) {
		StringAnnotation annotation = null;
		for (StringAnnotation stringAnnotation : stringAnnotations) {
			if (key.equals(stringAnnotation.getKey())) {
				annotation = stringAnnotation;
				break;
			}
		}
		if (annotation == null) {
			annotation = new StringAnnotation();
			annotation.setKey(key);
			annotation.setIsPrivate(false);
			stringAnnotations.add(annotation);
		}

		return annotation;
	}
}
