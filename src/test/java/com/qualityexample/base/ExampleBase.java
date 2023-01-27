package com.qualityexample.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import toolBox.toolBoxFunctions;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ExampleBase {
    toolBoxFunctions TBF = new toolBoxFunctions();
    private WebDriver chDriver;

    @BeforeClass

    public void setUp() {
        chDriver = TBF.setUp(chDriver);
    }

    @Test
    public void testSearchGooglePage() {
        WebElement searchField = chDriver.findElement(By.name("q"));
        searchField.clear();
        searchField.sendKeys("Colombia");
        searchField.submit();

        chDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("Colombia - Buscar con Google", chDriver.getTitle());

    }

    @AfterClass

    public void tearDown() {
        chDriver.quit();
    }
}
