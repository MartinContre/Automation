# Selenium WebDriver Test Project 💻

This project implements a basic testing framework for web automation using Selenium WebDriver. The framework includes several components and follows best practices in test automation.

## Features 🔧

- **BaseForm Class**: A base class for all forms/pages, ensuring consistent behavior across pages.
- **BaseElement Class**: Base class for web elements, with child classes for specific element types.
- **Singleton & BrowserFactory**: Implements the Singleton pattern and Factory Method to manage the driver instance and choose between Chrome and Firefox browsers.
- **Utility Class**: A utility class to work with the WebDriver and perform common actions.
- **ConfigManager Class**: Handles configuration data for the project.
- **Page Object Pattern**: Utilizes the Page Object pattern for organizing page elements and actions.
- **Pre- and Post- conditions**: Implements pre- and post- conditions for test cases.
- **Logger**: Incorporates logging to track and report test execution.
- **Data-Driven Testing (DDT)**: Includes at least one test case that uses a Data-Driven Testing approach and is parametrized.
- **Test and Configuration Data Separation**: Stores test data and configuration data in separate files.
- **Solution Structure**: Organizes the project structure into different folders and namespaces/packages for clarity.

## Additional Features 🛠️

- **Data Models**: Implements work with data models to enhance test data management.
- **Composition Principle**: Uses composition principles to work with forms on pages.

## Obligatory Test Cases ✅

### Test Case 1: Alerts

1. Navigate to the main page.
    - Expected Result: The main page is open.
2. Click on the "Alerts, Frame & Windows" button, then in the menu, click "Alerts."
    - Expected Result: The Alerts form has appeared on the page.
3. Click on the "Click Button to see alert" button.
    - Expected Result: An alert with the text "You clicked a button" is open.
4. Click the "OK" button.
    - Expected Result: The alert has closed.
5. Click on the "On button click, confirm box will appear" button.
    - Expected Result: An alert with the text "Do you confirm action?" is open.
6. Click the "OK" button.
    - Expected Result: The alert has closed, and the text "You selected Ok" has appeared on the page.
7. Click on the "On button click, prompt box will appear" button.
    - Expected Result: An alert with the text "Please enter your name" is open.
8. Enter randomly generated text, click the "OK" button.
    - Expected Result: The alert has closed, and the appeared text matches the entered text.

### Test Case 2: Iframe

1. Navigate to the main page.
    - Expected Result: The main page is open.
2. Click on the "Alerts, Frame & Windows" button, then in the menu, click "Nested Frames."
    - Expected Result: The page with Nested Frames form is open, and messages "Parent frame" & "Child Iframe" are present on the page.
3. Select "Frames" option in a left menu.
    - Expected Result: The page with Frames form is open, and the message from the upper frame is equal to the message from the lower frame.

### Test Case 3: Tables

1. Navigate to the main page.
    - Expected Result: The main page is open.
2. Click on the "Elements" button, then in the menu, click "Web Tables."
    - Expected Result: The page with Web Tables form is open.
3. Click on the "Add" button.
    - Expected Result: The Registration Form has appeared on the page.
4. Enter data for User № from the table and then click the "Submit" button.
    - Expected Result: The Registration form has closed, and the data of User № has appeared in a table.
5. Click the "Delete" button in a row containing User № data.
    - Expected Result: The number of records in the table has changed, and the data of User № has been deleted from the table.

### Test Case 4: Handles

1. Navigate to the main page.
    - Expected Result: The main page is open.
2. Click on the "Alerts, Frame & Windows" button, then in the menu, click "Browser Windows."
    - Expected Result: The page with Browser Windows form is open.
3. Click on the "New Tab" button.
    - Expected Result: A new tab with a sample page is open.
4. Close the current tab.
    - Expected Result: The page with Browser Windows form is open.
5. In the menu on the left, click "Elements" -> "Links" button.
    - Expected Result: The page with Links form is open.
6. Click on the "Home" link.
    - Expected Result: A new tab with the main page is open.
7. Resume to the previous tab.
    - Expected Result: The page with Links form is open.

## How to Run Tests 🚀

1. Configure the necessary files, including `config_data.json`.
2. Choose the browser (Chrome or Firefox) in your test configuration.
3. Execute test cases.

## Author 👤

Martin Perez