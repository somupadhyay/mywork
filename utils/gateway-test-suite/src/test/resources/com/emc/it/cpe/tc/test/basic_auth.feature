@TstEnv
Feature: Basic Authorization Validation

  Scenario Outline: A basic authentication based api validation. 
    Given The app <url> auth <username> and <password>
    When I Search for ntid upadhs5
    Then I get the <httpStatus> response code 

    Examples:
      | url                                                              | username       | password       | httpStatus |
      | https://ssgosgtst.isus.emc.com/tst/cpeldap/api/persons           | cpe_l7apiuser  | cpe_l7apiuser  | 200        | 
      | https://ssgosgtst.isus.emc.com/tst/cpeldap/api/persons           | cpe_l7apiuser  | cpe_l7apiuser1 | 401        |
      | https://ssgosgtst02.isus.emc.com:8443/tst/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        |
      | https://ssgosgtst03.isus.emc.com:8443/tst/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        |
      | https://ssgosgtst05.isus.emc.com:8443/tst/cpeldap/api/persons    | cpe_l7apiuser  | cpe_l7apiuser  | 200        |