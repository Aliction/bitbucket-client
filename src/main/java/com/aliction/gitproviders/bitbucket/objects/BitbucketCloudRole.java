package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

public enum BitbucketCloudRole {
    ADMIN("admin"),
    COLLABORATOR("collaborator"),
    MEMBER("member");

    private String role;

    private BitbucketCloudRole(String role) {
        this.role = role.toUpperCase();
    }

    @Override
    public String toString() {
        return role;
    }

    @JsonCreator
    public static BitbucketCloudRole setRole(String input) {
        return BitbucketCloudRole.valueOf(input.toUpperCase());
    }

    @JsonGetter
    // TODO: return JSON string (Serialized) value in lower case
    public String getRole() {
        return toString().toLowerCase();
    }


}
