@TstEnv
Feature: Node Ping Service

  Scenario Outline: Individual Nodes are reachable
    Given Node <url> and new restTemplate
    When I navigate the path /ssg/ping
    Then I get a <httpStatus> response and contains <content>

    Examples:
      | url                                  | httpStatus | content |
      | http://ssgosgtst02.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgtst03.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgtst05.isus.emc.com:8081 | 200        | OK      |
      | https://ssgosgtst.isus.emc.com       | 200        | Gateway |