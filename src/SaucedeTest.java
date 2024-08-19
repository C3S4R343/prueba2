import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SaucedeTest {
    WebDriver driver;
    SaucedeLogin saucedeLog;
    InventoryPage invPage;
    CartPage cartP;
    checkOutStepOne checkOneStp;
    checkOutStepTwo checkTwoStp;
    CheckOutComplete checkComp;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\julgonzalez\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        saucedeLog = new SaucedeLogin(driver);
        invPage = new InventoryPage(driver);
        cartP = new CartPage(driver);
        checkOneStp = new checkOutStepOne(driver);
        checkTwoStp = new checkOutStepTwo(driver);
        checkComp = new CheckOutComplete(driver);
    }

    @Test
    public void testSaucede(){
        saucedeLog.waitForLogo();
        saucedeLog.loginIntoPage("standard_user","secret_sauce");

        invPage.verificarPaginaProducto();
        invPage.verifyLogOut();
        invPage.addRandomItemsToCar(3);
        invPage.verifyShopyCart();

        cartP.verifyCartList();

        checkOneStp.fillCheckOut("Cesar","Gonzalez","3700");

        checkTwoStp.verifyCartList();

        checkComp.complete();

        invPage.closeSesion();

        saucedeLog.verifyUser("locked_out_user");

    }

    @After
    public  void finish(){
        if (driver != null){
            driver.quit();
        }
    }
}