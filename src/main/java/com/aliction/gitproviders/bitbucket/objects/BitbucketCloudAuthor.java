package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aly Ibrahim
 * Date: Oct 23, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudAuthor extends BitbucketCloudObject {

    private String raw;
    private BitbucketCloudUser user;

    /**
     * 
     * @param raw
     * @param uuid
     * @param created_on
     * @param user
     * @param type
     */
    public BitbucketCloudAuthor(@JsonProperty("raw") String raw,
                                @JsonProperty("uuid") String uuid,
                                @JsonProperty("created_on") String created_on,
                                @JsonProperty("user") BitbucketCloudUser user,
                                @JsonProperty("type") BitbucketCloudObjectType type) {
        super(uuid, created_on, type);
        this.user = user;
    }

    /**
     * 
     * @return user object
     */
    public BitbucketCloudUser getUser() {
        return user;
    }

}
