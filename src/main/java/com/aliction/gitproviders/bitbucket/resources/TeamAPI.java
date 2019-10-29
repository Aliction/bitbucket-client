package com.aliction.gitproviders.bitbucket.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.client.BitbucketV2API;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudGetTeamException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudPageException;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudRole;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudTeam;

/**
 * The class for the team resource
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
public class TeamAPI extends BaseAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamAPI.class);
    private static final String CONTROLLER = "teams";

    /**
     * 
     * @param bitbucket
     */
    public TeamAPI(BitbucketV2API bitbucket) {
        super(bitbucket);
    }

    /**
     * The method return team object with the team details
     * Resource /2.0/teams/{username}
     * @param team
     * @return team object
     */
    public BitbucketCloudTeam getTeamInfo(String team) {
        String URL = BuildURL(new String[]{CONTROLLER, team});
        BitbucketCloudTeam teamObj = null;
        try {
            teamObj = Validate(Get(URL), BitbucketCloudTeam.class);
        } catch (BitbucketCloudException exp) {
            exp.printStackTrace();
        }
        return teamObj;
    }

    /**
     * The method return the teams that the user belongs to and is filtered by the input role
     * Resource /2.0/teams?q=role={role}
     * @param role - logged user role are defined by the enum {admin, collaborator, member}
     * @return list of team objects
     * @throws BitbucketCloudPageException
     * @throws BitbucketCloudGetTeamException
     */
    public List<BitbucketCloudTeam> getUserTeams(BitbucketCloudRole role) throws BitbucketCloudPageException, BitbucketCloudGetTeamException {
        List<BitbucketCloudTeam> teams = null;
        Map<String, String> queryParam = null;
        if (role != null) {
            queryParam = new HashMap<String, String>();
            queryParam.put("role", role.toString());
        }
        String URL = BuildURL(new String[]{CONTROLLER});
        Response response = Get(URL, null, queryParam);
        try {
            teams = getPaginatedObjects(response, BitbucketCloudTeam.class);
        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudGetTeamException(exp.getMessage());
        }
        return teams;
    }
}
