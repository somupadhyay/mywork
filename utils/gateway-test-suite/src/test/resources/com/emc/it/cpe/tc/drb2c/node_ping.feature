@DrB2cEnv
Feature: Node Ping Service

  Scenario Outline: Individual Nodes are reachable
    Given Node <url> and new restTemplate
    When I navigate the path /ssg/ping
    Then I get a <httpStatus> response and contains <content>

    Examples:
      | url                          | httpStatus | content |
      | https://ssgosge.emc.com      | 200        | Gateway |