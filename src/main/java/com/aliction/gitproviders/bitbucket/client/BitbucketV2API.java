package com.aliction.gitproviters.bitbucket.client;

import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.resources.RepositoryAPI;
import com.aliction.gitproviders.bitbucket.resources.TeamAPI;
import com.aliction.gitproviders.bitbucket.resources.UserAPI;

public class BitbucketV2API {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitbucketV2API.class);

    private String username;
    private String token;
    private String hostURL;
    private static final String API_VERSION = "2.0";

    private BitbucketCloudClient client;

    private RepositoryAPI repositoryAPI;
    private UserAPI userAPI;
    private TeamAPI teamAPI;
    private WebTarget apiRootContext;

    public BitbucketCloudClient getClient() {
        if (client == null) {
            client = new BitbucketCloudClient().Build(hostURL);
        }
        return this.client;
    }
    
    public BitbucketV2API(final String hostURL, final String username, final String token) {
        this.hostURL = hostURL.endsWith("/") ? hostURL.replaceAll("/$", "") : hostURL;
        this.username = username;
        this.token = token;
        this.client = getClient();
        this.apiRootContext = this.client.createRootContext(API_VERSION, this.username, this.token);
    }

    public Response Create(final String requestURL, final Map<String, Object> urlValues, final Object object) {
        WebTarget requestTartget = apiRootContext.path(requestURL);
        Response response = requestTartget.request(MediaType.APPLICATION_JSON).post(Entity.entity(object, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response Get(final String requestURL, final Map<String, Object> urlValues, final Map<String, String> params) {
        LOGGER.info(requestURL);
        WebTarget requestTarget = apiRootContext.path(requestURL);
        if (urlValues != null) {
            requestTarget = requestTarget.resolveTemplates(urlValues);
        }
        if(params != null) {
            for (Map.Entry<String, String> queryParam : params.entrySet())
                requestTarget = requestTarget.queryParam(queryParam.getKey(), queryParam.getValue());
        }
        Response response = requestTarget.request(MediaType.APPLICATION_JSON).get();
        return response;
    }

    public Response Delete(String requestURL, final Map<String, Object> urlValues, Object object) {
        WebTarget requestTartget;
        requestTartget = apiRootContext.path(requestURL);
        Response response = requestTartget.request(MediaType.APPLICATION_JSON).delete();
        return response;
    }

    public RepositoryAPI RepositoryAPI() {
        if (repositoryAPI == null) {
            repositoryAPI = new RepositoryAPI(this);
        }
        return repositoryAPI;
    }

    public UserAPI UserAPI() {
        if (userAPI == null) {
            userAPI = new UserAPI(this);
        }
        return userAPI;
    }

    public TeamAPI TeamAPI() {
        if (teamAPI == null) {
            teamAPI = new TeamAPI(this);
        }
        return teamAPI;
    }

    public WebTarget getTarget() {
        return apiRootContext;
    }
}
