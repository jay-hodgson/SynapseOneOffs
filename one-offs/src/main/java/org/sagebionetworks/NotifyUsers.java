package org.sagebionetworks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.entity.ContentType;
import org.sagebionetworks.client.SynapseClientImpl;
import org.sagebionetworks.repo.model.UserGroupHeader;
import org.sagebionetworks.repo.model.UserProfile;
import org.sagebionetworks.repo.model.message.MessageToUser;
/** Based on the results from this query (the knock outs are by going to the specific entities.
* select distinct n.ID, f.CREATED_BY, f.CREATED_ON, f.CONTENT_SIZE, f.NAME from JDONODE n, JDOREVISION r, FILES f where f.ID in (
* select ID from FILES where CREATED_ON > '2014-05-03 00:00:030' and CREATED_ON < '2014-05-28 00:00:030'  and METADATA_TYPE = 'S3' and CONTENT_SIZE > 5242880 order by CREATED_BY)
* and n.ID = r.OWNER_NODE_ID and r.FILE_HANDLE_ID = f.ID and n.ID not in (select NODE_ID from TRASH_CAN) and f.CREATED_BY not in (3321036, 3319993, 1968150, 1586613, 681698, 2199873, 372127, 273959, 2166046, 1761529) and n.ID not in (2453913, 2455232) order by f.CREATED_BY;
 * @author jayhodgson
 *
 */
public class NotifyUsers {

	public static class Row {
		String nodeId, createdBy, createdOn, fileName, size;

		public Row(String nodeId, String createdBy, String createdOn,
				String fileName, String size) {
			super();
			this.nodeId = nodeId;
			this.createdBy = createdBy;
			this.createdOn = createdOn;
			this.fileName = fileName;
			this.size = size;
		}
		
	}
	
	/**
	 * Throw away program that will be used to clear a specific set of previews
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClientImpl synapseClient = (SynapseClientImpl)LoginUtils.createSynapseClient("JayNotification");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("/Users/jayhodgson/Documents/file-handle-ids.csv"));
			String baseText = "We are sorry to inform you that the following files appear to have been corrupted during upload, and ask that you please re-upload these files at your earliest convenience:\n\n<br><br>";
			
			Map<String, List<Row>> user2Row = new HashMap<String,List<Row>>(); 
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				if (tokens.length != 5)
					throw new IllegalArgumentException("invalid token length");
				List<Row> rows = user2Row.get(tokens[1]);
				if (rows == null) {
					rows = new ArrayList<Row>();
				}
				rows.add(new Row(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]));
				user2Row.put(tokens[1], rows);
			}
			for (String createdBy : user2Row.keySet()) {
				List<Row> rows = user2Row.get(createdBy);
				UserProfile profile = synapseClient.getUserProfile(createdBy);

				try {
					StringBuilder builder = new StringBuilder();
					builder.append("<html><head></head><body>Dear " + getDisplayName(profile) + ",\n\n<br><br>");
					builder.append(baseText);
					for (Row row : rows) {
						builder.append("<a href=\"https://www.synapse.org/#!Synapse:syn" + row.nodeId + "\">"+row.fileName+"</a> (uploaded on " + row.createdOn + ")\n<br>");	
					}
					
					builder.append("\n\n<br><br>If you have any other questions or concerns, please contact <a href=\"mailto:synapseInfo@sagebase.org\">synapseInfo@sagebase.org</a>");
					
					builder.append("\n\n<br><br>-Synapse Development Team</body></html>");
					
					String fileHandleId = synapseClient.uploadToFileHandle(builder.toString().getBytes(), ContentType.create("text/html", StandardCharsets.UTF_8));
					MessageToUser message = new MessageToUser();
					message.setFileHandleId(fileHandleId);
					message.setSubject("Synapse File issue");
					Set<String> recipientPrincipalIds = new HashSet<String>();
					recipientPrincipalIds.add(createdBy);
					message.setRecipients(recipientPrincipalIds);
					synapseClient.sendMessage(message);
				} catch (Exception e) {
					System.out.println("Failed to notify user " + createdBy + ": " + e.getMessage());
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			try {
				
				if (br != null)
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	
	public static String getDisplayName(UserProfile profile) {
		return getDisplayName(profile.getFirstName(), profile.getLastName(), profile.getUserName());
	}
	
	public static String getDisplayName(UserGroupHeader header) {
		return getDisplayName(header.getFirstName(), header.getLastName(), header.getUserName());
	}
	
	public static String getDisplayName(String firstName, String lastName, String userName) {
		StringBuilder sb = new StringBuilder();
		boolean hasDisplayName = false;
		if (firstName != null && firstName.length() > 0) {
			sb.append(firstName.trim());
			hasDisplayName = true;
		}
		if (lastName != null && lastName.length() > 0) {
			sb.append(" ");
			sb.append(lastName.trim());
			hasDisplayName = true;
		}
		
		sb.append(getUserName(userName, hasDisplayName));
		
		return sb.toString();
	}
	
	public static boolean isTemporaryUsername(String username){
		if(username == null) throw new IllegalArgumentException("UserName cannot be null");
		return username.startsWith("TEMPORARY-");
	}

	public static String getUserName(String userName, boolean inParens) {
		StringBuilder sb = new StringBuilder();
		
		if (userName != null && !isTemporaryUsername(userName)) {
			//if the name is filled in, then put the username in parens
			if (inParens)
				sb.append(" (");
			sb.append(userName);
			if (inParens)
				sb.append(")");
		}
		
		return sb.toString();
	}
}
