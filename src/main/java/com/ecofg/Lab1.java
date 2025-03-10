package com.ecofg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Lab1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demowebshop.tricentis.com");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//ul[starts-with(@class, 'list')]/li/a[starts-with(@href, '/gift-cards')]")));
        driver.findElement(By.xpath(
                      "//ul[starts-with(@class, 'list')]/li/a[starts-with(@href, '/gift-cards')]"))
              .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[@class='prices'][number(.//span[@class='price actual-price']) > 99]" +
                        "/following-sibling::div[@class='buttons'][1]//input[@type='button']")));
        driver.findElement(By.xpath(
                      "//div[@class='prices'][number(.//span[@class='price actual-price']) > 99]" + "/following-sibling::div[@class='buttons'][1]//input[@type='button']"))
              .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("giftcard_4_RecipientName")));
        driver.findElement(By.id("giftcard_4_RecipientName")).sendKeys("Auksinis Kardas");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("giftcard_4_SenderName")));
        driver.findElement(By.id("giftcard_4_SenderName")).sendKeys("Tavo mama");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
                "addtocart_4_EnteredQuantity")));
        WebElement quantityInput = driver.findElement(By.id("addtocart_4_EnteredQuantity"));
        quantityInput.clear();
        quantityInput.sendKeys("5000");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//input[@value='Add to cart'] | //button[contains(text(),'Add to cart')]")))
            .click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-wishlist-button-4")));
        driver.findElement(By.id("add-to-wishlist-button-4")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//ul[starts-with(@class, 'list')]/li/a[starts-with(@href, '/jewelry')]")));
        driver.findElement(By.xpath("//ul[starts-with(@class, 'list')]/li/a[starts-with(@href, " + "'/jewelry')]"))
              .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//h2[starts-with(@class, 'product-title')]/a[starts-with(@href, " + "'/create-it" +
                        "-yourself-jewelry')]")));
        driver.findElement(By.xpath(
                      "//h2[starts-with(@class, 'product-title')]/a[starts-with(@href, " +
                              "'/create-it-yourself-jewelry')]"))
              .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_attribute_71_9_15"
        )));
        WebElement materialDropdown = driver.findElement(By.id("product_attribute_71_9_15"));
        new Select(materialDropdown).selectByVisibleText("Silver (1 mm)");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
                "product_attribute_71_10_16")));
        WebElement lengthInput = driver.findElement(By.id("product_attribute_71_10_16"));
        lengthInput.sendKeys("80");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("product_attribute_71_11_17_50")));
        driver.findElement(By.id("product_attribute_71_11_17_50")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
                "addtocart_71_EnteredQuantity")));
        WebElement quantityInputJewerly = driver.findElement(By.id("addtocart_71_EnteredQuantity"));
        quantityInputJewerly.clear();
        quantityInputJewerly.sendKeys("26");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-71")));
        driver.findElement(By.id("add-to-cart-button-71")).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-wishlist-button-71")));
        driver.findElement(By.id("add-to-wishlist-button-71")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//a[starts-with(@class, 'ico-wishlist')]")));
        driver.findElement(By.xpath("//a[starts-with(@class, 'ico-wishlist')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "td.add-to-cart input[type='checkbox']")));
        List<WebElement> addToCartCheckboxes = driver.findElements(By.cssSelector(
                "td.add-to-cart input[type='checkbox']"));
        for (WebElement checkbox : addToCartCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[starts-with(@class, 'common-buttons')]/input[starts-with(@class, " +
                        "'button-2" + " wishlist-add-to-cart-button')]")));
        driver.findElement(By.xpath(
                      "//div[starts-with(@class, 'common-buttons')]/input[starts-with(@class, " + "'button-2 wishlist-add-to-cart-button')]"))
              .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span[starts-with(@class, 'product-price order-total')]/strong")));
        WebElement totalElement = driver.findElement(By.xpath(
                "//span[starts-with(@class, 'product-price order-total')]/strong"));
        if (totalElement.getText().equals("1002600.00")) {
            System.out.println("Pass");
        }
        else {
            System.out.println("Fail");
        }
    }
}
