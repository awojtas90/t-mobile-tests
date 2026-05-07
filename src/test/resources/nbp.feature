Feature: Exchange rates

  Scenario: Currency exchange rates
    Given Fetch exchange rates
    When Display exchange rate for currency code "USD"
    And Display exchange rate for currency name "US dollar"
    And Display currencies with rate above 5
    And Display currencies with rate below 3
