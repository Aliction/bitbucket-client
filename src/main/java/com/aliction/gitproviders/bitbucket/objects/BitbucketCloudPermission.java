package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * 
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
public enum BitbucketCloudPermission {
    ADMIN("admin"),
    READ("read"),
    WRITE("write");

    private String permission;

    private BitbucketCloudPermission(String role) {
        this.permission = role.toUpperCase();
    }

    @Override
    public String toString() {
        return permission;
    }

    @JsonCreator
    public static BitbucketCloudPermission setRole(String input) {
        return BitbucketCloudPermission.valueOf(input.toUpperCase());
    }

    @JsonGetter
    // TODO: return JSON string (Serialized) value in lower case
    public String getRole() {
        return toString().toLowerCase();
    }

}