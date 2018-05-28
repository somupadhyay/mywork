@PrdEnv
Feature: Node Ping Service

  Scenario Outline: Individual Nodes are reachable
    Given Node <url> and new restTemplate
    When I navigate the path /ssg/ping
    Then I get a <httpStatus> response and contains <content>

    Examples:
      | url                                  | httpStatus | content |
      | http://ssgosgprd03.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd04.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd09.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd10.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd11.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd13.isus.emc.com:8081 | 200        | OK      |
      | https://ssgosghop.isus.emc.com       | 200        | Gateway |
      | http://ssgosgprd07.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd08.isus.emc.com:8081 | 200        | OK      |
      | http://ssgosgprd12.isus.emc.com:8081 | 200        | OK      |
      | https://ssgosgdur.isus.emc.com       | 200        | Gateway |