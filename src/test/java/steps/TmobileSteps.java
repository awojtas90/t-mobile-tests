package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.ShopPage;

public class TmobileSteps {

    private final HomePage homePage = new HomePage();
    private final ShopPage shopPage = new ShopPage();
    private final ProductPage productPage = new ProductPage();
    private final CartPage cartPage = new CartPage();

    private String currentSlug;
    private int savedProductPrice;


    @Given("the browser is opened")
    public void openBrowser() {
        homePage.open();
    }

    @Given("the user is on T-Mobile homepage")
    public void theUserIsOnHomepage() {
        homePage.verifyHomepageVisible();
    }

    @When("the user opens the Shop menu")
    public void openShopMenu() {
        homePage.openShopMenu();
    }

    @Then("the shop dropdown is visible")
    public void theDropdownIsVisible() {
        homePage.theDropdownIsVisible();
    }

    @When("selects {string} from {string}")
    public void selectNoSubscriptionPhones(String option, String category) {
        shopPage.selectNoSubscriptionPhones();
    }

    @Then("the smartphone list is visible")
    public void smartphoneListIsVisible() {
        productPage.smartphoneListIsVisible();
    }

    @When("the user clicks on device named {string}")
    public void theUserClicksOnDeviceNamed(String deviceName) {

        currentSlug = deviceName
                .toLowerCase()
                .replace(" ", "-")
                .replaceAll("[^a-z0-9-]", "");

        productPage.openDeviceBySlug(currentSlug, deviceName);
    }

    @Then("the product page for device {string} is displayed")
    public void theProductPageForDeviceIsDisplayed(String deviceName) {
        productPage.theProductPageForDeviceIsDisplayed(deviceName);
    }

    @When("adds the device to the cart")

    public void addDeviceToCart() {
        savedProductPrice = productPage.getProductPrice();
        productPage.addToCart();
    }

    @Then("the cart page is displayed")
    public void cartPageDisplayed() {
        cartPage.verifyCartPageVisible();
    }

    @And("the device price in cart matches the product price")
    public void verifyPrice() {
        cartPage.verifyPriceMatches(savedProductPrice);
    }

    @When("the user returns to the T-Mobile homepage")
    public void returnToHomepage() {
        homePage.returnToHomepage();
    }

    @Then("the homepage is visible")
    public void homePageIsVisible() {
        homePage.homePageIsVisible();
    }

    @When("the user opens the cart")
    public void openTheCart() {
        homePage.openTheCart();
    }

    @Then("the cart page is visible")
    public void cartPageIsVisible() {
        cartPage.cartPageIsVisible();
    }

    @And("the cart contains the previously added device named {string}")
    public void theCartContainsThePreviouslyAddedDeviceNamed(String deviceName) {
        cartPage.verifyDeviceIsInCart(deviceName);
    }
}
