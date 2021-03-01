import com.thoughtworks.gauge.*;

import helper.StoreHelper;
import model.ElementInfo;
import helper.ElementHelper;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BaseTest {

    String url = "https://www.gittigidiyor.com/";

    public String fiyat;
    @BeforeSuite
    public void senaryoOncesi() throws InterruptedException {
        System.out.println(convertTurkishChar("Senaryo Başladı"));
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().window().maximize();
        System.out.println(convertTurkishChar("Driver Çalıştı"));
    }

    static WebDriver driver;
    static Actions action;

    @AfterSuite
    public void senaryoSonrasi() {
        driver.quit();
        System.out.println(convertTurkishChar("Senaryo Sonlandı"));
    }

    public WebElement findElement(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    public List<WebElement> findElements(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    public void enterClick(String by){
        findElement(by).sendKeys(Keys.ENTER);
    }

    public  void sendkeysElement(String by,String text){
        findElement(by).sendKeys(text);
    }

    public void hoverElement(String key) {
        Actions action = new Actions(driver);
        action.moveToElement(findElement(key)).build().perform();
    }

    public void assertControl(String assertName, String expectedName){
        Assert.assertEquals(assertName,expectedName);
    }

    public void assertTextControl(String key, String expectedName){
        String assertName = findElement(key).getText();
        System.out.println("======"+convertTurkishChar(assertName)+"======");
        Assert.assertEquals(convertTurkishChar(assertName),convertTurkishChar(expectedName));
    }

    public static String convertTurkishChar(String string) {
        string = string.replace("ç", "c");
        string = string.replace("ö", "o");
        string = string.replace("ş", "s");
        string = string.replace("ğ", "g");
        string = string.replace("ü", "u");
        string = string.replace("ı", "i");
        string = string.replace("Ç", "C");
        string = string.replace("Ö", "O");
        string = string.replace("Ş", "S");
        string = string.replace("Ğ", "G");
        string = string.replace("Ü", "U");
        string = string.replace("İ", "I");
        return string;
    }

    public void listButtonClick(int index, String button, String itemControl){

        List<WebElement> list = findElement(itemControl).findElements(By.tagName("li"));
        for (int i = 0; i < list.size(); i++) {
            if (i==index) {
                System.out.println("listenin boyutu " + list.size());
                System.out.println(list.get(i).getText() +" = ======");
                list.get(i).click();
                System.out.println(button+" = Elemente Tiklandi");
            }
        }
    }
    public  boolean pageControlBase(String button){

        WebElement searcBox = findElement(button);
        String attribute = searcBox.getAttribute("class");

        String select = "current";
        return (attribute.equals(select));
    }

    public void fiatKar(String key){

        String attribute = findElement(key).getText();
        this.fiyat=attribute;
    }








}