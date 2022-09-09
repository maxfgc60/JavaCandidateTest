
Introduction
============
Greetings!
This project is a bare-bones Spring Boot RESTful webservice, it is intended to be a simple backend to an online quiz application.


Build and Run
=============
Required: Java JDK11, Maven

The project is built with Maven with and can be packaged like so;
$ mvn clean package

The site can be run from the main class;
com.stepstone.quiz.QuizApplication

Or executed from the runnable jar;
$ java -jar quiz-0.0.1-SNAPSHOT.jar


Features to Implement
=====================
Please try adding the following features (feel free to make any code improvements and refactoring you deem necessary):

1) Feature One
As an api consumer,
I want the /questions endpoint to return all questions available in the question repository.
So that I can retrieve the questions available for a quiz.

2) Feature Two
As an api consumer,
I want an endpoint to check a given answer for any available question is correct.
So that I can validate the answers for each question in the quiz.

3) Feature Three
As an api consumer,
I want to retrieve a different set of questions depending on the type of quiz e.g. Geography Quiz or Science Quiz
So that I can support providing different types of quiz.

Good luck!
