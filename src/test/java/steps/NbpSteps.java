package steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.*;

import java.util.List;

public class NbpSteps {

    private List<Rate> rates;
    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("Fetch exchange rates")
    public void fetchExchangeRates() {

        Response response = RestAssured
                .given()
                .header("Accept", "application/json")
                .when()
                .get("http://api.nbp.pl/api/exchangerates/tables/A");

        scenario.log("Status code: " + response.statusCode());

        TableResponse[] tables = response.as(TableResponse[].class);
        rates = tables[0].rates();

        scenario.log("✅ Fetched " + rates.size() + " currency rates");
    }

    @When("Display exchange rate for currency code {string}")
    public void display_exchange_rate_for_currency_code(String code) {

        boolean found = false;

        for (Rate r : rates) {
            if (r.code().equalsIgnoreCase(code)) {
                scenario.log("💰 " + code + " = " + r.mid());
                found = true;
                break;
            }
        }

        if (!found) {
            scenario.log("❌ Currency not found: " + code);
        }
    }

    @When("Display exchange rate for currency name {string}")
    public void display_exchange_rate_for_currency_name(String currency) {

        boolean found = false;

        for (Rate r : rates) {
            if (r.currency().equalsIgnoreCase(currency)) {
                scenario.log("💰 " + currency + " = " + r.mid());
                found = true;
                break;
            }
        }

        if (!found) {
            scenario.log("❌ Currency not found: " + currency);
        }
    }

    @When("Display currencies with rate above {int}")
    public void above(int value) {

        StringBuilder report = new StringBuilder();

        report.append("\n📈 WALUTY > ").append(value).append("\n");
        report.append("----------------------------------------\n");

        for (Rate r : rates) {
            if (r.mid() > value) {
                report.append(r.code())
                        .append(" = ")
                        .append(r.mid())
                        .append("\n");
            }
        }

        scenario.log(report.toString());
    }

    @When("Display currencies with rate below {int}")
    public void below(int value) {

        StringBuilder report = new StringBuilder();

        report.append("\n📉 WALUTY < ").append(value).append("\n");
        report.append("----------------------------------------\n");

        for (Rate r : rates) {
            if (r.mid() < value) {
                report.append(r.code())
                        .append(" = ")
                        .append(r.mid())
                        .append("\n");
            }
        }

        scenario.log(report.toString());
    }
}
