package org.sagebionetworks;

import static org.sagebionetworks.Constants.*;
import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.client.SynapseClientImpl;

public class LoginUtils {
	public static SynapseClient createSynapseClient(String userAgent) {
		SynapseClient synapseClient = new SynapseClientImpl();		
		synapseClient.setSessionToken(SESSION_TOKEN);
		synapseClient.setRepositoryEndpoint(PROD_REPO_URL);
		synapseClient.setAuthEndpoint(PROD_AUTH_URL);
		synapseClient.setFileEndpoint(PROD_FILE_URL);
		synapseClient.appendUserAgent(userAgent);
		return synapseClient;
	}
	
	public static SynapseClient createLocalSynapseClient(String userAgent) {
		SynapseClient synapseClient = new SynapseClientImpl();		
		synapseClient.setSessionToken(SESSION_TOKEN);
		synapseClient.setRepositoryEndpoint(LOCAL_REPO_URL);
		synapseClient.setAuthEndpoint(LOCAL_AUTH_URL);
		synapseClient.setFileEndpoint(LOCAL_FILE_URL);
		synapseClient.appendUserAgent(userAgent);
		return synapseClient;
	}


}
