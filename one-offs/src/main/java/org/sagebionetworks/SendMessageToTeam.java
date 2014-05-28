package org.sagebionetworks;

import java.io.Console;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.entity.ContentType;
import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.repo.model.message.MessageToUser;

public class SendMessageToTeam {
	public static void main(String[] args) {
		try {
			Console console = System.console();
		    if (console == null) {
		        System.out.println("Couldn't get Console instance");
		        System.exit(0);
		    }
		    String username = console.readLine("Enter your Synapse username: ");
		    char passwordArray[] = console.readPassword("Enter your Synapse password: ");
		    String pw = new String(passwordArray);
		    SynapseClient synapseClient = LoginUtils.createSynapseClient(username, pw, "Jay-SendingMessage");
		    System.out.println("Successfully logged in.");
			
		    String subject = console.readLine("Enter message subject line: ");
		    String path = console.readLine("Enter the path to the html file containing the message body: ");
		    while (!new File(path).exists()) {
		    	System.out.println("Could not find file, please try again.");
		    	path = console.readLine("Enter the path to the html file containing the message body: ");
		    }
		    System.out.println("------------------------");
		    System.out.println("Team ID - Team Name");
		    System.out.println("3320951 - Acute Myeloid Leukemia Outcome Prediction DREAM Challenge Participants");
		    System.out.println("3320895 - Broad DREAM Gene Essentiality Prediction Challenge");
		    System.out.println("3319351 - Test Messaging Team");
		    System.out.println("------------------------");
		    String principalId = console.readLine("Enter the Team ID: ");
		    
		    String confirmation = console.readLine("Are you sure you want to send this message? [Y/N]");
		    if ("y".equalsIgnoreCase(confirmation.trim())) {
			    byte[] htmlContent = Files.readAllBytes(Paths.get(path));
				String fileHandleId = synapseClient.uploadToFileHandle(htmlContent, ContentType.create("text/html", StandardCharsets.UTF_8));
				MessageToUser message = new MessageToUser();
				message.setFileHandleId(fileHandleId);
				message.setSubject(subject);
				Set<String> recipientPrincipalIds = new HashSet<String>();
				recipientPrincipalIds.add(principalId);
				message.setRecipients(recipientPrincipalIds);
				synapseClient.sendMessage(message);
				System.out.println("Message Sent!");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
