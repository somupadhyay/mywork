@DevEnv
Feature: Basic Authorization Validation

  Scenario Outline: A basic authentication based api validation. 
    Given The app <url> auth <username> and <password>
    When I Search for ntid somnath
    Then I get the <httpStatus> response code 

    Examples:
      | url                                                 | username       | password       | httpStatus |
      | https://somehost.com/dev/cpeldap/api/persons        | apiuser        | apiuser        | 200        | 
      | https://somehost.com/dev/cpeldap/api/persons        | apiuser        | apiuser1       | 401        |
      | https://somehost02.com:8443/dev/cpeldap/api/persons | apiuser        | apiuser        | 200        |
      | https://somehost03.com:8443/dev/cpeldap/api/persons | apiuser        | apiuser        | 200        |