package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudOwner extends BitbucketCloudObject {

    private String username;
    private String display_name;
    private String nickname;
    private String account_id;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

}
