package test.base;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import models.TestModel;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import tables.AuthorTable;
import tables.ProjectTable;
import tables.TestTable;
import utils.DatabaseUtils;
import utils.TimestampUtils;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class BaseTestC2 {
    private static final String TEST_DATA_FILE_NAME = "test_data.json";

    public static ISettingsFile testData;
    protected Connection connection = DatabaseUtils.getConnection();
    protected AuthorTable authorTable = new AuthorTable();
    protected TestTable testTable = new TestTable();
    protected ProjectTable projectTable = new ProjectTable();
    private static ArrayList<Object[]> copiedTests;
    private static final ArrayList<Integer> idsOfInsertedTests = new ArrayList<>();

    /**
     * Starts connection with the database.
     */
    @BeforeSuite
    public void setUp() {
        testData = new JsonSettingsFile(TEST_DATA_FILE_NAME);
        connection = DatabaseUtils.getConnection();
        copiedTests = new ArrayList<>();
    }

    @DataProvider(name = "randomTests")
    private Iterator<Object[]> getTestCases() {
        ArrayList<Object[]> randomTests = testTable.getRandomTests();
        Logger.getInstance().info("Changing columns (project_id, author) of 'random' test from db and inserting them into db as new records");
        copiedTests = new ArrayList<>();
        for (Object[] testObj : randomTests) {
            TestModel originalTest = (TestModel) testObj[0];
            TestModel copiedTest = new TestModel();
            copiedTest.setName(originalTest.getName());
            copiedTest.setStatusId(originalTest.getStatusId());
            copiedTest.setMethodName(originalTest.getMethodName());
            copiedTest.setProjectId(projectTable.getCurrentProjectId());
            copiedTest.setSessionId(originalTest.getSessionId());
            copiedTest.setStartTime(originalTest.getStartTime());
            copiedTest.setEndTime(originalTest.getEndTime());
            copiedTest.setEnv(originalTest.getEnv());
            copiedTest.setBrowser(originalTest.getBrowser());
            copiedTest.setAuthorId(authorTable.getCurrentAuthorId());
            int generatedId = testTable.getInsertId(copiedTest);
            copiedTest.setId(generatedId);
            idsOfInsertedTests.add(generatedId);
            copiedTests.add(new Object[]{copiedTest});
        }
        return copiedTests.iterator();
    }

    /**
     * Insert rows with the new parameters into the database.
     * @param result iterator to get results from test.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        for (Object[] testObj : copiedTests) {
            TestModel testModel = (TestModel) testObj[0];

            Timestamp startTime = TimestampUtils.truncateNanos(new Timestamp(result.getStartMillis()));
            testModel.setStartTime(startTime);

            Timestamp endTime = TimestampUtils.truncateNanos(new Timestamp(result.getEndMillis()));
            testModel.setEndTime(endTime);

            testModel.setStatusId(result.getStatus());
            testTable.update(testModel);
        }

        Logger.getInstance().info("Verifying that tests are completed and information is updated");
        for (Object[] testObj : copiedTests) {
            TestModel testModel = (TestModel) testObj[0];
            TestModel updatedTest = (TestModel) testTable.getById(testModel.getId());
            Assert.assertEquals(updatedTest, testModel,
                    "The inserted TestModel does not match the retrieved TestModel");
        }


    }

    /**
     * Check if the rows added were deleted.
     * Closes connection with database.
     */
    @AfterTest
    public void close() {
        Logger.getInstance().info("Cleaning up previously created records");
        for (int testId : idsOfInsertedTests) {
            boolean isDeletedRow = testTable.delete(testId);
            Assert.assertTrue(isDeletedRow, "The row was not delete");
        }
        DatabaseUtils.closeConnection();
    }
}
