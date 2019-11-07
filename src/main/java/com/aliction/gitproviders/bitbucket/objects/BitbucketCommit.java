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
     * @param uuid
     * @param created_on
     * @param type
     * @param hash
     * @param message
     * @param author
     * @param repository
     */
    public BitbucketCommit(@JsonProperty("uuid") String uuid,
                                @JsonProperty("date") String created_on,
                                @JsonProperty("type") BitbucketObjectType type,
                                @JsonProperty("hash") String hash,
                                @JsonProperty("message") String message,
                                @JsonProperty("author") BitbucketAuthor author,
                                @JsonProperty("repository") BitbucketRepository repository) {
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
