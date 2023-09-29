package tables;

import aquality.selenium.core.logging.Logger;
import models.AuthorModel;
import utils.ColumnsKey;
import utils.InsertFieldGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorTable extends BaseTable<AuthorModel> {
    private static final String INSERT_VALUES = "(?, ?, ?)";
    private static final int UPDATE_VALUES_COUNT = 4;
    private static final ColumnsKey[] USED_COLUMNS = {ColumnsKey.NAME, ColumnsKey.LOGIN, ColumnsKey.EMAIL};

    public AuthorTable() {
        tableName = "author";
    }

    @Override
    protected AuthorModel createModel(ResultSet resultSet) {
        try {
            Logger.getInstance().info("Creating model");
            int id = resultSet.getInt(ColumnsKey.ID.getKey());
            String name = resultSet.getString(ColumnsKey.NAME.getKey());
            String login = resultSet.getString(ColumnsKey.LOGIN.getKey());
            String email = resultSet.getString(ColumnsKey.EMAIL.getKey());
            return new AuthorModel(id, name, login, email);
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't create model " + e.getMessage());
            throw new RuntimeException("Couldn't create model " + e);
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
    protected void setInsertStatementValues(PreparedStatement statement, AuthorModel author) {
        try {
            Logger.getInstance().info("Setting insert statement values for author model");
            statement.setString(1, author.getName());
            statement.setString(2, author.getLogin());
            statement.setString(3, author.getEmail());
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't set statement values: " + e.getMessage());
        }
    }

    @Override
    protected String getUpdateFields() {
        return InsertFieldGenerator.generateUpdateFields(USED_COLUMNS);
    }

    @Override
    protected void setUpdateStatementValues(PreparedStatement statement, AuthorModel author) {
        try {
            Logger.getInstance().info("Setting update statement values for author model");
            statement.setString(1, author.getName());
            statement.setString(2, author.getLogin());
            statement.setString(3, author.getEmail());
        } catch (SQLException e) {
            Logger.getInstance().error("Couldn't sut update statements values: " + e.getMessage());
        }
    }

    @Override
    protected int getUpdateStatementValuesCount() {
        return UPDATE_VALUES_COUNT;
    }

    @Override
    protected long getId(AuthorModel author) {
        return author.getId();
    }

    /**
     * Retrieves the current author ID.
     *
     * @return The current author ID.
     */
    public int getCurrentAuthorId() {
        Logger.getInstance().info("Getting current author id");
        AuthorModel author = new AuthorModel();
        author.setName(testData.getValue("/author/name").toString());
        author.setLogin(testData.getValue("/author/login").toString());
        author.setEmail(testData.getValue("/author/email").toString());

        if (isModelInDb(ColumnsKey.LOGIN.getKey(), author.getLogin())) {
            Logger.getInstance().info("Current author was not found in db, adding: " + author.getLogin());
            insert(author);
        }

        return getCurrentModelId(ColumnsKey.LOGIN.getKey(), author.getLogin());
    }
}
