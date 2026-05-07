package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    public void open() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

        Selenide.open("https://www.t-mobile.pl/");

        $(byText("Akceptuję wszystkie"))
                .shouldBe(visible, Duration.ofSeconds(5))
                .click();

        $(".didomi-popup-backdrop").should(disappear);
    }

    public void verifyHomepageVisible() {
        $("body")
                .shouldBe(visible, Duration.ofSeconds(5));
    }

    public void openShopMenu() {

        $("header[aria-label='Menu główne']")
                .find(withText("Sklep"))
                .shouldBe(visible)
                .click();
    }

    public void theDropdownIsVisible() {

        $(".ODSGlobalHeaderMegaMenu-Container")
                .shouldBe(visible, Duration.ofSeconds(10));
    }

    public void returnToHomepage() {
        $("img[alt='T-Mobile - przejdź na stronę główną']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .scrollIntoView(true)
                .click();

        $("body")
                .shouldBe(visible, Duration.ofSeconds(10));
    }

    public void homePageIsVisible() {
        $("header[aria-label='Menu główne']").shouldBe(visible);

    }

    public void openTheCart() {
        $("button[aria-label='Koszyk']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldBe(enabled)
                .click();
    }
}



