import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;
    By cartList = By.cssSelector("#cart_contents_container > div > div.cart_list");
    By btnCheckOut = By.xpath("//button[contains(@id,'checkout')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyCartList() {
        try {
            if (driver.findElement(cartList).getText() != null) {
                System.out.println("Hay elementos en el carrito");
            } else {
                throw new AssertionError("Error en carrito");
            }

            if(driver.findElement(btnCheckOut).isDisplayed()){
                driver.findElement(btnCheckOut).click();
            }else{
                throw new AssertionError("Error: no se encuentra boton checkout");
            }


        } catch (NoSuchElementException e) {
            System.out.println("Error " + e);
        }


    }

}
