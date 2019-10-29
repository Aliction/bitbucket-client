package com.aliction.gitproviders.bitbucket.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class for Bitbucket client that will be used to communicate with the server
 * @author Aly Ibrahim
 * Date: Oct 23, 2019
 *
 */
public class BitbucketCloudClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitbucketCloudClient.class);

    private Client client;
    private WebTarget webTarget;

    public BitbucketCloudClient() {

    }

    /**
     * The method is used to create a basic client to the the base server URL provided
     * @param hostURL - The base server URL
     * @return client - Returns a client object
     */
    public BitbucketCloudClient Build(final String hostURL) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(LoggingFeature.class);
        client = ClientBuilder.newClient(clientConfig);
        webTarget = client.target(hostURL);
        return this;
    }

    /**
     * The method will generate the base webtarget with the base api version and adds basic authentication from input username and password
     * @param apiURLRoot - Base API version
     * @param username - Username
     * @param password - API generated token by Bitbucket
     * @return baseTarget - Returns the base webTarget for the API version that is the base for all resources
     */
    public WebTarget createRootContext(final String apiURLRoot, final String username, final String password) {
        WebTarget apiRootTarget = webTarget.path(apiURLRoot);
        HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic(username, password);
        apiRootTarget.register(authFeature);
        return apiRootTarget;

    }

    public void disconnect() {
        this.client.close();
    }
}
