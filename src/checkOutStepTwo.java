import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class checkOutStepTwo {
    WebDriver driver;

    By cartList = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]");
    By sumaryInfo = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]");
    By btnFinish = By.xpath("//button[contains(@id,'finish')]");


    public checkOutStepTwo(WebDriver driver) {
        this.driver = driver;

    }

    public void verifyCartList(){
        try{
            if(driver.findElement(cartList).isDisplayed() & driver.findElement(sumaryInfo).isDisplayed()){

                driver.findElement(btnFinish).click();
            }else {
                throw new AssertionError("Error en pagina despues de check out");
            }
        }catch(NoSuchElementException e){
            System.out.println(e);
        }
    }
}
