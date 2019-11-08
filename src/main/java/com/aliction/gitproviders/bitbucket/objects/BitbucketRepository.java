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
public class BitbucketRepository extends BitbucketObject {

    @JsonProperty("name")
    private String name;
    private String slug;
    private String full_name;
    private BitbucketOwner owner;
    @JsonProperty("scm")
    private String scm;
    @JsonProperty("project")
    private BitbucketProject project;
    private Boolean is_private;
    private String fork_policy;
    private String language;
    private String main_branch;
    private Boolean has_issues;
    private int size;
    private String description;
    private BitbucketRepositoryLinks links;

    public BitbucketRepository() {
        super(null, null, null);
    };

    /**
     * 
     * @param uuid - String uuid
     * @param created_on - String creation date
     * @param name - String repository name
     * @param slug - String repository slug
     * @param full_name - String repository full name "Owner/Repository name
     * @param owner - BitbucketOwner owner weather a user or a team
     * @param scm - String SCM type Mercurial or GIT
     * @param is_private - Boolean true if the repository is private
     * @param project - BitbucketProject project
     * @param fork_policy - String fork policy
     * @param language - String programming language
     * @param main_branch - String main branch
     * @param has_issues - Boolean true if the repository has issues
     * @param size - Integer repository Size
     * @param description - String repository description
     * @param links - BitbucketRepositoryLinks repository links
     * @param type - BitbucketObjectType type
     */
    @JsonCreator
    public BitbucketRepository(@JsonProperty("uuid") final String uuid,
                                    @JsonProperty("created_on") final String created_on,
                               @JsonProperty("name") final String name,
                               @JsonProperty("slug") final String slug,
                                    @JsonProperty("full_name") final String full_name,
                                    @JsonProperty("owner") final BitbucketOwner owner,
                                    @JsonProperty("scm") final String scm,
                                    @JsonProperty("is_private") final Boolean is_private,
                                    @JsonProperty("project") final BitbucketProject project,
                                    @JsonProperty("fork_policy") final String fork_policy,
                                    @JsonProperty("language") final String language,
                                    @JsonProperty("main_branch") final String main_branch,
                                    @JsonProperty("has_issues") final Boolean has_issues,
                                    @JsonProperty("size") final int size,
                                    @JsonProperty("description") final String description,
                                    @JsonProperty("links") final BitbucketRepositoryLinks links,
                                    @JsonProperty("type") final BitbucketObjectType type) {
        super(uuid, created_on, type);
        this.name = name;
        this.full_name = full_name;
        this.owner = owner;
        this.scm = scm;
        this.is_private = is_private;
        this.project = project;
        this.fork_policy = fork_policy;
        this.language = language;
        this.main_branch = main_branch;
        this.has_issues = has_issues;
        this.size = size;
        this.links = links;
        this.description = description;
    }

    /**
     * A simple constructor to create repository
     * @param slug - String repository slug
     * @param owner - BitbucketOwner owner weather a user or a team
     * @param scm - String SCM type Mercurial or GIT
     * @param is_private - Boolean true if the repository is private
     */
    public BitbucketRepository(@JsonProperty("slug") final String slug,
                                    @JsonProperty("owner") final BitbucketOwner owner,
                                    @JsonProperty("scm") final String scm,
                                    @JsonProperty("is_private") final Boolean is_private) {
        super(null, null, null);
        this.slug = slug;
        this.owner = owner;
        this.scm = scm;
        this.is_private = is_private;
    }

    /**
     * 
     * @return repository name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param reponame - repository name
     */
    public void setName(String reponame) {
        this.name = reponame;
    }

    /**
     * 
     * @return repository slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * 
     * @param slug - String repository slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * 
     * @return - repository owner, user or team object
     */
    public BitbucketOwner getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner - repository owner, user or team object
     */
    public void setOwner(BitbucketOwner owner) {
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
    public BitbucketProject getProject() {
        return project;
    }

    /**
     * 
     * @param project - project object
     */
    public void setProject(BitbucketProject project) {
        this.project = project;
    }

    /**
     * 
     * @return fork policy
     */
    public String getFork_policy() {
        return fork_policy;
    }

    /**
     * 
     * @param fork_policy - String fork_policy
     */
    public void setFork_policy(String fork_policy) {
        this.fork_policy = fork_policy;
    }

    /**
     * 
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language - String programming language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @return mainbranch name
     */
    public String getMain_branch() {
        return main_branch;
    }

    /**
     * 
     * @param main_branch - String main branch
     */
    public void setMain_branch(String main_branch) {
        this.main_branch = main_branch;
    }

    /**
     * 
     * @return true if the repository has issues
     */
    public Boolean getHas_issues() {
        return has_issues;
    }

    /**
     * 
     * @param has_issues - true if the repository has issues
     */
    public void setHas_issues(Boolean has_issues) {
        this.has_issues = has_issues;
    }

    /**
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * 
     * @param size - repository size
     */
    private void setSize(int size) {
        this.size = size;
    }

    /**
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description - repository description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return list of links
     */
    public BitbucketRepositoryLinks getLinks() {
        return links;
    }

    /**
     * 
     * @param links - links attached to this repo
     */
    public void setLinks(BitbucketRepositoryLinks links) {
        this.links = links;
    }

}
