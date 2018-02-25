Feature: Lecturer log in
    Allow log in only if valid credentials
    
Scenario: User will go to the login page, enter their credentials and click the login button
	Given I am a valid user
    When I enter my credentials and click the login button
    Then I get redirected to the success page