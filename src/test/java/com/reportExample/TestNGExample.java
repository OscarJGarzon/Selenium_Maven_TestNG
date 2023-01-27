package com.reportExample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import toolBox.ReadExcelFile;
import toolBox.WriteExcelFile;
import toolBox.toolBoxFunctions;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TestNGExample {
    private toolBoxFunctions TBF= new toolBoxFunctions();
    private WriteExcelFile writeFile= new WriteExcelFile();
    private ReadExcelFile readFile= new ReadExcelFile();

    private WebDriver chDriver;
    By InpSearchLocator = By.cssSelector("#s");
    By DivResultLocator = By.id("content");


    @BeforeClass

    public void setUp() {
        chDriver = TBF.setUp(chDriver);
    }

    @Test
    public void testSearchPage() throws IOException {
        WebDriverWait timeSearch = new WebDriverWait(chDriver, Duration.ofSeconds(10));
        String dataIN, dataOUT;
        Path path = Paths.get("");
        String filePath= path.toAbsolutePath().toString()+"\\src\\test\\resources\\DataEntry\\DataEntryTestCases.xlsx";

        WebElement InpSearch = chDriver.findElement(InpSearchLocator);
        InpSearch.clear();
        dataIN=readFile.getCellValue(filePath, "DataTestCases",0,0);
        InpSearch.sendKeys(dataIN);
        InpSearch.submit();
        timeSearch.until(ExpectedConditions.presenceOfElementLocated(DivResultLocator));
        assertTrue("The result is not present.", chDriver.findElement(DivResultLocator).isDisplayed());
    }

    @AfterClass

    public void tearDown() {
        chDriver.quit();

    }
}
