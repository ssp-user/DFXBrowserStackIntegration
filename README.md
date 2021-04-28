# Introduction
TODO: This is cucumber test framework set up for DFX

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.intellij idea
2.set up jdk 1.8
3.install maven
4.mvn clean install from the project root 
5.run mvn test -Dtest=nameofTESTRUNNER

# Build and Test
TODO: Describe and show how to build your code and run the tests.
on the Intellij IDEA or eclipse: run the testrunner
on command line or Jenkin: run   mvn test -Dtest=nameofTESTRUNNER

# Contribute
TODO: Explain how other users and developers can contribute to make your code better.

#pending test coverage
TODO: Advisor Dashboard, Advisor Ledger, routesheet
Techician Dashboard:
1. Manually create an EVIR  //done
2. Use a technician to add 1 recommended service in inspection tab. //done
3. Check the new recommended service display in recommended tab  //done
4. check all mandatory inspection //To do
5. Mark inspection complete. //To do
6. Check EVIR Statsu goes to "Completed" //To do

#Gherkin language
TODO: understand Given When Then
Cucumber scenarios consist of steps, also known as Givens, Whens and Thens.
Definition
The Given-When-Then formula is a template intended to guide the writing of acceptance tests for a User Story:

(Given) some context
(When) some action is carried out
(Then) a particular set of observable consequences should obtain
An example:

Given my bank account is in credit, and I made no withdrawals recently,
When I attempt to withdraw an amount less than my card's limit,
Then the withdrawal should complete without errors or warnings

Example:
Scenario: Multiple Givens
  Given one thing
  Given another thing
  Given yet another thing
  When I open my eyes
  Then I see something
  Then I don't see something else


