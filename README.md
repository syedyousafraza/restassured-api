# API Automation with RestAssured

This repository contains a simple API automation project using RestAssured for testing RESTful APIs.

## Project Structure

api/ ├── pom.xml └── src └── test └── java ├── ApiTest1.java └── EmployeeTest.java


- **pom.xml**: Maven configuration file with dependencies for RestAssured and TestNG.
- **src/test/java**: Contains test classes (`ApiTest1.java`, `EmployeeTest.java`) that perform API calls and validations.

## Running Tests

To run the tests, navigate to the project directory and use the following command:

```bash
mvn test

mvn -Dtest=ApiTest1 test





