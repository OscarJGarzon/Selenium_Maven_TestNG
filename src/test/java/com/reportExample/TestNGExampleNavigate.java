package com.reportExample;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestNGExampleNavigate {

    private WebDriver chDriver;
    By ImgLinkHTMLLocator = By.xpath("//span[@class='onsale' ] /../img[@class='attachment-shop_catalog size-shop_catalog wp-post-image']");

    By ImgBookLocator = By.xpath("//img[@src='https://practice.automationtesting.in/wp-content/uploads/2017/01/Thinking-in-HTML-600x600.jpg']");
    By TxtTitleLocator = By.className("entry-title");
    By TxtPriceNormalLocator = By.cssSelector("#product-163 > div.summary.entry-summary > div:nth-child(2) > p > del > span");
    By TxtPriceOfferLocator = By.cssSelector("#product-163 > div.summary.entry-summary > div:nth-child(2) > p > ins > span");
    By DivDescriptionLocator = By.id("tab-description");

    @BeforeClass

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
        chDriver = new ChromeDriver();
        chDriver.manage().window().maximize();
        chDriver.get("https://practice.automationtesting.in/");
    }

    @Test
    public void testNavigate() {
        int Normalvalue=0;
        int Offerlvalue=0;
        Actions driveAction=new Actions(chDriver);;
        WebDriverWait timeSearch = new WebDriverWait(chDriver, Duration.ofSeconds(10));

        WebElement ImgLinkHTML = chDriver.findElement(ImgLinkHTMLLocator);
        ((JavascriptExecutor) chDriver).executeScript("arguments[0].scrollIntoView(true);", ImgLinkHTML);
        ImgLinkHTML.click();
        timeSearch.until(ExpectedConditions.presenceOfElementLocated(ImgBookLocator));

        WebElement TxtTitle = chDriver.findElement(TxtTitleLocator);
        assertEquals("The title is correct","Thinking in HTML",TxtTitle.getText());

        WebElement TxtPriceNormal = chDriver.findElement(TxtPriceNormalLocator);
        WebElement TxtPriceOffer = chDriver.findElement(TxtPriceOfferLocator);

        Normalvalue = Integer.parseInt(convertValuesFormat(TxtPriceNormal.getText()));
        Offerlvalue = Integer.parseInt(convertValuesFormat(TxtPriceOffer.getText()));
        assertTrue("The normal value is less that offer value ", compareValue(Normalvalue,Offerlvalue));

        assertTrue("The content is not present.", chDriver.findElement(DivDescriptionLocator).isDisplayed());

    }

    public Boolean compareValue(int Normal, int Offer){
        Boolean Result=false;
        Result= Normal>Offer;
        return Result;
    }
    public String convertValuesFormat(String Text){
        String Result;
        Text=Text.replace(".", "");
        Result= Text.substring(1);
        return Result;
    }
    @AfterClass

    public void tearDown() {
        chDriver.close();
    }
}
