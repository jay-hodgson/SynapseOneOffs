package org.sagebionetworks;

import java.io.BufferedReader;
import java.net.URL;

import org.sagebionetworks.client.SynapseClient;

public class DownloadAsAnonymous {

	
	/**
	 * Throw away program to test anonymous download
	 * @param args
	 */
	public static void main(String[] args) {
		SynapseClient synapseClient = LoginUtils.createAnonymousSynapseClient("JayTestAnonymousDownload");		
		
		BufferedReader br = null;
		try {
			String entityId =  "syn2343245";
			URL resolvedUrl;
			try {
				System.out.println("synapseClient.getFileEntityPreviewTemporaryUrlForCurrentVersion: ");
				resolvedUrl = synapseClient.getFileEntityPreviewTemporaryUrlForCurrentVersion(entityId);
				System.out.println(resolvedUrl.toString());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				System.out.println("synapseClient.getFileEntityTemporaryUrlForCurrentVersion:");
				resolvedUrl = synapseClient.getFileEntityTemporaryUrlForCurrentVersion(entityId);
				System.out.println(resolvedUrl.toString());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Long versionNumber = 1L;
			try {
				System.out.println("synapseClient.getFileEntityPreviewTemporaryUrlForVersion:");
				resolvedUrl = synapseClient.getFileEntityPreviewTemporaryUrlForVersion(entityId, versionNumber);
				System.out.println(resolvedUrl.toString());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				System.out.println("synapseClient.getFileEntityTemporaryUrlForVersion:");
				resolvedUrl = synapseClient.getFileEntityTemporaryUrlForVersion(entityId, versionNumber);
				System.out.println(resolvedUrl.toString());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (Throwable e) {}
		}
	}

}
