package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains the role of the user to the team
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudTeamPermission extends BitbucketCloudObject {

    private BitbucketCloudRole permission;
    private BitbucketCloudObjectType type;
    private BitbucketCloudUser user;
    private BitbucketCloudTeam team;

    /**
     * 
     * @param uuid
     * @param created_on
     * @param permission
     * @param type
     * @param user
     * @param team
     */
    public BitbucketCloudTeamPermission(@JsonProperty("uuid") String uuid,
                                        @JsonProperty("created_on") String created_on,
                                        @JsonProperty("permission") BitbucketCloudRole permission,
                                        @JsonProperty("type") BitbucketCloudObjectType type,
                                        @JsonProperty("user") BitbucketCloudUser user,
                                        @JsonProperty("team") BitbucketCloudTeam team) {
        super(uuid, created_on, type);
        this.permission = permission;
        this.user = user;
        this.team = team;
    }

    /**
     * 
     * @return user role/permission to the team
     */
    public BitbucketCloudRole getPermission() {
        return permission;
    }

    /**
     * 
     * @return user object
     */
    public BitbucketCloudUser getUser() {
        return user;
    }

    /**
     * 
     * @return team object
     */
    public BitbucketCloudTeam getTeam() {
        return team;
    }

}
