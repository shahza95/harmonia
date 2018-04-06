Feature: Student posts a question & lecturer answers
    Let a student be able to send a question on an (active) joined lecture and the lecturer to reply
    
Scenario: Student will go to the active lecture questions page, enter a question and click ask button then lecturer will go to question thread page, write an answer and press answer
	Given I am inside an active lecture question page
    When I enter a question and click the ask button and the lecturer answers
    Then I see the question thread with the answer