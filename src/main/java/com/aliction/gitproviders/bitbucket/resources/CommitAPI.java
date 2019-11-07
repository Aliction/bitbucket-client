package com.aliction.gitproviders.bitbucket.resources;

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.client.BitbucketV2API;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketGetCommitCommentException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketGetCommitException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketPageException;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCommit;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCommitComment;

/**
 * The class for the commit API
 * @author Aly Ibrahim
 * Date Nov 7, 2019
 *
 */
public class CommitAPI extends BaseAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryAPI.class);

    private static final String CONTROLLER = "repositories";
    private String URL;

    /**
     * 
     * @param bitbucket - BitbucketV2API object
     */
    public CommitAPI(BitbucketV2API bitbucket) {
        super(bitbucket);
    }

    /**
     * The method is used to get the details for the specified commit Id
     * URL /2.0/repositories/{username}/{repo_slug}/commit/{node}
     * @param username - String user/team name/uuid
     * @param repo_slug - String repository slug or uuid
     * @param sha - String SHA1 commit Id
     * @return commit object
     * @throws BitbucketGetCommitException - Exception for getting commit objects
     */
    public BitbucketCommit getCommitbyId(final String username, final String repo_slug, final String sha) throws BitbucketGetCommitException {
        BitbucketCommit commit = null;
        String URL = BuildURL(new String[]{CONTROLLER, username, repo_slug, "commit", sha});
        try {
            commit = Validate(Get(URL)).readEntity(BitbucketCommit.class);
        } catch (BitbucketException exp) {
            throw new BitbucketGetCommitException(exp.getMessage());
        }
        return commit;
    }

    /**
     * 
     * @param username - String user/team name/uuid
     * @param repo_slug - String repository slug or uuid
     * @param sha - String SHA1 commit Id
     * @return true if the apporval is successful
     * @throws BitbucketException - Base Bitbucket Exception with error showing in the message
     */
    public Boolean approveCommitbyId(final String username, final String repo_slug, final String sha) throws BitbucketException {
        Boolean approved = null;
        String URL = BuildURL(new String[]{CONTROLLER, username, repo_slug, "commit", sha, "approve"});
        approved = Validate(Create(URL, "{}")).getStatus() == 200 ? true : false;
        return approved;
    }

    /**
     * 
     * @param username - String user/team name/uuid
     * @param repo_slug - String repository slug or uuid
     * @param sha - String SHA1 commit Id
     * @return list of comment objects
     * @throws BitbucketPageException - Exception for validating page response
     * @throws BitbucketGetCommitCommentException - Error getting comment objects
     */
    public List<BitbucketCommitComment> getCommitComments(final String username, final String repo_slug, final String sha) throws BitbucketPageException, BitbucketGetCommitCommentException {
        List<BitbucketCommitComment> comments = null;
        String URL = BuildURL(new String[]{CONTROLLER, username, repo_slug, "commit", sha, "comments"});
        Response response = Get(URL);
        try {
            comments = getPaginatedObjects(response, BitbucketCommitComment.class);
        } catch (BitbucketException exp) {
            throw new BitbucketGetCommitCommentException(exp.getMessage());
        }
        return comments;

    }

}
