package com.aliction.gitproviders.bitbucket.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudOwner;
import com.aliction.gitproviters.bitbucket.client.BitbucketV2API;

public class TeamAPI extends BaseAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamAPI.class);
    private static final String CONTROLLER = "team";

    public TeamAPI(BitbucketV2API bitbucketAPI) {
        super(bitbucketAPI);
    }

    public BitbucketCloudOwner getTeamInfo() {
        // TODO Auto-generated method stub
        return null;
    }

}
