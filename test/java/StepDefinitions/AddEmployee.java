package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddEmployee extends CommonMethods {
    @When("user clicks on pim option")
    public void user_clicks_on_pim_option() {
       WebElement pimTab= driver.findElement(By.id("menu_pim_viewPimModule"));
       DoClick(pimTab);
    }
    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
    WebElement addEmpBtn= driver.findElement(By.id("menu_pim_addEmployee"));
    DoClick(addEmpBtn);
    }
    @When("user enters firstname and middlename and lastname")
    public void user_enters_firstname_and_middlename_and_lastname() {

       // driver.findElement(By.id("firstName")).sendKeys(ConfigReader.getPropertyValue("firstname"));

        WebElement firstNameTextBox=driver.findElement(By.id("firstName"));

        sendText(firstNameTextBox,ConfigReader.getPropertyValue("firstname"));
        //we added names in config.property and import code from configReader

        //driver.findElement(By.id("middleName")).sendKeys(ConfigReader.getPropertyValue("middlename"));

        WebElement midleName= driver.findElement(By.id("middleName"));
        sendText(midleName,ConfigReader.getPropertyValue("middlename"));

      //  driver.findElement(By.id("lastName")).sendKeys(ConfigReader.getPropertyValue("lastname"));
        WebElement lastName= driver.findElement(By.id("lastName"));
        sendText(lastName,ConfigReader.getPropertyValue("lastname"));
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
       WebElement saveBtn= driver.findElement(By.id("btnSave"));
       DoClick(saveBtn);
    }

}
