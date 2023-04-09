package TestRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\resources\\Features" ,
        glue = "StepDefinitions",
        dryRun = false,
        tags = "@smoke2",
         plugin = {"pretty"} //it makes your console more readable which class its executing
)
//tags use for= it will execute the specific test case with the same tag as mentioned in your feature file,we can use both too testcases
//tags= "@testcase1 or @testcase2" its just like grouping test just like testNG its called sanity testing
//dry run check which step of your feature file does not have glue code and if code is not there it will auto generate
//after adding code which is auto generated then put false instead of true otherwise it will not execute the test case
public class SmokeRunner {
}
// in tags we use and in that case if we have all the tags in one scenario e.g @tastcase1 and @testcase2 and @smoke"



/*The ways we can use data in our codes.

1.Hard code
2.property file
3.regular expression in " " double code like "admin" and "password"
4.scenario outline

    1. Parameterization allows you to pass values into your tests at runtime, rather than hardcoding them into the code itself.
    This makes your tests more flexible and less brittle, as you can modify the values without having to modify the code.

    2. Data Driven is when we use any excel sheet to fitch data or from any other external source into our code.
*/