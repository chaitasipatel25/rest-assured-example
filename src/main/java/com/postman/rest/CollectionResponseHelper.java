package com.postman.rest;

import io.restassured.response.Response;
import org.junit.Assert;

import java.util.*;

public final class CollectionResponseHelper {

    private final static Set<Integer> VALID_CODES = new HashSet<Integer>(Arrays.asList(200));

    private CollectionResponseHelper() {}

    public static void validateResponseStatus(final Response response) throws Exception {
        ExecutorHelper.validateResponse(VALID_CODES, response);
        Assert.assertNotNull(response);

    }

    public static Map<String, String> extractDataFromCreateResponse(final Response response) {
        Map<String,String> r = response.jsonPath().getJsonObject("collection");
        return r;
    }

}
