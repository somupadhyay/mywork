@DevEnv
Feature: Node Ping Service

  Scenario Outline: Individual Nodes are reachable
    Given Node <url> and new restTemplate
    When I navigate the path /ssg/ping
    Then I get a <httpStatus> response and contains <content>

    Examples:
      | url                        | httpStatus | content |
      | http://somehost02.com:8081 | 200        | OK      |
      | http://somehost03.com:8081 | 200        | OK      |
      | https://somehost.com       | 200        | Gateway |