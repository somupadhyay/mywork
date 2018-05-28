@DrEnv
Feature: Apikey Authorization Validation

  Scenario Outline: A apikey based api validation. 
    Given The API <url> and <apikey>
    When I query for upadhs5
    Then I get <httpStatus> status code 

    Examples:
      | url                                                              | apikey                               | httpStatus |
      | https://ssgosgdur.isus.emc.com/prd/cpeldap/api/persons           | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgdur.isus.emc.com/prd/cpeldap/api/persons           | invalid                              | 401        |
      | https://ssgosgprd07.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgprd08.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgprd12.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        |
      | https://ssgosgprd14.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        |
      | https://ssgosgprd15.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        |            
