package utils;

import java.util.Random;

public class StringUtils {

    /**
     * Generates a random double-digit as a string.
     *
     * @return The random double-digit.
     */
    public static String getRandomDoubleDigit() {
        Random random = new Random();
        int digit = random.nextInt(10);
        return String.format("%d%d", digit, digit);
    }
}
