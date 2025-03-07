import com.ecofg.Lab4;
import com.ecofg.lab3.RegistrationFlow;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.fail;

public class SearchDescriptionTest extends Lab4 {

    @BeforeClass
    public static void setupUser() {
        if (RegistrationFlow.registeredEmail == null || RegistrationFlow.registeredPass == null) {
            RegistrationFlow.registerNewUser();
        }
    }

    @Test
    public void searchDescriptionOne() {
        performSearch("Web site toward");
    }

    @Test
    public void searchDescriptionTwo() {
        performSearch("Build it");
    }

    private void performSearch(String searchKey) {
        webDriver.get("https://demowebshop.tricentis.com/");
        webDriver.manage().window().maximize();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("small-searchterms")))
               .sendKeys(searchKey);
        waitFor.until(ExpectedConditions.elementToBeClickable(By.className("search-box-button")))
               .click();
        waitFor.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class=\"basic-search\"]/div/*[@id=\"As\"]"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id=\"advanced-search-block\"]//input[@id=\"Sid\"]"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@class=\"button-1 search-button\"]"))).click();

        waitFor.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-grid")));
        List<WebElement> productLinks = webDriver.findElements(
                By.cssSelector("div.product-grid .product-title a"));

        for (WebElement productLink : productLinks) {
            productLink.click();

            String productDescription = webDriver.findElement(
                    By.cssSelector("div.short-description")).getText();
            if (!productDescription.contains(searchKey)) {
                System.out.println(
                        "Product description does not contain search key: " + productDescription);
                fail();
            }

            webDriver.navigate().back();
        }
    }

    private List<String> loadProducts(String file) {
        try {
            return Files.readAllLines(Paths.get("src/main/resources/" + file));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
