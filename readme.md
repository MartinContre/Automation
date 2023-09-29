# Database Connection Project 💻

This project connects to a MySQL database named `union_reporting` and uses CRUD operations to perform various actions on the database.


## Description 📄

The project connects to a MySQL database and performs CRUD operations to manage test data. It includes test scenarios for adding, updating, and deleting records in the database.

## Test Scenario 📋

### Test Case 1: Running Tests 🏃

1. Run any test (or several) and add to the database.

### Test Case 2: Processing Test Data 🔄

1. Select tests from the database where the ID contains two random repeating digits, for example, "11" or "77," but no more than 10 tests.

2. Copy these tests with an indication of the current project and the author.

3. Simulate the launch of each test. Update information about them in the database.

4. Delete the records created in Preconditions from the database.

## Prerequisites 📦

Before using this project, ensure you have the following prerequisites:

- Run `dump.sql` on your favorite database manager, like MySQL Workbench.

## Usage 🚀

To use this project, follow these steps:

1. Run the `dump.sql` script located in the project's root folder to set up the database schema.

2. Update the `db_credentials.json` file with your MySQL database username and password.

3. Update the `config_data.json` file with the URL of the database connection.

4. Run the Java classes provided in your IDE or using a build tool like Maven.

## Dependencies 🛠️

This project uses the following dependencies, which are included in the project's `pom.xml` file:

- `mysql-connector-java` for MySQL database connectivity.
- `testng` for testing purposes.
- `lombok` for reducing boilerplate code.
- `aquality-selenium` for reading JSON files.
- `log4j` for logging.

## Configuration ⚙️

Make sure to configure the following files before running the tests:

- `db_credentials.json`: Provide your MySQL database username and password.
- `config_data.json`: Set the URL for the database connection.

## Contributing 🤝

If you want to contribute to this project, please follow the standard GitHub flow: Fork, make changes, and create a pull request. Your contributions are welcome!

## Author 👤

**Martin Perez**

