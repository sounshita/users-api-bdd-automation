package com.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

public class ApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(ApiUtils.class);

    public static Response getRequest(String endpoint) {
        String requestId = UUID.randomUUID().toString().substring(0,8);
        MDC.put("requestId", requestId);
        logger.info("➡️ [GET] {} | RequestId: {}", endpoint, requestId);

        Response response = RestAssured.given().get(endpoint);

        int status = response.getStatusCode();
        if(status >= 400) logger.error("⬅️ [{}] [Response {}] Status: {}", requestId, endpoint, status);
        else logger.info("⬅️ [{}] [Response {}] Status: {}", requestId, endpoint, status);

        MDC.remove("requestId");
        return response;
    }

    public static Response postRequest(String endpoint, String payload) {
        String requestId = UUID.randomUUID().toString().substring(0,8);
        MDC.put("requestId", requestId);
        logger.info("➡️ [POST] {} | RequestId: {}", endpoint, requestId);

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post(endpoint);

        int status = response.getStatusCode();
        if(status >= 400) logger.error("⬅️ [{}] [Response {}] Status: {}", requestId, endpoint, status);
        else logger.info("⬅️ [{}] [Response {}] Status: {}", requestId, endpoint, status);

        MDC.remove("requestId");
        return response;
    }

    public static Response putRequest(String endpoint, String payload) {
        // Generate unique requestId for logging
        String requestId = UUID.randomUUID().toString().substring(0, 8);
        MDC.put("requestId", requestId);

        // Log request start
        logger.info("➡️ [PUT] {} | RequestId: {}", endpoint, requestId);

        // Send PUT request with JSON payload
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload)
                .put(endpoint);

        // Log response status
        int status = response.getStatusCode();
        if (status >= 400) {
            logger.error("⬅️ [{}] [Response {}] Status: {}", requestId, endpoint, status);
        } else {
            logger.info("⬅️ [{}] [Response {}] Status: {}", requestId, endpoint, status);
        }

        // Clear MDC
        MDC.remove("requestId");

        return response;
    }

    public static Response deleteRequest(String endpoint) {
        // Generate unique requestId for logging
        String requestId = UUID.randomUUID().toString().substring(0, 8);
        MDC.put("requestId", requestId);

        // Log request start
        logger.info("➡️ [DELETE] {} | RequestId: {}", endpoint, requestId);

        // Send DELETE request
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .delete(endpoint);

        // Log response status
        int status = response.getStatusCode();
        if (status >= 400) {
            logger.error("⬅️ [{}] [Response {}] Status: {}", requestId, endpoint, status);
        } else {
            logger.info("⬅️ [{}] [Response {}] Status: {}", requestId, endpoint, status);
        }

        // Clear MDC
        MDC.remove("requestId");

        return response;
    }


}
