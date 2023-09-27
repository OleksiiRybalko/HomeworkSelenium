import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class main {
    String Mobile_Service_URL = "https://next.privat24.ua/payments/dashboard";
    String IBAN = "UA333510050000026005325079000";
    String PIB = "Тест";
    String RECIPIENT = "Тест";
    String OKPO = "12345678";
    String USER_CARD_NUMBER = "4552331448138217";
    By fio = By.xpath("//textarea[@data-qa-node='FIO']");
    By recipient = By.xpath("//textarea[@data-qa-node='CUSTOM_COMPANY']");
    By okpo = By.xpath("//textarea[@data-qa-node='CUSTOM_OKPO']");
    By dest = By.xpath("//textarea[@data-qa-node='DEST']");
    By sum = By.xpath("//input[@data-qa-node='SUM']");
    By numberdebitSource = By.xpath("//input[@data-qa-node='numberdebitSource']");
    By expiredebitSource = By.xpath("//input[@data-qa-node='expiredebitSource']");
    By cvvdebitSource = By.xpath("//input[@data-qa-node='cvvdebitSource']");
    By inputIban = By.xpath("//input[@data-qa-node='query']");
    By buttonReq = By.xpath("//button[contains(text(), 'За реквізитами')]");
    By buttonSubmit = By.xpath("//button[@type='submit']");
    By buttonAdd = By.xpath("//button[contains(text(), 'Додати в кошик')]");
    By expDest = By.xpath("//div[@data-qa-node='details']");
    By expCard = By.xpath("//td[@data-qa-node='card']");
    By amount = By.xpath("//div[@data-qa-node='amount']");
    By commission = By.xpath("//span[@data-qa-node='commission']");

    @Test
    public void checkPayment(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(Mobile_Service_URL);
        driver.findElement(inputIban).sendKeys(IBAN);
        driver.findElement(buttonReq).click();
        driver.findElement(fio).sendKeys(PIB);
        driver.findElement(recipient).sendKeys(RECIPIENT);
        driver.findElement(okpo).sendKeys(OKPO);
        driver.findElement(dest).sendKeys("комунальні послуги");
        driver.findElement(sum).sendKeys("1");
        driver.findElement(numberdebitSource).sendKeys(USER_CARD_NUMBER);
        driver.findElement(expiredebitSource).sendKeys("0124");
        driver.findElement(cvvdebitSource).sendKeys("123");
        driver.findElement(buttonSubmit).click();
        driver.findElement(buttonAdd).click();

        Assert.assertEquals("Сплата за комунальні послуги", driver.findElement(expDest).getText());
        Assert.assertEquals("4552 **** **** 8217", driver.findElement(expCard).getText());
        Assert.assertEquals("1 UAH", driver.findElement(amount).getText());
        Assert.assertEquals("3", driver.findElement(commission).getText());


    }
}
