package pages;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage  extends BasePage{
    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }


    @FindAll(@FindBy(xpath = "//ul[@class='list-chkbox']/li"))
    protected List<WebElement> busTypeCB;


    @FindBy(xpath = "//div[text()='BUS TYPES']")
    protected WebElement busTypeTitle;

    @FindBy(xpath = "//div[text()='DEPARTURE TIME']")
    protected WebElement depTypeTitle;


    public void verifyBusType(){

        scroll(driver,depTypeTitle);
        genericWait(1000);
        for(WebElement ele : busTypeCB){
            if(!ele.isSelected()){
                ele.click();
                System.out.println("Bus Type is selected :" + ele.getText() );
            }
        }

        for(WebElement ele :busTypeCB){
            if(ele.isSelected()){
                System.out.println("Bus Type is selected :" + ele.getText() );
            }
        }

    }


}
