package com.reportExample;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TestNGExample {

    private WebDriver chDriver;
    By InpSearchLocator = By.cssSelector("#s");
    By DivResultLocator = By.id("content");


    @BeforeClass

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
        chDriver = new ChromeDriver();
        chDriver.manage().window().maximize();
        chDriver.get("https://practice.automationtesting.in/");
    }

    @Test
    public void testSearchPage() {
        WebDriverWait timeSearch = new WebDriverWait(chDriver, Duration.ofSeconds(10));

        WebElement InpSearch = chDriver.findElement(InpSearchLocator);
        InpSearch.clear();
        InpSearch.sendKeys("Ruby");
        InpSearch.submit();

        timeSearch.until(ExpectedConditions.presenceOfElementLocated(DivResultLocator));
        assertTrue("The result is not present.", chDriver.findElement(DivResultLocator).isDisplayed());
    }

    @AfterClass

    public void tearDown() {
        chDriver.close();
    }
}
