package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitbucketCloudTeam extends BitbucketCloudOwner {

    public BitbucketCloudTeam(@JsonProperty("uuid") final String uuid,
                              @JsonProperty("created_on") final String created_on,
                              @JsonProperty("username") final String username,
                              @JsonProperty("display_name") final String display_name,
                              @JsonProperty("nickname") final String nickname,
                              @JsonProperty("account_id") final String account_id,
                              @JsonProperty("type") final BitbucketCloudObjectType type) {
        super(uuid, created_on, username, display_name, nickname, account_id, BitbucketCloudObjectType.TEAM);
        // TODO Auto-generated constructor stub
    }

}
