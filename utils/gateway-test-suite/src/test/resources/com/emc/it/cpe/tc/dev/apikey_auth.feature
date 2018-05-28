@DevEnv
Feature: Apikey Authorization Validation

  Scenario Outline: A apikey based api validation. 
    Given The API <url> and <apikey>
    When I query for upadhs5
    Then I get <httpStatus> status code 

    Examples:
      | url                                                           | apikey                               | httpStatus |
      | https://ssgosgdev.isus.emc.com/dev/cpeldap/api/persons        | l7xxc26a2c0585c94a32a54a0f6e22139765 | 200        | 
      | https://ssgosgdev.isus.emc.com/dev/cpeldap/api/persons        | invalid                              | 401        |
      | https://ssgosgdev02.isus.emc.com:8443/dev/cpeldap/api/persons | l7xxc26a2c0585c94a32a54a0f6e22139765 | 200        | 
      | https://ssgosgdev03.isus.emc.com:8443/dev/cpeldap/api/persons | l7xxc26a2c0585c94a32a54a0f6e22139765 | 200        | 
