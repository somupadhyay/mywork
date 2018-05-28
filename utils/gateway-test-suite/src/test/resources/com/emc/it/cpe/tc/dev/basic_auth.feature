@DevEnv
Feature: Basic Authorization Validation

  Scenario Outline: A basic authentication based api validation. 
    Given The app <url> auth <username> and <password>
    When I Search for ntid upadhs5
    Then I get the <httpStatus> response code 

    Examples:
      | url                                                           | username       | password       | httpStatus |
      | https://ssgosgdev.isus.emc.com/dev/cpeldap/api/persons        | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosgdev.isus.emc.com/dev/cpeldap/api/persons        | apiuser        | apiuser1       | 401        |
      | https://ssgosgdev02.isus.emc.com:8443/dev/cpeldap/api/persons | cpe_l7apiuser  | cpe_l7apiuser  | 200        |
      | https://ssgosgdev03.isus.emc.com:8443/dev/cpeldap/api/persons | cpe_l7apiuser  | cpe_l7apiuser  | 200        |