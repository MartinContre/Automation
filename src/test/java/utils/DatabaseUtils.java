package utils;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    private static Connection connection = null;
    private static final String URL = "/Url";
    private static final String USER = "/User";
    private static final String PASSWORD = "/Password";
    private static final ISettingsFile configData = SettingsFilesUtils.getConfigDataFile();
    private static final ISettingsFile dbCredentials = SettingsFilesUtils.getDbCredentials();

    /**
     * Retrieves the database connection. If the connection is not yet established, it creates a new one.
     *
     * @return The database connection.
     * @throws RuntimeException if an error occurs while creating the connection.
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = configData.getValue(URL).toString();
                String user = dbCredentials.getValue(USER).toString();
                String password = dbCredentials.getValue(PASSWORD).toString();
                connection = DriverManager.getConnection(url, user, password);
                Logger.getInstance().info("Connected to the database");
            } catch (SQLException e) {
                Logger.getInstance().error("Error while creating connection to database: " + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
        return connection;
    }

    /**
     * Closes the database connection.
     * If the connection is not null, it will be closed and set to null.
     * Any error that occurs while closing the connection will be logged.
     */
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException e) {
            Logger.getInstance().error("Error while closing connection: " + e.getMessage());
        }
    }
}
