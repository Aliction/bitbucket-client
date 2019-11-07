package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * An enum class that restrict object type used within the library
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
public enum BitbucketObjectType {
    USER("user"),
    TEAM("team"),
    EMAIL("email"),
    PROJECT("project"),
    REPOSITORY("repository"),
    COMMIT("commit"),
    COMMIT_COMMENT("commit_comment"),
    AUTHOR("author"),
    REPOSITORY_PERMISSION("repository-permission"),
    TEAM_PERMISSION("team-permission"),
    ERROR("error");

    private String type;

    private BitbucketObjectType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    @JsonCreator
    public static BitbucketObjectType getType(String type) {
        return BitbucketObjectType.valueOf(type.toUpperCase());
    }

    @JsonValue
    public String getType() {
        return toString().toLowerCase();
    }
}
