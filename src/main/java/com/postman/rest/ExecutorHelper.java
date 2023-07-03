package com.postman.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.logging.Logger;

public final class ExecutorHelper {
    private static final Logger LOGGER = Logger.getLogger(ExecutorHelper.class.getName());

    final static String CONTENT_TYPE = "application/json";
    private static final String AUTHORIZATION = "x-api-key";
    private static String getAuthorization(final String accessToken) {
        return accessToken;
    }

    private ExecutorHelper() {
    }

    private static RequestSpecification getRequestSpec() {
        final RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader(AUTHORIZATION, getAuthorization("x-api-key"));
        builder.setContentType(CONTENT_TYPE);
        return builder.build();
    }

    public static Response postObjectWithBody(final String targetUrl, final String jsonBody ) throws IOException {
        final RequestSpecification request = getRequestSpec();
        return RestAssured.given()
                .spec(request)
                .body(jsonBody)
                .when()
                .post(targetUrl)
                .then()
                .extract()
                .response();
    }

    public static Response putObjectWithBody(final String targetUrl, final String jsonBody) throws IOException {
        final RequestSpecification request = getRequestSpec();
        return RestAssured.given()
                .spec(request)
                .body(jsonBody)
                .when()
                .put(targetUrl)
                .then()
                .extract()
                .response();
    }

    public static Response getObject(final String targetUrl) {
        final RequestSpecification request = getRequestSpec();
        return RestAssured.given()
                .spec(request)
                .when()
                .get(targetUrl)
                .then()
                .extract()
                .response();
    }

    public static Response deleteObject(final String targetUrl) throws IOException {
        final RequestSpecification request = getRequestSpec();
        return RestAssured.given()
                .spec(request)
                .when()
                .delete(targetUrl)
                .then()
                .extract()
                .response();
    }

    public static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(ExecutorHelper.class.getClassLoader().getResource("singleCollectionPayload.json").getFile())));
    }


    public static void validateResponse(final Set<Integer> validCodes, final Response response) throws Exception {
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        }
        if (!validCodes.contains(response.getStatusCode())) {
            String message = "";
            try {
                message = response.path("message");
            } catch (ClassCastException e) {
                LOGGER.info("No message value found in response json");
            }

            final StringBuilder builder = new StringBuilder();
            builder.append("Error in response (HTTP ")
                    .append(response.getStatusCode())
                    .append("): ")
                    .append(message);

            final String result = builder.toString();
            LOGGER.severe(result);
            throw new Exception(result);
        }
    }
}
