package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudTeamPermission {

    private BitbucketCloudRole permission;
    private BitbucketCloudObjectType type;
    private BitbucketCloudUser user;
    private BitbucketCloudTeam team;

    public BitbucketCloudTeamPermission(@JsonProperty("permission") BitbucketCloudRole permission,
                                        @JsonProperty("type") BitbucketCloudObjectType type,
                                        @JsonProperty("user") BitbucketCloudUser user,
                                        @JsonProperty("team") BitbucketCloudTeam team) {
        super();
        this.permission = permission;
        this.type = type;
        this.user = user;
        this.team = team;
    }

    public BitbucketCloudRole getPermission() {
        return permission;
    }

    public BitbucketCloudObjectType getType() {
        return type;
    }

    public BitbucketCloudUser getUser() {
        return user;
    }

    public BitbucketCloudTeam getTeam() {
        return team;
    }

}
