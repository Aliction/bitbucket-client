package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * An enum class that restrict object type used within the library
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
public enum BitbucketCloudObjectType {
    USER("user"),
    TEAM("team"),
    EMAIL("email"),
    PROJECT("project"),
    REPOSITORY("repository"),
    COMMIT("commit"),
    AUTHOR("author"),
    REPOSITORY_PERMISSION("repository-permission"),
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
