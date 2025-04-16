package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PageObject {

    private SelenideElement
            loginButton = $x("//div[contains(@class, 'UniversMainIcBtn__Text') and normalize-space(text())='Войти']"),
            phoneNumberInput = $x("//input[@name='USER_PHONE']"),
            phoneNumberContinue = $x("//button[normalize-space()='Продолжить']"),
            smsCodeInput = $x("//input[@name='SMS']"),
            profileButton = $x("//div[@class='UniversMainIcBtn__Text btn_text _desktop-md' and normalize-space(text())='Кабинет']"),
            cardNumber = $x("//div[@class='VV_PersonalSB20User']"),
            searchQuery = $x("//input[@name='q']"),
    checkVisibleProductInTheSearch = $x("//div[@class='ProductCards__item ProductCards__item--col-lg-1-3']"),
    inCartButton = $x("//div[@class='ProductCard__cartButton']"),
    goToCartButton = $x("//a[@title='Корзина']"),
    cartClearConfirmButton = $x("//a[@id='js-lk-modal-confirm-callback']"),
checkVisibleProductInCart =  $x("//span[@class='js-delivery__basket--totals_q']");

    public PageObject openPage() {
        open("https://vkusvill.ru/");

        return this;
    }

    public PageObject loginButtonClick() {
        loginButton.click();

        return this;
    }

    public PageObject phoneNumber(String value) {
        phoneNumberInput.setValue(value);
        phoneNumberContinue.click();

        return this;
    }

    public PageObject smsCode(String value) {
        smsCodeInput.setValue(value);

        return this;
    }

    public PageObject profileButtonClick() {
        profileButton.click();

        return this;
    }

    public PageObject checkCardNumber(String value) {
        cardNumber.shouldHave(text(value));

        return this;
    }
    public PageObject searchQueryInput (String value) {
        searchQuery.setValue(value).pressEnter();

        return this;
    }

    public PageObject productVisibilityCheck () {
        checkVisibleProductInTheSearch.shouldBe(visible);

        return this;
    }

    public PageObject addedToCart () {
        inCartButton.click();

        return this;
    }

    public PageObject sleep (long milliseconds) {
        Selenide.sleep(milliseconds);;

        return this;
    }

    public PageObject goToCart () {
        goToCartButton.click();

        return this;
    }

    public PageObject cartClear () {
        executeJavaScript(
                "arguments[0].click();",
                $x("//a[contains(@class, 'js-delivery__basket--clear')]")
        );
        cartClearConfirmButton.click();

        return this;
    }

    public PageObject checkHavingProductInCart () {
       checkVisibleProductInCart
               .shouldBe(Condition.visible)
               .shouldHave(Condition.text("1 товар"));

        return this;
    }
}
