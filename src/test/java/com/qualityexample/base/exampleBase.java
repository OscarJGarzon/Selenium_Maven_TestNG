package com.qualityexample.base;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class exampleBase {
    private WebDriver chDriver;

    @Before

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
        chDriver = new ChromeDriver();
        chDriver.manage().window().maximize();
        chDriver.get("https://www.google.com");
    }

    @Test
    public void testSearchGooglePage() {
        WebElement searchField = chDriver.findElement(By.name("q"));
        searchField.clear();
        searchField.sendKeys("Colombia");
        searchField.submit();

        chDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("Colombia - Buscar con Google",chDriver.getTitle());

    }

    @After

    public void tearDown(){
        chDriver.quit();
    }
}
