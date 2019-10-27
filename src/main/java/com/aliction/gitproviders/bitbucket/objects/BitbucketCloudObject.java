package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is the base abstract class for all objects
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BitbucketCloudObject {

    private String uuid;
    private String created_on;
    private BitbucketCloudObjectType type;

    /**
     * 
     * @param uuid
     * @param created_on
     * @param type
     */
    public BitbucketCloudObject(@JsonProperty("uuid") final String uuid,
                                @JsonProperty("created_on") final String created_on,
                                @JsonProperty("type") final BitbucketCloudObjectType type) {
        super();
        this.uuid = uuid;
        this.created_on = created_on;
        this.type = type;
    }

    /**
     * 
     * @return object UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 
     * @return creation time of the object
     */
    public String getCreated_on() {
        return created_on;
    }

    /**
     * 
     * @return object type
     */
    public BitbucketCloudObjectType getType() {
        return type;
    }

}
