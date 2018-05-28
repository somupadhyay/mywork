@PrdEnv
Feature: Basic Authorization Validation

  Scenario Outline: A basic authentication based api validation. 
    Given The app <url> auth <username> and <password>
    When I Search for ntid upadhs5
    Then I get the <httpStatus> response code 

    Examples:
      | url                                                              | username       | password       | httpStatus |
      | https://ssgosghop.isus.emc.com/prd/cpeldap/api/persons           | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosghop.isus.emc.com/prd/cpeldap/api/persons           | cpe_l7apiuser  | cpe_l7apiuser1 | 401        |
      | https://ssgosgprd03.isus.emc.com:8443/prd/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosgprd04.isus.emc.com:8443/prd/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosgprd09.isus.emc.com:8443/prd/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        |
      | https://ssgosgprd10.isus.emc.com:8443/prd/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        |
      | https://ssgosgprd11.isus.emc.com:8443/prd/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        |
      | https://ssgosgprd13.isus.emc.com:8443/prd/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosgdur.isus.emc.com/prd/cpeldap/api/persons           | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosgdur.isus.emc.com/prd/cpeldap/api/persons           | cpe_l7apiuser  | cpe_l7apiuser1 | 401        |
      | https://ssgosgprd07.isus.emc.com:8443/prd/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosgprd08.isus.emc.com:8443/prd/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosgprd12.isus.emc.com:8443/prd/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        |      