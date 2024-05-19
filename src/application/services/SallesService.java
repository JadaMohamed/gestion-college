package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import application.model.CategorieSalle;
import application.model.Horaires;
import application.model.MaterielSalle;
import application.model.Salle;
import application.model.enums.JoursSemaine;
import application.repositories.SallesRepository;

public class SallesService {

    public static Vector<Salle> getAllSalles() {

        Vector<Salle> resSalle = new Vector<Salle>();
        ResultSet result;
        try {
            result = SallesRepository.getAllSalles();
            while (result.next()) {
                Salle statSalle = new Salle();
                statSalle.setId(result.getInt("id"));
                statSalle.setCapacite(result.getInt("capacite"));
                statSalle.setNomSalle(result.getString("nom"));
                statSalle.setCategorieSalle(
                        new CategorieSalle(result.getInt("idCategorieSalle"), result.getString("nomCategorieSalle")));
                resSalle.add(statSalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSalle;
    }

    public static Vector<Salle> getAvailableSallesByHoraire(JoursSemaine jour, Horaires horaire) {

        Vector<Salle> resSalle = new Vector<Salle>();
        ResultSet result;
        try {
            result = SallesRepository.getAvailableSallesByHoraire(jour, horaire);
            while (result.next()) {
                Salle statSalle = new Salle();
                statSalle.setId(result.getInt("id"));
                statSalle.setCapacite(result.getInt("capacite"));
                statSalle.setNomSalle(result.getString("nom"));
                statSalle.setCategorieSalle(
                        new CategorieSalle(result.getInt("idCategorieSalle"), result.getString("nomCategorieSalle")));
                resSalle.add(statSalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSalle;
    }

    public static Vector<Map<String, String>> getAllSallesWithCurrentSeances() {
        Vector<Map<String, String>> salles = new Vector<>();
        ResultSet result;
        try {
            result = SallesRepository.getAllSallesWithCurrentSeances();
            while (result.next()) {
                Map<String, String> salle = new HashMap<>();
                salle.put("idSalle", result.getString("id"));
                salle.put("nomSalle", result.getString("nom"));
                salle.put("capacite", result.getString("capacite"));
                salle.put("statut", result.getString("statut"));
                salle.put("cours", result.getString("nomCours"));
                salle.put("classe", result.getString("numeroClasse"));
                salles.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salles;
    }

    public static Vector<MaterielSalle> getMaterialBySalleId(int SalleId) {
        Vector<MaterielSalle> res = new Vector<>();
        ResultSet result;
        try {
            result = SallesRepository.getMaterialBySalleId(SalleId);
            while (result.next()) {
                MaterielSalle materielsalle = new MaterielSalle();
                materielsalle.setId(result.getInt("id"));
                materielsalle.setNom(result.getString("nomMateriel"));
                materielsalle.setQuantite(result.getInt("quantite"));
                res.add(materielsalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Vector<Map<String, String>> getEmploiDeTempsSalle(String idSalle) {
        Vector<Map<String, String>> res = new Vector<>();
        ResultSet result;
        try {
            result = SallesRepository.getEmploiDeTempsSalle1(idSalle);
            while (result.next()) {
                Map<String, String> stat = new HashMap<>();
                stat.put("Day", result.getString("Day"));
                stat.put("coursnom8_10", result.getString("coursnom8_10"));
                stat.put("enseignantFullName8_10",
                        result.getString("nomEnseignant8_10") + " " + result.getString("prenomEnseignant8_10"));
                stat.put("photoUrlEnseignant8_10", result.getString("photoUrlEnseignant8_10"));
                stat.put("heureDebut8_10", result.getString("heureDebut8_10"));
                stat.put("heureFin8_10", result.getString("heureFin8_10"));
                stat.put("nomSalle8_10", result.getString("nomSalle8_10"));
                stat.put("coursnom10_12", result.getString("coursnom10_12"));
                stat.put("seanceId8_10", result.getString("seanceId8_10"));
                stat.put("seanceId10_12", result.getString("seanceId10_12"));
                stat.put("seanceId14_16", result.getString("seanceId14_16"));
                stat.put("seanceId16_18", result.getString("seanceId16_18"));
                stat.put("enseignantFullName10_12",
                        result.getString("nomEnseignant10_12") + " " + result.getString("prenomEnseignant10_12"));
                stat.put("photoUrlEnseignant10_12", result.getString("photoUrlEnseignant10_12"));
                stat.put("heureDebut10_12", result.getString("heureDebut10_12"));
                stat.put("heureFin10_12", result.getString("heureFin10_12"));
                stat.put("nomSalle10_12", result.getString("nomSalle10_12"));
                stat.put("coursnom14_16", result.getString("coursnom14_16"));
                stat.put("enseignantFullName14_16",
                        result.getString("nomEnseignant14_16") + " " + result.getString("prenomEnseignant14_16"));
                stat.put("photoUrlEnseignant14_16", result.getString("photoUrlEnseignant14_16"));
                stat.put("heureDebut14_16", result.getString("heureDebut14_16"));
                stat.put("heureFin14_16", result.getString("heureFin14_16"));
                stat.put("nomSalle14_16", result.getString("nomSalle14_16"));
                stat.put("coursnom16_18", result.getString("coursnom16_18"));
                stat.put("enseignantFullName16_18",
                        result.getString("nomEnseignant16_18") + " " + result.getString("prenomEnseignant16_18"));
                stat.put("photoUrlEnseignant16_18", result.getString("photoUrlEnseignant16_18"));
                stat.put("heureDebut16_18", result.getString("heureDebut16_18"));
                stat.put("heureFin16_18", result.getString("heureFin16_18"));
                stat.put("nomSalle16_18", result.getString("nomSalle16_18"));
                res.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<String> getAllCategorieNames() {
        List<CategorieSalle> niveaux = getAllCategories();
        return niveaux.stream().map(CategorieSalle::getNom).collect(Collectors.toList());
    }

    public static List<CategorieSalle> getAllCategories() {
        List<CategorieSalle> resCategories = new ArrayList<>();
        ResultSet result;
        try {
            result = SallesRepository.getAllCategories();
            while (result.next()) {
                CategorieSalle statCategorie = new CategorieSalle();
                statCategorie.setNom(result.getString("nom"));
                resCategories.add(statCategorie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resCategories;
    }

    public static int getAvailableHoursPerWeek(int idSalle) {
        try {
            ResultSet seances = SallesRepository.getSeancesBySalle(idSalle);

            // Définir une variable pour suivre le nombre total de séances
            int totalSeances = 0;

            // Parcourir chaque séance pour compter le nombre total de séances
            while (seances.next()) {
                totalSeances++;
            }

            // Chaque séance contient deux heures, donc multiplions le nombre total de
            // séances par deux
            int totalOccupiedHours = totalSeances * 2;

            // Définir la capacité totale de la salle (40 heures dans votre exemple)
            int capaciteTotale = 48;

            // Calculer les heures disponibles en soustrayant les heures occupées de la
            // capacité totale
            int availableHoursPerWeek = capaciteTotale - totalOccupiedHours;

            // Retourner le nombre d'heures disponibles par semaine
            return availableHoursPerWeek;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // En cas d'erreur, retourner 0 pour indiquer qu'aucune heure n'est disponible
        }
    }

    public static int getOccupiedHoursPerWeek(int idSalle) {
        try {
            ResultSet seances = SallesRepository.getSeancesBySalle(idSalle);

            // Définir une variable pour suivre le nombre total de séances
            int totalSeances = 0;

            // Parcourir chaque séance pour compter le nombre total de séances
            while (seances.next()) {
                totalSeances++;
            }

            // Chaque séance contient deux heures, donc multiplions le nombre total de
            // séances par deux
            int totalOccupiedHours = totalSeances * 2;

            // Retourner le nombre d'heures occupées par semaine
            return totalOccupiedHours;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // En cas d'erreur, retourner 0 pour indiquer qu'aucune heure n'est occupée
        }
    }

    // public static int getOccupiedHoursPerWeek(int idSalle) {
    // int occupiedHours = 0;
    // try {
    // ResultSet resultSet = SallesRepository.getOccupiedHoursPerWeek(idSalle);
    // // Traitement du ResultSet pour obtenir le nombre total d'heures occupées
    // // Utilisez les données récupérées dans ResultSet pour calculer le nombre
    // total d'heures occupées par semaine
    // // Ajoutez ces heures à la variable occupiedHours
    // } catch (SQLException e) {
    // e.printStackTrace();
    // // Gérer l'exception SQL
    // }
    // return occupiedHours;
    // }

}
