package utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampUtils {
    public static Timestamp truncateNanos(Timestamp timestamp) {
        timestamp.setNanos(0);
        return timestamp;
    }

    /**
     * Retrieves the current timestamp.
     *
     * @return The current timestamp.
     */
    public static Timestamp getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = currentTime.format(formatter);
        return Timestamp.valueOf(formattedTimestamp);
    }
}
