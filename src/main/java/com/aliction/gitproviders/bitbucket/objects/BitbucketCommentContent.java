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
public class BitbucketCommentContent {

    private String raw;
    private String markup;
    private String html;
    private String type = "rendered";

    /**
     * 
     * @param raw - String raw comment text
     * @param markup - String markup comment text
     * @param html - String html comment text
     * @param type - String type - default value is "rendered"
     */
    public BitbucketCommentContent(@JsonProperty("raw") final String raw,
                                        @JsonProperty("markup") final String markup,
                                        @JsonProperty("html") final String html,
                                        @JsonProperty("type") final String type) {
        super();
        this.raw = raw;
        this.markup = markup;
        this.html = html;
        this.type = type;
    }

    /**
     * 
     * @return raw comment text
     */
    public String getRaw() {
        return raw;
    }

    /**
     * 
     * @param raw - String raw comment text
     */
    public void setRaw(String raw) {
        this.raw = raw;
    }

    /**
     * 
     * @return markup comment text
     */
    public String getMarkup() {
        return markup;
    }

    /**
     * 
     * @param markup - String markup comment text
     */
    public void setMarkup(String markup) {
        this.markup = markup;
    }

    /**
     * 
     * @return html comment text
     */
    public String getHtml() {
        return html;
    }

    /**
     * 
     * @param html - String html comment text
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * 
     * @return comment type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type - String comment type
     */
    public void setType(String type) {
        this.type = type;
    }

}
