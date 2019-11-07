package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * An enum class for the roles
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
public enum BitbucketRole {
    ADMIN("admin"),
    COLLABORATOR("collaborator"),
    MEMBER("member");

    private String role;

    private BitbucketRole(String role) {
        this.role = role.toUpperCase();
    }

    @Override
    public String toString() {
        return role;
    }

    @JsonCreator
    public static BitbucketRole setRole(String input) {
        return BitbucketRole.valueOf(input.toUpperCase());
    }

    @JsonGetter
    // TODO: return JSON string (Serialized) value in lower case
    public String getRole() {
        return toString().toLowerCase();
    }


}
