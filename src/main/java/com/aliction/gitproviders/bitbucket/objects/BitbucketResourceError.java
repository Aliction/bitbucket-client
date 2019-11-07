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
public class BitbucketResourceError {

    private String message;
    private String detail;

    /**
     * 
     * @param message
     * @param detail
     */
    @JsonCreator
    public BitbucketResourceError(@JsonProperty("message") final String message,
                                       @JsonProperty("detail") final String detail) {
        super();
        this.message = message;
        this.detail = detail;
    }

    /**
     * 
     * @return error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @return error details
     */
    public String getDetail() {
        return detail;
    }

}
