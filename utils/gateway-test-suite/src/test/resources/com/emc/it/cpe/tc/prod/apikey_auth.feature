@PrdEnv
Feature: Apikey Authorization Validation

  Scenario Outline: A apikey based api validation. 
    Given The API <url> and <apikey>
    When I query for upadhs5
    Then I get <httpStatus> status code 

    Examples:
      | url                                                              | apikey                               | httpStatus |
      | https://ssgosghop.isus.emc.com/prd/cpeldap/api/persons           | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosghop.isus.emc.com/prd/cpeldap/api/persons           | invalid                              | 401        |
      | https://ssgosgprd03.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgprd04.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgprd09.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        |
      | https://ssgosgprd10.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        |
      | https://ssgosgprd11.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        |
      | https://ssgosgprd13.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgdur.isus.emc.com/prd/cpeldap/api/persons           | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgdur.isus.emc.com/prd/cpeldap/api/persons           | invalid                              | 401        |
      | https://ssgosgprd07.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgprd08.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        | 
      | https://ssgosgprd12.isus.emc.com:8443/prd/cpeldap/api/persons    | l7xxa7542d8dda7c4c4ab08771f2c5bf01c8 | 200        |      
