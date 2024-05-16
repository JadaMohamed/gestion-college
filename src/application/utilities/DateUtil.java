package application.utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateUtil {
    final private static LocalDate commencerLesEtudes = LocalDate.of(2023, 9, 15);

    public static int nombreDeSemainesDepuisDebutDesEtudes() {
        LocalDate now = LocalDate.now();
        return (int) ChronoUnit.WEEKS.between(commencerLesEtudes, now);
    }

    public static String nomDuJour() {
        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        Locale locale = new Locale.Builder().setLanguage("fr").setRegion("FR").build(); // French locale
        return dayOfWeek.getDisplayName(TextStyle.FULL, locale).toUpperCase();
    }

    public static int numDuJour() {
        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        return dayOfWeek.getValue();
    }
}
