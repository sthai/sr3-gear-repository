Feature: List personal weapons
  As a Game Master
  I want to list all personal weapons
  In order to check their stats during a game

  Background:
    Given there are 25 personal weapons in the repository
    And the default number of personal weapons returned is 10

  Scenario: Retrieve personal weapons without parameters
    Given I am not logged in
    When I ask for a list of personal weapons
    Then A page of 10 personal weapons is returned

  Scenario Outline: Retrieve personal weapons with range parameters
    Given I am not logged in
    When I ask for a list of personal weapons with a range from <start> to <end>
    Then A page of <numberOfItems> personal weapons is returned

    Examples:
      | start | end | numberOfItems |
      |   0   |  5  |       5       |
      |  10   | 30  |      15       |
      |  25   | 30  |       0       |

