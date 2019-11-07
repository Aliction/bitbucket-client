package com.aliction.gitproviders.bitbucket.objects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to enclose all pageinated responses and it is can be parameterized 
 * with any object extends the base abstract object class 
 * @author Aly Ibrahim
 * Date: Oct 25, 2019
 *
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketPage<T extends BitbucketObject> {

    private int size;
    private int page;
    private int pagelen;
    private String next;
    private String previous;
    private List<T> objects;

    /**
     * 
     * @param size
     * @param page
     * @param pagelen
     * @param next
     * @param previous
     * @param objects
     */
    @JsonCreator
    public BitbucketPage(@JsonProperty("size") final int size,
                              @JsonProperty("page") final int page,
                              @JsonProperty("pagelen") final int pagelen,
                              @JsonProperty("next") final String next,
                              @JsonProperty("previous") final String previous,
                              @JsonProperty("values") final List<T> objects) {
        super();
        this.size = size;
        this.page = page;
        this.pagelen = pagelen;
        this.next = next;
        this.previous = previous;
        this.objects = objects;
    }


    /**
     * 
     * @return response size, total number of objects
     */
    public int getSize() {
        return size;
    }

    /**
     * 
     * @return page number
     */
    public int getPage() {
        return page;
    }

    /**
     * 
     * @return page size, number of objects in the page
     */
    public int getPagelen() {
        return pagelen;
    }

    /**
     * 
     * @return url to the next page if found
     */
    public String getNext() {
        return next;
    }

    /**
     * 
     * @return true if next page is expected
     */
    public Boolean hasNext() {
        return (this.getNext() != null);
    }

    /**
     * 
     * @return url to the previous page if found
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * 
     * @return list of the objects in the page
     */
    public List<T> getObjects() {
        return objects;
    }


}
