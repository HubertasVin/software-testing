package com.ecofg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Lab2_1 {
    private static WebDriver browser;
    private static WebDriverWait wait;

    private void performTest() {
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        browser.get("https://demoqa.com/");

        browser.findElement(By.xpath("//div[@class='card-body']//h5[contains(text(),'Widgets')]")).click();
        browser.findElement(By.xpath("//li[.//span[contains(text(),'Progress Bar')]]")).click();

        browser.findElement(By.id("startStopButton")).click();

        WebElement progressElem = browser.findElement(By.xpath("//div[@role='progressbar']"));
        wait.until(driver -> "100".equals(progressElem.getAttribute("aria-valuenow")));

        browser.findElement(By.id("resetButton")).click();
        wait.until(driver -> "0".equals(progressElem.getAttribute("aria-valuenow")));
    }

    public void execute() {
        try {
            performTest();
            System.out.println("Passed");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            browser.quit();
        }
    }

    public static void main(String[] args) {
        browser = new ChromeDriver();
        wait = new WebDriverWait(browser, Duration.ofSeconds(30));
        new Lab2_1().execute();
    }
}
