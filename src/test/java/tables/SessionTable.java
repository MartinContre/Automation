package tables;

import aquality.selenium.core.logging.Logger;
import models.SessionModel;
import utils.ColumnsKey;
import utils.InsertFieldGenerator;
import utils.TimestampUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SessionTable extends BaseTable<SessionModel> {
    private static final String INSERT_VALUES = "(?, ?, ?)";
    private static final int UPDATE_VALUES_COUNT = 4;
    private static final ColumnsKey[] USED_COLUMNS = {ColumnsKey.SESSION_KEY, ColumnsKey.CREATED_TIME, ColumnsKey.BUILD_NUMBER};

    public SessionTable() {
        tableName = "session";
    }

    @Override
    protected SessionModel createModel(ResultSet resultSet) {
        try {
            Logger.getInstance().info("Creating model");
            int id = resultSet.getInt(ColumnsKey.ID.getKey());
            String sessionKey = resultSet.getString(ColumnsKey.SESSION_KEY.getKey());
            Timestamp createdTime = resultSet.getTimestamp(ColumnsKey.CREATED_TIME.getKey());
            int buildNumber = resultSet.getInt(ColumnsKey.BUILD_NUMBER.getKey());
            return new SessionModel(id, sessionKey, createdTime, buildNumber);
        } catch (SQLException e) {
            String errorMsg = "Couldn't create model ";
            Logger.getInstance().error(errorMsg + e.getMessage());
            throw new RuntimeException(errorMsg + e.getMessage());
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
    protected void setInsertStatementValues(PreparedStatement statement, SessionModel session) {
        try {
            Logger.getInstance().info("Setting insert statement values for session model");
            statement.setString(1, session.getSessionKey());
            statement.setTimestamp(2, session.getCreatedTime());
            statement.setInt(3, session.getBuildNumber());
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't set statement values " + e.getMessage());
        }
    }

    @Override
    protected String getUpdateFields() {
        return InsertFieldGenerator.generateInsertFields(USED_COLUMNS);
    }

    @Override
    protected void setUpdateStatementValues(PreparedStatement statement, SessionModel session) {
        try {
            Logger.getInstance().info("Setting update statement values for session model");
            statement.setString(1, session.getSessionKey());
            statement.setTimestamp(2, session.getCreatedTime());
            statement.setInt(3, session.getBuildNumber());
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't sut update statements values: " + e.getMessage());
        }
    }

    @Override
    protected int getUpdateStatementValuesCount() {
        return UPDATE_VALUES_COUNT;
    }

    @Override
    protected long getId(SessionModel session) {
        return session.getId();
    }

    /**
     * Gets the current session ID.
     *
     * @return The current session ID.
     */
    public int getCurrentSessionID() {
        Logger.getInstance().info("Getting current session id");
        SessionModel session = new SessionModel();
        String sessionKey = testData.getValue("/session/sessionKey").toString();
        session.setSessionKey(sessionKey);
        session.setCreatedTime(TimestampUtils.getCurrentTime());
        int buildNumber = Integer.parseInt(testData.getValue("/session/buildNumber").toString());
        session.setBuildNumber(buildNumber);

        if (isModelInDb(ColumnsKey.SESSION_KEY.getKey(), session.getSessionKey())) {
            Logger.getInstance().info("Current session was not found in db, adding: " + session.getSessionKey());
            insert(session);
        }

        return getCurrentModelId(ColumnsKey.SESSION_KEY.getKey(), session.getSessionKey());
    }
}
