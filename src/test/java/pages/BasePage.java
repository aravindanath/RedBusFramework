package pages;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.ini4j.Ini;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

public class BasePage {

    protected static WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }


    /**
     * @author: Arvind
     * @param key
     * @return
     */

    public static String getValue(String key){
        String path = System.getProperty("user.dir")+ File.separator+"config.properties";
        String value = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            Properties prop = new Properties();
            prop.load(fis);
            value = prop.getProperty(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }


    public  static int getTodaysDate(){
        Date date = new Date();
        int today = date.getDate();
        return today;
    }

    public static void genericWait(long sleep){
        try {
            Thread.sleep(sleep);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void click(WebElement element){

        Actions act = new Actions(driver);
        act.click(element).build().perform();


    }


    public static void scroll(WebDriver driver, WebElement element){

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);

    }

    /**
     *
     * @param header
     * @param key
     * @return
     * @throws IOException
     */

    public static String getValue(String header, String key) throws IOException {
        String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "data.ini";
        FileInputStream fis = new FileInputStream(path);
        Ini i = new Ini();
        i.load(fis);
        String val = i.get(header,key);
        return val;
    }


    public static void assertWebElement(WebElement element){
        Assert.assertTrue(element.isDisplayed(),"Element is not found!");
    }

    public static void assertElementTitle(WebElement element,String excepted){
        Assert.assertEquals(element.getText(),excepted,"Title mis match!");
        Reporter.log("Title name is "+ element.getText());
        System.out.println("Title name is "+ element.getText());
    }

    public static void assertTitle(String actual, String expected) {
        Assert.assertEquals(actual, expected, "Title Mismatch");
    }

    public static void verifyButton(WebElement actual) {
        Assert.assertTrue(actual.isDisplayed(), "Button is not displayed");
    }

    public static void verifyButtonEnabled(WebElement actual) {
        Assert.assertTrue(actual.isEnabled(), "Button is not Enabled");
    }


    public static void sfassertTitle(String actual, String expected) {
        SoftAssert sf= new SoftAssert();
        sf.assertEquals(actual, expected,"Title mis match");
        sf.assertAll();
    }


    public static void acceptAlert(WebDriver driver){
        Alert alt = driver.switchTo().alert();
        System.out.println("Alert title: "+ alt.getText());
        alt.accept();
    }

    public static void dismissAlert(WebDriver driver){
        Alert alt = driver.switchTo().alert();
        System.out.println("Alert title: "+ alt.getText());
        alt.dismiss();
    }


    public static void acceptAlertAndSendValue(WebDriver driver,String value){
        Alert alt = driver.switchTo().alert();
        System.out.println("Alert title: "+ alt.getText());
        alt.sendKeys(value);
        alt.accept();
    }


    public static void mouseHover(WebDriver driver, WebElement target){
        Actions act = new Actions(driver);
        act.moveToElement(target).build().perform();
    }
    public static void mouseHoverAndClick(WebDriver driver, WebElement target){
        Actions act = new Actions(driver);
        act.moveToElement(target).build().perform();
        act.click(target).perform();
    }


    public static void actionsClick(WebDriver driver, WebElement target){
        Actions act = new Actions(driver);
        act.click(target).build().perform();
    }

    public static void dragAndDrop(WebDriver driver, WebElement src, WebElement tgt){
        Actions act = new Actions(driver);
        act.dragAndDrop(src,tgt).build().perform();
    }


    public static void rightClick(WebDriver driver, WebElement element){
        Actions act = new Actions(driver);
        act.contextClick(element).build().perform();

    }

    public static void doubleClick(WebDriver driver, WebElement element){
        Actions act = new Actions(driver);
        act.doubleClick(element).build().perform();

    }

    public static void selectByValue(WebElement element,String val){
        Select sel = new Select(element);
        sel.selectByValue(val);
    }

    public static void selectByIndex(WebElement element,int index){
        Select sel = new Select(element);
        sel.selectByIndex(index);
    }

    public static void selectByVisible(WebElement element,String val){
        Select sel = new Select(element);
        sel.selectByVisibleText(val);
    }


    public static void deselectByValue(WebElement element,String val){
        Select sel = new Select(element);
        sel.deselectByValue(val);
    }

    public static void deselectByIndex(WebElement element,int index){
        Select sel = new Select(element);
        sel.deselectByIndex(index);
    }

    public static void deSelectByVisibleText(WebElement element,String val){
        Select sel = new Select(element);
        sel.deselectByVisibleText(val);
    }


    public static void deselectAllValue(WebElement element){
        Select sel = new Select(element);
        sel.deselectAll();
    }

    public void littleScrollVertical(WebDriver driver, String num) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + num + ");");
    }


    public void littleScrollHorizontal(WebDriver driver, String num) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy( " + num + ",0);");
    }
//scrollHeight

    public void ScrollTillEnd(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollHeight");
    }


    public static void jsClick(WebDriver driver, WebElement ele) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

    }

    public static void scrollTillElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

    }

    public static void highlightElement(WebDriver driver, WebElement element, String colour) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px solid " + colour.toLowerCase() + "'", element);
    }

    public static void changeValueToAttribute(WebDriver driver, WebElement element, String val,String attribute){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('"+element+"').setAttribute('"+attribute+"', '"+val+"')");
    }


    public static String generateEmail(){
        Faker fk = new Faker(new Locale("en-IND"));
        String fn = fk.name().fullName().replace(" ","").replace(".","");
        String em = fn+"@testmail.com";
        return em;
    }


    public static String generatePin(){
        Faker fk = new Faker(new Locale("en-IND"));
        String pin = fk.address().zipCode();
        return pin;
    }


    public static String generateMobile(){
        Faker fk = new Faker(new Locale("en-IND"));
        String num = fk.number().digits(10);
        return num;
    }



    public static void takeScreenShot(WebDriver driver, String name) throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot)driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File desFile = new File(System.getProperty("user.dir")+File.separator+name+currentDate()+".png");
        FileUtils.copyFile(srcFile,desFile);
    }

    public static void takeFullScreenScreenShot(WebDriver driver, String name) throws IOException {
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"png",new File(System.getProperty("user.dir")+File.separator+name+currentDate()+".png"));
    }




    public static String currentDate(){
        Date dt = new Date();
        String cDate = dt.toString().replace(" ","_").replace(":","_").trim();
        return cDate;
    }


    public  void genricWait(int sec){

        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {

        }


    }


    public static  void switchToNewWindow(WebDriver driver){
        Set<String> win = driver.getWindowHandles();
        for(String w : win){
            driver.switchTo().window(w);
        }
    }
}
