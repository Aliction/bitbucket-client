package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The team class is a sub class of owner class with type pre-set to user
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
public class BitbucketUser extends BitbucketOwner {

    /**
     * 
     * @param uuid - String uui
     * @param created_on - String creation date
     * @param username - String user name
     * @param display_name - String display name
     * @param nickname - String nick name
     * @param account_id - String account Id
     * @param type - BitbucketObjectType type
     */
    public BitbucketUser(@JsonProperty("uuid") final String uuid,
                              @JsonProperty("created_on") final String created_on,
                              @JsonProperty("username") final String username,
                              @JsonProperty("display_name") final String display_name,
                              @JsonProperty("nickname") final String nickname,
                              @JsonProperty("account_id") final String account_id,
                              @JsonProperty("type") final BitbucketObjectType type) {
        super(uuid, created_on, username, display_name, nickname, account_id, BitbucketObjectType.USER);
    }
}
