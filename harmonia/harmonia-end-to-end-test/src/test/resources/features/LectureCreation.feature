Feature: Lecturer create lecture
    Let lecturer create a lecture
    
Scenario: User will go to the lecture creation page, enter details and click create button
    When I create a lecture starting now
    Then I get redirected to the active lecture page