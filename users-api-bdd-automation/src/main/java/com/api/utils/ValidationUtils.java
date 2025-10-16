package com.api.utils;

import com.api.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

public class ValidationUtils {

    private static final Logger logger = LoggerFactory.getLogger(ValidationUtils.class);

    /**
     * Validates the entire User schema including nested objects.
     * @param users List of User objects to validate
     */
    public static void validateSchema(List<User> users) {

        logger.info("Validating response schema: {}", users);
        // Validate the list itself
        Assert.assertNotNull(users, "User list is null");
        Assert.assertFalse(users.isEmpty(), "User list is empty");

        for (User user : users) {
            // Core fields
            Assert.assertTrue(user.getId() > 0, "User id should be greater than 0: " + user);
            Assert.assertNotNull(user.getEmail(), "Email is null for user: " + user.getId());
            Assert.assertTrue(user.getEmail().contains("@"), "Invalid email for user: " + user.getId());
            Assert.assertNotNull(user.getUsername(), "Username is null for user: " + user.getId());
            Assert.assertFalse(user.getUsername().isEmpty(), "Username is empty for user: " + user.getId());
            Assert.assertNotNull(user.getPassword(), "Password is null for user: " + user.getId());
            Assert.assertFalse(user.getPassword().isEmpty(), "Password is empty for user: " + user.getId());
            Assert.assertNotNull(user.getPhone(), "Phone is null for user: " + user.getId());
            Assert.assertFalse(user.getPhone().isEmpty(), "Phone is empty for user: " + user.getId());

            // Nested Name object
            Assert.assertNotNull(user.getName(), "Name object is null for user: " + user.getId());
            Assert.assertNotNull(user.getName().getFirstname(), "First name is null for user: " + user.getId());
            Assert.assertFalse(user.getName().getFirstname().isEmpty(), "First name is empty for user: " + user.getId());
            Assert.assertNotNull(user.getName().getLastname(), "Last name is null for user: " + user.getId());
            Assert.assertFalse(user.getName().getLastname().isEmpty(), "Last name is empty for user: " + user.getId());

            // Nested Address object
            Assert.assertNotNull(user.getAddress(), "Address object is null for user: " + user.getId());
            Assert.assertNotNull(user.getAddress().getStreet(), "Street is null for user: " + user.getId());
            Assert.assertFalse(user.getAddress().getStreet().isEmpty(), "Street is empty for user: " + user.getId());
            Assert.assertNotNull(user.getAddress().getCity(), "City is null for user: " + user.getId());
            Assert.assertFalse(user.getAddress().getCity().isEmpty(), "City is empty for user: " + user.getId());
            Assert.assertNotNull(user.getAddress().getZipcode(), "ZipCode is null for user: " + user.getId());
            Assert.assertFalse(user.getAddress().getZipcode().isEmpty(), "ZipCode is empty for user: " + user.getId());
            Assert.assertNotNull(user.getAddress().getGeolocation(), "GeoLocation is null for user: " + user.getId());
        }
    }

    public static  void validateSchema(User user){
        logger.info("Validating response schema: {}", user);
        // Validate the list itself
        Assert.assertNotNull(user, "User list is null");
        // Core fields
        Assert.assertTrue(user.getId() > 0, "User id should be greater than 0: " + user);
    }
}
