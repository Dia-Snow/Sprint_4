package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

/*Выпадающий список в разделе «Вопросы о важном». Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.*/
@RunWith(Parameterized.class)
public class MainPageTest {
    private WebDriver driver;
    private final String lineText;
    private final String lineIndex;

    public MainPageTest(String lineText, String lineIndex) {
        this.lineText = lineText;
        this.lineIndex = lineIndex;
    }
    @Parameterized.Parameters
    public static Object[][] getTextOfLine() {
        /* Название строк внутри выпадающего списка, порядковый номер строки*/
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.","1"},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "2"},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "3"},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "4"},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "5"},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "6"},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "7"},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", "8"},
        };
    }
     /*Создать драйвер для браузера*/
    @Before
    public void turnOnBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        /*Переходим на страницу тестового приложения*/
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    /*Проверить, что при открытии выпадающего списка отображается соответствующий текст*/
    @Test
    public void checkList() throws InterruptedException {


        MainPage objMainPage = new MainPage(driver);

        /*Закрыть строку про куки*/
        objMainPage.clickCookiesButton();

        /*Пролистнуть до вопросов о важном*/
        objMainPage.scrollToQuestion();
        Thread.sleep(3000);
        /*Открыть строку из списка*/
        By newMainLine = objMainPage.mainLine(lineIndex);
        objMainPage.clickMainLine(newMainLine);

        /*Проверить, что открывается соответствующий текст*/
        assertEquals("Текст не соответствует", driver.findElement(objMainPage.inLine(lineIndex)).getText(), lineText);
    }
    @After
    public void turnOffBrowser() {
        /* Закрыть браузер*/
        driver.quit();
    }
}