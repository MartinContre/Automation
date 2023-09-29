package test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import models.TestModel;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import tables.AuthorTable;
import tables.ProjectTable;
import tables.SessionTable;
import tables.TestTable;
import utils.DatabaseUtils;
import utils.SettingsFilesUtils;

import java.sql.Timestamp;

public class InsertTest {

    private final ISettingsFile testData = SettingsFilesUtils.getTestData();


    @Test
    public void test() {
        Assert.assertEquals(15, 16, "There a no equals");
    }

    /**
     * Performs the test teardown operations.
     *
     * @param result The test result.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            Assert.assertEquals(result.getStatus(), ITestResult.SUCCESS,
                    "The test has completed");
        } catch (AssertionError e) {
            Logger.getInstance().error("The test has not completed successfully: " + e.getMessage());
        }

        AuthorTable authorTable = new AuthorTable();
        ProjectTable projectTable = new ProjectTable();
        SessionTable sessionTable = new SessionTable();

        TestModel testModel = new TestModel();
        testModel.setName(result.getName());
        testModel.setStatusId(result.getStatus());
        testModel.setMethodName(result.getMethod().getMethodName());
        testModel.setProjectId(projectTable.getCurrentProjectId());
        testModel.setSessionId(sessionTable.getCurrentSessionID());
        testModel.setStartTime(new Timestamp(result.getStartMillis()));
        testModel.setEndTime(new Timestamp(result.getEndMillis()));
        testModel.setEnv(testData.getValue("/test/env").toString());
        testModel.setBrowser(AqualityServices.getConfiguration().getBrowserProfile().getBrowserName().name());
        testModel.setAuthorId(authorTable.getCurrentAuthorId());

        TestTable testTable = new TestTable();

        Assert.assertNotEquals(testTable.getInsertId(testModel), 0, "The test was not inserted");

        DatabaseUtils.closeConnection();
    }
}
