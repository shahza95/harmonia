Feature: Lecturer ends lecture & student sends end of lecture feedback
    Let a lecturer end the lecture & let the student be able to send a rating and written feedback
    
Scenario: Lecturer will end an active lecture, student will build feedback and submit
	Given the lecturer ends the lecturer
    When I enter feedback and click submit
    Then the lecturer should see my feedback