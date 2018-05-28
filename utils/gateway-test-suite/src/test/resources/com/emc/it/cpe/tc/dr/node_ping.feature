@DrEnv
Feature: Node Ping Service

  Scenario Outline: Individual Nodes are reachable
    Given Node <url> and new restTemplate
    When I navigate the path /ssg/ping
    Then I get a <httpStatus> response and contains <content>

    Examples:
      | url                                  | httpStatus | content |
      | http://ssgosgprd08.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd07.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd12.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd14.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd15.isus.emc.com:8081 | 200        | OK      |            
      | https://ssgosgdur.isus.emc.com       | 200        | Gateway |