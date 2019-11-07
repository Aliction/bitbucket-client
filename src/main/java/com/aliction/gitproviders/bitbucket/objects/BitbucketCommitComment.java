package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aly Ibrahim
 * @date Nov 7, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCommitComment extends BitbucketObject {

    private BitbucketOwner user;
    private BitbucketCommentContent content;
    private String updated_on;
    private int id;
    private Boolean deleted;

    /**
     * 
     * @param uuid
     * @param created_on
     * @param type
     * @param user
     * @param content
     * @param updated_on
     * @param id
     * @param deleted
     */
    public BitbucketCommitComment(@JsonProperty("uuid") final String uuid,
                                  @JsonProperty("created_on") final String created_on,
                                  @JsonProperty("type") final BitbucketObjectType type,
                                  @JsonProperty("user") final BitbucketOwner user,
                                  @JsonProperty("content") final BitbucketCommentContent content,
                                  @JsonProperty("updated_on") final String updated_on,
                                  @JsonProperty("id") final int id,
                                  @JsonProperty("deleted") final Boolean deleted) {
        super(uuid, created_on, type);
        this.user = user;
        this.content = content;
        this.updated_on = updated_on;
        this.id = id;
        this.deleted = deleted;
    }

    public BitbucketCommitComment(String uuid, String created_on, BitbucketObjectType type) {
        super(uuid, created_on, type);
    }

    /**
     * 
     * @return user object
     */
    public BitbucketOwner getUser() {
        return user;
    }

    /**
     * 
     * @return commit content object
     */
    public BitbucketCommentContent getContent() {
        return content;
    }

    /**
     * 
     * @return updated_on time
     */
    public String getUpdated_on() {
        return updated_on;
    }

    /**
     * 
     * @return comment id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return true if the comment is deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

}
