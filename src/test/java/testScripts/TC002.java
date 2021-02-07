package testScripts;

import org.testng.annotations.Test;
import pages.HomePage;

public class TC002 extends  BaseTest {

    /**
     *
     * TC002: Book a ticket from A to B for future date.
     */


    @Test
    public void TC002(){

        HomePage hp  = new HomePage(driver);
        hp.bookATicket("TC002");

    }
}
