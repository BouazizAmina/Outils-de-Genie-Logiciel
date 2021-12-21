Feature: math
  Scenario: determinant
    Given I have this matrix
      | 1 | 0 | 0 |
      | 2 | 3 | 2 |
      | 2 | 1 | 4 |
    When I calculate the determinant
    Then The result must be 10
  Scenario: matrice tranposee
    Given I have this matrix to calculate its transpose
      | 1 | 0 | 0 |
      | 2 | 3 | 2 |
      | 2 | 1 | 4 |
    When I calculate its transpose
    Then The result must be
      | 1 | 2 | 2 |
      | 0 | 3 | 1 |
      | 0 | 2 | 4 |
