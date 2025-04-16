package ru.vkusvillTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.PageObject;

public class Tests extends TestBase {
    PageObject pageObject = new PageObject();

    @BeforeEach
    @DisplayName("Предусловия (тест на авторизацию)")
    void authorization() {
        pageObject
                .openPage()
                .loginButtonClick()             //Нажатие по кнопке "Войти" на главном экране
                .phoneNumber("9505511064") //Ввод номера телефона
                .smsCode("013600")          //Ввод смс-кода
                //$x("//button[@class='VV_Button _desktop-lg _tablet-lg _mobile-md _block' and normalize-space(text())='Войти']").click();
                .profileButtonClick()               //Переход в личный кабинет
                .checkCardNumber("M383581"); //Проверка имени в личном кабинете
    }

    @DisplayName("Поиск товара")
    @ValueSource(strings = {
            "Банан", "Хлеб"
    })
    @ParameterizedTest(name = "Тест на поиск товаров по поисковому запросу {0}")
    void searchProducts(String searchQuery) {
        pageObject
                .searchQueryInput(searchQuery) //Поиск товара
                .productVisibilityCheck(); //Проверка что в поиске нашелся товар
    }

    @DisplayName("Добавление товара в корзину")
    @ValueSource(strings = {
            "Мыло", "Шоколад"
    })
    @ParameterizedTest(name = "Тест на добавление товара в корзину из поисковой выдачи {0}")
    void addedProductToCartViaSearch(String searchQuery) {
        pageObject
                .searchQueryInput(searchQuery)//Поиск товара
                .sleep(5000)    //Ожидание
                .addedToCart()             //Добавление товара в корзину
                .goToCart()                //Переход в корзину
                .cartClear()               //Очистка корзины
                .sleep(5000)    //Ожидание
                .searchQueryInput(searchQuery)//Поиск товара
                .addedToCart()              //Добавление товара в корзину
                .goToCart()                 //Переход в корзину
                .checkHavingProductInCart(); //Проверка на добавление товара
    }
}