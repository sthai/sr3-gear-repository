Feature: List personal weapons
  As a Game Master
  I want to list all personal weapons
  In order to check their stats during a game

  Background:
    Given there are 25 personal weapons in the repository

  Scenario: Retrieve personal weapons without parameters
    Given I am not logged in
    When I ask for a list of personal weapons
    Then A page of 20 personal weapons is returned

  Scenario Outline: Retrieve personal weapons with range parameters
    Given I am not logged in
    When I ask for page <number> of personal weapons with page size equals to <size>
    Then A page of <numberOfItems> personal weapons is returned

    Examples:
      | number | size | numberOfItems |
      |    0   |  15  |      15       |
      |    1   |  15  |      10       |
      |    2   |  15  |       0       |

