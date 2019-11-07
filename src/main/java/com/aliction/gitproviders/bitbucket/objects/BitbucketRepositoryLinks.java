package com.aliction.gitproviders.bitbucket.objects;

import com.aliction.gitproviders.bitbucket.objects.deserializers.LinksDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 
 * @author Aly Ibrahim
 * Date Nov 6, 2019
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = LinksDeserializer.class)
public class BitbucketRepositoryLinks {

    String watchers;
    String branches;
    String tags;
    String commits;
    String clone_https;
    String clone_ssh;
    String self;
    String source;
    String html;
    String avatar;
    String hooks;
    String forks;
    String downloads;
    String pullrequests;

    public BitbucketRepositoryLinks(final String watchers, final String branches, final String tags, final String commits, final String clone_https,
                                    final String clone_ssh, final String self, final String source, final String html, final String avatar, final String hooks,
                                    final String forks, final String downloads, final String pullrequests) {
        super();
        this.watchers = watchers;
        this.branches = branches;
        this.tags = tags;
        this.commits = commits;
        this.clone_https = clone_https;
        this.clone_ssh = clone_ssh;
        this.self = self;
        this.source = source;
        this.html = html;
        this.avatar = avatar;
        this.hooks = hooks;
        this.forks = forks;
        this.downloads = downloads;
        this.pullrequests = pullrequests;
    }

    public BitbucketRepositoryLinks() {}

    /**
     * 
     * @return watchers url
     */
    public String getWatchers() {
        return watchers;
    }

    /**
     * 
     * @param watchers - String url
     */
    public void setWatchers(String watchers) {
        this.watchers = watchers;
    }

    /**
     * 
     * @return branches url
     */
    public String getBranches() {
        return branches;
    }

    /**
     * 
     * @param branches - String url
     */
    public void setBranches(String branches) {
        this.branches = branches;
    }

    /**
     * 
     * @return tags url
     */
    public String getTags() {
        return tags;
    }

    /**
     * 
     * @param tags - String url
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return commits url
     */
    public String getCommits() {
        return commits;
    }

    /**
     * 
     * @param commits - String url
     */
    public void setCommits(String commits) {
        this.commits = commits;
    }

    /**
     * 
     * @return clone https url
     */
    public String getClone_https() {
        return clone_https;
    }

    /**
     * 
     * @param clone_https - String url
     */
    public void setClone_https(String clone_https) {
        this.clone_https = clone_https;

    }

    /**
     * 
     * @return clone ssh url
     */
    public String getClone_ssh() {
        return clone_ssh;
    }

    /**
     * 
     * @param clone_ssh - String url
     */
    public void setClone_ssh(String clone_ssh) {
        this.clone_ssh = clone_ssh;
    }

    /**
     * 
     * @return self url
     */
    public String getSelf() {
        return self;
    }

    /**
     * 
     * @param self - String url
     */
    public void setSelf(String self) {
        this.self = self;
    }

    /**
     * 
     * @return source url
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source - String url
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return html url
     */
    public String getHtml() {
        return html;
    }

    /**
     * 
     * @param html - String url
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * 
     * @return avatar url
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 
     * @param avatar - String url
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     * @return hooks url
     */
    public String getHooks() {
        return hooks;
    }

    /**
     * 
     * @param hooks - String url
     */
    public void setHooks(String hooks) {
        this.hooks = hooks;
    }

    /**
     * 
     * @return forks url
     */
    public String getForks() {
        return forks;
    }

    /**
     * 
     * @param forks - String url
     */
    public void setForks(String forks) {
        this.forks = forks;
    }

    /**
     * 
     * @return downloads url
     */
    public String getDownloads() {
        return downloads;
    }

    /**
     * 
     * @param downloads - String url
     */
    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    /**
     * 
     * @return pull requests url
     */
    public String getPullrequests() {
        return pullrequests;
    }

    /**
     * 
     * @param pullrequests - String url
     */
    public void setPullrequests(String pullrequests) {
        this.pullrequests = pullrequests;
    }

}
