package com.practice.automationtesting.in;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import toolBox.FunctionDataEntry;
import toolBox.toolBoxFunctions;

import java.time.Duration;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class TestSearch {
    private toolBoxFunctions TBF = new toolBoxFunctions();
    FunctionDataEntry dataEntry = new FunctionDataEntry("DataEntryTestCases.xlsx", "DataTestCases");

    private WebDriver chDriver;
    By InpSearchLocator = By.cssSelector("#s");
    By DivResultLocator = By.id("content");

    @DataProvider(name = "testDataSupplier")
    public Object[][] testDataSupplier() {
        Object[][] obj = new Object[dataEntry.getRowCount()][1];
        for (int i = 1; i <= dataEntry.getRowCount(); i++) {
            HashMap<String, HashMap<String, String>> testData = dataEntry.getTestDataInMap(i);
            obj[i - 1][0] = testData;
        }
        return obj;
    }


    @BeforeMethod
    public void setUp() {
        chDriver = TBF.setUp("CHROME", "https://practice.automationtesting.in/",true);
    }

    @Test(dataProvider = "testDataSupplier")
    public void testSearchPage(Object obj1) {
        HashMap<String, HashMap<String, String>> masterData = (HashMap<String, HashMap<String, String>>) obj1;
        HashMap<String, String> testCaseData = masterData.get("TEST_ID");

        WebDriverWait timeSearch = new WebDriverWait(chDriver, Duration.ofSeconds(10));
        WebElement InpSearch = chDriver.findElement(InpSearchLocator);
        InpSearch.clear();

        InpSearch.sendKeys(testCaseData.get("Words"));
        InpSearch.submit();
        timeSearch.until(ExpectedConditions.presenceOfElementLocated(DivResultLocator));
        assertTrue("The result is not present.", chDriver.findElement(DivResultLocator).isDisplayed());
    }

    @AfterMethod

    public void tearDown() {
        chDriver.quit();
    }
}
