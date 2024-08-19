import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SaucedeLogin {

    WebDriver driver;

    By logo = By.xpath("//div[contains(@class,'login_logo')]");
    By usrName = By.id("user-name");
    By password = By.id("password");
    By btnLogin = By.id("login-button");
    By lockedUser = By.xpath("//div[contains(@id,'login_credentials')]");


    public SaucedeLogin(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLogo() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
            System.out.println("Logo existe");

        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
    }

    public void loginIntoPage(String strUserName, String strPassword) {
        try {
            driver.findElement(usrName).sendKeys(strUserName);
            driver.findElement(password).sendKeys(strPassword);
            driver.findElement(btnLogin).click();
            System.out.println("Login correcto");
        } catch (NoSuchElementException e) {
            System.out.println("No se encuentran elementos" + e);
        }
    }

    public void verifyUser(String strUser){
        try{
            String usuario = driver.findElement(lockedUser).getText();

            if(usuario.contains(strUser)){
                System.out.println("Usuario "+ strUser+ " verificado");
            }else{

                throw new AssertionError("No se encuentra este usuario");
            }
        }catch(NoSuchElementException e){
            System.out.println("Error:" + e);
        }
    }




}
