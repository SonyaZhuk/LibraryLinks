package org.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.library.exception.ElasticClientException;
import org.library.exception.ObjectMapperException;
import org.library.model.elastic.UserContent;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

/**
 * The service which contains CRUD operations related to the Content.
 */
@Deprecated
@Slf4j
@Service
@RequiredArgsConstructor
public class UserContentService {
    private final static String CONTENT_INDEX_NAME = "content";
    @NonNull
    private final ObjectMapper mapper;
    @NonNull
    private final RestHighLevelClient client;

    /**
     * Creates new Content.
     *
     * <p>save new Content with unique uuid, otherwise throw validation exception.
     *
     * @param userContent Data Transfer Object for Content.
     * @return Content ID.
     */
    public String createContent(UserContent userContent) {
        final String contentJson = getContentAsString(userContent);

        final String uuid = UUID.randomUUID().toString();
        return indexDocument(uuid, contentJson);
    }

    /**
     * Gets Content details by ID.
     *
     * <p>get Content details as a factory transfer object, otherwise throw NotFound exception.
     *
     * @param uuid - Content ID.
     * @return model transfer object UserContent.
     */
    public UserContent readContent(String uuid) {
        final GetRequest getRequest = new GetRequest(CONTENT_INDEX_NAME, uuid);

        GetResponse response;
        try {
            response = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("Elasticsearch client error while reading {} document from content index", uuid);
            throw new ElasticClientException("Elasticsearch client error while getting " +
                    "the document with id %d : " +  e.getMessage(), uuid);
        }

        if (!response.isExists()) throw new RuntimeException();

        UserContent content = null;
        try {
            content = mapper.readValue(response.getSourceAsString(), UserContent.class);
        } catch (JsonProcessingException e) {
            throw new ObjectMapperException("Error while processing object from %s json.",
                    response.getSourceAsString());
        }
        return content;
    }

    /**
     * Updates Content details by ID.
     *
     * <p>update Content details by ID, otherwise throw Validation exception.
     *
     * @param uuid -  Content ID.
     * @param userContent - Content factory transfer object for update.
     * @return Content ID.
     */
    public String updateContent(String uuid, UserContent userContent) {
        if (!isDocumentExists(uuid)) throw new RuntimeException();

        final String contentJson = getContentAsString(userContent);

        return indexDocument(uuid, contentJson);
    }

    /**
     * Delete content by it's uuid
     *
     * @param uuid content id
     */
    public void deleteContent(String uuid) {
        if (!isDocumentExists(uuid)) throw new RuntimeException();

        final DeleteRequest indexRequest = new DeleteRequest(CONTENT_INDEX_NAME, uuid);
        log.info("Deletion document {} for the content index", uuid);

        try {
            client.delete(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("Elasticsearch client error while deletion document with id {}", uuid);
            throw new ElasticClientException("Elasticsearch client error for document with id %d : " +
                    e.getMessage(), uuid);
        }
    }
    private String getContentAsString(UserContent userContent) {
        String contentJson = null;
        try {
            contentJson = mapper.writeValueAsString(userContent);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new ObjectMapperException("Error while processing data to json from %s object.", userContent);
        }
        return contentJson;
    }
    private String indexDocument(String uuid, String contentJson) {
        final IndexRequest indexRequest = new IndexRequest(CONTENT_INDEX_NAME)
                .id(uuid)
                .source(contentJson, XContentType.JSON);
        log.info("Indexing document {} for the content index {}", uuid, contentJson);

        try {
            return client.index(indexRequest, RequestOptions.DEFAULT).getId();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ElasticClientException("Elasticsearch client error while indexing " +
                    "the document with id %d : " +  e.getMessage(), uuid);
        }
    }
    private boolean isDocumentExists(String uuid) {
        final GetRequest request = new GetRequest(CONTENT_INDEX_NAME, uuid);
        try {
            return client.exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ElasticClientException("Elasticsearch client error for " +
                    "the document with id %d : " +  e.getMessage(), uuid);
        }
    }
}
