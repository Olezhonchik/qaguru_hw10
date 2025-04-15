package ru.vkusvillTests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Tests extends TestBase {
    @BeforeEach
    @DisplayName("Предусловия (тест на авторизацию)")
    void authorization() {
        open("https://vkusvill.ru/");
        //Нажатие по кнопке "Войти" на главном экране
        $x("//div[contains(@class, 'UniversMainIcBtn__Text') and normalize-space(text())='Войти']").click();

        //Ввод номера телефона
        $x("//input[@name='USER_PHONE']").setValue("9505511064");
        $x("//button[normalize-space()='Продолжить']").click();

        //Ввод смс-кода
        $x("//input[@name='SMS']").setValue("013600");
        //$x("//button[@class='VV_Button _desktop-lg _tablet-lg _mobile-md _block' and normalize-space(text())='Войти']").click();
        //Переход в личный кабинет
        $x("//div[@class='UniversMainIcBtn__Text btn_text _desktop-md' and normalize-space(text())='Кабинет']").click();

        //Проверка имени в личном кабинете
        $x("//div[@class='VV_PersonalSB20User']")
                .shouldHave(text("M383581"));

    }

    @Test
    @DisplayName("Тест на поиск товаров")
    void searchProducts() {
        $x("//input[@name='q']").setValue("Банан").pressEnter();
        $x("//div[@class='ProductCards__item ProductCards__item--col-lg-1-3']").shouldBe(visible);
    }

    @Test
    @DisplayName("Тест на добавление товара в корзину из поисковой выдачи 'Бананы'")
    void addedProductToCartViaSearch() {
        $x("//input[@name='q']").setValue("Бананы").pressEnter();
        sleep(5000);
        $x("//div[@class='ProductCard__cartButton']").click();
        $x("//a[@title='Корзина']").click();
        executeJavaScript(
                "arguments[0].click();",
                $x("//a[contains(@class, 'js-delivery__basket--clear')]")
        );
        $x("//a[@id='js-lk-modal-confirm-callback']").click();
        sleep(5000);
        $x("//input[@name='q']").setValue("Бананы").pressEnter();
        $x("//div[@class='ProductCard__cartButton']").click();
        $x("//a[@title='Корзина']").click();
        //Проверка на добавление товара
        $x("//span[@class='js-delivery__basket--totals_q']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("1 товар"));
    }
}


