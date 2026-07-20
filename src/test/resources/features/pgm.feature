Feature: SauceDemo Shopping Flow
  This feature tests the complete shopping flow in the SauceDemo website
  including login, adding products, removing one, checkout and returning home.

  Scenario: Verify full purchase flow in SauceDemo
    Given user is on the SauceDemo login page
    When user enters valid username and password
    And clicks on the login button
    Then user should be redirected to the home page
    When user adds three products to the cart
    And user removes one product from the cart
    And user goes to the cart page
    And user proceeds to checkout
    And user clicks the finish button
    Then user clicks on back to home button
    And browser is closed
