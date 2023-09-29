package utils;

public class StringUtils {

    public static String removeAllExceptNumbers(String str) {
        return str.replaceAll("[^0-9]", "");
    }
}
