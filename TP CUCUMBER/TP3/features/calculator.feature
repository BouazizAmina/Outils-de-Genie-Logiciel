Feature: calculator
  Scenario Outline: calculator
    Given I have a calculator
    When I add <num1> and <num2>
    Then The result must be <results>
    Examples:
      |num1|num2|results|
      |12  |5   |17     |
      |20  |5   |25     |
  Scenario: Get number of books by author
    Given I have the following books in the store
      | title | author |
      | The Devil in the White City | Erik Larson |
      | The Lion, the Witch and the Wardrobe | C.S. Lewis |
      | In the Garden of Beasts | Erik Larson |
    When I search for books by author "Erik Larson"
    Then I find 2 books
