import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CheckOutComplete {
    WebDriver driver;

    By ThanksMessage = By.xpath("//h2[@class='complete-header'][contains(.,'Thank you for your order!')]");
    By btnBack = By.xpath("//button[@id='back-to-products']");

    public CheckOutComplete(WebDriver driver) {
        this.driver = driver;
    }

    public void complete(){
        try{
            if(driver.findElement(ThanksMessage).isDisplayed()){
                System.out.println("Compra completada con exito");
                driver.findElement(btnBack).click();
            }
        }catch(NoSuchElementException e){
            System.out.println("Error: " +e);
        }


    }
}
