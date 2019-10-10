package com.aliction.gitproviters.bitbucket.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitbucketCloudClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitbucketCloudClient.class);

    private Client client;
    private WebTarget webTarget;

    public BitbucketCloudClient() {

    }

    public BitbucketCloudClient Build(final String hostURL) {
        LOGGER.info(hostURL);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(LoggingFeature.class);
        client = ClientBuilder.newClient(clientConfig);
        webTarget = client.target(hostURL);
        return this;
    }

    public WebTarget createRootContext(final String apiURLRoot, final String username, final String password) {
        WebTarget apiRootTarget = webTarget.path(apiURLRoot);
        HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic(username, password);
        apiRootTarget.register(authFeature);
        return apiRootTarget;

    }
}
