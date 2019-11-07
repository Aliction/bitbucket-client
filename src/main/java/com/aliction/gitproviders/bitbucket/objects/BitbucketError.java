package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketError {

    private BitbucketObjectType type;
    private BitbucketResourceError error;

    /**
     * 
     * @param type - BitbucketObjectType type
     * @param error - BitbucketResourceError error object
     */
    @JsonCreator
    public BitbucketError(@JsonProperty("type") final BitbucketObjectType type,
                               @JsonProperty("error") final BitbucketResourceError error) {
        this.type = type;
        this.error = error;
    }

    /**
     * 
     * @return error object
     */
    public BitbucketResourceError getError() {
        return error;
    }

}
