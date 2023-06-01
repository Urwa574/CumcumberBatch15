Feature: Login Functionalities

  @smoke
  Scenario: Valid Admin login
   # Given open the browser and launch HRMS application .... THIS STEP IS MANAGE BY HOOKS
    When user enters valid email and valid password
    And click on login button
    Then user is logged in successfully
    #And close the browser  .... THIS STEP IS MANAGE BY HOOKS
    # for comment

  #HOOKS:FOR DEFINING PRE AND POST STEPS IN ANY CUCUMBER FRAMEWORK
  # this is always created inside the StepDef folder
  # this class can't be inherited


  @smoke2
  Scenario: Valid Admin login
   # Given open the browser and launch HRMS application .... THIS STEP IS MANAGE BY HOOKS
    When user enters valid "Admin" and valid "Hum@nhrm123"
    And click on login button
    Then user is logged in successfully
    #And close the browser  .... THIS STEP IS MANAGE BY HOOKS

  @scenarioOutline
  #parameterization/ Data Driven
  Scenario Outline:
       # Given open the browser and launch HRMS application .... THIS STEP IS MANAGE BY HOOKS
    When user enters valid "<username>" and valid "<password>"
    And click on login button
    Then user is logged in successfully
    #And close the browser
    Examples:
      | username | password    |
      | admin    | Hum@nhrm123 |
      | ADMIN    | Hum@nhrm123 |
      | Jason    | Hum@nhrm123 |

  @dataTable
  Scenario: Login with multiple credentials using data table
    When user enters username and password and verifies login
      | username | password    |
      | admin    | Hum@nhrm123 |
      | ADMIN    | Hum@nhrm123 |
      | Jason    | Hum@nhrm123 |