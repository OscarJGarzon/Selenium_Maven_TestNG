package com.automationexercise.com;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.*;
import toolBox.FunctionDataEntry;
import toolBox.toolBoxFunctions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;

import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;


//This exercise is about TestCase1 https://automationexercise.com/test_cases

public class RegisterUser {
    private toolBoxFunctions TBF = new toolBoxFunctions();
    FunctionDataEntry dataEntry = new FunctionDataEntry("DataEntry-RegisterUser.xlsx", "DataTestCases");
    private WebDriver chDriver;

    // locators

    By ImgLogoLocator = By.xpath("//img[@src='/static/images/home/logo.png']");
    By LnkSingupLocator = By.xpath("//i[@class='fa fa-lock']");
    // Page Sing UP
    By InpNameLocator = By.xpath("//input[@data-qa='signup-name']");
    By InpEmailLocator = By.xpath("//input[@data-qa='signup-email']");
    By InpSingupLocator = By.xpath("//button[@data-qa='signup-button']");
    // Form to register user
    //Form to Account
    By FormRegisterInformationLocator = By.cssSelector("#form > div > div > div > div.login-form");
    By RdBtnMrLocator = By.cssSelector("#id_gender1");
    By RdBtnMrsLocator = By.cssSelector("#id_gender2");
    By InpNameSULocator = By.id("name");
    By InpEmailSULocator = By.id("email");
    By InpPassSULocator = By.id("password");
    By SelDayLocator = By.id("days");
    By SelMonthLocator = By.id("months");
    By SelYearLocator = By.id("years");
    By CheckBoxNewLetterLocator = By.name("newsletter");
    By CheckSpecialOfferLocator = By.name("optin");
    //Form to Account
    By InpFristNameLocator = By.id("first_name");
    By InpLastNameLocator = By.id("last_name");
    By InpCompanyLocator = By.id("company");
    By InpAddress1Locator = By.id("address1");
    By InpAddress2Locator = By.id("address2");
    By SelCountryLocator = By.name("country");
    By InpStateLocator = By.id("state");
    By InpCityLocator = By.id("city");
    By InpZipCodeLocator = By.id("zipcode");
    By InpMobileNumberLocator = By.id("mobile_number");
    By BtnCreateAccountLocator = By.xpath("//button[@data-qa='create-account']");
    //Page Account Created
    By H2MessageLocator = By.xpath("//h2[@data-qa='account-created']");
    By LnkContinueLocator = By.xpath("//a[@data-qa='continue-button']");
    // Page User Logged
    By LnkSingLoggedUserLocator = By.cssSelector("#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(10) > a");
    By LnkTrashUserLocator = By.xpath("//a[contains(text(),'Delete Account')]");
    //Page Account Deleted
    By H2MessageDeletedLocator = By.xpath("//h2[@data-qa='account-deleted']");
    By LnkContinueLocator2 = new By.ByCssSelector("#form > div > div > div > div > a");
    private Object testContext;


    @DataProvider(name = "DataUser")
    public Object[][] testDataSupplier() {
        Object[][] obj = new Object[dataEntry.getRowCount()][1];
        for (int i = 1; i <= dataEntry.getRowCount(); i++) {
            HashMap<String, HashMap<String, String>> testData = dataEntry.getTestDataInMap(i);
            obj[i - 1][0] = testData;
        }
        return obj;
    }

    @BeforeTest
    public void setOutputDirectory(ITestContext context) {
        TestRunner runner = (TestRunner) context;
        String path=System.getProperty("user.dir");
        runner.setOutputDirectory(path+"/testNGReports");
    }
    @BeforeMethod
    //1. Launch browser
    //2. Navigate to url 'http://automationexercise.com'
    public void setUp() {
        chDriver = TBF.setUp("CHROME", "http://automationexercise.com",true);
    }

