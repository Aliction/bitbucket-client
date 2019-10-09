package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudObject {

    private String uuid;
    private String created_on;
    private BitbucketCloudObjectType type;

    public BitbucketCloudObject(@JsonProperty("uuid") final String uuid,
                                @JsonProperty("created_on") final String created_on,
                                @JsonProperty("type") final BitbucketCloudObjectType type) {
        super();
        this.uuid = uuid;
        this.created_on = created_on;
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public String getCreated_on() {
        return created_on;
    }

    public BitbucketCloudObjectType getType() {
        return type;
    }

}
