package testScripts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.BasePage;

public class BaseTest {

    protected  WebDriver driver;
    @BeforeClass
    public void launchBroswer(){
        ChromeOptions ops = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        ops.addArguments("--disable-notifications");
        driver = new ChromeDriver(ops);
        driver.get(BasePage.getValue("url"));

    }

   // @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
