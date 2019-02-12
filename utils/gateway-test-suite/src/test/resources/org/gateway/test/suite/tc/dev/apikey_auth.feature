@DevEnv
Feature: Apikey Authorization Validation

  Scenario Outline: A apikey based api validation. 
    Given The API <url> and <apikey>
    When I query for somnath.upadhyay
    Then I get <httpStatus> status code 

    Examples:
      | url                                                 | apikey                               | httpStatus |
      | https://somehost.com/dev/cpeldap/api/persons        | l7xxc26a2c0585c94a32a54a0f6e22139765 | 200        | 
      | https://somehost.com/dev/cpeldap/api/persons        | invalid                              | 401        |
      | https://somehost.com:8443/dev/cpeldap/api/persons   | l7xxc26a2c0585c94a32a54a0f6e22139765 | 200        |
      | https://somehost.com:8443/dev/cpeldap/api/persons   | l7xxc26a2c0585c94a32a54a0f6e22139765 | 200        |
