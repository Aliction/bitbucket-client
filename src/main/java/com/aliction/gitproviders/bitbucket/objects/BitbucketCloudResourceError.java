package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudResourceError {

    private String message;
    private String detail;

    @JsonCreator
    public BitbucketCloudResourceError(@JsonProperty("message") final String message,
                                       @JsonProperty("detail") final String detail) {
        super();
        this.message = message;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

}
