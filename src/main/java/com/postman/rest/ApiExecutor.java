package com.postman.rest;

import io.restassured.response.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.Map;

public class ApiExecutor {

    private static final Logger LOGGER = Logger.getLogger(ApiExecutor.class.getName());
    private static final String POSTMAN_API = "https://api.getpostman.com";

    public ApiExecutor() { }

    public static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(ExecutorHelper.class.getClassLoader().getResource("singleCollectionPayload.json").getFile())));
    }

    public String postSingleCollection() throws Exception{
        Map<String, String> myMap;
        String jsonBody = generateStringFromResource("resources/singleCollectionPayload.json");

        final Response response = ExecutorHelper.postObjectWithBody(POSTMAN_API + "/" + "collections", jsonBody);
        LOGGER.info("Creating brand new collection");
        CollectionResponseHelper.validateResponseStatus(response);

        myMap = CollectionResponseHelper.extractDataFromCreateResponse(response);
        return  myMap.get("uid");
    }

    public void putSingleCollection(final String createdUid) throws Exception{
        String jsonBody = generateStringFromResource("resources/singleCollectionPayload.json");
        final Response response = ExecutorHelper.putObjectWithBody(POSTMAN_API + "/" +"collections"+ "/" +createdUid, jsonBody);
        LOGGER.info("Updating uid: " + createdUid);
        CollectionResponseHelper.validateResponseStatus(response);
    }

    public void getSingleCollection(final String createdUid) throws Exception{
        final Response response = ExecutorHelper.getObject(POSTMAN_API + "/"  +"collections"+ "/" + createdUid);
        LOGGER.info("Getting single collection for uid: " + createdUid);
        CollectionResponseHelper.validateResponseStatus(response);
    }

    public void getAllCollections() throws Exception{
        final Response response = ExecutorHelper.getObject((POSTMAN_API+ "/" + "collections"));
        LOGGER.info("Validating all Collections");
        CollectionResponseHelper.validateResponseStatus(response);
    }

    public void deleteSingleCollection(final String createdUid) throws Exception{
        final Response response = ExecutorHelper.deleteObject(POSTMAN_API + "/"  +"collections"+ "/" + createdUid);
        LOGGER.info("Deleting single collection for uid: " + createdUid);
        CollectionResponseHelper.validateResponseStatus(response);
    }

    public void getAllEnvironments() throws Exception{
        final Response response = ExecutorHelper.getObject((POSTMAN_API + "/" + "environments"));
        LOGGER.info("Validating all environments");
        CollectionResponseHelper.validateResponseStatus(response);
    }

    public void getAllMocks() throws Exception{
        final Response response = ExecutorHelper.getObject((POSTMAN_API + "/" + "mocks"));
        LOGGER.info("Validating all environments");
        CollectionResponseHelper.validateResponseStatus(response);
    }

    public void getAllMonitors() throws Exception{
        final Response response = ExecutorHelper.getObject((POSTMAN_API + "/" + "monitors"));
        LOGGER.info("Validating all environments");
        CollectionResponseHelper.validateResponseStatus(response);
    }

    public void getAllWorkspaces() throws Exception{
        final Response response = ExecutorHelper.getObject((POSTMAN_API + "/" + "workspaces"));
        LOGGER.info("Validating all environments");
        CollectionResponseHelper.validateResponseStatus(response);
    }

    public void getAllUsers() throws Exception{
        final Response response = ExecutorHelper.getObject((POSTMAN_API + "/" + "me"));
        LOGGER.info("Validating all environments");
        CollectionResponseHelper.validateResponseStatus(response);
    }
}
