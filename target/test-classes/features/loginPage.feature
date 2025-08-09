Feature: Login functionality for OrangeHRM Website
 
  As a Admin Of the OrangeHRM website
  I want to be able to log in with admin account
  So that I can access admin account-related features and manage the dashboard
  
#Background:
  # Given I am on the OrangeHRM login page
 
 
Scenario: Verify  login Page Title
   Then  I should get the Page Title

 
Scenario Outline: Successful login with valid credentials
	 Given I have entered a valid username "<uname>" and password "<pswd>"
	 When I click on the login button
	 Then I should logged in successfully "<success_message>"
Examples:
|uname         | pswd        | success_message |
|Admin         | admin123 |Successfull Logged In | 

 
Scenario Outline: Unsuccessful login with invalid or empty credentials
   Given I have entered invalid  username "<username>" and  username "<password>"
   When I click on the login button
   Then I should see an error message indicating   
Examples:
| username         | password        |
|Admin             | invalidPassword |
|invalidPassword   | admin@123       |


#Scenario: Navigation to the forgotten password page
    #When I click on the Forgot your password? link
    #Then I should be restricted to the Reset Password page
    
Scenario: Logout from OrangeHRM
  Given I have entered a valid username "<uname>" and password "<pswd>"
  When I click on the login button
	Then I should logged in successfully "<success_message>"
	When User clicks on the profile icon
  And User clicks on the Logout option
  Then User should be redirected to the OrangeHRM login page
Examples:
|uname         | pswd        | success_message |
|Admin         | admin123 |Successfull Logged In | 
   