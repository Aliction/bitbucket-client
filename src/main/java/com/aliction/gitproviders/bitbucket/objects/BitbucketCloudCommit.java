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
public class BitbucketCloudCommit extends BitbucketCloudObject {

    private String hash;
    private String message;
    private BitbucketCloudAuthor author;
    private BitbucketCloudRepository repository;

    /**
     * @param uuid
     * @param created_on
     * @param type
     * @param hash
     * @param message
     * @param author
     * @param repository
     */
    public BitbucketCloudCommit(@JsonProperty("uuid") String uuid,
                                @JsonProperty("date") String created_on,
                                @JsonProperty("type") BitbucketCloudObjectType type,
                                @JsonProperty("hash") String hash,
                                @JsonProperty("message") String message,
                                @JsonProperty("author") BitbucketCloudAuthor author,
                                @JsonProperty("repository") BitbucketCloudRepository repository) {
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
    public BitbucketCloudAuthor getAuthor() {
        return author;
    }

    /**
     * 
     * @return repository object
     */
    public BitbucketCloudRepository getRepository() {
        return repository;
    }

}
