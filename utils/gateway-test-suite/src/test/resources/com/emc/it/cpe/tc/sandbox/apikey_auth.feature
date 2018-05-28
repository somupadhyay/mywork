@SbxEnv
Feature: Apikey Authorization Validation

  Scenario Outline: A apikey based api validation. 
    Given The API <url> and <apikey>
    When I query for upadhs5
    Then I get <httpStatus> status code 

    Examples:
      | url                                                              | apikey                               | httpStatus |
      | https://ssgosgsbx.isus.emc.com/sbx/cpeldap/api/persons           | l7xx6e4ef07c32d048fc965941fdfdd5a249 | 200        | 
      | https://ssgosgsbx.isus.emc.com/sbx/cpeldap/api/persons           | invalid                              | 401        |
      
