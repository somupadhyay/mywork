@PrdB2cEnv
Feature: Apikey Authorization Validation

  Scenario Outline: A apikey based api validation. 
    Given The API <url> and <apikey>
    When I query for upadhs5
    Then I get <httpStatus> status code 

    Examples:
      | url                                                          | apikey                               | httpStatus |
      | https://ssgosgehop.emc.com/prd/b2c/ldap/api/persons          | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgehop.emc.com/prd/b2c/ldap/api/persons          | invalid                              | 401        |
      
