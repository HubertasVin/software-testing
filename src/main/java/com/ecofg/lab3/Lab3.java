package com.ecofg.lab3;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Lab3 {
    protected WebDriver webDriver;
    protected WebDriverWait waitFor;
    private static final int DEFAULT_WAIT = 10;

    @Before
    public void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        waitFor = new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_WAIT));
    }

    @After
    public void destruct() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
