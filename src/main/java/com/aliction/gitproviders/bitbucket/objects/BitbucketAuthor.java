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
public class BitbucketAuthor extends BitbucketObject {

    private String raw;
    private BitbucketUser user;

    /**
     * 
     * @param raw - String "username <email_address>
     * @param uuid - String uuid
     * @param created_on - String creation date
     * @param user - BitbucketUser type
     * @param type - BitbucketObjectType type
     */
    public BitbucketAuthor(@JsonProperty("raw") String raw,
                                @JsonProperty("uuid") String uuid,
                                @JsonProperty("created_on") String created_on,
                                @JsonProperty("user") BitbucketUser user,
                                @JsonProperty("type") BitbucketObjectType type) {
        super(uuid, created_on, type);
        this.user = user;
    }

    /**
     * 
     * @return user object
     */
    public BitbucketUser getUser() {
        return user;
    }

}
