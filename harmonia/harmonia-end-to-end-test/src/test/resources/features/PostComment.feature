Feature: Student posts a comment
    Let a student be able to comment on an (active) joined lecture
    
Scenario: User will go to the active lecture page, enter a message and click comment button
	Given I have joined an active lecture
    When I enter a message and click the comment button
    Then I see my comment appear in the table