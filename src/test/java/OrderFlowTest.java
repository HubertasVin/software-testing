import com.ecofg.lab3.Lab3;
import com.ecofg.lab3.RegistrationFlow;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OrderFlowTest extends Lab3 {

    @BeforeClass
    public static void setupUser() {
        if (RegistrationFlow.registeredEmail == null || RegistrationFlow.registeredPass == null) {
            RegistrationFlow.registerNewUser();
        }
    }

    @Test
    public void purchaseUsingFileOne() {
        processPurchase("data1.txt");
    }

    @Test
    public void purchaseUsingFileTwo() {
        processPurchase("data2.txt");
    }

    private void processPurchase(String fileName) {
        webDriver.get("https://demowebshop.tricentis.com/");
        webDriver.manage().window().maximize();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.linkText("Log in"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("Email"))).sendKeys(RegistrationFlow.registeredEmail);
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("Password"))).sendKeys(RegistrationFlow.registeredPass);
        waitFor.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Log in']"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.linkText("Digital downloads"))).click();

        List<String> items = loadProducts(fileName);
        for (String item : items) {
            waitFor.until(ExpectedConditions.elementToBeClickable(By.linkText(item))).click();
            waitFor.until(ExpectedConditions.elementToBeClickable(By.className("add-to-cart-button"))).click();
            webDriver.navigate().back();
        }

        waitFor.until(ExpectedConditions.elementToBeClickable(By.linkText("Shopping cart"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("termsofservice"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        List<WebElement> addressDropdown = webDriver.findElements(By.id("billing-address-select"));
        if (!addressDropdown.isEmpty() && addressDropdown.get(0).isDisplayed()) {
            waitFor.until(ExpectedConditions.elementToBeClickable(By.className("new-address-next-step-button"))).click();
        } else {
            WebElement countryElem = webDriver.findElement(By.id("BillingNewAddress_CountryId"));
            new Select(countryElem).selectByVisibleText("Lithuania");

            waitFor.until(ExpectedConditions.elementToBeClickable(By.id("BillingNewAddress_City")))
                   .sendKeys("Vilnius");
            waitFor.until(ExpectedConditions.elementToBeClickable(By.id("BillingNewAddress_Address1")))
                   .sendKeys("Didlaukio g. 59");
            waitFor.until(ExpectedConditions.elementToBeClickable(By.id("BillingNewAddress_ZipPostalCode")))
                   .sendKeys("LT00126");
            waitFor.until(ExpectedConditions.elementToBeClickable(By.id("BillingNewAddress_PhoneNumber")))
                   .sendKeys("9876543210");

            waitFor.until(ExpectedConditions.elementToBeClickable(By.className("new-address-next-step-button"))).click();
        }

        waitFor.until(ExpectedConditions.elementToBeClickable(By.className("payment-method-next-step-button"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.className("payment-info-next-step-button"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.className("confirm-order-next-step-button"))).click();

        WebElement confirmationMsg = waitFor.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-completed strong")));
        String finalMessage = confirmationMsg.getText();
        Assert.assertEquals("Your order has been successfully processed!", finalMessage);
    }

    private List<String> loadProducts(String file) {
        try {
            return Files.readAllLines(Paths.get("src/main/resources/" + file));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
