@PrdB2cEnv
Feature: Basic Authorization Validation

  Scenario Outline: A basic authentication based api validation. 
    Given The app <url> auth <username> and <password>
    When I Search for ntid upadhs5
    Then I get the <httpStatus> response code 

    Examples:
      | url                                                          | username       | password       | httpStatus |
      | https://ssgosgehop.emc.com/prd/b2c/ldap/api/persons          | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosgehop.emc.com/prd/b2c/ldap/api/persons          | cpe_l7apiuser  | cpe_l7apiuser1 | 401        |
