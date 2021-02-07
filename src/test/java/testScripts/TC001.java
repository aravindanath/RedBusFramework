package testScripts;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;

public class TC001 extends BaseTest {

    /**
     *  TC001 : Verify links present on home Page
     */

    @Test
    public void TC001(){

        HomePage hp = new HomePage(driver);
        hp.verifyHomePageLinks();


    }
}
