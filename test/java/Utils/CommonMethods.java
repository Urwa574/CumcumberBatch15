package Utils;

import StepDefinitions.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CommonMethods extends PageInitializer {
    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties();

        //trying to handle multiple browsers with the usage of switch case
        String browserType = ConfigReader.getPropertyValue("browserType");
        switch (browserType) {
            case "Chrome":
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--no-sandbox");
                ops.addArguments("--remote-allow-origins=*");
                if(ConfigReader.getPropertyValue("Headless").equals("true")){
                    ops.addArguments("--headless=new"); //if it sets to true it will not open browser but test runs
                }
                driver = new ChromeDriver(ops);
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;

            case "IE":
                driver = new InternetExplorerDriver();
                break;

            default:
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url")); //config prop
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.Wait_Time));  //constants class
        initializePageObjects(); // this will initialize all the pages we have in our page
        // PageInitializer class along with the launching of application

        //to configure a file and pattern it has
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("This is the beginning of my test case");
        Log.info("My test case is executing write now");
        Log.warning("my test case might have some trivial issues");


    }

    public static void closeBrowser() {
        Log.info("This test case is about to get completed");
        Log.endTestCase("This test case is finished");
        driver.close();
    }


    public static void DoClick(WebElement element) {
        element.click();
    }

    public static void sendText(WebElement element, String text) {
        element.clear(); //first clear the text box if there is any text
        element.sendKeys(text); //made generic methods here
    }

    public static Select clickOnDropDown(WebElement element) {
        Select select = new Select(element);
        return select;
    }

    public static void selectByValue(WebElement element, String value) {
        clickOnDropDown(element).selectByValue(value);
    }

    public static void selectByVisibleText(WebElement element, String text) {
        clickOnDropDown(element).selectByVisibleText(text);
    }

    public static void selectByVisibleIndex(WebElement element, int index) {
        clickOnDropDown(element).selectByIndex(index);
    }

    public static void selectByOptions(WebElement element, String text) {
        List<WebElement> options = clickOnDropDown(element).getOptions();
        for (WebElement option : options) {
            String ddlOptionText = option.getText();
            if (ddlOptionText.equalsIgnoreCase(text)) {
                option.click();
            }
        }
    }
  //  --------ScreenShot---------------
  public static byte[] takeScreenshot(String imageName)
  {
      // This casts the webDriver instance 'driver' to TakeScreenshot Interface
      TakesScreenshot ts = (TakesScreenshot)driver;

      //This captures the screenshot and stores it as byte array
      byte[] picBytes=ts.getScreenshotAs(OutputType.BYTES);//(return type is going to be byte and this line returns byte of ss)

      //This captures the screenshot and stores it as a file in the sourceFile variable
      File sourcePath=ts.getScreenshotAs(OutputType.FILE);

      try {
          FileUtils.copyFile(sourcePath, new File(Constants.SCREENSHOT_FILEPATH+imageName+getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
          //saving in constant +pic name +date time year mins(upper and lower is just to differentiate)+ sec and than +png
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
      return picBytes;
  }
  public  static  String getTimeStamp(String pattern)
  {
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      return sdf.format(date);
  }





}