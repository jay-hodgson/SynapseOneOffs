package org.sagebionetworks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.client.SynapseClientImpl;
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
		String evalId = "1683419";
		String sessionToken = "xxxxx";
//		String repoUrl = "https://repo-prod.prod.sagebase.org/repo/v1";
//		String authUrl = "https://repo-prod.prod.sagebase.org/auth/v1";
//		String fileEndpointUrl = "https://repo-prod.prod.sagebase.org/file/v1";
//		
		String repoUrl = "http://localhost:8080/services-repository-develop-SNAPSHOT/repo/v1";
		String authUrl = "http://localhost:8080/services-repository-develop-SNAPSHOT/auth/v1";
		String fileEndpointUrl = "http://localhost:8080/services-repository-develop-SNAPSHOT/file/v1";
		
		SynapseClient synapseClient = new SynapseClientImpl();		
		synapseClient.setSessionToken(sessionToken);
		synapseClient.setRepositoryEndpoint(repoUrl);
		synapseClient.setAuthEndpoint(authUrl);
		synapseClient.setFileEndpoint(fileEndpointUrl);
		synapseClient.appendUserAgent("JaysRandomScoringHarness");
		
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
				StringAnnotation scoreAnnotation = findStringAnnotation("SCORE", stringAnnotations);
				scoreAnnotation.setValue(Double.toString(new Random().nextDouble()));
				
				StringAnnotation userId = findStringAnnotation("USER_ID", stringAnnotations);
				userId.setValue(submission.getUserId());
				
				status.setStatus(SubmissionStatusEnum.SCORED);
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
