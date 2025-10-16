package com.api.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Configure mapper once globally
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Deserialize JSON string to a list of given type.
     */
    public static <T> List<T> deserializeList(String json, TypeReference<List<T>> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            logger.error("Error deserializing JSON to List: {}", e.getMessage());
            throw new RuntimeException("Error during JSON deserialization", e);
        }
    }

    /**
     * Deserialize JSON string to a single object.
     */
    public static <T> T deserialize(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error("❌ Error deserializing JSON to Object: {}", e.getMessage());
            throw new RuntimeException("Error during JSON deserialization", e);
        }
    }

    /**
     * Serialize an object to JSON string.
     */
    public static String serialize(Object obj) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("❌ Error serializing object to JSON: {}", e.getMessage());
            throw new RuntimeException("Error during JSON serialization", e);
        }
    }

    /**
     * Parse a JSON string to JsonNode (useful for field validations)
     */
    public static JsonNode parseJson(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            logger.error("❌ Error parsing JSON to JsonNode: {}", e.getMessage());
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}

