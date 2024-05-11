package application.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormattedLocalDateTime {
    public static String getFormattedDateTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the formatter with the desired format and locale
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM HH:mm", Locale.FRANCE);

        // Format the date and time using the formatter
        String formattedDateTime = now.format(formatter);
        formattedDateTime = formattedDateTime.substring(0, 1).toUpperCase() + formattedDateTime.substring(1);
        return formattedDateTime;
    }
}
