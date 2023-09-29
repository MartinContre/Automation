package elements;

import base.BaseElement;
import models.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class WebTables extends BaseElement {
    private static final Logger logger = LogManager.getLogger();

    public WebTables(By locator, String name) throws IOException {
        super(locator, name);
        logger.info("Web tables instance created");
    }

    public List<Employee> getRowsFromTable(@NotNull List<WebElement> rows, By cellsLocator) {
        logger.info("Got rows from table.");
        return rows.stream()
                .map(row -> {
                    List<WebElement> cells = row.findElements(cellsLocator);
                    String firstName = cells.get(0).getText();
                    String lastName = cells.get(1).getText();
                    int age = Integer.parseInt(cells.get(2).getText());
                    String email = cells.get(3).getText();
                    int salary = Integer.parseInt(cells.get(4).getText());
                    String department = cells.get(5).getText();
                    return new Employee(firstName, lastName, email, age, salary, department);
                })
                .collect(Collectors.toList());
    }

    public List<WebElement> filterEmptyRows(@org.jetbrains.annotations.NotNull List<WebElement> rows, By cellLocator) {
        logger.info("Filtering empty rows from table");
        return rows.stream()
                .filter(row -> row.findElements(cellLocator)
                        .stream()
                        .anyMatch(cell -> !cell.getText().trim().isEmpty()))
                .collect(Collectors.toList());
    }

    public WebElement table() {
        logger.info("Finding table: {}", getName());
        return findElementByLocator(getLocator());
    }
}
