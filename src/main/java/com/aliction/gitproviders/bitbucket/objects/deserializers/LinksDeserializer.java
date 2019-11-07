package com.aliction.gitproviders.bitbucket.objects.deserializers;

import java.io.IOException;

import com.aliction.gitproviders.bitbucket.objects.BitbucketRepositoryLinks;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class LinksDeserializer extends StdDeserializer<BitbucketRepositoryLinks> {

    public LinksDeserializer() {
        this(null);

    }

    protected LinksDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BitbucketRepositoryLinks deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        BitbucketRepositoryLinks links = new BitbucketRepositoryLinks();
        JsonNode node = p.getCodec().readTree(p);

        ArrayNode clone = (ArrayNode) node.get("clone");
        if (clone != null) {
            for (int i = 0; i < clone.size(); i++) {
                JsonNode cloneNode = clone.get(i);
                if (cloneNode.get("name").asText().equals("https")) {
                    links.setClone_https(cloneNode.get("href").asText());
                } else if (cloneNode.get("name").asText().equals("ssh")) {
                    links.setClone_ssh(cloneNode.get("href").asText());
                }
            }
        }
        if (node.get("watchers") != null)
            links.setWatchers(node.path("watchers").get("href").asText());
        if (node.get("branches") != null)
            links.setBranches(node.get("branches").get("href").asText());
        if (node.get("tags") != null)
            links.setTags(node.get("tags").get("href").asText());
        if (node.get("commits") != null)
            links.setCommits(node.get("commits").get("href").asText());
        if (node.get("self") != null)
            links.setSelf(node.get("self").get("href").asText());
        if (node.get("source") != null)
            links.setSource(node.get("source").get("href").asText());
        if (node.get("html") != null)
            links.setHtml(node.get("html").get("href").asText());
        if (node.get("avatar") != null)
            links.setAvatar(node.get("avatar").get("href").asText());
        if (node.get("hooks") != null)
            links.setHooks(node.get("hooks").get("href").asText());
        if (node.get("forks") != null)
            links.setForks(node.get("forks").get("href").asText());
        if (node.get("downloads") != null)
            links.setDownloads(node.get("downloads").get("href").asText());
        if (node.get("pullrequests") != null)
            links.setPullrequests(node.get("pullrequests").get("href").asText());
        return links;
    }

}
