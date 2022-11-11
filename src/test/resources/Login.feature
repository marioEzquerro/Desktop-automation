Feature: Login Test Suite

  @DM-03
    @smoke
  Scenario Outline: Login with correct credentials
    Given the user is on the login page "www.login.com"
    When the user introduces the username "<username>"
    And the user introduces the password "<password>"
    And the user clicks on the login button
    Then the user is logged in
    Examples:
      | username | password |
      | Admin    | password |
