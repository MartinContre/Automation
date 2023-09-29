package pageobjects;

import base.BaseForm;
import elements.Button;
import elements.FormField;
import models.Employee;
import org.openqa.selenium.By;
import utils.ExplicitWait;

import java.io.IOException;

public class FormPage extends BaseForm {
    private final By formLocator = By.id("userForm");
    private final By firstNameLocator = By.id("firstName");
    private final By lastNameLocator = By.id("lastName");
    private final By emailLocator = By.id("userEmail");
    private final By ageLocator = By.id("age");
    private final By salaryLocator = By.id("salary");
    private final By departmentLocator = By.id("department");
    private final By submitBtnLocator = By.id("submit");

    private final FormField nameField;
    private final FormField lastNameField;
    private final FormField emailField;
    private final FormField ageField;
    private final FormField salaryField;
    private final FormField departmentField;

    private final ExplicitWait wait;
    public FormPage() throws IOException {
        nameField = new FormField(firstNameLocator, "First name");
        lastNameField = new FormField(lastNameLocator, "Last name");
        emailField = new FormField(emailLocator, "Email");
        ageField = new FormField(ageLocator, "Age");
        salaryField = new FormField(salaryLocator, "Salary");
        departmentField = new FormField(departmentLocator, "Department");
        wait = new ExplicitWait();
    }

    public void clickSubmitBtn() throws IOException {
        Button btn = new Button(submitBtnLocator, "Submit button");
        btn.clickOnBtn();
    }

    public void fillForm(Employee employee) {
        nameField.fillForm(employee.getFirstName());
        lastNameField.fillForm(employee.getLastName());
        emailField.fillForm(employee.getEmail());
        ageField.fillForm(String.valueOf(employee.getAge()));
        salaryField.fillForm(String.valueOf(employee.getSalary()));
        departmentField.fillForm(employee.getDepartment());
    }

    public Boolean isRegistrationFormDisplayed() {
        wait.waitForPresenceOfElement(formLocator);
        return isFormOpen(formLocator);
    }
}
