package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudProject extends BitbucketCloudObject {

    private String name;
    private String key;

    public BitbucketCloudProject(@JsonProperty("uuid") final String uuid,
                                 @JsonProperty("name") final String name,
                                 @JsonProperty("type") final BitbucketCloudObjectType type,
                                 @JsonProperty("created_on") final String created_on,
                                 @JsonProperty("key") final String key) {
        super(uuid, created_on, type);
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

}
