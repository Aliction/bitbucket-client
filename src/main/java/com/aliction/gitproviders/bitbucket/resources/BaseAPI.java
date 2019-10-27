package com.aliction.gitproviders.bitbucket.resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.client.BitbucketV2API;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudException;
import com.aliction.gitproviders.bitbucket.exceptions.BitbucketCloudPageException;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudError;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudObject;
import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudPage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * This class is the base abstract class for all the resource APIs
 * @author Aly Ibrahim
 * Date: Oct 23, 2019
 *
 */
public abstract class BaseAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAPI.class);
    private BitbucketV2API bitbucketAPI;

    public BaseAPI(BitbucketV2API bitbucket) {
        this.bitbucketAPI = bitbucket;
    }

    protected String BuildURL(String[] items) {
        StringBuilder url = new StringBuilder();
        try {
            for (String item : items) {
                url.append("/");
                url.append(URLEncoder.encode(item, "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url.toString();
    }

    /**
     * The method to return list of bitbucket objects from response page
     * @param pageJSON - String of JSON page response
     * @param objectClass - Class used to cast objects
     * @return list of Bitbucket objects
     */
    protected <T> List<T> getPageValues(String pageJSON, Class<T> objectClass) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> bitbucketObjects = null;
        try {
            JsonNode valuesNode = mapper.readTree(pageJSON).findValue("values");
            String valuesString = valuesNode.toString();
            TypeFactory typeFactory = mapper.getTypeFactory();
            bitbucketObjects =
                    mapper.readValue(valuesString, typeFactory.constructCollectionType(List.class, objectClass));
            LOGGER.info(valuesString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitbucketObjects;
    }

    /**
     * The method return list of objects extends Bitbucket object from response page
     * @param response - response object
     * @param numberOfPages - int max number of returned pages
     * @param objectClass - Class used to cast objects
     * @return list of Bitbucket objects
     * @throws BitbucketCloudPageException
     * @throws BitbucketCloudException
     */
    protected <T extends BitbucketCloudObject> List<T> getPaginatedObjects(final Response response, final int numberOfPages, Class<T> objectClass) throws BitbucketCloudPageException, BitbucketCloudException {
        List<T> bitbucketObjects = null;
        try {
            Response myresp = Validate(response);
            String pageStr = myresp.readEntity(String.class);
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            BitbucketCloudPage<T> page = mapper.readValue(pageStr, typeFactory.constructParametricType(BitbucketCloudPage.class, objectClass));

            bitbucketObjects = page.getObjects();
        } catch (BitbucketCloudException exp) {
            throw new BitbucketCloudPageException(exp.getMessage());
        } catch (JsonParseException e) {
            throw new BitbucketCloudException("Couldn't map page values to " + objectClass.getSimpleName() + " objects.");
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitbucketObjects;

    }

    /**
     * The method is used to create Bitbucket objects
     * @param url - String resource URL
     * @param urlValues - Map of template values
     * @param object - Bitbucket object that will be serialized as json data
     * @return response object
     */
    protected Response Create(final String url, final Map<String, Object> urlValues, final Object object) {
        return bitbucketAPI.Create(url, urlValues, object);
    }

    /**
     * The method is used to create Bitbucket objects
     * @param url - String resource URL
     * @param object - Bitbucket object that will be serialized as json data
     * @return response object
     */
    protected Response Create(final String url, final Object object) {
        return bitbucketAPI.Create(url, null, object);
    }

    /**
     * The method to get Bitbucket objects
     * @param url - String resource URL
     * @param urlValues - Map of template values
     * @param params - Map of URL parameters such as query and sorting
     * @return response object
     */
    protected Response Get(final String url, final Map<String, Object> urlValues, final Map<String, String> params) {
        return bitbucketAPI.Get(url, urlValues, params);
    }

    /**
     * The method to get Bitbucket objects
     * @param url - String resource URL
     * @return response object
     */
    protected Response Get(final String url) {
        return bitbucketAPI.Get(url, null, null);
    }

    /**
     * The method to delete Bitbucket objects
     * @param url - String resource URL
     * @param urlValues - Map of template values
     * @param object - Bitbucket object that will be serialized as json data
     * @return response object
     */
    protected Response Delete(final String url, final Map<String, Object> urlValues, final Object object) {
        return bitbucketAPI.Delete(url, urlValues, object);
    }

    /**
     * The method to delete Bitbucket objects
     * @param url - String resource URL
     * @param object - Bitbucket object that will be serialized as json data
     * @return response object
     */
    protected Response Delete(final String url, final Object object) {
        return bitbucketAPI.Delete(url, null, object);
    }

    /**
     * The method to validate response object based on the status
     * The method will be removed soon after totally replaced by the following
     * The overloaded response method that will return the casted objects
     * @param response - response object
     * @return response object
     * @throws BitbucketCloudException
     */
    protected Response Validate(final Response response) throws BitbucketCloudException {
        if (response.getStatusInfo().equals(Response.Status.OK)) {
            return response;
        }
        BitbucketCloudError bitbucketCloudError = response.readEntity(BitbucketCloudError.class);
        throw new BitbucketCloudException(bitbucketCloudError.getError().getMessage());

    }

    /**
     * The method is used to validate response object and cast the response to object extends the base object 
     * @param response - response object
     * @param objectClass - object class to be used for casting
     * @return casted Bitbucket object
     * @throws BitbucketCloudException
     */
    protected <T extends BitbucketCloudObject> T Validate(final Response response, final Class<T> objectClass) throws BitbucketCloudException {
        if (response.getStatusInfo().equals(Response.Status.OK)) {
            T object = null;
            try {
                object = response.readEntity(objectClass);
            } catch (ProcessingException e) {
                throw new BitbucketCloudException("Couldn't map page values to " + objectClass.getSimpleName() + " objects.");
            }
            return object;
        }
        BitbucketCloudError bitbucketCloudError = response.readEntity(BitbucketCloudError.class);
        throw new BitbucketCloudException(bitbucketCloudError.getError().getMessage());

    }

}
