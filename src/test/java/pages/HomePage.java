package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.JavaUtils;

import java.util.HashMap;
import java.util.List;

public class HomePage extends BasePage{


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Object repo

    @FindBy(css = "#src")
    public WebElement srcTextField;

    @FindAll(@FindBy(xpath = "//ul[@class='autoFill']/li"))
    public List<WebElement> srcList;

    @FindBy(css = "#dest")
    public WebElement destTextField;

    @FindAll(@FindBy(xpath = "//ul[@class='autoFill']/li"))
    public List<WebElement> destList;

    @FindBy(id="onward_cal")
    public WebElement onWardCaldender;

    @FindAll(@FindBy(xpath = "//table[@class='rb-monthTable first last']/tbody/tr/td"))
    public List<WebElement> calenderValues;

    @FindBy(xpath = "//button[text()='Search Buses']")
    public WebElement searchBtn;

    @FindAll(@FindBy(tagName = "a"))
    public List<WebElement> pageLinks;



    HashMap<String, String>hp;

    // Logic


    public void verifyHomePageLinks(){
      int linkSize =   pageLinks.size();
      System.out.println("Total No of  links on Home page: " + linkSize);
      for(WebElement ele : pageLinks){
          System.out.println("LInk text: "+ ele.getText() + " ----> " + ele.getAttribute("href"));
      }
    }


    public void bookATicket(String workflowID){
        hp = JavaUtils.readExcelData("HomePage",workflowID);
        String src = hp.get("SOURCE");
        String dest = hp.get("DEST");
        System.out.println("Home Page Title: "+ driver.getTitle());
        int day = getTodaysDate()+10;
        System.out.println("User has entered "+src+" as pickup point.");
        srcTextField.sendKeys(src);
        genericWait(2000);
        for(WebElement ele : srcList)
        {
           if(ele.getText().equalsIgnoreCase(src)){
               System.out.println(ele.getText());
               ele.click();
               break;
           }
        }
        destTextField.sendKeys(dest);
        genericWait(2000);
        System.out.println("User has entered "+dest+" as drop point.");
        for(WebElement ele : destList)
        {

            if(ele.getText().equalsIgnoreCase(dest)){
                System.out.println(ele.getText());
                ele.click();
                break;
            }
        }
        onWardCaldender.click();
        System.out.println("User has entered "+day+" as booking date.");
        for(WebElement ele : calenderValues){
            if(ele.getText().equals(String.valueOf(day))){
                ele.click();
                System.out.println("User has entered "+day+" as booking date.");
                break;
            }
        }
        genericWait(3000);
        click(searchBtn);
        genericWait(3000);
        System.out.println("Search Result Page Title: "+ driver.getTitle());
    }



}
