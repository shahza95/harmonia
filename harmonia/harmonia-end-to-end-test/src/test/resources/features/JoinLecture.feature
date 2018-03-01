Feature: Student joins a lecture
    Let a student be able to join an active lecture
    
Scenario: User will go to the join lecture page, enter a password and click join button
	Given there exists an active lecture
    When I enter the valid password
    Then I get redirected to that active lecture