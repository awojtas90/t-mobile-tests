Feature: Add phone to the cart

  Scenario: Add device to cart and verify it
    Given the browser is opened
    And the user is on T-Mobile homepage

    When the user opens the Shop menu
    Then the shop dropdown is visible

    When selects "No subscription" from "Smartphones"
    Then the smartphone list is visible

    When the user clicks on device named "Xiaomi Redmi Note 15 Pro 5G"
    Then the product page for device "Xiaomi Redmi Note 15 Pro 5G" is displayed

    When adds the device to the cart
    Then the cart page is displayed
    And the device price in cart matches the product price

    When the user returns to the T-Mobile homepage
    Then the homepage is visible

    When the user opens the cart
    Then the cart page is visible
    And the cart contains the previously added device named "Xiaomi Redmi Note 15 Pro 5G"