@TstEnv
Feature: Apikey Authorization Validation

  Scenario Outline: A apikey based api validation. 
    Given The API <url> and <apikey>
    When I query for upadhs5
    Then I get <httpStatus> status code 

    Examples:
      | url                                                              | apikey                               | httpStatus |
      | https://ssgosgtst.isus.emc.com/tst/cpeldap/api/persons           | l7xx1965261573044475bdb4994736dee46a | 200        | 
      | https://ssgosgtst.isus.emc.com/tst/cpeldap/api/persons           | invalid                              | 401        |
      | https://ssgosgtst02.isus.emc.com:8443/tst/cpeldap/api/persons    | l7xx1965261573044475bdb4994736dee46a | 200        | 
      | https://ssgosgtst03.isus.emc.com:8443/tst/cpeldap/api/persons    | l7xx1965261573044475bdb4994736dee46a | 200        | 
      | https://ssgosgtst05.isus.emc.com:8443/tst/cpeldap/api/persons    | l7xx1965261573044475bdb4994736dee46a | 200        | 
