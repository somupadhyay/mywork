@PrdB2bEnv
Feature: Basic Authorization Validation

  Scenario Outline: A basic authentication based api validation. 
    Given The app <url> auth <username> and <password>
    When I Search for ntid upadhs5
    Then I get the <httpStatus> response code 

    Examples:
      | url                                                          | username       | password       | httpStatus |
      | https://ssgosghop.emc.com/prd/b2b/ldap/api/persons           | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosghop.emc.com/prd/b2b/ldap/api/persons           | cpe_l7apiuser  | cpe_l7apiuser1 | 401        |
