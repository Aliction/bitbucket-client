package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudOwner extends BitbucketCloudObject {

    private String username;
    private String display_name;
    private String nickname;
    private String account_id;

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
    @JsonCreator
    public BitbucketCloudOwner(@JsonProperty("uuid") final String uuid,
                               @JsonProperty("created_on") final String created_on,
                               @JsonProperty("username") final String username,
                               @JsonProperty("display_name") final String display_name,
                               @JsonProperty("nickname") final String nickname,
                               @JsonProperty("account_id") final String account_id,
                               @JsonProperty("type") final BitbucketCloudObjectType type) {
        super(uuid, created_on, type);
        this.username = username;
        this.display_name = display_name;
        this.nickname = nickname;
        this.account_id = account_id;
    }

    /**
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @return display name
     */
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * 
     * @return user nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     * @return user account id
     */
    public String getAccount_id() {
        return account_id;
    }

}
