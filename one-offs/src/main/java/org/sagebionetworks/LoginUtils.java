package org.sagebionetworks;

import static org.sagebionetworks.Constants.*;
import org.sagebionetworks.client.SynapseClient;
import org.sagebionetworks.client.SynapseClientImpl;
import org.sagebionetworks.client.exceptions.SynapseException;

public class LoginUtils {
	public static SynapseClient createSynapseClient(String userAgent) {
		return createSynapseClient(SESSION_TOKEN, PROD_REPO_URL, PROD_AUTH_URL, PROD_FILE_URL, userAgent);
	}
	
	public static SynapseClient createSynapseClient(String username, String password, String userAgent) throws SynapseException {
		return createSynapseClient(username, password, PROD_REPO_URL, PROD_AUTH_URL, PROD_FILE_URL, userAgent);
	}
	
	public static SynapseClient createAdminStagingSynapseClient(String userAgent) {
		return createSynapseClient(ADMIN_SESSION_TOKEN, STAGING_REPO_URL, STAGING_AUTH_URL, STAGING_FILE_URL, userAgent);
	}
	
	public static SynapseClient createStagingSynapseClient(String userAgent) {
		return createSynapseClient(SESSION_TOKEN, STAGING_REPO_URL, STAGING_AUTH_URL, STAGING_FILE_URL, userAgent);
	}


	public static SynapseClient createLocalSynapseClient(String userAgent) {
		return createSynapseClient(SESSION_TOKEN, LOCAL_REPO_URL, LOCAL_AUTH_URL, LOCAL_FILE_URL, userAgent);
	}
	
	public static SynapseClient createLocalAnonymousSynapseClient(String userAgent) {
		return createSynapseClient(null, LOCAL_REPO_URL, LOCAL_AUTH_URL, LOCAL_FILE_URL, userAgent);
	}

	public static SynapseClient createAnonymousSynapseClient(String userAgent) {
		return createSynapseClient(null, PROD_REPO_URL, PROD_AUTH_URL, PROD_FILE_URL, userAgent);
	}
	
	public static SynapseClient createAnonymousStagingSynapseClient(String userAgent) {
		return createSynapseClient(null, STAGING_REPO_URL, STAGING_AUTH_URL, STAGING_FILE_URL, userAgent);
	}
	
	public static SynapseClient createSynapseClient(String sessionToken, String repo, String auth, String file, String userAgent) {
		SynapseClient synapseClient = new SynapseClientImpl();
		synapseClient.setSessionToken(sessionToken);
		synapseClient.setRepositoryEndpoint(repo);
		synapseClient.setAuthEndpoint(auth);
		synapseClient.setFileEndpoint(file);
		synapseClient.appendUserAgent(userAgent);
		return synapseClient;
	}
	
	public static SynapseClient createSynapseClient(String username, String password, String repo, String auth, String file, String userAgent) throws SynapseException {
		SynapseClient synapseClient = new SynapseClientImpl();
		synapseClient.login(username, password);
		synapseClient.setRepositoryEndpoint(repo);
		synapseClient.setAuthEndpoint(auth);
		synapseClient.setFileEndpoint(file);
		synapseClient.appendUserAgent(userAgent);
		return synapseClient;
	}

}
