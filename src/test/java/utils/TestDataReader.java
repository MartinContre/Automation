package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.Employee;
import models.TestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestDataReader {
    private JsonNode configNode;
    private static final Logger logger = LogManager.getLogger(TestDataReader.class);

    public TestDataReader(String filePath) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            byte[] bytes = inputStream.readAllBytes();
            String jsonString = new String(bytes, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            configNode = objectMapper.readTree(jsonString);
        } catch (FileNotFoundException e) {
            logger.error("File not found " + e);
        } catch (IOException e) {
            logger.error("Error while reading file: " + filePath, e);
        }
    }

    public TestData getConfig() {
        TestData testData = new TestData();

        JsonNode employeeNode = configNode.path("employees");
        if (employeeNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) employeeNode;
            List<Employee> employees = new ArrayList<>();
            for (JsonNode node: arrayNode) {
                String firstName = node.path("firstName").asText();
                String lastName = node.path("lastName").asText();
                String email = node.path("email").asText();
                int age = node.path("age").asInt();
                int salary = node.path("salary").asInt();
                String department = node.path("department").asText();
                Employee employee = new Employee(firstName, lastName, email, age, salary,
                        department);
                employees.add(employee);
            }
            testData.setEmployees(employees);
        }
        JsonNode btnAlertNode = configNode.path("btnAlert");
        if (btnAlertNode.isTextual()) {
            testData.setBtnAlert(btnAlertNode.asText());
        }
        JsonNode btnClickNode = configNode.path("btnClick");
        if (btnClickNode.isTextual()) {
            testData.setBtnClick(btnClickNode.asText());
        }

        JsonNode appearedTxtNode = configNode.path("appearedTxt");
        if (appearedTxtNode.isTextual()) {
            testData.setAppearedTxt(appearedTxtNode.asText());
        }

        JsonNode enterNameNode = configNode.path("enterName");
        if (enterNameNode.isTextual()) {
            testData.setEnterName(enterNameNode.asText());
        }

        JsonNode lengthRandomTextNode = configNode.path("lengthText");
        if (lengthRandomTextNode.isInt()) {
            testData.setLengthRandomText(lengthRandomTextNode.asInt());
        }

        JsonNode parentTxtNode = configNode.path("parentTxt");
        if (parentTxtNode.isTextual()) {
            testData.setParentTxt(parentTxtNode.asText());
        }

        JsonNode childTxtNode = configNode.path("childTxt");
        if (childTxtNode.isTextual()) {
            testData.setChildTxt(childTxtNode.asText());
        }

        JsonNode newTabTxtNode = configNode.path("newTabTxt");
        if (newTabTxtNode.isTextual()) {
            testData.setNewTabTxt(newTabTxtNode.asText());
        }

        JsonNode rowsTableNode = configNode.path("rowsTable");
        if (rowsTableNode.isInt()) {
            testData.setRowsTable(rowsTableNode.asInt());
        }

        return testData;
    }

}
