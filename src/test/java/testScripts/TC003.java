package testScripts;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

public class TC003 extends  BaseTest {

    /**
     *
     * TC002: Book a ticket from A to B for future date.
     */


    @Test
    public void TC003(){

        HomePage hp  = new HomePage(driver);
        hp.bookATicket("TC002");
        SearchResultPage sp = new SearchResultPage(driver);
        sp.verifyBusType();

    }
}
