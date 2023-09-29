package pageobjects;

import base.BaseForm;
import elements.Button;
import elements.WebTables;
import models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class WebTablesPage extends BaseForm {
    private final By nextBntLocator = By.className("-next");
    private final By addBtnLocator = By.id("addNewRecordButton");
    private final By deleteRecord4Locator = By.id("delete-record-4");
    private final By deleteRecord5Locator = By.id("delete-record-5");
    private final By tableLocator = By.className("rt-table");
    private final By rowsLocator = By.className("rt-tr");
    private final By cellsLocator = By.className("rt-td");

    public WebTablesPage() throws IOException {
    }

    public Boolean isWebPagesDisplayed() {
        return isFormOpen(nextBntLocator);
    }

    public void clickAddBtn() throws IOException {
        Button btn =new Button(addBtnLocator, "Add button");
        btn.clickOnBtn();
    }

    public List<Employee> getDataFromTable() throws IOException {
        WebTables webTables = new WebTables(tableLocator, "Table");
        WebElement table = webTables.table();
        List<WebElement> rows = table.findElements(rowsLocator);

        rows = webTables.filterEmptyRows(rows, cellsLocator);

        return webTables.getRowsFromTable(rows, cellsLocator);
    }

    public void clickDeleteRecord4Btn() throws IOException {
        Button btn = new Button(deleteRecord4Locator, "Delete record 4");
        btn.clickOnBtn();
    }

    public void clickDeleteRecord5Btn() throws IOException {
        Button btn = new Button(deleteRecord5Locator, "Delete record 5");
        btn.clickOnBtn();
    }
}
