package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BitbucketCloudObjectType {
    USER("user"),
    TEAM("team"),
    REPOSITORY("repository"),
    PROJECT("project"),
    TEAM_PERMISSION("team-permission"),
    ERROR("error");

    private String type;

    private BitbucketCloudObjectType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    @JsonCreator
    public static BitbucketCloudObjectType getType(String type) {
        return BitbucketCloudObjectType.valueOf(type.toUpperCase());
    }

    @JsonValue
    public String getType() {
        return toString().toLowerCase();
    }
}
