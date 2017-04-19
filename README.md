# Highwire Java developer interview test

Welcome to the Highwire interview test. Please spend roughly three hours working on these challenges.

# How it works
The project is small a Spring Boot application that runs with an in-memory database. Spring will get JPA to generate the database tables on start-up, so there is no SQL for this project.  The focus here is on writing Java code.

The system revolves around simple Fact entities. Each Fact has some text, and a category.

# Getting started
You will require the following installed in your computer:

* Git
* Java 8
* Maven (3.2+)

Navigate to the project directory (on your command line) and run

    mvn clean verify

You will notice there are test failures.

To run the application, run

    mvn spring-boot:run

Or alternatively, run the main method in `InterviewChallengeApplication`

# The test
Please work through the levels in order, committing your changes to git as you progress.

## Level 0 - project setup
Create a git repository in the project. Commit your work to this project as you go, as if you were working on a real project.

## Level 1 - fix application
The tests in InterviewChallengeApplicationITCase should all pass.

You are required to change the application code (in src/main) to fulfil the requirements of the tests.

Do not change the assertions of the tests themselves (but feel free to add more if you think it would be appropriate).

## Level 2 - add new functionality
Please implement the following feature. Consider unit and integration tests in your answer.

1. Implement an endpoint that delivers a JSON-based report of the number of facts in the system for each fact category. Sort the fact categories alphabetically. For example: `[{ "category": "aeroplanes", "count": 4}, { "category": animals", "count": 5 }]`


# Wrap up
Thank you for taking part in the test.

Please return your submission as a .zip file to us.

We will analyse your submission and discuss your solutions in the interview, so keep this project fresh in your mind.
