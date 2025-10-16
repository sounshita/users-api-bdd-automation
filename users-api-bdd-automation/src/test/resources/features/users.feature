Feature: Users API CRUD Validations

  Scenario: Create a valid user
    When I create a valid user
    Then the user should be created successfully

  Scenario: Update the created user
    When I update the user's username to "john_updated"
    Then the username should be updated

  Scenario: Get all users
    When I send a GET request to "/users"
    Then the response code should be 200
    And  the response should contain a list of users with fields "id", "username", "email", "name"

  Scenario: Get a single user
    When I send a GET request to "/users/1"
    Then the response code should be 200
    And  the response should contain a user

  Scenario: Delete the created user
    When I delete the user
    Then the user should be deleted

  Scenario: Create a user with null payload
    When I create a user with null payload
    Then the API should return a 400 Bad Request

  Scenario: Get user with invalid ID (character)
    When I send a GET request to "/users/abc"
    Then I should receive a 400 Bad Request response

