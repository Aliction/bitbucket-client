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
public class BitbucketTeamPermission extends BitbucketObject {

    private BitbucketRole permission;
    private BitbucketObjectType type;
    private BitbucketUser user;
    private BitbucketTeam team;

    /**
     * 
     * @param uuid
     * @param created_on
     * @param permission
     * @param type
     * @param user
     * @param team
     */
    public BitbucketTeamPermission(@JsonProperty("uuid") String uuid,
                                        @JsonProperty("created_on") String created_on,
                                        @JsonProperty("permission") BitbucketRole permission,
                                        @JsonProperty("type") BitbucketObjectType type,
                                        @JsonProperty("user") BitbucketUser user,
                                        @JsonProperty("team") BitbucketTeam team) {
        super(uuid, created_on, type);
        this.permission = permission;
        this.user = user;
        this.team = team;
    }

    /**
     * 
     * @return user role/permission to the team
     */
    public BitbucketRole getPermission() {
        return permission;
    }

    /**
     * 
     * @return user object
     */
    public BitbucketUser getUser() {
        return user;
    }

    /**
     * 
     * @return team object
     */
    public BitbucketTeam getTeam() {
        return team;
    }

}
