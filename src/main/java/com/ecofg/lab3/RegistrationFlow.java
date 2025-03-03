package com.ecofg.lab3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationFlow {
    public static String registeredEmail;
    public static String registeredPass;
    private static final int WAIT_LIMIT = 10;

    public static void registerNewUser() {
        WebDriver localDriver = new ChromeDriver();
        localDriver.manage().window().maximize();
        localDriver.get("https://demowebshop.tricentis.com/");

        WebDriverWait waitFor = new WebDriverWait(localDriver, Duration.ofSeconds(WAIT_LIMIT));
        waitFor.until(ExpectedConditions.elementToBeClickable(By.linkText("Log in"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.linkText("Register"))).click();

        registeredEmail = "newUser" + System.currentTimeMillis() + "@mail.com";
        registeredPass = "Secret!";

        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("gender-male"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("FirstName"))).sendKeys("Alex");
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("LastName"))).sendKeys("Brown");
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("Email"))).sendKeys(registeredEmail);
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("Password"))).sendKeys(registeredPass);
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("ConfirmPassword"))).sendKeys(registeredPass);
        waitFor.until(ExpectedConditions.elementToBeClickable(By.id("register-button"))).click();
        waitFor.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Continue']"))).click();
        localDriver.quit();
    }
}
