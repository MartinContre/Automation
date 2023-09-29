package tables;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import utils.ColumnsKey;
import utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseTable<T> {
    protected ISettingsFile testData = new JsonSettingsFile("test_data.json");
    protected Connection connection;
    protected String tableName;
    protected static String errorSQLMessage = "Failed to execute SQL statement: ";

    public BaseTable() {
        this.connection = DatabaseUtils.getConnection();
    }

    protected abstract T createModel(ResultSet resultSet);

    /**
     * Retrieves all objects from the database table.
     *
     * @return An ArrayList containing all the objects.
     */
    public ArrayList<T> getAll() {
        ArrayList<T> result = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s", tableName);
        Logger.getInstance().info("Getting all the objects from the table: " + tableName);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T model = createModel(resultSet);
                result.add(model);
            }
        } catch (SQLException e) {
            String errorMessage = errorSQLMessage + sql + " ";
            Logger.getInstance().error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
        return  result;
    }

    /**
     * Retrieves an object by its ID from the database table.
     *
     * @param id The ID of the object to retrieve.
     * @return The retrieved object, or null if not found.
     */
    public Object getById(int id) {
        String sql = String.format("SELECT * FROM %s WHERE id = ?", tableName);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            Logger.getInstance().info(String.format("Getting object by id: %s from table: %s", id, tableName));
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createModel(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            String errorMessage = String.format("%s %s ", errorSQLMessage, sql);
            Logger.getInstance().error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    /**
     * Inserts a new object into the database table.
     *
     * @param model The object to insert.
     */
    public void insert(T model) {
        validateObject(model);
        String sql = String.format("INSERT INTO %s %s VALUES %s", tableName, getInsertFields(), getInsertValues());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            Logger.getInstance().info("Inserting object to table: " + tableName);
            statement.clearParameters();
            setInsertStatementValues(statement, model);
            statement.executeUpdate();
        } catch (SQLException e) {
            String errorMessage = String.format("%s %s ", errorSQLMessage, sql);
            Logger.getInstance().error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    /**
     * Updates an existing object in the database table.
     *
     * @param model The object to update.
     */
    public void update(T model) {
        validateObject(model);
        String sql = String.format("UPDATE %s SET %s WHERE id = ?", tableName, getUpdateFields());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setUpdateStatementValues(statement, model);
            statement.setLong(getUpdateStatementValuesCount(), getId(model));
            statement.executeUpdate();
        } catch (SQLException e) {
            String errorMessage = String.format("%s %s ", errorSQLMessage, sql);
            Logger.getInstance().error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    /**
     * Deletes an object from the database table by its ID.
     *
     * @param id The ID of the object to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    public boolean delete(long id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", tableName);
        try {
            Logger.getInstance().info(sql + id);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            String errorMessage = String.format("%s %s ", errorSQLMessage, sql);
            Logger.getInstance().error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    protected boolean isModelInDb(String key, String modelValue) {
        String sql = String.format("SELECT COUNT(id) FROM %s WHERE %s = ?", tableName, key);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, modelValue);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("COUNT(id)") != 1;
        } catch (SQLException e) {
            String errorMsg = String.format("%s %s ", errorSQLMessage, sql);
            Logger.getInstance().error(errorMsg + e.getMessage());
            throw new RuntimeException(errorMsg + e);
        }
    }

    protected int getCurrentModelId(String key, String value) {
        String sql = String.format("SELECT id FROM %s WHERE %s = ?", tableName, key);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(ColumnsKey.ID.getKey());
        } catch (SQLException e) {
            String errorMsg = String.format("%s %s ", errorSQLMessage, sql);
            Logger.getInstance().error(errorMsg + e.getMessage());
            throw new RuntimeException(errorMsg + e);
        }
    }

    protected abstract String getInsertFields();

    protected abstract String getInsertValues();

    protected abstract void setInsertStatementValues(PreparedStatement statement, T model);

    protected abstract String getUpdateFields();

    protected abstract void setUpdateStatementValues(PreparedStatement statement, T model);

    protected abstract int getUpdateStatementValuesCount();

    protected abstract long getId(T model);

    protected void validateObject(T model) {
        if (model == null) {
            Logger.getInstance().error("Model cannot be null");
        }
    }
}
