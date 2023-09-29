package utils;

import java.util.Random;

public class ArithmeticUtils {

    public static int generateRandomIntUpToMaxWithoutZero(int max) {
        return new Random().nextInt(max - 1) + 1;
    }
}
