package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudRepository extends BitbucketCloudObject {

    @JsonProperty("name")
    private String reponame;
    private String full_name;
    private BitbucketCloudOwner owner;
    @JsonProperty("scm")
    private String scm;
    @JsonProperty("project")
    private BitbucketCloudProject project;
    private Boolean is_private;

    public BitbucketCloudRepository() {
        super(null, null, null);
    };

    /**
     * 
     * @param uuid
     * @param created_on
     * @param reponame
     * @param full_name
     * @param owner
     * @param scm
     * @param is_private
     * @param project
     * @param type
     */
    @JsonCreator
    public BitbucketCloudRepository(@JsonProperty("uuid") final String uuid,
                                    @JsonProperty("created_on") final String created_on,
                                    @JsonProperty("name") final String reponame,
                                    @JsonProperty("full_name") final String full_name,
                                    @JsonProperty("owner") final BitbucketCloudOwner owner,
                                    @JsonProperty("scm") final String scm,
                                    @JsonProperty("is_private") final Boolean is_private,
                                    @JsonProperty("project") final BitbucketCloudProject project,
                                    @JsonProperty("type") final BitbucketCloudObjectType type) {
        super(uuid, created_on, type);
        this.reponame = reponame;
        this.full_name = full_name;
        this.owner = owner;
        this.scm = scm;
        this.is_private = is_private;
        this.project = project;
    }

    /**
     * A simple constructor to create repository
     * @param reponame
     * @param owner
     * @param scm
     * @param is_private
     */
    public BitbucketCloudRepository(@JsonProperty("name") final String reponame,
                                    @JsonProperty("owner") final BitbucketCloudOwner owner,
                                    @JsonProperty("scm") final String scm,
                                    @JsonProperty("is_private") final Boolean is_private) {
        super(null, null, null);
        this.reponame = reponame;
        this.owner = owner;
        this.scm = scm;
        this.is_private = is_private;
    }

    /**
     * 
     * @return repository name
     */
    public String getReponame() {
        return reponame;
    }

    /**
     * 
     * @param reponame - repository name
     */
    public void setReponame(String reponame) {
        this.reponame = reponame;
    }

    /**
     * 
     * @return - repository owner, user or team object
     */
    public BitbucketCloudOwner getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner - repository owner, user or team object
     */
    public void setOwner(BitbucketCloudOwner owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return - SCM type, "hg" for Mercurial or "git" for GIT
     */
    public String getScm() {
        return scm;
    }

    public void setScm(String scm) {
        this.scm = scm;
    }

    /**
     * 
     * @return - repository full name
     */
    public String getFull_name() {
        return full_name;
    }

    /**
     * 
     * @param full_name - repository fullname "user/repository"
     */
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    /**
     * 
     * @return - true if this repository is private
     */
    public Boolean getIs_private() {
        return is_private;
    }

    /**
     * 
     * @param is_private - true if the repository is private
     */
    public void setIs_private(Boolean is_private) {
        this.is_private = is_private;
    }

    /**
     * 
     * @return project object
     */
    public BitbucketCloudProject getProject() {
        return project;
    }

    /**
     * 
     * @param project - project object
     */
    public void setProject(BitbucketCloudProject project) {
        this.project = project;
    }

}
