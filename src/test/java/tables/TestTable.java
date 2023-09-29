package tables;

import aquality.selenium.core.logging.Logger;
import models.TestModel;
import utils.ColumnsKey;
import utils.InsertFieldGenerator;
import utils.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TestTable extends BaseTable<TestModel> {
    private static final String INSERT_VALUES = "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final int UPDATE_VALUES_COUNT = 11;
    private static final ColumnsKey[] USED_COLUMNS = {ColumnsKey.NAME, ColumnsKey.STATUS_ID, ColumnsKey.METHOD_NAME,
            ColumnsKey.PROJECT_ID, ColumnsKey.SESSION_ID,  ColumnsKey.START_TIME, ColumnsKey.END_TIME, ColumnsKey.ENV,
            ColumnsKey.BROWSER, ColumnsKey.AUTHOR_ID};

    public TestTable() {
        tableName = "test";
    }

    @Override
    protected TestModel createModel(ResultSet resultSet) {
        try {
            Logger.getInstance().info("Creating model");
            int id = resultSet.getInt(ColumnsKey.ID.getKey());
            String name = resultSet.getString(ColumnsKey.NAME.getKey());
            int statusId = resultSet.getInt(ColumnsKey.STATUS_ID.getKey());
            String methodName = resultSet.getString(ColumnsKey.METHOD_NAME.getKey());
            int projectId = resultSet.getInt(ColumnsKey.PROJECT_ID.getKey());
            int sessionId = resultSet.getInt(ColumnsKey.SESSION_ID.getKey());
            Timestamp statTime = resultSet.getTimestamp(ColumnsKey.START_TIME.getKey());
            Timestamp endTime = resultSet.getTimestamp(ColumnsKey.END_TIME.getKey());
            String env = resultSet.getString(ColumnsKey.ENV.getKey());
            String browser = resultSet.getString(ColumnsKey.BROWSER.getKey());
            int authorId = resultSet.getInt(ColumnsKey.AUTHOR_ID.getKey());
            return new TestModel(id, name, statusId, methodName, projectId, sessionId, statTime, endTime, env, browser, authorId);
        } catch (SQLException e) {
            Logger.getInstance().error(e.getMessage());
            return null;
        }
    }

    @Override
    protected String getInsertFields() {
        return InsertFieldGenerator.generateInsertFields(USED_COLUMNS);
    }

    @Override
    protected String getInsertValues() {
        return INSERT_VALUES;
    }

    @Override
    protected void setInsertStatementValues(PreparedStatement statement, TestModel test) {
        try {
            Logger.getInstance().info("Setting insert statement values for test model");
            statement.setString(1, test.getName());
            statement.setInt(2, test.getStatusId());
            statement.setString(3, test.getMethodName());
            statement.setInt(4, test.getProjectId());
            statement.setInt(5, test.getSessionId());
            statement.setTimestamp(6, test.getStartTime());
            statement.setTimestamp(7, test.getEndTime());
            statement.setString(8, test.getEnv());
            statement.setString(9, test.getBrowser());
            statement.setInt(10, test.getAuthorId());
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't set statement values: " + e.getMessage());
        }
    }

    @Override
    protected String getUpdateFields() {
        return InsertFieldGenerator.generateUpdateFields(USED_COLUMNS);
    }

    @Override
    protected void setUpdateStatementValues(PreparedStatement statement, TestModel test) {
        try {
            Logger.getInstance().info("Setting update statement values for test model");
            statement.setString(1, test.getName());
            statement.setInt(2, test.getStatusId());
            statement.setString(3, test.getMethodName());
            statement.setInt(4, test.getProjectId());
            statement.setInt(5, test.getSessionId());
            statement.setTimestamp(6, test.getStartTime());
            statement.setTimestamp(7, test.getEndTime());
            statement.setString(8, test.getEnv());
            statement.setString(9, test.getBrowser());
            statement.setInt(10, test.getAuthorId());
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't set update statement values: " + e.getMessage());
        }
    }

    @Override
    protected int getUpdateStatementValuesCount() {
        return UPDATE_VALUES_COUNT;
    }

    @Override
    protected long getId(TestModel test) {
        return test.getId();
    }

    /**
     * Retrieves a list of random tests from the table.
     *
     * @return The list of random tests.
     */
    public ArrayList<Object[]> getRandomTests() {
        ArrayList<Object[]> result = new ArrayList<>();
        String randomIdDigits = StringUtils.getRandomDoubleDigit();
        String sql = String.format("SELECT * FROM %s WHERE id LIKE ? LIMIT 10", tableName);
        Logger.getInstance().info("Looking up to 10 test records which contain repeating digits in id: " + randomIdDigits);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, randomIdDigits);
            ResultSet resultSet = statement.executeQuery();

            int count = 0;
            while (resultSet.next() && count < 10) {
                TestModel testModel = new TestModel();
                testModel.setId(resultSet.getInt(ColumnsKey.ID.getKey()));
                testModel.setName(resultSet.getString(ColumnsKey.NAME.getKey()));
                testModel.setStatusId(resultSet.getInt(ColumnsKey.STATUS_ID.getKey()));
                testModel.setMethodName(resultSet.getString(ColumnsKey.METHOD_NAME.getKey()));
                testModel.setProjectId(resultSet.getInt(ColumnsKey.PROJECT_ID.getKey()));
                testModel.setSessionId(resultSet.getInt(ColumnsKey.SESSION_ID.getKey()));
                testModel.setStartTime(resultSet.getTimestamp(ColumnsKey.START_TIME.getKey()));
                testModel.setEndTime(resultSet.getTimestamp(ColumnsKey.END_TIME.getKey()));
                testModel.setEnv(resultSet.getString(ColumnsKey.ENV.getKey()));
                testModel.setBrowser(resultSet.getString(ColumnsKey.BROWSER.getKey()));
                testModel.setAuthorId(resultSet.getInt(ColumnsKey.AUTHOR_ID.getKey()));
                result.add(new Object[]{testModel});
                count++;
            }
            return result;
        } catch (SQLException e) {
            String errorMessage = String.format("%s %s ", errorSQLMessage, sql);
            Logger.getInstance().error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    /**
     * Inserts a test into the table and retrieves the generated ID.
     *
     * @param test The test to insert.
     * @return The generated ID of the inserted test.
     */
    public int getInsertId(TestModel test) {
        int generatedId;
        validateObject(test);
        String sql = String.format("INSERT INTO %s %s VALUES %s", tableName, getInsertFields(), getInsertValues());
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            Logger.getInstance().info("Inserting object to table: " + tableName);
            statement.clearParameters();
            setInsertStatementValues(statement, test);
            statement.executeUpdate();
            ResultSet generatedIds = statement.getGeneratedKeys();
            if (generatedIds.next()) {
                generatedId = generatedIds.getInt(1);
            } else {
                throw new SQLException("Inserting test data, no Id obtained.");
            }
        } catch (SQLException e) {
            String errorMessage = String.format("%s %s ", errorSQLMessage, sql);
            Logger.getInstance().error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
        return generatedId;
    }
}
