Feature: Student sends an emoji
    Let a student be able to send an emoji on an (active) joined lecture to express their mood
    
Scenario: User will go to the active lecture mood page, enter an emoji and click send button
	Given I am in an active lecture
    When I enter an emoji and click the send button
    Then the lecturer can see the emoji