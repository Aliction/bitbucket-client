package com.aliction.gitproviders.bitbucket.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.client.BitbucketV2API;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketEmailNotFound;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketGetRepositoryPermissionsException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketGetTeamPermissionsException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketGetUserEmailException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketPageException;
import com.aliction.gitproviders.bitbucket.objects.BitbucketEmail;
import com.aliction.gitproviders.bitbucket.objects.BitbucketPermission;
import com.aliction.gitproviders.bitbucket.objects.BitbucketRepository;
import com.aliction.gitproviders.bitbucket.objects.BitbucketRepositoryPermission;
import com.aliction.gitproviders.bitbucket.objects.BitbucketRole;
import com.aliction.gitproviders.bitbucket.objects.BitbucketTeam;
import com.aliction.gitproviders.bitbucket.objects.BitbucketTeamPermission;
import com.aliction.gitproviders.bitbucket.objects.BitbucketUser;

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
    public BitbucketUser getLoggedUser() {
        String URL = BuildURL(new String[]{CONTROLLER});
        BitbucketUser user = Get(URL).readEntity(BitbucketUser.class);
        return user;
    }

    /**
     * The method will get information about the user's email address
     * Resource /2.0/user/emails/{email}
     * @param email - String email address
     * @return email object
     * @throws BitbucketEmailNotFound
     */
    public BitbucketEmail checkUserEmail(final String email) throws BitbucketEmailNotFound {
        String URL = BuildURL(new String[]{CONTROLLER, "emails", email});
        BitbucketEmail emailObj = null;
        Response response = Get(URL);
        try {
            emailObj = Validate(response).readEntity(BitbucketEmail.class);
        } catch (BitbucketException exp) {
            throw new BitbucketEmailNotFound(exp.getMessage());
        }
        return emailObj;
    }

    /**
     * The method will return list of email objects of the current user
     * Resource /2.0/user/emails/
     * @return list of email objects
     * @throws BitbucketGetUserEmailException
     * @throws BitbucketPageException
     */
    public List<BitbucketEmail> getUserEmails() throws BitbucketGetUserEmailException, BitbucketPageException {
        String URL = BuildURL(new String[]{CONTROLLER, "emails"});
        //        String pageJSON = Get(URL).readEntity(String.class);
        //        List<BitbucketCloudEmail> emails = getPageValues(pageJSON, BitbucketCloudEmail.class);
        List<BitbucketEmail> emails = null;
        try {
            Response response = Get(URL);
            emails = getPaginatedObjects(response, BitbucketEmail.class);
        } catch (BitbucketException exp) {
            throw new BitbucketGetUserEmailException(exp.getMessage());
        }
        return emails;
    }

    /**
     * The method return user repositories
     * Resource /2.0/user/permissions/repositories
     * @return list of repositories
     * @throws BitbucketGetRepositoryPermissionsException
     * @throws BitbucketPageException
     */
    public List<BitbucketRepository> getUserRepositories() throws BitbucketGetRepositoryPermissionsException, BitbucketPageException {
        return getUserRepositoriesPermissions(null);
    }

    /**
     * The method return user repositories where user has permission defined by the input parameter
     * Resource /2.0/user/permissions/repositories?q=permission={permission}
     * @param permission - user permissions are defined by the enum {admin, read, write}
     * @return list of repositories
     * @throws BitbucketGetRepositoryPermissionsException
     * @throws BitbucketPageException
     */
    public List<BitbucketRepository> getUserRepositoriesPermissions(final BitbucketPermission permission) throws BitbucketGetRepositoryPermissionsException, BitbucketPageException {
        Map<String, String> queryParam = null;
        if (permission != null) {
            queryParam = new HashMap<String, String>();
            queryParam.put("role", permission.toString());
        }
        String URL = BuildURL(new String[]{CONTROLLER, "permissions", "repositories"});
        List<BitbucketRepositoryPermission> repositoryPermissions = null;
        try {
            Response response = Validate(Get(URL, null, queryParam));
            repositoryPermissions = getPaginatedObjects(response, BitbucketRepositoryPermission.class);

        } catch (BitbucketException exp) {
            throw new BitbucketGetRepositoryPermissionsException(exp.getMessage());
        }
        List<BitbucketRepository> repositories = new ArrayList<BitbucketRepository>();
        for (BitbucketRepositoryPermission repositoryPermission : repositoryPermissions) {
            repositories.add(repositoryPermission.getRepository());
        }
        return repositories;
    }

    /**
     * The method return teams the user is belongs to
     * Resource /2.0/user/permissions/teams
     * @return list of team objects
     * @throws BitbucketGetTeamPermissionsException
     * @throws BitbucketPageException
     */
    public List<BitbucketTeam> getUserTeamsPermissions() throws BitbucketGetTeamPermissionsException, BitbucketPageException {
        return getUserTeamsPermissions(null);
    }

    /**
     * The method return teams the user is belongs to and has the role as the input parameter
     * Resource /2.0/user/permissions/teams?q=permission={role}
     * @param role - user roles are defined by the enum {admin, collaborator}
     * @return list of team objects
     * @throws BitbucketGetTeamPermissionsException
     * @throws BitbucketPageException
     */
    public List<BitbucketTeam> getUserTeamsPermissions(final BitbucketRole role) throws BitbucketGetTeamPermissionsException, BitbucketPageException {
        Map<String, String> queryParam = null;
        if (role != null) {
            queryParam = new HashMap<String, String>();
            queryParam.put("role", role.toString());
        }
        String URL = BuildURL(new String[]{CONTROLLER, "permissions", "teams"});
        List<BitbucketTeamPermission> teamPermissions = null;
        try {
            Response response = Validate(Get(URL, null, queryParam));
            teamPermissions = getPaginatedObjects(response, BitbucketTeamPermission.class);
        } catch (BitbucketException exp) {
            throw new BitbucketGetTeamPermissionsException(exp.getMessage());
        }
        List<BitbucketTeam> teams = new ArrayList<BitbucketTeam>();
        for (BitbucketTeamPermission teamPermission : teamPermissions) {
            teams.add(teamPermission.getTeam());
        }
        return teams;
    }

}
