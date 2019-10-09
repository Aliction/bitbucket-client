package com.aliction.gitproviders.bitbucket.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudRole;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudTeam;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudTeamPermission;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudUser;
import com.aliction.gitproviters.bitbucket.client.BitbucketV2API;

public class UserAPI extends BaseAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAPI.class);

    private static final String CONTROLLER = "user";

    public UserAPI(BitbucketV2API bitbucketAPI) {
        super(bitbucketAPI);
        // TODO Auto-generated constructor stub
    }

    public BitbucketCloudUser getLoggedUser() {
        String URL = BuildURL(new String[]{CONTROLLER});
        BitbucketCloudUser user = Get(URL).readEntity(BitbucketCloudUser.class);
        return user;
    }

    public List<BitbucketCloudTeam> getUserTeams() {
        return getUserTeams(null);
    }

    public List<BitbucketCloudTeam> getUserTeams(BitbucketCloudRole role) {
        Map<String, String> queryParam = null;
        if (role != null) {
            queryParam = new HashMap<String, String>();
            queryParam.put("role", role.toString());
        }
        String URL = BuildURL(new String[]{CONTROLLER, "permissions", "teams"});
        String pageJSON = Get(URL, null, queryParam).readEntity(String.class);
        List<BitbucketCloudTeamPermission> teamPermissions = getPageValues(pageJSON, BitbucketCloudTeamPermission.class);
        List<BitbucketCloudTeam> teams = new ArrayList<BitbucketCloudTeam>();
        for (BitbucketCloudTeamPermission teamPermission : teamPermissions) {
            teams.add(teamPermission.getTeam());
        }
        return teams;
    }

}
