package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    public String getReponame() {
        return reponame;
    }

    public void setReponame(String reponame) {
        this.reponame = reponame;
    }

    public BitbucketCloudOwner getOwner() {
        return owner;
    }

    public void setOwner(BitbucketCloudOwner owner) {
        this.owner = owner;
    }

    public String getScm() {
        return scm;
    }

    public void setScm(String scm) {
        this.scm = scm;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Boolean getIs_private() {
        return is_private;
    }

    public void setIs_private(Boolean is_private) {
        this.is_private = is_private;
    }

    public BitbucketCloudProject getProject() {
        return project;
    }

    public void setProject(BitbucketCloudProject project) {
        this.project = project;
    }

}
