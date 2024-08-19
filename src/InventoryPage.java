import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class InventoryPage {
    WebDriver driver;
    String expectedUrl = "https://www.saucedemo.com/inventory.html";

    By menu = By.xpath("//button[@id='react-burger-menu-btn']");
    By logout = By.xpath("//a[contains(@id,'logout_sidebar_link')]");
    By cart = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verificarPaginaProducto() {
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Titulo de la pagina correcta");
        } else {
            throw new AssertionError("Pagina incorrecta, la pagina actual es " + actualUrl);
        }
    }


    public void verifyLogOut() {
        try {
            driver.findElement(menu).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(ExpectedConditions.visibilityOfElementLocated(logout));

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }


    public void addRandomItemsToCar(int numberOfItems) {
        // Encuentra la lista de inventario
        WebElement inventoryList = driver.findElement(By.className("inventory_list"));
        List<WebElement> inventoryItems = inventoryList.findElements(By.className("inventory_item"));

        Random random = new Random();
        int addedItems = 0;

        // Agrega artículos aleatorios al carrito hasta alcanzar el número especificado
        while (addedItems < numberOfItems) {
            // Selecciona un artículo aleatorio de la lista de inventario
            int randomIndex = random.nextInt(inventoryItems.size());
            WebElement randomItem = inventoryItems.get(randomIndex);

            // Obtiene el nombre del artículo y hace clic en él
            WebElement itemNameElement = randomItem.findElement(By.className("inventory_item_name"));
            String itemName = itemNameElement.getText();
            itemNameElement.click();

            // Verifica que el nombre del artículo coincida en la página de detalles
            WebElement productTitle = driver.findElement(By.className("inventory_details_name"));
            String productTitleText = productTitle.getText();

            if (!productTitleText.equals(itemName)) {
                throw new AssertionError("El nombre no coincide");
            } else {
                System.out.println("Producto " + itemName + " agregado con éxito");
            }

            // Encuentra el botón de agregar al carrito y verifica su estado
            WebElement addToCartButton = driver.findElement(By.xpath("//button[contains(@class, 'btn_inventory')]"));
            String buttonText = addToCartButton.getText();

            // Si el botón dice "Add to cart", haz clic y aumenta el contador
            if (buttonText.equals("Add to cart")) {
                addToCartButton.click();
                addedItems++;
            }
            // Si el botón dice "Remove", significa que el artículo ya está en el carrito, así que haz clic para eliminarlo
            else if (buttonText.equals("Remove")) {
                addToCartButton.click();
                System.out.println("Producto repetido, se elimina de la lista");
            }

            // Navega de vuelta a la lista de inventario y actualiza la lista de artículos
            driver.navigate().back();
            inventoryItems = driver.findElements(By.className("inventory_item"));
        }
    }


    public void verifyShopyCart() {
        try {
            WebElement carrito = driver.findElement(cart);
            if (carrito != null) {
                carrito.click();
            }

        } catch (java.util.NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
    }

    public void closeSesion() {
        try {
            if (driver.findElement(menu).isDisplayed()) {
                driver.findElement(menu).click();
                driver.findElement(logout).click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e);

        }
    }
}
