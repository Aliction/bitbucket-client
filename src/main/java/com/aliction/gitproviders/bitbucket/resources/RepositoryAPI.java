package com.aliction.gitproviders.bitbucket.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.client.BitbucketV2API;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCreateRepositoryException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketGetCommitException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketGetRepositoryException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketPageException;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCommit;
import com.aliction.gitproviders.bitbucket.objects.BitbucketRepository;
import com.aliction.gitproviders.bitbucket.objects.BitbucketUser;
import com.aliction.gitproviders.bitbucket.utils.Converters;

/**
 * The class for the repository resource
 * @author Aly Ibrahim
 * Date: Oct 23, 2019
 *
 */
public class RepositoryAPI extends BaseAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryAPI.class);

    private static final String CONTROLLER = "repositories";
    private String URL;

    /**
     * 
     * @param bitbucket - BitbucketV2API object
     */
    public RepositoryAPI(BitbucketV2API bitbucket) {
        super(bitbucket);
    }

    /**
     * The method will return all the pulic repositories the user can access
     * This method is can be extremely expensive call, and can be interrupted 
     * if number of pages is not set.
     * @return list of repository objects
     * @throws BitbucketPageException - Exception for validating page response
     * @throws BitbucketGetRepositoryException - Exception for getting repositories
     */
    public List<BitbucketRepository> getAllPublicRepositories() throws BitbucketPageException, BitbucketGetRepositoryException {
        return this.getAllPublicRepositories(null);
    }

    /**
     * The method is a limited version that will return all the public repositories
     * the user can access created after the provided afterDatetime input parameter 
     * This method is can be extremely expensive call, and can be interrupted 
     * if number of pages is not set.
     * @param afterDatetime - a date object used to limit repositories to the ones created after this date
     * @return list of repository objects
     * @throws BitbucketPageException - Exception for validating page response
     * @throws BitbucketGetRepositoryException - Exception for getting repositories
     */
    public List<BitbucketRepository> getAllPublicRepositories(Date afterDatetime) throws BitbucketPageException, BitbucketGetRepositoryException {
        List<BitbucketRepository> publicRepos = null;
        Map<String, String> queryParam = null;
        if (afterDatetime != null) {
            queryParam.put("after", Converters.ConvertDateToISO8601(afterDatetime, null));
        }
        URL = BuildURL(new String[]{CONTROLLER});
        Response response = Get(URL, null, queryParam);
        try {
            response = Validate(response);
            publicRepos = getPaginatedObjects(response, BitbucketRepository.class);
        } catch (BitbucketException exp) {
            throw new BitbucketGetRepositoryException(exp.getMessage());
        }
        return publicRepos;

    }

    /**
     * The method returns all the repository belongs to the logged user
     * This API is paginated
     * @param user - user object
     * @return list of repository objects belongs to the user
     * @throws BitbucketGetRepositoryException - Exception for getting repositories
     * @throws BitbucketPageException - Exception for validating page response
     */
    public List<BitbucketRepository> getUserRepositories(BitbucketUser user) throws BitbucketGetRepositoryException, BitbucketPageException {
        return this.getUserRepositories(user.getUsername());
    }

    /**
     * The method returns all the repository belongs to the logged user
     * @param user - String username
     * @return list of repository objects
     * @throws BitbucketGetRepositoryException - Exception for getting repositories
     * @throws BitbucketPageException - Exception for validating page response
     */
    public List<BitbucketRepository> getUserRepositories(String user) throws BitbucketGetRepositoryException, BitbucketPageException {
        List<BitbucketRepository> userRepos = new ArrayList<BitbucketRepository>();
        URL = BuildURL(new String[]{CONTROLLER, user});
        Response response = Get(URL);
        try {
            userRepos = getPaginatedObjects(response, BitbucketRepository.class);
        } catch (BitbucketException exp) {
            throw new BitbucketGetRepositoryException(exp.getMessage());
        }
        return userRepos;

    }

    /**
     * The method is used to create the input repository object
     * @param repository - repository object
     * @return the created repository object
     * @throws BitbucketCreateRepositoryException - Exception for creating repository
     */
    public BitbucketRepository createRepository(final BitbucketRepository repository) throws BitbucketCreateRepositoryException {
        BitbucketRepository createdRepository;
        URL = BuildURL(new String[]{CONTROLLER, repository.getOwner().getUsername(), repository.getSlug()});
        Response response = Create(URL, repository);
        try {
            response = Validate(response);
            createdRepository = response.readEntity(BitbucketRepository.class);
        } catch (BitbucketException exp) {
            throw new BitbucketCreateRepositoryException(exp.getMessage());
        }
        return createdRepository;
    }

    /**
     * The method return repository object owned by the input owner name and input repository name 
     * @param owner - String user/team name/uuid
     * @param repoName - String repository name
     * @return repository object
     * @throws BitbucketException - Base Bitbucket Exception with error showing in the message
     */
    public BitbucketRepository getRepositoryByName(final String owner, final String repoName) throws BitbucketException {
        URL = BuildURL(new String[]{CONTROLLER, owner, repoName});
        BitbucketRepository repository = Validate(Get(URL)).readEntity(BitbucketRepository.class);
        return repository;
    }

    /**
     * The method delete the repository owned by the input owner name and input repository name
     * @param owner - String user/team name/uuid
     * @param repoName - String repository name
     * @return true if the delete operations is successful
     * @throws BitbucketException - Base Bitbucket Exception with error showing in the message 
     */
    public boolean deleteRepositoryByName(final String owner, final String repoName) throws BitbucketException {
        return this.deleteRepositoryByName(this.getRepositoryByName(owner, repoName));
    }

    /**
     * The method delete the input repository object
     * @param repository - the repository object to be deleted
     * @return true if the delete operations is successful
     */
    public boolean deleteRepositoryByName(final BitbucketRepository repository) {
        URL = BuildURL(new String[]{CONTROLLER, repository.getOwner().getUuid(), repository.getName()});
        if (Delete(URL, repository).getStatus() == 204) {
            return true;
        }
        return false;

    }

    /**
     * The method return list of commits in reverse chronological order as output of git log
     * @param owner - String user/team name/uuid
     * @param repoName - String repository name
     * @return list of commit objects
     * @throws BitbucketPageException - Exception for validating page response
     * @throws BitbucketGetCommitException - Exception for getting commit objects
     */
    public List<BitbucketCommit> getRepositoryCommits(final String owner, final String repoName) throws BitbucketPageException, BitbucketGetCommitException {
        return this.getRepositoryCommitsPerBranch(owner, repoName, null);
    }

    /**
     * The method return list of commits for the input branch in reverse chronological order as output of git log
     * @param owner - String user/team name/uuid
     * @param repoName - String repository name
     * @param branchName - String branch name
     * @return list of commit objects
     * @throws BitbucketPageException - Exception for validating page response
     * @throws BitbucketGetCommitException - Exception for getting commit objects
     */
    public List<BitbucketCommit> getRepositoryCommitsPerBranch(final String owner, final String repoName, final String branchName) throws BitbucketPageException, BitbucketGetCommitException {
        List<BitbucketCommit> commits = null;
        if (branchName == null) {
            URL = BuildURL(new String[]{CONTROLLER, owner, repoName, "commits"});
        } else {
            URL = BuildURL(new String[]{CONTROLLER, owner, repoName, "commits", branchName});
        }
        Response response = Get(URL);
        try {
            commits = getPaginatedObjects(response, BitbucketCommit.class);
        } catch (BitbucketException exp) {
            throw new BitbucketGetCommitException(exp.getMessage());
        }
        return commits;
    }

}
