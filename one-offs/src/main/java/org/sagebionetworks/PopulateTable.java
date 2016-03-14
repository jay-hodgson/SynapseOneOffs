package org.sagebionetworks;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.evaluation.model.Evaluation;
import org.sagebionetworks.evaluation.model.EvaluationStatus;
import org.sagebionetworks.evaluation.model.Submission;
import org.sagebionetworks.evaluation.model.SubmissionQuota;
import org.sagebionetworks.repo.model.Challenge;
import org.sagebionetworks.repo.model.UserProfile;
import org.sagebionetworks.repo.model.table.ColumnModel;
import org.sagebionetworks.repo.model.table.PartialRow;
import org.sagebionetworks.repo.model.table.PartialRowSet;
import org.sagebionetworks.repo.model.table.TableEntity;

public class PopulateTable {
	
	
	/**
	 * Throw away program that will be used to populate a table
	 * @param args
	 */
	public static void main(String[] args) {
		
		SynapseClient synapseClient = LoginUtils.createSynapseClient("Jay-SWC-2944");
		try {
			List<PartialRow> rows = new ArrayList<PartialRow>();
			String tableEntityId = "syn5758483";
			List<ColumnModel> headers = synapseClient.getColumnModelsForTableEntity(tableEntityId);
			
			for (int i = 0; i < 5; i++) {
				Thread.sleep(3142);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(headers.get(0).getId(), Long.toString(System.currentTimeMillis()));
				PartialRow pr = new PartialRow();
				pr.setValues(map);
				rows.add(pr);
			}
			
			//append rows to the table
			PartialRowSet prs = new PartialRowSet();
			prs.setRows(rows);
			prs.setTableId(tableEntityId);
			synapseClient.appendRowsToTable(prs, 5000, tableEntityId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
