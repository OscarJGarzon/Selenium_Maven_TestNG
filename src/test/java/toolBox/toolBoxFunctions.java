package toolBox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.TestNG;
import org.testng.TestRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

public class toolBoxFunctions {

    public WebDriver setUp(WebDriver Driver) {
        setOutputDirectory();
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
        Driver = new ChromeDriver();
        Driver.manage().window().maximize();
        Driver.get("https://practice.automationtesting.in/");
        return Driver;
    }

    public Boolean compareValue(int Normal, int Offer) {
        Boolean Result = false;
        Result = Normal > Offer;
        return Result;
    }

    public String convertValuesFormat(String Text) {
        String Result;
        Text = Text.replace(".", "");
        Result = Text.substring(1);
        return Result;
    }
// verficar esta solucion de funcion
    public void setOutputDirectory() {
        Path path = Paths.get("");
        char Backslash  = (char) 92;
        String currentlyDirectoryName = path.toAbsolutePath().toString();
        String repoDirectoryName = "testNGReports";
        String repo =  currentlyDirectoryName +Backslash+ repoDirectoryName;
        System.out.println("The report Directory is = " + repo);

        TestNG testng = new TestNG();
        testng.setOutputDirectory(repo);
    }
}