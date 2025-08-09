Feature: Admin functionality for OrangeHRM Website


Background:
  Given user has already logged in to application
   |username|password|
   |Admin         | admin123 |
   When User clicks on the Admin tab from the menu
   And Admin panel page should display Admin / User Management options

 
Scenario Outline: Add a new user from Admin Panel
		   When User clicks on the add button
		   And User selects user role "<User Role>"
		   And User enters employee name "<Employee Name>"
		   And User selects status "<Status>" 
		   And User enters username "<Username>"
		   And User enters password "<Password>" 
		   And User enters confirm password "<Confirm Password>"
		   And User clicks on the save button
		   Then The system should show a success message "Successfully Saved"
		   And The new user "<Employee Name>" should appear in the user list
Examples:
|User Role|Employee Name|Status  |Username      |Password    |Confirm Password|
|ESS      |Radha Gupta  |Enabled |radha1234     |test@12345  |test@12345      |
#|Admin    |Sarah Smith  |Disabled |Smith1234     |Password123 |Password123     |
#|ESS      |Jhon doe     |Disabled|Jhon123       |Jhon@123    |Jhon@123        |
#|Admin    |Paul Collings|Enabled |PaulCollings123 |Password@123|Password@123   |
#|Admin    |Rocky Pachai |Disabled|Panchai       |Panchai@123 |Panchai@123     |
#|Admin|Anushree Kamble|Enabled|anushree123|anushree123| anushree123|
#Scenario for the Search Functionality 

Scenario Outline: Search for an existing user in Admin Panel
    When User enters username "<Search_User>" in the search field
    And User clicks on the search button
    Then The system should display user details "<Search_User>"
     
Examples:
  |Search_User|
  #|smith1234|
  |radha1234|


Scenario Outline: Verify search functionality By UserRole
		And User selects user role "<User Role>"
    And User clicks on the search button
    Then I should see the results matching all fields by user role "<User Role>" 
    
Examples:
  |User Role|
  |ESS| 
  |Admin|
  
  

Scenario Outline: Verify search functionality By Employee Name
		And User enters employee name "<Employee Name>"
    And User clicks on the search button
    Then I should see the results matching all Employee fields "<Employee Name>" 
    
Examples:
  |Employee Name|
  |Radha Gupta| 
  
 

Scenario Outline: Verify search functionality By Status
		And User selects status "<Status>" 
    And User clicks on the search button
    Then I should see the results matching all Status fields "<Status>"  
    
Examples:
  |Status|
  |Enabled|
 # |Disabled| 
 
 
Scenario Outline: Verify search functionality in Admin User Management
    When User enters username "<Search_User>" in the search field
		And User selects user role "<User Role>"
		And User enters employee name "<Employee Name>"
    And User selects status "<Status>" 
    And User clicks on the search button
    Then I should see the results matching all fields "<Search_User>""<User Role>" "<Employee Name>""<Status>"
Examples:
|Search_User |User Role|Employee Name|Status | 
|radha1234   |ESS    |Radha Gupta  |Enabled| 

Scenario Outline: Verify reset button clears the search fields
    When User enters username "<Search_User>" in the search field
		And User selects user role "<User Role>"
		And User enters employee name "<Employee Name>"
    And User selects status "<Status>" 
    And User clicks on Reset button
    Then All search fields should be cleared
Examples:
|Search_User |User Role|Employee Name|Status | 
|radha1234   |ESS    |Radha Gupta  |Enabled| 


#Scenario: Delete a user from Admin Panel
    #Given User is on the Admin panel page
    #When User searches for username "linda.anderson"
    #And Selects the user "linda.anderson" from the list
    #And Clicks on the "Delete" button
    #And Confirms the deletion
    #Then The system should show a success message "Successfully Deleted"
    #And The user "linda.anderson" should no longer appear in the user list

