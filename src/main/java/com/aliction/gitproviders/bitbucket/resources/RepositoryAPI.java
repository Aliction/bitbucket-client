package com.aliction.gitproviders.bitbucket.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.client.BitbucketV2API;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudCreateRepositoryException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudGetCommitException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudGetRepositoryException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudPageException;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudCommit;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudRepository;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudUser;
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
     * @param bitbucket
     */
    public RepositoryAPI(BitbucketV2API bitbucket) {
        super(bitbucket);
    }

    /**
     * The method will return all the pulic repositories the user can access
     * This method is can be extremely expensive call, and can be interrupted 
     * if number of pages is not set.
     * @return list of repository objects
     * @throws BitbucketCloudPageException
     * @throws BitbucketCloudGetRepositoryException
     */
    public List<BitbucketCloudRepository> getAllPublicRepositories() throws BitbucketCloudPageException, BitbucketCloudGetRepositoryException {
        return this.getAllPublicRepositories(null);
    }

    /**
     * The method is a limited version that will return all the public repositories
     * the user can access created after the provided afterDatetime input parameter 
     * This method is can be extremely expensive call, and can be interrupted 
     * if number of pages is not set.
     * @param afterDatetime - a date object used to limit repositories to the ones created after this date
     * @return list of repository objects
     * @throws BitbucketCloudPageException
     * @throws BitbucketCloudGetRepositoryException
     */
    public List<BitbucketCloudRepository> getAllPublicRepositories(Date afterDatetime) throws BitbucketCloudPageException, BitbucketCloudGetRepositoryException {
        List<BitbucketCloudRepository> publicRepos = null;
        Map<String, String> queryParam = null;
        if (afterDatetime != null) {
            queryParam.put("after", Converters.ConvertDateToISO8601(afterDatetime, null));
        }
        URL = BuildURL(new String[]{CONTROLLER});
        Response response = Get(URL, null, queryParam);
        try {
            response = Validate(response);
            publicRepos = getPaginatedObjects(response, BitbucketCloudRepository.class);
        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudGetRepositoryException(exp.getMessage());
        }
        return publicRepos;

    }

    /**
     * The method returns all the repository belongs to the logged user
     * This API is paginated
     * @param user - user object
     * @return list of repository objects belongs to the user
     * @throws BitbucketCloudGetRepositoryException
     * @throws BitbucketCloudPageException
     */
    public List<BitbucketCloudRepository> getUserRepositories(BitbucketCloudUser user) throws BitbucketCloudGetRepositoryException, BitbucketCloudPageException {
        return this.getUserRepositories(user.getUsername());
    }

    /**
     * The method returns all the repository belongs to the logged user
     * @param user - String username
     * @return list of repository objects
     * @throws BitbucketCloudGetRepositoryException
     * @throws BitbucketCloudPageException
     */
    public List<BitbucketCloudRepository> getUserRepositories(String user) throws BitbucketCloudGetRepositoryException, BitbucketCloudPageException {
        List<BitbucketCloudRepository> userRepos = new ArrayList<BitbucketCloudRepository>();
        URL = BuildURL(new String[]{CONTROLLER, user});
        Response response = Get(URL);
        try {
            userRepos = getPaginatedObjects(response, BitbucketCloudRepository.class);
        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudGetRepositoryException(exp.getMessage());
        }
        return userRepos;

    }

    /**
     * The method is used to create the input repository object
     * @param repository - repository object
     * @return the created repository object
     * @throws BitbucketCloudCreateRepositoryException
     */
    public BitbucketCloudRepository createRepository(final BitbucketCloudRepository repository) throws BitbucketCloudCreateRepositoryException {
        BitbucketCloudRepository createdRepository;
        URL = BuildURL(new String[]{CONTROLLER, repository.getOwner().getUsername(), repository.getReponame()});
        Response response = Create(URL, repository);
        try {
            response = Validate(response);
            createdRepository = response.readEntity(BitbucketCloudRepository.class);
        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudCreateRepositoryException(exp.getMessage());
        }
        return createdRepository;
    }

    /**
     * The method return repository object owned by the input owner name and input repository name 
     * @param owner - String user/team name/uuid
     * @param repoName - String repository name
     * @return repository object
     */
    public BitbucketCloudRepository getRepositoryByName(final String owner, final String repoName) {
        URL = BuildURL(new String[]{CONTROLLER, owner, repoName});
        BitbucketCloudRepository repository = Get(URL).readEntity(BitbucketCloudRepository.class);
        return repository;
    }

    /**
     * The method delete the repository owned by the input owner name and input repository name
     * @param owner - String user/team name/uuid
     * @param repoName - String repository name
     * @return true if the delete operations is successful
     */
    public boolean deleteRepositoryByName(final String owner, final String repoName) {
        return this.deleteRepositoryByName(this.getRepositoryByName(owner, repoName));
    }

    /**
     * The method delete the input repository object
     * @param repository - the repository object to be deleted
     * @return true if the delete operations is successful
     */
    public boolean deleteRepositoryByName(final BitbucketCloudRepository repository) {
        URL = BuildURL(new String[]{CONTROLLER, repository.getOwner().getUuid(), repository.getReponame()});
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
     * @throws BitbucketCloudPageException
     * @throws BitbucketCloudGetCommitException
     */
    public List<BitbucketCloudCommit> getRepositoryCommits(final String owner, final String repoName) throws BitbucketCloudPageException, BitbucketCloudGetCommitException {
        return this.getRepositoryCommitsPerBranch(owner, repoName, null);
    }

    /**
     * The method return list of commits for the input branch in reverse chronological order as output of git log
     * @param owner - String user/team name/uuid
     * @param repoName - String repository name
     * @param branchName - String branch name
     * @return list of commit objects
     * @throws BitbucketCloudPageException
     * @throws BitbucketCloudGetCommitException
     */
    public List<BitbucketCloudCommit> getRepositoryCommitsPerBranch(final String owner, final String repoName, final String branchName) throws BitbucketCloudPageException, BitbucketCloudGetCommitException {
        List<BitbucketCloudCommit> commits = null;
        if (branchName == null) {
            URL = BuildURL(new String[]{CONTROLLER, owner, repoName, "commits"});
        } else {
            URL = BuildURL(new String[]{CONTROLLER, owner, repoName, "commits", branchName});
        }
        Response response = Get(URL);
        try {
            commits = getPaginatedObjects(response, BitbucketCloudCommit.class);
        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudGetCommitException(exp.getMessage());
        }
        return commits;
    }

}
