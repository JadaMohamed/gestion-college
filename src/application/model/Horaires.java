package application.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Horaires {
    private LocalTime heureDebut;
    private LocalTime heureFin;

    public Horaires(LocalTime heureDebut, LocalTime heureFin) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public Horaires() {

    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    @Override
    public String toString() {
        // Define a DateTimeFormatter for formatting time in the desired 24-hour format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // Format the start and end times
        String debutStr = heureDebut.format(formatter);
        String finStr = heureFin.format(formatter);

        // Return the formatted string
        return debutStr + " - " + finStr;
    }

    public static Vector<Horaires> horairesDisponible() {
        Vector<Horaires> res = new Vector<>();

        // Define predefined time intervals
        int[][] timeIntervals = {
                { 8, 0, 10, 0 }, // 8:00 AM - 10:00 AM
                { 10, 0, 12, 0 }, // 10:00 AM - 12:00 PM
                { 14, 0, 16, 0 }, // 2:00 PM - 4:00 PM
                { 16, 0, 18, 0 } // 4:00 PM - 6:00 PM
        };

        // Iterate over the time intervals
        for (int[] interval : timeIntervals) {
            // Create LocalTime objects for start and end times
            LocalTime startTime = LocalTime.of(interval[0], interval[1]);
            LocalTime endTime = LocalTime.of(interval[2], interval[3]);

            // Create a new Horaires object for the time interval
            Horaires horaire = new Horaires(startTime, endTime);
            res.add(horaire);
        }

        return res;
    }
}
