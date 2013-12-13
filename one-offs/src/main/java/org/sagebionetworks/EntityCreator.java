package org.sagebionetworks;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.evaluation.model.Submission;
import org.sagebionetworks.repo.model.Data;
import org.sagebionetworks.repo.model.Entity;
import org.sagebionetworks.repo.model.Study;
import org.sagebionetworks.repo.model.Versionable;

public class EntityCreator {
	
	/**
	 * Throw away program used to create entities using the Synapse Java client
	 */
	public static void main(String[] args) {
		int entitiesToCreate = 50;
		
		String parentEntityId = "syn1683604";
		
		SynapseClient synapseClient = LoginUtils.createLocalSynapseClient("JaysAutomatedCreation");		
		
		try {
			for (int i = 0; i < entitiesToCreate; i++) {
				Data child = new Data();
				child.setParentId(parentEntityId);
				child.setName("Part "+ i);
				synapseClient.createEntity(child);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
