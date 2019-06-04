Feature: Google search
  Verify if google search return links

  Scenario: Single search with return
    Given user is on homepage
    When user search about "BATATA"
    Then has many links displayed

  Scenario: Single search on ESTOU COM SORTE
    Given user is on homepage
    When user search about "BATATA" with ESTOU COM SORTE
    Then opened another site