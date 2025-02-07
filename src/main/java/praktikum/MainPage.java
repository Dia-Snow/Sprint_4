package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    /*Закрыть куки кнопка*/
    private final By cookiesButton = By.xpath(".//button[@id='rcc-confirm-button']");

    /*Кнопка Заказать (верхняя)*/
    private final By bookingTopButton = By.xpath(".//button[@class='Button_Button__ra12g']");

    /*Кнопка Заказать (нижняя)*/
    private final By bookingBotButton = By.xpath(".//div[starts-with(@class, 'Home_RoadMap')]//button[starts-with(@class, 'Button_Button')]");

    /*Вопросы о важном*/
    private final By importantQuestions = By.className("Home_FAQ__3uVm4");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    /*Строка из выпадающего списка по порядковому номеру*/
    public By mainLine(String index){
        return  By.xpath("(.//div[@class='accordion__item'])[" + index + "]");
    }

    /*Строка при открытии выпадающего списка по порядковому номеру*/
    public By inLine(String index){
        return By.xpath("(.//div[@class='accordion__panel'])[" + index + "]");
    }

    /*Закрыть куки*/
    public void clickCookiesButton() {
        driver.findElement(cookiesButton).click();
    }

    /*Открыть строку из списка*/
    public void clickMainLine(By mainLine) {
        driver.findElement(mainLine).click();
    }

    /*Нажать верхнюю кнопку "Заказать"*/
    public void clickBookingTopButton() {
        driver.findElement(bookingTopButton).click();
    }

    /*Нажать нижнюю кнопку "Заказать"*/
    public void clickBookingBotButton() {
        driver.findElement(bookingBotButton).click();
    }

    /*Пролистнуть до вопросов о важном*/
    public void scrollToQuestion() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(importantQuestions));
    }
}