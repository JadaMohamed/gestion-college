package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import application.model.CategorieSalle;
import application.model.Classe;
import application.model.Cours;
import application.model.Enseignant;
import application.model.Horaires;
import application.model.Salle;
import application.model.Seance;
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

    public static Seance getSeanceById(int idSeance) {
        Seance resSeance = new Seance();
        try {
            ResultSet result = SeanceRepository.getSeanceById(idSeance);
            if (result.next()) {
                // Create and set the Salle object
                Salle salle = new Salle();
                salle.setId(result.getInt("salle_id"));
                salle.setNomSalle(result.getString("salle_nom"));
                salle.setCapacite(result.getInt("salle_capacite"));
                CategorieSalle categorieSalle = new CategorieSalle();
                categorieSalle.setId(result.getInt("categorie_salle_id"));
                salle.setCategorieSalle(categorieSalle);
                resSeance.setSalle(salle);

                // Create and set the Cours object
                Cours cours = new Cours();
                cours.setId(result.getInt("cours_id"));
                cours.setNomCours(result.getString("cours_nom"));
                TypeCours typeCours = new TypeCours();
                typeCours.setId(result.getInt("typecours_id"));
                typeCours.setNom(result.getString("typecours_nom"));
                cours.setTypeCours(typeCours);
                Enseignant enseignant = new Enseignant();
                enseignant.setId(result.getInt("enseignant_id"));
                enseignant.setNom(result.getString("enseignant_nom"));
                enseignant.setPrenom(result.getString("enseignant_prenom"));
                enseignant.setSexe(result.getString("enseignant_sexe"));
                enseignant.setEmail(result.getString("enseignant_email"));
                enseignant.setTelephone(result.getString("enseignant_telephone"));
                enseignant.setDateNaissance(result.getDate("enseignant_date_naissance"));
                enseignant.setPhotoURL(result.getString("enseignant_photo_url"));
                cours.setEnseignant(enseignant);
                resSeance.setCours(cours);

                // Create and set the Classe object
                Classe classe = new Classe();
                classe.setId(result.getInt("classe_id"));
                resSeance.setClasse(classe);

                // Set the remaining Seance fields
                resSeance.setId(result.getInt("seance_id"));
                String jourString = result.getString("seance_jour");
                JoursSemaine jourEnum = JoursSemaine.valueOf(jourString.toUpperCase());
                resSeance.setJour(jourEnum);
                resSeance.setHeureDebut(result.getTime("seance_heureDebut"));
                resSeance.setHeureFin(result.getTime("seance_heureFin"));
            }
        } catch (SQLException e) {
            System.out.println("error while getting seance by id : " + idSeance);
            e.printStackTrace();
        }
        return resSeance;
    }

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

    public static void modifierSeance(Classe classe, TypeCours typeCours, Enseignant enseignant, String coursNom,
            Horaires horaires, JoursSemaine jour, Salle salle, int idCours, int idSeance) {
        try {
            CoursRepository.modifierCours(idCours, typeCours.getId(), coursNom,
                    enseignant.getId(),
                    classe.getNiveau().getId());
        } catch (SQLException e) {
            System.out.println("Error while updating course information");
            e.printStackTrace();
        }
        try {
            SeanceRepository.modifierSeance(idSeance, salle.getId(), jour.name(), horaires.getHeureDebut().toString(),
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
                nombreCoursParNiveau[0] = resultSet.getInt("nombreCours3eme");
                nombreCoursParNiveau[1] = resultSet.getInt("nombreCours4eme");
                nombreCoursParNiveau[2] = resultSet.getInt("nombreCours5eme");
                nombreCoursParNiveau[3] = resultSet.getInt("nombreCours6eme");
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

    public static Vector<Map<String, String>> getSeancesEnCours() {
        Vector<Map<String, String>> seances = new Vector<Map<String, String>>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        ResultSet result;
        try {
            result = SeanceRepository.getSeancesEnCours();
            while (result.next()) {
                Map<String, String> seance = new HashMap<>();
                seance.put("enseignantFullName",
                        result.getString("nomEnseignant") + " " + result.getString("prenomEnseignant"));
                seance.put("enseignantEmail", result.getString("emailEnseignant"));
                seance.put("enseignantPhotoUrl", result.getString("photoUrlEnseignant"));
                seance.put("horaires",
                        result.getTime("heureDebut").toLocalTime().format(formatter) + " - "
                                + result.getTime("heureFin").toLocalTime().format(formatter));
                seance.put("coursNom", result.getString("nomCours"));
                seance.put("classeNom", result.getString("nomNiveau") + " " + result.getString("numeroClasse"));
                seance.put("salleNom", result.getString("nomSalle"));
                seance.put("effectif", result.getString("effectif"));
                seances.add(seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
    }

    public static Vector<Map<String, String>> getSeancesEnCoursBis() {
        Vector<Map<String, String>> seances = new Vector<Map<String, String>>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        ResultSet result;
        try {
            result = SeanceRepository.getSeancesEnCours();
            while (result.next()) {
                Map<String, String> seance = new HashMap<>();
                seance.put("enseignantFullName",
                        result.getString("nomEnseignant") + " " + result.getString("prenomEnseignant"));
                seance.put("enseignantEmail", result.getString("emailEnseignant"));
                seance.put("enseignantPhotoUrl", result.getString("photoUrlEnseignant"));
                seance.put("horaire",
                        result.getTime("heureDebut").toLocalTime().format(formatter) + " - "
                                + result.getTime("heureFin").toLocalTime().format(formatter));
                seance.put("nomCours", result.getString("nomCours"));
                seance.put("classe", result.getString("nomNiveau") + " " + result.getString("numeroClasse"));
                seance.put("nomSalle", result.getString("nomSalle"));
                seance.put("effectif", result.getString("effectif"));
                seances.add(seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
    }

    public static Vector<Map<String, String>> getSeances_search(String searchKey) {
        Vector<Map<String, String>> res = new Vector<>();
        ResultSet result;
        try {
            result = SeanceRepository.getSeances_search(searchKey);
            while (result.next()) {
                Map<String, String> seance = new HashMap<>();
                seance.put("id", String.valueOf(result.getInt(1)));
                seance.put("nomSalle", String.valueOf(result.getString(2)));
                seance.put("nomCours", result.getString(3));
                seance.put("classe", result.getString(4) + " " + result.getString(6));
                seance.put("effectif", String.valueOf(result.getInt(5)));
                seance.put("enseignantFullName", result.getString(7) + " " + result.getString(8));
                seance.put("enseignantEmail", result.getString(9));
                String heureDebut = result.getTime(11).toLocalTime().toString();
                String heureFin = result.getTime(12).toLocalTime().toString();
                String horaire = heureDebut.substring(0, 5) + " - " + heureFin.substring(0, 5);
                seance.put("horaire", horaire);
                seance.put("enseignantPhotoUrl", result.getString(10));
                res.add(seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
