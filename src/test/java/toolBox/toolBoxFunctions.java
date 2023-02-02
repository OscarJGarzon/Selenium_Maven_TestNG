package toolBox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;


public class toolBoxFunctions {
    /**
     *
     *
     * @return
     */
    /**
     * This method is responsible for initializing the properties of the Drive browser for the execution of the different tests
     * @param Browser Browser Name will use
     * @param URL Url of APP
     * @param Headless Option if you want to see the browser execution
     * @return Driver, an object of type drive with the properties applied
     */

    public WebDriver setUp(String Browser, String URL,Boolean Headless) {
        WebDriver Driver = null;
        switch (Browser.toUpperCase()){
            case "CHROME":
                if(Headless==true){
                    ChromeOptions chromeOptions= new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
                    Driver = new ChromeDriver(chromeOptions);
                    Driver.get(URL);
                }else {
                    System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
                    Driver = new ChromeDriver();
                    Driver.manage().window().maximize();
                    Driver.get(URL);
                }
                break;
            case "FIREFOX":
                break;
            case "EDGE":
                break;
            default:break;
        }
        
        
        return Driver;
    }

    /**
     * This method is responsible for comparing two values, the Normal value must be greater than the value on offer.
     *
     * @param Normal, is the normal sale value
     * @param Offer,  is the value on offer
     * @return Result, es un valor de tipo boleano que es el resultado de la comparacion
     * @author Oscar J Garzon.
     */
    public Boolean compareValue(int Normal, int Offer) {
        Boolean Result;
        Result = Normal > Offer;
        return Result;
    }

    /**
     * This method is responsible for receiving a text string and applies a format to later convert it to an integer.
     *
     * @param Text, is a string of characters
     * @return Result, It is a string of characters with formatting applied.
     * @author Oscar J Garzon.
     */
    public String convertValuesFormat(String Text) {
        String Result;
        Text = Text.replace(".", "");
        Result = Text.substring(1);
        return Result;
    }

    /**
     * This function is responsible for preparing the data to obtain the date from a string
     *
     * @param format, Parameter to identify which format to apply
     * @param text,   String to convert to array.
     * @return array result
     */
    public String[] convertValueDate(String format, String text) {
        String[] Result = new String[2];

        switch (format) {
            case "mm-dd-yyyy":
                Result = text.split("-");
                break;
            case "dd/mm/yyyy":
                Result = text.split("/");
                break;
            default:
                break;
        }
        return Result;
    }

    /**
     * Function to get the day of the string
     *
     * @param dataTest String to get day.
     * @param format
     * @return string day
     */

    public String getDay(String dataTest, String format) {
        String day = null;
        String[] date = convertValueDate(format, dataTest);
        switch (format) {
            case "mm-dd-yyyy":
                day = date[1];
                break;
            case "dd/mm/yyyy":
                day = date[0];
                break;
            default:
                break;
        }
        return day;
    }

    /**
     * Function to get the month of the string
     *
     * @param dataTest String to get month.
     * @param format
     * @return string month
     */
    public String getMonth(String dataTest, String format) {
        String month = null;
        String[] date = convertValueDate(format, dataTest);
        switch (format) {
            case "mm-dd-yyyy":
                month = date[0];
                break;
            case "dd/mm/yyyy":
                month = date[1];
                break;
            default:
                break;
        }
        switch (month) {
            case "01":
                month = "January";
                break;
            case "02":
                month = "February";
                break;
            case "03":
                month = "March";
                break;
            case "04":
                month = "April";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "June";
                break;
            case "07":
                month = "July";
                break;
            case "08":
                month = "August";
                break;
            case "09":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "December";
                break;
            default:
                break;
        }
        return month;
    }

    /**
     * Function to get the year of the string
     *
     * @param dataTest String to get year.
     * @param format
     * @return string year
     */
    public String getYear(String dataTest, String format) {
        String year = null;
        String[] date = convertValueDate(format, dataTest);
        year = date[2];
        return year;
    }

    public void closeAD(WebDriver driver) {
        try {
            Thread.sleep(2000);
            WebElement frame1 = driver.findElement(By.id("aswift_1"));
            driver.switchTo().frame(frame1);
            driver.findElement(By.id("dismiss-button")).click();
            driver.switchTo().defaultContent();
            Thread.sleep(1000);
        } catch (Exception err) {
            err.printStackTrace();
        }
        try {
            WebElement frame1 = driver.findElement(By.id("ad_iframe"));
            driver.switchTo().frame(frame1);
            driver.findElement(By.id("dismiss-button")).click();
            driver.switchTo().defaultContent();
            Thread.sleep(1000);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}