package com.aliction.gitproviders.bitbucket.resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliction.gitproviders.bitbucket.objects.BitbucketCloudError;
import com.aliction.gitproviters.bitbucket.client.BitbucketV2API;
import com.aliction.gitproviters.bitbucket.exceptions.BitbucketCloudException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class BaseAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAPI.class);
    private BitbucketV2API bitbucketAPI;

    public BaseAPI(BitbucketV2API bitbucketAPI) {
        this.bitbucketAPI = bitbucketAPI;
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

    protected <T> List getPageValues(String pageJSON, Class T) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> bitbucketObjects = null;
        try {
            JsonNode valuesNode = mapper.readTree(pageJSON).findValue("values");
            String valuesString = valuesNode.toString();
            TypeFactory typeFactory = mapper.getTypeFactory();
            bitbucketObjects =
                    mapper.readValue(valuesString, typeFactory.constructCollectionType(List.class, T));
            LOGGER.info(valuesString.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitbucketObjects;

    }

    protected Response Create(final String URL, final Map<String, Object> urlValues, final Object object) {
        return bitbucketAPI.Create(URL, urlValues, object);
    }

    protected Response Create(final String URL, final Object object) {
        return bitbucketAPI.Create(URL, null, object);
    }

    protected Response Get(final String url, final Map<String, Object> urlValues, final Map<String, String> params) {
        return bitbucketAPI.Get(url, urlValues, params);
    }

    protected Response Get(final String url) {
        return bitbucketAPI.Get(url, null, null);
    }

    protected Response Delete(final String url, final Map<String, Object> urlValues, final Object object) {
        return bitbucketAPI.Delete(url, urlValues, object);
    }

    protected Response Delete(final String url, final Object object) {
        return bitbucketAPI.Delete(url, null, object);
    }

    protected Response Validate(final Response response) throws BitbucketCloudException {
        if (response.getStatusInfo().equals(Response.Status.OK)) {
            return response;
        }
        BitbucketCloudError bitbucketCloudError = response.readEntity(BitbucketCloudError.class);
        throw new BitbucketCloudException(bitbucketCloudError.getError().getMessage());

    }

}
