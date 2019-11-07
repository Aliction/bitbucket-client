package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aly Ibrahim
 * Date: Oct 23, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCommit extends BitbucketObject {

    private String hash;
    private String message;
    private BitbucketAuthor author;
    private BitbucketRepository repository;

    /**
     * @param uuid - String uuid
     * @param created_on - String creation date
     * @param type - BitbucketObjectType type
     * @param hash - String commit SHA1
     * @param message - String Commit message
     * @param author - BitbucketAuthor commmit author
     * @param repository - BitbucketRepository
     */
    public BitbucketCommit(@JsonProperty("uuid") final String uuid,
                           @JsonProperty("date") final String created_on,
                           @JsonProperty("type") final BitbucketObjectType type,
                           @JsonProperty("hash") final String hash,
                           @JsonProperty("message") final String message,
                           @JsonProperty("author") final BitbucketAuthor author,
                           @JsonProperty("repository") final BitbucketRepository repository) {
        super(uuid, created_on, type);
        this.hash = hash;
        this.message = message;
        this.author = author;
        this.repository = repository;
    }

    /**
     * 
     * @return commit hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * 
     * @return commit message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @return return commit author
     */
    public BitbucketAuthor getAuthor() {
        return author;
    }

    /**
     * 
     * @return repository object
     */
    public BitbucketRepository getRepository() {
        return repository;
    }

}
