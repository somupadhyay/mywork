@PrdB2bEnv
Feature: Apikey Authorization Validation

  Scenario Outline: A apikey based api validation. 
    Given The API <url> and <apikey>
    When I query for upadhs5
    Then I get <httpStatus> status code 

    Examples:
      | url                                                          | apikey                               | httpStatus |
      | https://ssgosghop.emc.com/prd/b2b/ldap/api/persons           | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosghop.emc.com/prd/b2b/ldap/api/persons           | invalid                              | 401        |
      
