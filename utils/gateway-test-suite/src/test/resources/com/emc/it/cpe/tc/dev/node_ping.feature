@DevEnv
Feature: Node Ping Service

  Scenario Outline: Individual Nodes are reachable
    Given Node <url> and new restTemplate
    When I navigate the path /ssg/ping
    Then I get a <httpStatus> response and contains <content>

    Examples:
      | url                                  | httpStatus | content |
      | http://ssgosgdev02.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgdev03.isus.emc.com:8081 | 200        | OK      |
      | https://ssgosgdev.isus.emc.com       | 200        | Gateway |