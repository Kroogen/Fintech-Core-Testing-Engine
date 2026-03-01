# Fintech Core Testing Engine

## Project Description
This repository contains the automation framework with the main credits evaluation engine of a Fintech. The main objective is show abilities applying software engineer standards and ISTQB tests design

## Configuration
* **Language:** Java 17 (Amazon Corretto)
* **Dependencies administrator:** Maven
* **Tests Framework:** TestNG
* **Logging:** Log4j2
* **Reports:** Allure Reports
* **Data Management:** JSON-based Data-Driven Testing with Jackson Serialization
* **CI/CD:** GitHub Actions

## Arcquitechture
The project is created under a modular structure:
* `src/main/java`: Contains the **SUT (System Under Test)**, simulating the business logic in a bank application.
* `src/test/java`: Contains the Tests suite, using design patterns and advanced assertions.
* `src/test/resources`: Stores configuration files and external datasets in JSON format.

## Advanced Testing Strategies
1. **Boundary Value Analysis (BVA):** Applying the age and credit score rules.
2. **Equivalence Partition:** Validate the incomes and debt ratio.
3. **Data-Driven Testing(DDT) with JSON** Test data is fully decoupled from code. Use external JSON files to define complex scenarios, which are mapped into Java objects via Jackson.

## How to Run and View Reports
1. **Execute Tests:**
   ```bash
   mvn clean test
2. **Generate Allure Report:**
   ```bash
   mvn allure:serve
