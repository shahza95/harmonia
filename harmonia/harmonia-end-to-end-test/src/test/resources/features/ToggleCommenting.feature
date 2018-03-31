Feature: Lecturer disables commenting for lecture
    Let a lecturer be able to toggle an (active) lecture's commenting feature
    
Scenario: User will go to the active lecture page & click the disable button
	Given I created an active lecture
    When I click the disable button
    Then the student cannot post comments