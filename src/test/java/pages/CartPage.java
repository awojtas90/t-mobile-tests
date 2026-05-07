package pages;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    public void verifyCartPageVisible() {
        $("h1")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Twój koszyk"));
    }

    public void verifyPriceMatches(int expectedPrice) {

        String cartPriceText =
                $("span[data-qa='BKT_Activation']")
                        .shouldBe(visible, Duration.ofSeconds(15))
                        .getText();

        int cartPrice = priceToInt(cartPriceText);

        if (cartPrice != expectedPrice) {
            throw new AssertionError(
                    "Cena w koszyku (" + cartPrice + " zł) nie zgadza się z ceną produktu (" + expectedPrice + " zł)");
        }
    }

    private int priceToInt(String priceText) {
        return Integer.parseInt(
                priceText
                        .replace("zł", "")
                        .replace(" ", "")
                        .trim());
    }

    public void cartPageIsVisible() {

        $("h1")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Twój koszyk"));
    }

    public void verifyDeviceIsInCart(String deviceName) {

        $("body")
                .shouldHave(text(deviceName), Duration.ofSeconds(15));
    }
}