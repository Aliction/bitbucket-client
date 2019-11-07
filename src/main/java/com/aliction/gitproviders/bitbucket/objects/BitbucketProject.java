package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketProject extends BitbucketObject {

    private String name;
    private String key;

    /**
     * 
     * @param uuid
     * @param name
     * @param type
     * @param created_on
     * @param key
     */
    public BitbucketProject(@JsonProperty("uuid") final String uuid,
                                 @JsonProperty("name") final String name,
                                 @JsonProperty("type") final BitbucketObjectType type,
                                 @JsonProperty("created_on") final String created_on,
                                 @JsonProperty("key") final String key) {
        super(uuid, created_on, type);
        this.name = name;
        this.key = key;
    }

    /**
     * 
     * @return project name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return project key
     */
    public String getKey() {
        return key;
    }

}
