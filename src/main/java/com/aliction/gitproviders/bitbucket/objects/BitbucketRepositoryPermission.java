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
public class BitbucketRepositoryPermission extends BitbucketObject {

    private BitbucketRole permission;
    private BitbucketUser user;
    private BitbucketRepository repository;

    public BitbucketRepositoryPermission(@JsonProperty("uuid") String uuid,
                                              @JsonProperty("created_on") String created_on,
                                              @JsonProperty("permission") BitbucketRole permission,
                                              @JsonProperty("type") BitbucketObjectType type,
                                              @JsonProperty("user") BitbucketUser user,
                                              @JsonProperty("team") BitbucketRepository repository) {
        super(uuid, created_on, type);
        this.permission = permission;
        this.user = user;
        this.repository = repository;
    }

    /**
     * 
     * @return user role/permission to the repository
     */
    public BitbucketRole getPermission() {
        return permission;
    }

    /**
     * 
     * @return user object
     */
    public BitbucketUser getUser() {
        return user;
    }

    /**
     * 
     * @return repository object
     */
    public BitbucketRepository getRepository() {
        return repository;
    }

}
