package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//commit my code
public class HardCodedExamples {

    // This is my Base URI
    String base="";
   String baseURI= RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
   // //in postman is directly connected with that server but in here script in not connected
   // with server that's why we need to add http:
   // //bearer is a value of token then put token in it
   String token= "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODQ5NzE0ODgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTAxNDY4OCwidXNlcklkIjoiNTI0OCJ9.n-ivgMn0e5mD-AA7Fiio-RW-KCMZeoWgzLX26H2bEqA";
  static String employee_id;

  @Test
    public void bgetCreatedEmployee() {
        RequestSpecification preparedRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        // hitting the endpoint
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        // verify the response
        response.then().assertThat().statusCode(200);
     String tempEmpId= employee_id= response.jsonPath().getString("employee.employee_id");

     // we have 2 emp id one is global other one is local
      Assert.assertEquals(employee_id,tempEmpId); //comparing two id's here
    }

   @Test
   public void acreateEmployee(){
       //prepare request
       RequestSpecification prepareRequest= given().header("Content-Type","application/json")
               .header("Authorization",token).body("{\n" +
               "  \"emp_firstname\": \"Urwa\",\n" +
               "  \"emp_lastname\": \"Waseem\",\n" +
               "  \"emp_middle_name\": \"Ms\",\n" +
               "  \"emp_gender\": \"F\",\n" +
               "  \"emp_birthday\": \"1998-10-13\",\n" +
               "  \"emp_status\": \"Confirmed\",\n" +
               "  \"emp_job_title\": \"Engineer\"\n" +
               "}");

       //hitting the endpoint/send the req
       //after preparing the req you are going to hit end point and after hitting you will get response
       Response response= prepareRequest.when().post("/createEmployee.php");
          //it will print the output in the console(it will print response it doesn't allow u to write your own comment)
       response.prettyPrint();
       // verify the assertions/ get response
       response.then().assertThat().statusCode(201);
       //we are capturing emp id from the response
       employee_id= response.jsonPath().getString("Employee.employee_id");
       System.out.println(employee_id);

       //verifying the firstname in the response body
       response.then().assertThat().body("Employee.emp_firstname",equalTo("Urwa"));
       response.then().assertThat().body("Employee.emp_lastname",equalTo("Waseem"));

       //verify the response headers
       response.then().assertThat().header("Content-Type","application/json");
       System.out.println("test case is passed");
   }
   //in homeWork create a method to get all employee status and job title
    @Test
    public void cupdateEmployee(){
      RequestSpecification prepareRequest= given().header("Content-Type","application/json")
              .header("Authorization",token).body("{\n" +
                      "  \"employee_id\": \""+employee_id+"\",\n" +
                      "  \"emp_firstname\": \"Alayna\",\n" +
                      "  \"emp_lastname\": \"Hussnain\",\n" +
                      "  \"emp_middle_name\": \"Ms\",\n" +
                      "  \"emp_gender\": \"F\",\n" +
                      "  \"emp_birthday\": \"2018-05-20\",\n" +
                      "  \"emp_status\": \"probation\",\n" +
                      "  \"emp_job_title\": \"CEO\"\n" +
                      "}");
      // hitting the endpoint
        Response response= prepareRequest.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);

        //verification
        response.then().assertThat().body("Message",equalTo("Employee record Updated"));
    }
    @Test
    public void dgetUpdatedEmployee(){
        RequestSpecification preparedRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        // verify the response
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        //if you want to verify the body of the response.
        //you can do that using hamcrest matchers

    }
}
