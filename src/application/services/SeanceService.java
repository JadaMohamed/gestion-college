package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Vector;

import application.model.Classe;
import application.model.Enseignant;
import application.model.Horaires;
import application.model.Salle;
import application.model.TypeCours;
import application.model.enums.JoursSemaine;
import application.repositories.CoursRepository;
import application.repositories.SeanceRepository;

public class SeanceService {
    // Define the predefined time intervals
    private static final int[][] timeIntervals = {
            { 8, 0, 10, 0 }, // 8:00 AM - 10:00 AM
            { 10, 0, 12, 0 }, // 10:00 AM - 12:00 PM
            { 14, 0, 16, 0 }, // 2:00 PM - 4:00 PM
            { 16, 0, 18, 0 } // 4:00 PM - 6:00 PM
    };

    public static Vector<Horaires> getAvailableHoraires(JoursSemaine jour, Classe classe, Enseignant enseignant) {
        Vector<Horaires> resHoraires = new Vector<>();

        // Add predefined time intervals
        for (int[] interval : timeIntervals) {
            LocalTime intervalDebut = LocalTime.of(interval[0], interval[1]);
            LocalTime intervalFin = LocalTime.of(interval[2], interval[3]);
            resHoraires.add(new Horaires(intervalDebut, intervalFin));
        }

        // Fetch occupied time slots from the database
        ResultSet result;
        try {
            result = SeanceRepository.getOccupiedHoraires(jour, classe, enseignant);
            while (result.next()) {
                // Retrieve the heureDebut and heureFin from the ResultSet
                LocalTime heureDebut = result.getTime("heureDebut").toLocalTime();
                LocalTime heureFin = result.getTime("heureFin").toLocalTime();

                // Iterate through the available time slots and remove occupied ones
                for (Horaires horaire : resHoraires) {
                    LocalTime intervalDebut = horaire.getHeureDebut();
                    LocalTime intervalFin = horaire.getHeureFin();
                    if (heureDebut.equals(intervalDebut) && heureFin.equals(intervalFin)) {
                        resHoraires.remove(horaire);
                        break; // Break the loop since we found a match
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resHoraires;
    }

    public static void affecterUneSeance(Classe classe, TypeCours typeCours, Enseignant enseignant, String coursNom,
            Horaires horaires, JoursSemaine jour, Salle salle) {
        int idCours = 0;
        try {
            ResultSet coursResultSet = CoursRepository.insertIntoCours(typeCours.getId(), coursNom, enseignant.getId(),
                    classe.getNiveau().getId());
            while (coursResultSet.next()) {
                idCours = coursResultSet.getInt("LAST_INSERT_ID()");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            SeanceRepository.insertIntoSeance(salle.getId(), jour.name(), horaires.getHeureDebut().toString(),
                    horaires.getHeureFin().toString(), idCours, classe.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNombreSallesDisponibles() {
        int nombreTotalSalles = 22;
        int nombreSeancesEnCours = 0;
    
        try {
            ResultSet resultSet = SeanceRepository.getNombreSeanceEnCours();
            if (resultSet.next()) {
                nombreSeancesEnCours = resultSet.getInt("seancesencours");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreTotalSalles - nombreSeancesEnCours;
    }
    
    public static int[] getNombreSallesOccupes() throws SQLException {
        int[] nombreSallesOccupes = new int[3]; 
        try {
            ResultSet resultSet = SeanceRepository.getNombreSallesOccupes();
            if (resultSet.next()) {
                nombreSallesOccupes[0] = resultSet.getInt("nombreLaboratoiresOccupes");
                nombreSallesOccupes[1] = resultSet.getInt("nombreSalleCoursOccupes");
                nombreSallesOccupes[2] = resultSet.getInt("nombreSalleDeSportOccupes");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreSallesOccupes;
    } 
    
    public static int[] getNombreCoursParNiveau() {
        int[] nombreCoursParNiveau = new int[4];
        try {
            ResultSet resultSet = SeanceRepository.getNombreCoursParNiveau();
            if (resultSet.next()) {
                nombreCoursParNiveau[0] = resultSet.getInt("nombreCours6eme");
                nombreCoursParNiveau[1] = resultSet.getInt("nombreCours5eme");
                nombreCoursParNiveau[2] = resultSet.getInt("nombreCours4eme");
                nombreCoursParNiveau[3] = resultSet.getInt("nombreCours3eme");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreCoursParNiveau;
    }

    public static int getEffectifEnCours() throws SQLException {
        int effectifEnCours = 0;
        try {
            ResultSet resultSet = SeanceRepository.getEffectifEnCours();
            if (resultSet.next()) {
                effectifEnCours = resultSet.getInt("effectifEnCours");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return effectifEnCours;
    }
    

}
