package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class BookingPage {
    private final WebDriver driver;

    /*Поле ввода имени*/
    private final By name = By.xpath(".//input[@placeholder='* Имя']");

    /*Поле ввода фамилии*/
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");

    /*Поле ввода адреса*/
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    /*Поле ввода станции метро*/
    private final By underground = By.xpath(".//input[@placeholder='* Станция метро']");

    /*Поле ввода телефона*/
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    /*Кнопка Далее*/
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    /*Поле ввода даты*/
    private final By bookingDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    /*Поле ввода срока аренды*/
    private final By rentPeriod = By.xpath(".//div[@class='Dropdown-control']");

    /*Поле ввода комментария*/
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    /*Кнопка Заказать*/
    private final By bookingButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    /*Кнопка Да*/
    private final By submitButton = By.xpath(".//button[contains(text(),'Да')]");

    /*Заказ оформлен*/
    private final By bookingIsReady = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

    /*Ввести имя*/
    public void setName(String name){
        driver.findElement(this.name).sendKeys(name);
    }

    /*Ввести фамилию*/
    public void setSurname(String surname) {
        driver.findElement(this.surname).sendKeys(surname);
    }

    /*Ввести адрес*/
    public void setAddress(String address) {
        driver.findElement(this.address).sendKeys(address);
    }

    /*Выбрать станцию метро*/
    public void setUnderground(String indexOfUnderground) {
        driver.findElement(this.underground).click();
        driver.findElement(By.xpath(".//button[@value='" + indexOfUnderground + "']//div[1]")).click();
    }

    /*Ввести телефон*/
    public void setPhone(String phone) {
        driver.findElement(this.phone).sendKeys(phone);
    }

    /*Нажать кнопку Далее*/
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    /*Заполнить данные получателя и нажать Далее*/
    public void setRecipient(String name, String surname, String address, String phone, String indexOfUnderground){
        setName(name);
        setSurname(surname);
        setAddress(address);
        setPhone(phone);
        setUnderground(indexOfUnderground);
        clickNextButton();
    }

    /*Ввести дату доставки*/
    public void setBookingDate(String bookingDate) {
        driver.findElement(this.bookingDate).click();
        driver.findElement(By.xpath(".//div[contains(@class,'react-datepicker__day') and text()='"+ bookingDate + "']")).click();
    }

    /*Ввести срок аренды*/
    public void setRentPeriod(String rentPeriod) {
        driver.findElement(this.rentPeriod).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']//div[" + rentPeriod + "]")).click();
    }

   /*Выбрать цвет самоката*/
    public void setColor(String color) {
        driver.findElement(By.xpath(".//label[contains(text(),'" + color + "')]")).click();
    }

    /*Ввести комментарий курьеру*/
    public void setComment(String comment) {
        driver.findElement(this.comment).sendKeys(comment);
    }

    /*Нажать кнопку "Заказать"*/
    public void clickBookingButton() {
        driver.findElement(bookingButton).click();
    }

    /*Нажимаем кнопку "Да"*/
    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    /*Заполнить данные по аренде и нажать кнопку Заказать, после чего нажать Да*/
    public void setInfoOfRent(String bookingDate, String rentPeriod, String comment, String color){
        setBookingDate(bookingDate);
        setRentPeriod(rentPeriod);
        setComment(comment);
        setColor(color);
        clickBookingButton();
        clickSubmitButton();
    }

    /*Вернуть статус оформленного заказа*/
    public By textOfBooking(){
        return bookingIsReady;
    }
}