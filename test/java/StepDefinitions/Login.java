package StepDefinitions;

import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Login extends CommonMethods {
//    @Given("open the browser and launch HRMS application")
//    public void open_the_browser_and_launch_hrms_application() {  already done in hooks and added in common so everytime it will execute
//        openBrowserAndLaunchApplication();
//
//    }

    @When("user enters valid email and valid password")
    public void user_enters_valid_email_and_valid_password() {
      //  LoginPage login = new LoginPage();
        sendText(login.usernameTextBox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordTextBox, ConfigReader.getPropertyValue("password"));
        //for this step we made config properties files and config reader file
      //  driver.findElement(By.id("txtUsername")).sendKeys(ConfigReader.getPropertyValue("username"));
     //  WebElement userNameTextBox= driver.findElement(By.id("txtUsername"));
      // sendText(userNameTextBox,ConfigReader.getPropertyValue("username"));
        //driver.findElement(By.id("txtPassword")).sendKeys(ConfigReader.getPropertyValue("password"));
      //  WebElement passwordTextBox = driver.findElement(By.id("txtPassword"));
      //  sendText(passwordTextBox, ConfigReader.getPropertyValue("password"));

    }

    @When("click on login button")
    public void click_on_login_button() {
      //  LoginPage login=new LoginPage();
      // WebElement login= driver.findElement(By.id("btnLogin"));
       DoClick(login.loginBtn);
    }

    @Then("user is logged in successfully")
    public void user_is_logged_in_successfully() {
        boolean userloggedIn = driver.findElement(By.xpath("//a[contains(text(), 'Welcome')]")).isDisplayed();
        if (userloggedIn) {
            System.out.println("User is logged in Successfully");
        }
    }
//    @Then("close the browser")
//    public void close_the_browser() {
//      closeBrowser();
//    }
@When("user enters valid {string} and valid {string}") //this way is called regular expression
public void user_enters_valid_and_valid(String username, String password) {
   // WebElement usernameTextBox = driver.findElement(By.id("txtUsername"));
   // LoginPage login=new LoginPage();
    sendText(login.usernameTextBox, username);

  //  WebElement passwordTextBox = driver.findElement(By.id("txtPassword"));

    sendText(login.passwordTextBox, password);
}
//this is another way to enter password and username
// phly humy Config Reader se data fetch krna parta tha send keys krni parti thi
// by this way we can avoid congif files this is the option its up to your framework
// yha hum ne scenario ma username password dala in cucumber this is possible to do cucumber fetches this data from feature file
// this is inbuilt functionality of cucumber


    //tidy gherkin is a third party tool this can generate your code in reg expression

    @When("user enters username and password and verifies login")
    public void user_enters_username_and_password_and_verifies_login(DataTable dataTable) {
       // LoginPage login =new LoginPage(); coz we made a method for this
        List<Map<String, String>> userCredentials=dataTable.asMaps();
        for (Map<String ,String> userCreds: userCredentials){
            String username=userCreds.get("username");
            String password= userCreds.get("password");

            //WebElement usernameTextBox = driver.findElement(By.id("txtUsername"));
            sendText(login.usernameTextBox, username);
           // WebElement passwordTextBox = driver.findElement(By.id("txtPassword"));
            sendText(login.passwordTextBox, password);

          //  WebElement loginBtn= driver.findElement(By.id("btnLogin"));
            DoClick(login.loginBtn);
           // WebElement welcomeIcon= driver.findElement(By.id("welcome"));
            DoClick(login.welcomeIcon);

          //  WebElement logoutBtn= driver.findElement(By.xpath("//a[text()='Logout']"));
            DoClick(login.logoutLink);
        }
    }
   // #hard Code
  //  # config file
  //  ===============
          //  #Cucumber Expression [ limited set of test data]
          //  #Scenario Outline    [Parametrization],[Data Driven Testing][Browser will open and close number of times we have test data in Examples table]
         //   #data Table

  //  # Page Object Model : An object Repository [ It stores or holds all the webElements specific to a particular page]

}
