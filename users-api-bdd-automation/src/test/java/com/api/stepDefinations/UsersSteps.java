package com.api.stepDefinations;

import com.api.datagenerator.UserDataGenerator;
import com.api.models.User;
import com.api.utils.ApiUtils;
import com.api.utils.JsonUtils;
import com.api.utils.ValidationUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

import static org.testng.Assert.assertEquals;

public class UsersSteps {

    private static final Logger logger = LoggerFactory.getLogger(UsersSteps.class);
    private Response response;
    private User createdUser;


    @When("I create a valid user")
    public void i_create_a_valid_user() {
        createdUser = UserDataGenerator.getValidUser();
        String payload = JsonUtils.serialize(createdUser);
        response = ApiUtils.postRequest("/users", payload);
        logger.info("Created user response: {}", response.getBody().asString());
    }
    @Then("the user should be created successfully")
    public void the_user_should_be_created_successfully() {
        assertEquals(response.getStatusCode(), 201);
        User userResponse = JsonUtils.deserialize(response.getBody().asString(), User.class);
        ValidationUtils.validateSchema(userResponse);
        logger.info("validated the Created user {}",userResponse);
    }

    @When("I update the user's username to {string}")
    public void i_update_the_user_s_username_to(String newUsername) {
        createdUser=new User();
        createdUser.setUsername(newUsername);
        String payload = JsonUtils.serialize(createdUser);
        response = ApiUtils.putRequest("/users/" + createdUser.getId(), payload);
        logger.info("Updated user response: {}", response.getBody().asString());
    }
    @Then("the username should be updated")
    public void the_username_should_be_updated() {
        assertEquals(response.getStatusCode(), 200);
        User updatedUser = JsonUtils.deserialize(response.getBody().asString(), User.class);
        assertEquals(updatedUser.getUsername(), createdUser.getUsername());
        logger.info("validated the update user with  requests has updated name as {} with coming response has similar name as : {}",createdUser.getUsername(), updatedUser.getUsername());
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint){
        logger.info("Sending GET request to {}", endpoint);
        response = ApiUtils.getRequest(endpoint);
    }

    @Then("the response code should be {int}")
    public void validateResponseCode(int status){
        logger.info("Validating response code {}", status);
        response.then().statusCode(status);
    }


    @Then("the response should contain a list of users with fields {string}, {string}, {string}, {string}")
    public void the_response_should_contain_a_list_of_users_with_fields(String string, String string2, String string3, String string4) {

        String responseBody = response.getBody().asString();
        List<User> users = JsonUtils.deserializeList(responseBody, new TypeReference<List<User>>() {});
        logger.info("✅ Users deserialized successfully: {}", users);
        ValidationUtils.validateSchema(users);

    }

    @Then("the response should contain a user")
    public void the_response_should_contain_a_user() {
        User userResponse = JsonUtils.deserialize(response.getBody().asString(), User.class);
        ValidationUtils.validateSchema(userResponse);
        logger.info("validated response that user contains a user as {}",userResponse.toString());
    }

    @When("I delete the user")
    public void i_delete_the_user() {
        createdUser=new User();
        response = ApiUtils.deleteRequest("/users/" + createdUser.getId());
        logger.info("Delete user response: {}", response.getBody().asString());
    }
    @Then("the user should be deleted")
    public void the_user_should_be_deleted() {
        assertEquals(response.getStatusCode(), 200);
        logger.info("✅ Users successfully gets deleted with status code as {}", response.getStatusCode());
    }

    @When("I create a user with null payload")
    public void i_create_a_user_with_null_payload() {
        // Pass null payload
        String emptyPayload = "{null}";

        // Call POST API
        response = ApiUtils.postRequest("/users", emptyPayload);
        logger.info("Create user with null payload response: {}", response.getBody().asString());
    }
    @Then("the API should return a {int} Bad Request")
    public void the_api_should_return_a_bad_request(Integer int1) {
        // Validate 400 response
        assertEquals(response.getStatusCode(), 400);
        logger.info("Validated negative scenario: API returned 400 for null payload");
    }

    @Then("I should receive a {int} Bad Request response")
    public void i_should_receive_a_bad_request_response(Integer statusCode) {
        assertEquals(response.getStatusCode(), 400);
        logger.info("Validated negative scenario: Bad request Status code for Wrong id  as {}",statusCode);
    }
}
