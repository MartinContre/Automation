package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GenerateRandomText {
    private static final Logger logger = LogManager.getLogger(GenerateRandomText.class);
    public static @NotNull String RandomTxtGenerator(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            char randomChar = chars.charAt(index);
            sb.append(randomChar);
        }
        String randomText = sb.toString();
        logger.info("Generated random text: " + randomText);
        return randomText;
    }
}
