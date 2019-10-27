package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains the role of the user to the repository
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudRepositoryPermission extends BitbucketCloudObject {

    private BitbucketCloudRole permission;
    private BitbucketCloudUser user;
    private BitbucketCloudRepository repository;

    public BitbucketCloudRepositoryPermission(@JsonProperty("uuid") String uuid,
                                              @JsonProperty("created_on") String created_on,
                                              @JsonProperty("permission") BitbucketCloudRole permission,
                                              @JsonProperty("type") BitbucketCloudObjectType type,
                                              @JsonProperty("user") BitbucketCloudUser user,
                                              @JsonProperty("team") BitbucketCloudRepository repository) {
        super(uuid, created_on, type);
        this.permission = permission;
        this.user = user;
        this.repository = repository;
    }

    /**
     * 
     * @return user role/permission to the repository
     */
    public BitbucketCloudRole getPermission() {
        return permission;
    }

    /**
     * 
     * @return user object
     */
    public BitbucketCloudUser getUser() {
        return user;
    }

    /**
     * 
     * @return repository object
     */
    public BitbucketCloudRepository getRepository() {
        return repository;
    }

}
