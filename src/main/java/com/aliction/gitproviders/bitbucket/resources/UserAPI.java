package com.aliction.gitproviders.bitbucket.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.client.BitbucketV2API;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudEmailNotFound;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudGetRepositoryPermissionsException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudGetTeamPermissionsException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudGetUserEmailException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudPageException;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudEmail;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudPermission;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudRepository;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudRepositoryPermission;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudRole;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudTeam;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudTeamPermission;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudUser;

/**
 * The class for the user resource
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
public class UserAPI extends BaseAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAPI.class);
    private static final String CONTROLLER = "user";

    /**
     * 
     * @param bitbucket
     */
    public UserAPI(BitbucketV2API bitbucket) {
        super(bitbucket);
    }

    /**
     * The method will retrieve the current logged in user
     * @return user object
     */
    public BitbucketCloudUser getLoggedUser() {
        String URL = BuildURL(new String[]{CONTROLLER});
        BitbucketCloudUser user = Get(URL).readEntity(BitbucketCloudUser.class);
        return user;
    }

    /**
     * The method will get information about the user's email address
     * Resource /2.0/user/emails/{email}
     * @param email - String email address
     * @return email object
     * @throws BitbucketCloudEmailNotFound
     */
    public BitbucketCloudEmail checkUserEmail(final String email) throws BitbucketCloudEmailNotFound {
        String URL = BuildURL(new String[]{CONTROLLER, "emails", email});
        BitbucketCloudEmail emailObj = null;
        Response response = Get(URL);
        try {
            emailObj = Validate(response).readEntity(BitbucketCloudEmail.class);
        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudEmailNotFound(exp.getMessage());
        }
        return emailObj;
    }

    /**
     * The method will return list of email objects of the current user
     * Resource /2.0/user/emails/
     * @return list of email objects
     * @throws BitbucketCloudGetUserEmailException
     * @throws BitbucketCloudPageException
     */
    public List<BitbucketCloudEmail> getUserEmails() throws BitbucketCloudGetUserEmailException, BitbucketCloudPageException {
        String URL = BuildURL(new String[]{CONTROLLER, "emails"});
        //        String pageJSON = Get(URL).readEntity(String.class);
        //        List<BitbucketCloudEmail> emails = getPageValues(pageJSON, BitbucketCloudEmail.class);
        List<BitbucketCloudEmail> emails = null;
        try {
            Response response = Get(URL);
            emails = getPaginatedObjects(response, BitbucketCloudEmail.class);
        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudGetUserEmailException(exp.getMessage());
        }
        return emails;
    }

    /**
     * The method return user repositories
     * Resource /2.0/user/permissions/repositories
     * @return list of repositories
     * @throws BitbucketCloudGetRepositoryPermissionsException
     * @throws BitbucketCloudPageException
     */
    public List<BitbucketCloudRepository> getUserRepositories() throws BitbucketCloudGetRepositoryPermissionsException, BitbucketCloudPageException {
        return getUserRepositoriesPermissions(null);
    }

    /**
     * The method return user repositories where user has permission defined by the input parameter
     * Resource /2.0/user/permissions/repositories?q=permission={permission}
     * @param permission - user permissions are defined by the enum {admin, read, write}
     * @return list of repositories
     * @throws BitbucketCloudGetRepositoryPermissionsException
     * @throws BitbucketCloudPageException
     */
    public List<BitbucketCloudRepository> getUserRepositoriesPermissions(final BitbucketCloudPermission permission) throws BitbucketCloudGetRepositoryPermissionsException, BitbucketCloudPageException {
        Map<String, String> queryParam = null;
        if (permission != null) {
            queryParam = new HashMap<String, String>();
            queryParam.put("role", permission.toString());
        }
        String URL = BuildURL(new String[]{CONTROLLER, "permissions", "repositories"});
        List<BitbucketCloudRepositoryPermission> repositoryPermissions = null;
        try {
            Response response = Validate(Get(URL, null, queryParam));
            repositoryPermissions = getPaginatedObjects(response, BitbucketCloudRepositoryPermission.class);

        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudGetRepositoryPermissionsException(exp.getMessage());
        }
        List<BitbucketCloudRepository> repositories = new ArrayList<BitbucketCloudRepository>();
        for (BitbucketCloudRepositoryPermission repositoryPermission : repositoryPermissions) {
            repositories.add(repositoryPermission.getRepository());
        }
        return repositories;
    }

    /**
     * The method return teams the user is belongs to
     * Resource /2.0/user/permissions/teams
     * @return list of team objects
     * @throws BitbucketCloudGetTeamPermissionsException
     * @throws BitbucketCloudPageException
     */
    public List<BitbucketCloudTeam> getUserTeamsPermissions() throws BitbucketCloudGetTeamPermissionsException, BitbucketCloudPageException {
        return getUserTeamsPermissions(null);
    }

    /**
     * The method return teams the user is belongs to and has the role as the input parameter
     * Resource /2.0/user/permissions/teams?q=permission={role}
     * @param role - user roles are defined by the enum {admin, collaborator}
     * @return list of team objects
     * @throws BitbucketCloudGetTeamPermissionsException
     * @throws BitbucketCloudPageException
     */
    public List<BitbucketCloudTeam> getUserTeamsPermissions(final BitbucketCloudRole role) throws BitbucketCloudGetTeamPermissionsException, BitbucketCloudPageException {
        Map<String, String> queryParam = null;
        if (role != null) {
            queryParam = new HashMap<String, String>();
            queryParam.put("role", role.toString());
        }
        String URL = BuildURL(new String[]{CONTROLLER, "permissions", "teams"});
        List<BitbucketCloudTeamPermission> teamPermissions = null;
        try {
            Response response = Validate(Get(URL, null, queryParam));
            teamPermissions = getPaginatedObjects(response, BitbucketCloudTeamPermission.class);
        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudGetTeamPermissionsException(exp.getMessage());
        }
        List<BitbucketCloudTeam> teams = new ArrayList<BitbucketCloudTeam>();
        for (BitbucketCloudTeamPermission teamPermission : teamPermissions) {
            teams.add(teamPermission.getTeam());
        }
        return teams;
    }

}
