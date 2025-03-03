package com.ecofg;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Lab2_2 {
    private static WebDriver browser;

    private void performTest() {
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        browser.get("https://demoqa.com/");

        browser.findElement(By.xpath("//div[@class='card-body']//h5[contains(text(),'Elements')]")).click();
        browser.findElement(By.xpath("//li[.//span[contains(text(),'Web Tables')]]")).click();

        while (!browser.findElement(By.cssSelector("span.-totalPages")).getText().trim().equals("2")) {
            browser.findElement(By.id("addNewRecordButton")).click();
            browser.findElement(By.id("firstName")).sendKeys("John");
            browser.findElement(By.id("lastName")).sendKeys("Doe");
            browser.findElement(By.id("userEmail")).sendKeys("john.doe@example.com");
            browser.findElement(By.id("age")).sendKeys("30");
            browser.findElement(By.id("salary")).sendKeys("50000");
            browser.findElement(By.id("department")).sendKeys("Engineering");
            browser.findElement(By.id("submit")).click();
        }

        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollTo(0, 300)");

        browser.findElement(By.xpath("//button[contains(text(),'Next') and contains(@class, '-btn')]")).click();
        browser.findElement(By.xpath("//div[@class='rt-tbody']/div[1]//span[@title='Delete']")).click();

        String pageCount = browser.findElement(By.cssSelector("span.-totalPages")).getText().trim();
        String currPage = browser.findElement(By.xpath("//div[@class='-pageJump']//input")).getAttribute("value");
        if ("1".equals(pageCount) && "1".equals(currPage)) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
    }

    public void execute() {
        try {
            performTest();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            browser.quit();
        }
    }

    public static void main(String[] args) {
        browser = new ChromeDriver();
        new Lab2_2().execute();
    }
}
