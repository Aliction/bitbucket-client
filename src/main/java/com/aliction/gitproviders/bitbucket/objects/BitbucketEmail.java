package com.aliction.gitproviders.bitbucket.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketEmail extends BitbucketObject {

    private String email;
    private Boolean is_primary;
    private Boolean is_confirmed;

    /**
     * 
     * @param uuid
     * @param created_on
     * @param email
     * @param is_primary
     * @param is_confirmed
     * @param type
     */
    public BitbucketEmail(@JsonProperty("uuid") final String uuid,
                               @JsonProperty("created_on") final String created_on,
                               @JsonProperty("email") final String email,
                               @JsonProperty("is_primary") final Boolean is_primary,
                               @JsonProperty("is_confirmed") final Boolean is_confirmed,
                               @JsonProperty("type") final BitbucketObjectType type) {
        super(uuid, created_on, type);

        this.email = email;
        this.is_primary = is_primary;
        this.is_confirmed = is_confirmed;
    }

    /**
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @return true if this email is primary
     */
    public Boolean getIs_primary() {
        return is_primary;
    }

    /**
     * 
     * @return true if this email is confirmed by the user
     */
    public Boolean getIs_confirmed() {
        return is_confirmed;
    }


}
