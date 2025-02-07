package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.containsString;

/*Заказ самоката. Нужно проверить весь флоу позитивного сценария с двумя наборами данных.
Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.
Из чего состоит позитивный сценарий:
1. Нажать кнопку «Заказать». На странице две кнопки заказа.
2. Заполнить форму заказа.
3. Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.*/
@RunWith(Parameterized.class)
public class BookingPageTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String indexOfUnderground;
    private final String phone;
    private final String indexOfBookingDate;
    private final String indexOfRentPeriod;
    private final String color;
    private final String comment;

    public BookingPageTest(String name, String surname, String address, String indexOfUnderground, String phone, String indexOfBookingDate, String indexOfRentPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.indexOfUnderground = indexOfUnderground;
        this.phone = phone;
        this.indexOfBookingDate = indexOfBookingDate;
        this.indexOfRentPeriod = indexOfRentPeriod;
        this.color = color;
        this.comment = comment;
    }
    @Parameterized.Parameters
    public static Object[][] getDetails() {
        /* имя, фамилия, адрес, индекс метро, телефон, дата доставки, срок аренды, цвет самоката, комментарий*/
        return new Object[][]{
                {"Владимир","Иванов","улица Степная, д.32, кв. 25","7","79233623993","13","2","серая безысходность","вход через парадную"},
                {"Игорь","Масленников","улица Масленникова, дом 52","5","79274322332","20","4","чёрный жемчуг","спасибо"},
                {"Димка","Журавлев","улица Журавль","3","79133451331","26","7","серая безысходность",""}
        };
    }
    /*Создать драйвер для браузера Chrome*/
    @Before
    public void turnOnBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        /*Переходим на страницу тестового приложения*/
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    /*Оформить заказ через верхнюю кнопку*/
    @Test
    public void checkBookingWithTopButton() {

        MainPage objMainPage = new MainPage(driver);

        /*Закрыть строку про куки*/
        objMainPage.clickCookiesButton();

        /*Нажать верхнюю кнопку Заказать*/
        objMainPage.clickBookingTopButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        BookingPage objBookingPage = new BookingPage(driver);

        /*Ввести данные получателя*/
        objBookingPage.setRecipient(name,surname,address,phone,indexOfUnderground);

        /*Ввести данные по аренде*/
        objBookingPage.setInfoOfRent(indexOfBookingDate, indexOfRentPeriod, comment, color);

        /*Проверить, что заказ оформлен*/
        MatcherAssert.assertThat(driver.findElement(objBookingPage.textOfBooking()).getText(), containsString("Заказ оформлен"));
    }

    /*Оформить заказ через нижнюю кнопку*/
    @Test
    public void checkBookingWithBotButton() {

        MainPage objMainPage = new MainPage(driver);

        /*Закрыть строку про куки*/
        objMainPage.clickCookiesButton();

        /*Нажать нижнюю кнопку Заказать*/
        objMainPage.clickBookingBotButton();

        BookingPage objBookingPage = new BookingPage(driver);

        /*Ввести данные получателя*/
        objBookingPage.setRecipient(name,surname,address,phone,indexOfUnderground);

        /*Ввести данные по аренде*/
        objBookingPage.setInfoOfRent(indexOfBookingDate, indexOfRentPeriod, comment, color);

        /*Проверить, что заказ оформлен*/
        MatcherAssert.assertThat(driver.findElement(objBookingPage.textOfBooking()).getText(), containsString("Заказ оформлен"));
    }

    @After
    public void turnOffBrowser() {
        /*Закрой браузер*/
        driver.quit();
    }
}
