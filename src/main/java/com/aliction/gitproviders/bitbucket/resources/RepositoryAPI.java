package com.aliction.gitproviders.bitbucket.resources;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudRepository;
import com.aliction.gitproviters.bitbucket.client.BitbucketV2API;
import com.aliction.gitproviters.bitbucket.exceptions.BitbucketCloudCreateRepositoryException;
import com.aliction.gitproviters.bitbucket.exceptions.BitbucketCloudException;

public class RepositoryAPI extends BaseAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryAPI.class);

    private static final String CONTROLLER = "repositories";
    private String URL;

    public RepositoryAPI(BitbucketV2API bitbucketAPI) {
        super(bitbucketAPI);
    }

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

    public BitbucketCloudRepository getRepositoryByName(final String owner, final String name) {
        URL = BuildURL(new String[]{CONTROLLER, owner, name});
        //        LOGGER.info(Get(URL).readEntity(String.class));
        BitbucketCloudRepository repository = Get(URL).readEntity(BitbucketCloudRepository.class);
        return repository;
    }

    public boolean deleteRepositoryByName(final String owner, final String name) {
        return false;
    }

    public boolean deleteRepositoryByName(final BitbucketCloudRepository repository) {
        URL = BuildURL(new String[]{CONTROLLER, repository.getOwner().getUuid(), repository.getReponame()});
        if (Delete(URL, repository).getStatus() == 204) {
            return true;
        }
        return false;

    }

}
