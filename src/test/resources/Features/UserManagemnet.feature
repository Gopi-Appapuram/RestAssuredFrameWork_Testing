Feature: User Management

    Scenario: Create a new user
        Given I have users Apis
        When I have user data
        And I create a new user
        Then the user should be created successfully

    Scenario: Get all users
        Given I have users Apis
        When I Get all the users