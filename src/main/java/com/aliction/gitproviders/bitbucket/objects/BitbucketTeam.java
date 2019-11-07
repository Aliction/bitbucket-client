package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The team class is a sub class of owner class with type pre-set to team
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
public class BitbucketTeam extends BitbucketOwner {

    /**
     * 
     * @param uuid
     * @param created_on
     * @param username
     * @param display_name
     * @param nickname
     * @param account_id
     * @param type
     */
    public BitbucketTeam(@JsonProperty("uuid") final String uuid,
                              @JsonProperty("created_on") final String created_on,
                              @JsonProperty("username") final String username,
                              @JsonProperty("display_name") final String display_name,
                              @JsonProperty("nickname") final String nickname,
                              @JsonProperty("account_id") final String account_id,
                              @JsonProperty("type") final BitbucketObjectType type) {
        super(uuid, created_on, username, display_name, nickname, account_id, BitbucketObjectType.TEAM);
    }

}
