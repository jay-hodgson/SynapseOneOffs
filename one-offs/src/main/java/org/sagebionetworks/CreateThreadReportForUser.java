package org.sagebionetworks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.client.exceptions.SynapseException;
import org.sagebionetworks.client.exceptions.SynapseServerException;
import org.sagebionetworks.reflection.model.PaginatedResults;
import org.sagebionetworks.repo.model.UserProfile;
import org.sagebionetworks.repo.model.discussion.DiscussionFilter;
import org.sagebionetworks.repo.model.discussion.DiscussionReplyBundle;
import org.sagebionetworks.repo.model.discussion.DiscussionReplyOrder;
import org.sagebionetworks.repo.model.discussion.DiscussionThreadBundle;

import au.com.bytecode.opencsv.CSVWriter;

public class CreateThreadReportForUser {

	/**
	 * Throw away program that will be used to generate a report on thread
	 * activity (from ids)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createSynapseClient("Jay-create-user-discussion-report");
		String threadId = "";
		try {
			CSVWriter writer = new CSVWriter(new FileWriter("/Users/jayhodgson/Downloads/threadReport.csv"),',');
			writer.writeNext(new String[] { "Thread ID", "Thread Author", "Message", "Reply ID", "Reply Author", "Message" });
			BufferedReader reader = new BufferedReader(new FileReader("/Users/jayhodgson/Downloads/thread-ids.txt"));
			while ((threadId = reader.readLine()) != null) {
				getThread(synapseClient, writer, threadId);
			}
			reader.close();
			writer.close();
		} catch (SynapseServerException e) {
			System.out.println("status code: " + e.getStatusCode());
			System.out.println(threadId);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(threadId);
			e.printStackTrace();
		}
	}
	
	public static void getThread(SynapseClient synapseClient, CSVWriter writer, String threadId) throws IOException, SynapseException {
		// get the thread message key
		DiscussionThreadBundle threadBundle = synapseClient.getThread(threadId);
		String messageKey = threadBundle.getMessageKey();
		// get the thread url
		URL url = synapseClient.getThreadUrl(messageKey);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream())));
		String inputLine;
		StringBuilder sb = new StringBuilder();
		
		while ((inputLine = in.readLine()) != null)
			sb.append(inputLine);
		in.close();
		String threadAuthor = getDisplayName(synapseClient.getUserProfile(threadBundle.getCreatedBy()));
		writer.writeNext(new String[] { threadId, threadAuthor, sb.toString(), "", "", "" });
		// get all relies for this thread
		long offset = 0L;
		long total = 1L;
		while(offset < total) {
			PaginatedResults<DiscussionReplyBundle> replies = synapseClient.getRepliesForThread(threadId, 100L, offset, DiscussionReplyOrder.CREATED_ON, true, DiscussionFilter.NO_FILTER);
			for (DiscussionReplyBundle reply : replies.getResults()) {
				messageKey = reply.getMessageKey();
				url = synapseClient.getReplyUrl(messageKey);
				con = (HttpURLConnection) url.openConnection();
				// optional default is GET
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
				in = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream())));
				sb = new StringBuilder();
				while ((inputLine = in.readLine()) != null)
					sb.append(inputLine);
				in.close();
				String replyAuthor = getDisplayName(synapseClient.getUserProfile(reply.getCreatedBy()));
				writer.writeNext(new String[] { "", "", "", reply.getId(), replyAuthor, sb.toString() });
			}
			total = replies.getTotalNumberOfResults();
			offset += 100;
		}
	}
	
	public static String getDisplayName(UserProfile profile) {
		return getDisplayName(profile.getFirstName(), profile.getLastName(), profile.getUserName());
	}
	
	public static String getDisplayName(String firstName, String lastName, String userName) {
		StringBuilder sb = new StringBuilder();
		if (firstName != null && firstName.length() > 0) {
			sb.append(firstName.trim());
		}
		if (lastName != null && lastName.length() > 0) {
			sb.append(" ");
			sb.append(lastName.trim());
		}
		
		sb.append(" (");
		sb.append(userName);
		sb.append(")");
		return sb.toString();
	}
	

}
