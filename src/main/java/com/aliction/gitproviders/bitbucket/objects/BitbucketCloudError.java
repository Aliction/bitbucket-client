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
public class BitbucketCloudError {

    private BitbucketCloudObjectType type;
    private BitbucketCloudResourceError error;

    /**
     * 
     * @param type
     * @param error
     */
    @JsonCreator
    public BitbucketCloudError(@JsonProperty("type") final BitbucketCloudObjectType type,
                               @JsonProperty("error") final BitbucketCloudResourceError error) {
        this.type = type;
        this.error = error;
    }

    /**
     * 
     * @return
     */
    public BitbucketCloudResourceError getError() {
        return error;
    }

}
