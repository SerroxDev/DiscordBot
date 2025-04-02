package Main.Commands;

import Main.ResourceLoader;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimestampUtil {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static String getTimestamp(String datetime, char format) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(datetime, dateFormat);
            Instant instant = localDateTime.atZone(ZoneId.of("Europe/Berlin")).toInstant();
            long seconds = instant.getEpochSecond();
            String TIMESTAMP = ResourceLoader.getMessage("TIMESTAMP");
            return TIMESTAMP + "<t:" + seconds + ":" + format + ">";

        } catch (DateTimeParseException e) {
            return "Bitte sende den timestamp in dem folgendem Format. dd.MM.yyyy HH:mm damit ein Timestamp erstellt werden kann";
        }
    }
}
