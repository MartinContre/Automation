package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.util.HashMap;
import java.util.Map;

public class SettingsFilesUtils {
    private static final String CONFIG_DATA_FILE_NAME = "config_data.json";
    private static final String TEST_DATA_FILE_NAME = "test_data.json";
    private static final Map<String, ISettingsFile> dataFiles = new HashMap<>();

    static {
        dataFiles.put(CONFIG_DATA_FILE_NAME, new JsonSettingsFile(CONFIG_DATA_FILE_NAME));
        dataFiles.put(TEST_DATA_FILE_NAME, new JsonSettingsFile(TEST_DATA_FILE_NAME));
    }

    public static ISettingsFile getConfigDataFile() {
        return dataFiles.get(CONFIG_DATA_FILE_NAME);
    }

    public static ISettingsFile getTestDataFile() {
        return dataFiles.get(TEST_DATA_FILE_NAME);
    }
}