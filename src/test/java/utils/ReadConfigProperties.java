package utils;

import models.ConfigModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Properties;

public class ReadConfigProperties {
    private static final Logger logger = LogManager.getLogger(ReadConfigProperties.class);
    private static final String CONFIG_FILE_PATH = "./src/test/resources/config.properties";

    public static @NotNull ConfigModel getConfig() {
        Properties properties = new Properties();
        ConfigModel configModel = new ConfigModel();
        try (InputStream inputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(inputStream);
            logger.info("ReadConfigProperties instance created successfully");

            configModel.setBrowser(properties.getProperty("browser"));
            configModel.setTestURL(properties.getProperty("testURL"));
            configModel.setPathDataTest(properties.getProperty("dataTest"));
            configModel.setTimeWait(Integer.parseInt(properties.getProperty("timeWait")));

        } catch (IOException e) {
            logger.error("Error while creating ReadConfigProperties instance", e);
        }
        return configModel;
    }

}
