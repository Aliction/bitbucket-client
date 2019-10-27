package com.aliction.gitproviders.bitbucket.exceptions;

/**
 * The base exception for BitbucketCloud Client
 * @author Aly Ibrahim
 * Date: Oct 23, 2019
 *
 */
public class BitbucketCloudException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -4701721882480329013L;

    /**
     * 
     * @param errorMsg
     */
    public BitbucketCloudException(String errorMsg) {
        // TODO Auto-generated constructor stub
        super(errorMsg);
    }

}
