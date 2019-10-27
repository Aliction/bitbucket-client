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
public class BitbucketClientRawObject extends BitbucketCloudObject {

    /**
     * 
     * @param type
     */
    public BitbucketClientRawObject(@JsonProperty("type") BitbucketCloudObjectType type) {
        super(null, null, type);
        // TODO Auto-generated constructor stub
    }

}
