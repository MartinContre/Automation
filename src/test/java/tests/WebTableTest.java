package tests;

import base.BaseTest;
import models.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.ElementsWindow;
import pageobjects.FormPage;
import pageobjects.WebTablesPage;

import java.io.IOException;
import java.util.List;

public class WebTableTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(WebTableTest.class);
    @Test
    public void webTablesTest() throws IOException {

        List<Employee> employeesToAdd = testData.getEmployees();
        ElementsWindow elementsWindow = new ElementsWindow();
        WebTablesPage webTablesPage = new WebTablesPage();

        logger.info("Starting Web Tables Test");

        Boolean mainPageOpen = mainPage.isMainPageOpen();
        Assert.assertTrue(mainPageOpen, "Main page is not open");
        logger.debug("Main page is open");

        mainPage.scrollDown();

        mainPage.goToElementBtn();

        elementsWindow.clickWebTablesBtn();

        Assert.assertTrue(webTablesPage.isWebPagesDisplayed(), "Web Tables form is not open");
        logger.debug("Web table page is open");

        for (Employee employee : employeesToAdd) {
            webTablesPage.clickAddBtn();

            FormPage formPage = new FormPage();

            Boolean registrationFormDisplay = formPage.isRegistrationFormDisplayed();
            Assert.assertTrue(registrationFormDisplay, "Registration Form is not displayed");
            logger.debug("Registration form appeared");

            formPage.fillForm(employee);
            formPage.clickSubmitBtn();
        }

        List<Employee> employeeListGetFromWebTable = webTablesPage.getDataFromTable();

        int numberOfEntriesToCompare = employeesToAdd.size();

        for (int i = 0; i < numberOfEntriesToCompare; i++) {
            Employee employeeFromTable = employeeListGetFromWebTable.get(
                    employeeListGetFromWebTable.size() - numberOfEntriesToCompare + i);
            Employee expectedEmployee = employeesToAdd.get(i);

            Assert.assertEquals(employeeFromTable.getFirstName(), expectedEmployee.getFirstName(), "Name does not match");
            Assert.assertEquals(employeeFromTable.getLastName(), expectedEmployee.getLastName(), "Last name does not match");
            Assert.assertEquals(employeeFromTable.getAge(), expectedEmployee.getAge(), "Age does not match");
            Assert.assertEquals(employeeFromTable.getEmail(), expectedEmployee.getEmail(), "Email does not match");
            Assert.assertEquals(employeeFromTable.getSalary(), expectedEmployee.getSalary(), "Salary does not match");
            Assert.assertEquals(employeeFromTable.getDepartment(), expectedEmployee.getDepartment(), "Department does not match");
            logger.debug("Checked info for employee on web table");
        }

        webTablesPage.clickDeleteRecord4Btn();
        webTablesPage.clickDeleteRecord5Btn();

        List<Employee> updatedEmployeeListFromWebTable = webTablesPage.getDataFromTable();

        int numberOfRecords = updatedEmployeeListFromWebTable.size();
        int numberOfRecordExpected = testData.getRowsTable();
        Assert.assertEquals(numberOfRecords, numberOfRecordExpected, "The records in the table doesn't match");
        logger.debug("Number on records on table has changed: {}", numberOfRecords);

        Assert.assertFalse(updatedEmployeeListFromWebTable.contains(employeesToAdd.get(0)), "Expected employee's info is still in the table.");
        Assert.assertFalse(updatedEmployeeListFromWebTable.contains(employeesToAdd.get(1)), "Expected employee's info is still in the table.");
        logger.debug("Employees not more on web table");

        logger.info("Web Tables Test completed successfully");
    }

}
