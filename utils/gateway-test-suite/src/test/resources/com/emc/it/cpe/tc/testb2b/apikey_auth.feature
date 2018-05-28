@TstB2bEnv
Feature: Apikey Authorization Validation

  Scenario Outline: A apikey based api validation. 
    Given The API <url> and <apikey>
    When I query for upadhs5
    Then I get <httpStatus> status code 

    Examples:
      | url                                                          | apikey                               | httpStatus |
      | https://ssgosgtst.emc.com/tst/b2b/ldap/api/persons           | l7xx1965261573044475bdb4994736dee46a | 200        | 
      | https://ssgosgtst.emc.com/tst/b2b/ldap/api/persons           | invalid                              | 401        |
      
