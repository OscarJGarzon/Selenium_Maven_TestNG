package toolBox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class toolBoxFunctions {
    /**
     * This method is responsible for initializing the properties of the Drive browser for the execution of the different tests
     * @param Driver, is the object to which the properties will be applied
     * @return Driver, an object of type drive with the properties applied
     */

    public WebDriver setUp(WebDriver Driver) {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
        Driver = new ChromeDriver();
        Driver.manage().window().maximize();
        Driver.get("https://practice.automationtesting.in/");
        return Driver;
    }

    /**
     * This method is responsible for comparing two values, the Normal value must be greater than the value on offer.
     * @param Normal, is the normal sale value
     * @param Offer, is the value on offer
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
}