    @Test(dataProvider = "DataUser")
    public void testSearchPage(Object obj1,ITestContext testContext) {
        HashMap<String, HashMap<String, String>> masterData = (HashMap<String, HashMap<String, String>>) obj1;
        HashMap<String, String> testCaseData = masterData.get("TEST_ID");
        WebDriverWait timeSearch = new WebDriverWait(chDriver, Duration.ofSeconds(10));


        //3. Verify that home page is visible successfully
        WebElement ImgLogo = chDriver.findElement(ImgLogoLocator);
        assertTrue("The page not load", chDriver.findElement(ImgLogoLocator).isDisplayed());
        //4. Click on 'Signup / Login' button
        WebElement LnkSingup = chDriver.findElement(LnkSingupLocator);
        LnkSingup.click();
        //5. Verify 'New User Signup!' is visible
        timeSearch.until(ExpectedConditions.presenceOfElementLocated(InpNameLocator));
        WebElement InpName = chDriver.findElement(InpNameLocator);
        assertTrue("The page SingUP not load", chDriver.findElement(InpNameLocator).isDisplayed());
        //6. Enter name and email address
        InpName.clear();
        InpName.sendKeys(testCaseData.get("NAME"));
        WebElement InpEmail = chDriver.findElement(InpEmailLocator);
        InpEmail.clear();
        InpEmail.sendKeys(testCaseData.get("EMAIL"));
        //7. Click 'Signup' button
        WebElement InpSingup = chDriver.findElement(InpSingupLocator);
        InpSingup.click();
        //8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        timeSearch.until(ExpectedConditions.presenceOfElementLocated(FormRegisterInformationLocator));
        assertTrue("The form not displayed", chDriver.findElement(FormRegisterInformationLocator).isDisplayed());
        //9. Fill details: Title, Name, Email, Password, Date of birth
        WebElement RdBtnMr = chDriver.findElement(RdBtnMrLocator);
        WebElement RdBtnMrs = chDriver.findElement(RdBtnMrsLocator);
        switch (testCaseData.get("TITLE").toUpperCase(Locale.ROOT)) {
            case "MR":
                RdBtnMr.click();
                break;
            case "MRS":
                RdBtnMrs.click();
                break;
            default:
                break;
        }
        WebElement InpNameSU = chDriver.findElement(InpNameSULocator);
        WebElement InpEmailSU = chDriver.findElement(InpEmailSULocator);
        WebElement InpPassSU = chDriver.findElement(InpPassSULocator);

        assertEquals("The name is different", testCaseData.get("NAME"), InpNameSU.getAttribute("value"));
        assertEquals("The email is different", testCaseData.get("EMAIL"), InpEmailSU.getAttribute("value"));

        InpPassSU.clear();
        InpPassSU.sendKeys(testCaseData.get("PASSWORD"));

        Select SelDay = new Select(chDriver.findElement(SelDayLocator));
        SelDay.selectByVisibleText(TBF.getDay(testCaseData.get("DATE_BIRTH").toString(), "dd/mm/yyyy"));
        Select SelMonth = new Select(chDriver.findElement(SelMonthLocator));
        SelMonth.selectByVisibleText(TBF.getMonth(testCaseData.get("DATE_BIRTH").toString(), "dd/mm/yyyy"));
        Select SelYear = new Select(chDriver.findElement(SelYearLocator));
        SelYear.selectByVisibleText(TBF.getYear(testCaseData.get("DATE_BIRTH").toString(), "dd/mm/yyyy"));

        //10. Select checkbox 'Sign up for our newsletter!'
        WebElement CheckBoxNewLetter = chDriver.findElement(CheckBoxNewLetterLocator);
        CheckBoxNewLetter.click();
        //11. Select checkbox 'Receive special offers from our partners!'
        WebElement CheckSpecialOffer = chDriver.findElement(CheckSpecialOfferLocator);
        CheckSpecialOffer.click();
        //12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number

        WebElement InpFristName = chDriver.findElement(InpFristNameLocator);
        ((JavascriptExecutor) chDriver).executeScript("arguments[0].scrollIntoView(true);", InpFristName);
        InpFristName.clear();
        InpFristName.sendKeys(testCaseData.get("FIRST_NAME"));
        WebElement InpLastName = chDriver.findElement(InpLastNameLocator);
        InpLastName.clear();
        InpLastName.sendKeys(testCaseData.get("LAST_NAME"));
        WebElement InpCompany = chDriver.findElement(InpCompanyLocator);
        InpCompany.clear();
        InpCompany.sendKeys(testCaseData.get("COMPANY"));
        WebElement InpAddress1 = chDriver.findElement(InpAddress1Locator);
        InpAddress1.clear();
        InpAddress1.sendKeys(testCaseData.get("ADDRESS"));
        WebElement InpAddress2 = chDriver.findElement(InpAddress2Locator);
        InpAddress2.clear();
        InpAddress2.sendKeys(testCaseData.get("ADDRESS"));
        Select SelCountry = new Select(chDriver.findElement(SelCountryLocator));
        SelCountry.selectByVisibleText(testCaseData.get("COUNTRY"));
        WebElement InpState = chDriver.findElement(InpStateLocator);
        InpState.clear();
        InpState.sendKeys(testCaseData.get("STATE"));
        WebElement InpCity = chDriver.findElement(InpCityLocator);
        InpCity.clear();
        InpCity.sendKeys(testCaseData.get("CITY"));
        WebElement InpZipCode = chDriver.findElement(InpZipCodeLocator);
        ((JavascriptExecutor) chDriver).executeScript("arguments[0].scrollIntoView(true);", InpZipCode);
        InpZipCode.clear();
        InpZipCode.sendKeys(testCaseData.get("ZIP_CODE"));
        WebElement InpMobileNumber = chDriver.findElement(InpMobileNumberLocator);
        InpMobileNumber.clear();
        InpMobileNumber.sendKeys(testCaseData.get("MOBILE_NUMBER"));

        //13. Click 'Create Account button'
        WebElement BtnCreateAccount = chDriver.findElement(BtnCreateAccountLocator);
        BtnCreateAccount.click();
        //14. Verify that 'ACCOUNT CREATED!' is visible
        WebElement H2Message = chDriver.findElement(H2MessageLocator);
        timeSearch.until(ExpectedConditions.presenceOfElementLocated(H2MessageLocator));
        assertTrue("The message account not displayed", chDriver.findElement(H2MessageLocator).isDisplayed());
        //15. Click 'Continue' button
        WebElement LnkContinue = chDriver.findElement(LnkContinueLocator);
        LnkContinue.click();
        //16. Verify that 'Logged in as username' is visible
        //TBF.closeAD(chDriver);
        WebElement LnkSingLoggedUser = chDriver.findElement(LnkSingLoggedUserLocator);
        timeSearch.until(ExpectedConditions.presenceOfElementLocated(LnkSingLoggedUserLocator));
        assertTrue("The Logged user not displayed", chDriver.findElement(LnkSingLoggedUserLocator).isDisplayed());
        //17. Click 'Delete Account' button
        WebElement LnkTrashUser = chDriver.findElement(LnkTrashUserLocator);
        LnkTrashUser.click();
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        WebElement H2MessageDeleted = chDriver.findElement(H2MessageDeletedLocator);
        timeSearch.until(ExpectedConditions.presenceOfElementLocated(H2MessageDeletedLocator));
        assertTrue("The message account not displayed", chDriver.findElement(H2MessageDeletedLocator).isDisplayed());
        WebElement LnkContinue2 = chDriver.findElement(LnkContinueLocator2);
        LnkContinue2.click();

    }

    @AfterMethod
    public void tearDown() {
        chDriver.quit();
    }
}
