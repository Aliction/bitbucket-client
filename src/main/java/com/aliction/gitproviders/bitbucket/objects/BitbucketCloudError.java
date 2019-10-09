package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudError {

    private BitbucketCloudObjectType type;
    private BitbucketCloudResourceError error;

    @JsonCreator
    public BitbucketCloudError(@JsonProperty("type") final BitbucketCloudObjectType type,
                               @JsonProperty("error") final BitbucketCloudResourceError error) {
        this.type = type;
        this.error = error;
    }

    public BitbucketCloudObjectType getType() {
        return type;
    }


    public BitbucketCloudResourceError getError() {
        return error;
    }

}
