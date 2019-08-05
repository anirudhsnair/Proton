Feature: Logical Server validation

  Scenario: Verify that all required values are returned on performing API get request
    Given Retrieve API URL
    When GET request is passed to the URL
    Then All required values should be available in the retrieved response

  Scenario: Verify that all servers are working properly
    Given Retrieve API URL
    When GET request is passed to the URL
    Then The servers should work properly

  Scenario: Verify that all servers are having acceptable amount of load
    Given Retrieve API URL
    When GET request is passed to the URL
    Then The servers should have acceptable amount of load

  Scenario: Verify that VPN is functioning properly
    Given Retrieve API URL
    When GET request is passed to the URL
    Then There should be atleast one secure core server, one basic server and one free server
