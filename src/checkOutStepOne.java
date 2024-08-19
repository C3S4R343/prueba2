import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class checkOutStepOne {

    WebDriver driver;

    By title = By.xpath("//span[@class='title'][contains(.,'Checkout: Your Information')]");
    By firstName = By.xpath("//input[@id='first-name']");
    By lastName = By.xpath("//input[contains(@id,'last-name')]");
    By zipCode = By.xpath("//input[@id='postal-code']");
    By btnContinue = By.id("continue");

    By errMessage = By.xpath("//div[contains(@class,'error-message-container error')]");

    public checkOutStepOne(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCheckOut (String strfirstName, String strlastName, String strZipCode){
        try{
            driver.findElement(firstName).sendKeys(strfirstName);
            driver.findElement(lastName).sendKeys(strlastName);
            driver.findElement(zipCode).sendKeys(strZipCode);
            driver.findElement(btnContinue).click();


        }catch(NoSuchElementException e){
            System.out.println("Error: " + e);
        }
    }



}
