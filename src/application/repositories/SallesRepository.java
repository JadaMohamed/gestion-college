package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import application.database.dbClient;
import application.model.Horaires;
import application.model.enums.JoursSemaine;

public class SallesRepository {

    public static ResultSet getAllSalles() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT salle.*, categoriealle.nom AS nomcategoriesalle from salle, categorieSalle WHERE categorieSalle.id = salle.idCategorieSalle";
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getAvailableSallesByHoraire(JoursSemaine jour, Horaires horaire) throws SQLException {
        Vector<Object> parameters = new Vector<>();
        String query = "SELECT s.id, cs.nom AS nomcategoriesalle, s.idCategorieSalle, s.nom, s.capacite FROM salle s, categorieSalle cs WHERE cs.id = s.idCategorieSalle AND s.id NOT IN ( SELECT se.idSalle FROM seance se WHERE se.jour = ? AND ( (se.heureDebut >= ? AND se.heureDebut < ?) OR (se.heureFin > ? AND se.heureFin <= ?) OR (se.heureDebut <= ? AND se.heureFin >= ?) ) )";
        // Add parameters
        parameters.add(jour.toString()); // Day of the week
        parameters.add(horaire.getHeureDebut().toString()); // Start time
        parameters.add(horaire.getHeureFin().toString()); // End time
        parameters.add(horaire.getHeureDebut().toString()); // Start time
        parameters.add(horaire.getHeureFin().toString()); // End time
        parameters.add(horaire.getHeureDebut().toString()); // Start time
        parameters.add(horaire.getHeureFin().toString()); // End time

        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getAllSallesWithCurrentSeances() throws SQLException {
        Vector<Object> parameters = new Vector<>();
        String query = 
    "SELECT " +
    "    salle.id, " +
    "    salle.nom, " +
    "    salle.capacite, " +
    "    IFNULL(seance_info.statut, '0') AS statut, " +
    "    CASE " +
    "        WHEN IFNULL(seance_info.statut, '0') = '1' THEN IFNULL(seance_info.nomCours, '-') " +
    "        ELSE '-' " +
    "    END AS nomCours, " +
    "    CASE " +
    "        WHEN IFNULL(seance_info.statut, '0') = '1' THEN IFNULL(seance_info.numeroClasse, '-') " +
    "        ELSE '-' " +
    "    END AS numeroClasse " +
    "FROM " +
    "    salle " +
    "LEFT JOIN " +
    "    ( " +
    "    SELECT " +
    "        idSalle, " +
    "        MAX( " +
    "            CASE " +
    "                WHEN CURTIME() BETWEEN seance.heureDebut AND seance.heureFin THEN '1' " +
    "                ELSE '0' " +
    "            END " +
    "        ) AS statut, " +
    "        COALESCE(cours.nom, '-') AS nomCours, " +
    "        CONCAT(niveau.nom, ' ', classe.numero) AS numeroClasse " +
    "    FROM " +
    "        seance " +
    "    LEFT JOIN " +
    "        cours ON cours.id = seance.idCours " +
    "    LEFT JOIN " +
    "        classe ON classe.id = seance.idClasse " +
    "    LEFT JOIN " +
    "        niveau ON niveau.id = classe.idNiveauClasse " +
    "    WHERE " +
    "        DAYOFWEEK(CURDATE()) = " +
    "        CASE " +
    "            WHEN seance.jour = 'LUNDI' THEN 2 " +
    "            WHEN seance.jour = 'MARDI' THEN 3 " +
    "            WHEN seance.jour = 'MERCREDI' THEN 4 " +
    "            WHEN seance.jour = 'JEUDI' THEN 5 " +
    "            WHEN seance.jour = 'VENDREDI' THEN 6 " +
    "            WHEN seance.jour = 'SAMEDI' THEN 7 " +
    "            WHEN seance.jour = 'DIMANCHE' THEN 1 " +
    "        END " +
    "    AND " +
    "        CURTIME() BETWEEN seance.heureDebut AND seance.heureFin " +
    "    GROUP BY " +
    "        idSalle " +
    "    ) AS seance_info ON salle.id = seance_info.idSalle;";

        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getEmploiDeTempsSalle1(String idSalle) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT weekdays.weekday AS Day, " +
                "COALESCE(coursnom8_10, '-') AS coursnom8_10, " +
                "COALESCE(seanceId8_10, '-') AS seanceId8_10, COALESCE(seanceId10_12, '-') AS seanceId10_12, COALESCE(seanceId14_16, '-') AS seanceId14_16,COALESCE(seanceId16_18, '-') AS seanceId16_18,"
                +
                "COALESCE(nomEnseignant8_10, '-') AS nomEnseignant8_10, " +
                "COALESCE(prenomEnseignant8_10, '-') AS prenomEnseignant8_10, " +
                "COALESCE(photoUrlEnseignant8_10, '-') AS photoUrlEnseignant8_10, " +
                "COALESCE(heureDebut8_10, '-') AS heureDebut8_10, " +
                "COALESCE(heureFin8_10, '-') AS heureFin8_10, " +
                "COALESCE(nomSalle8_10, '-') AS nomSalle8_10, " +
                "COALESCE(coursnom10_12, '-') AS coursnom10_12, " +
                "COALESCE(nomEnseignant10_12, '-') AS nomEnseignant10_12, " +
                "COALESCE(prenomEnseignant10_12, '-') AS prenomEnseignant10_12, " +
                "COALESCE(photoUrlEnseignant10_12, '-') AS photoUrlEnseignant10_12, " +
                "COALESCE(heureDebut10_12, '-') AS heureDebut10_12, " +
                "COALESCE(heureFin10_12, '-') AS heureFin10_12, " +
                "COALESCE(nomSalle10_12, '-') AS nomSalle10_12, " +
                "COALESCE(coursnom14_16, '-') AS coursnom14_16, " +
                "COALESCE(nomEnseignant14_16, '-') AS nomEnseignant14_16, " +
                "COALESCE(prenomEnseignant14_16, '-') AS prenomEnseignant14_16, " +
                "COALESCE(photoUrlEnseignant14_16, '-') AS photoUrlEnseignant14_16, " +
                "COALESCE(heureDebut14_16, '-') AS heureDebut14_16, " +
                "COALESCE(heureFin14_16, '-') AS heureFin14_16, " +
                "COALESCE(nomSalle14_16, '-') AS nomSalle14_16, " +
                "COALESCE(coursnom16_18, '-') AS coursnom16_18, " +
                "COALESCE(nomEnseignant16_18, '-') AS nomEnseignant16_18, " +
                "COALESCE(prenomEnseignant16_18, '-') AS prenomEnseignant16_18, " +
                "COALESCE(photoUrlEnseignant16_18, '-') AS photoUrlEnseignant16_18, " +
                "COALESCE(heureDebut16_18, '-') AS heureDebut16_18, " +
                "COALESCE(heureFin16_18, '-') AS heureFin16_18, " +
                "COALESCE(nomSalle16_18, '-') AS nomSalle16_18 " +
                "FROM (SELECT 'LUNDI' AS weekday UNION ALL SELECT 'MARDI' AS weekday " +
                "UNION ALL SELECT 'MERCREDI' AS weekday UNION ALL SELECT 'JEUDI' AS weekday " +
                "UNION ALL SELECT 'VENDREDI' AS weekday UNION ALL SELECT 'SAMEDI' AS weekday) AS weekdays " +
                "LEFT JOIN (SELECT weekdays.weekday AS Day, " +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN cours.nom END) AS coursnom8_10, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.nom END) AS nomEnseignant8_10, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.prenom END) AS prenomEnseignant8_10, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '8:00:00' AND '9:59:00' THEN seance.id END) AS seanceId8_10,"
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant8_10, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.heureDebut END) AS heureDebut8_10, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.heureFin END) AS heureFin8_10, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN salle.nom END) AS nomSalle8_10, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN cours.nom END) AS coursnom10_12, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.id END) AS seanceId10_12,"
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.nom END) AS nomEnseignant10_12, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.prenom END) AS prenomEnseignant10_12, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant10_12, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.heureDebut END) AS heureDebut10_12, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.heureFin END) AS heureFin10_12, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN salle.nom END) AS nomSalle10_12, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN cours.nom END) AS coursnom14_16, "
                + "MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.id END) AS seanceId14_16,"
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.nom END) AS nomEnseignant14_16, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.prenom END) AS prenomEnseignant14_16, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant14_16, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.heureDebut END) AS heureDebut14_16, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.heureFin END) AS heureFin14_16, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN salle.nom END) AS nomSalle14_16, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN cours.nom END) AS coursnom16_18, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.nom END) AS nomEnseignant16_18, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.prenom END) AS prenomEnseignant16_18, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant16_18, "
                + "MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.id END) AS seanceId16_18,"
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.heureDebut END) AS heureDebut16_18, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.heureFin END) AS heureFin16_18, "
                +
                "MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN salle.nom END) AS nomSalle16_18 "
                +
                "FROM (SELECT 'LUNDI' AS weekday UNION ALL SELECT 'MARDI' AS weekday " +
                "UNION ALL SELECT 'MERCREDI' AS weekday UNION ALL SELECT 'JEUDI' AS weekday " +
                "UNION ALL SELECT 'VENDREDI' AS weekday UNION ALL SELECT 'SAMEDI' AS weekday) AS weekdays " +
                "LEFT JOIN seance ON weekdays.weekday = seance.jour " +
                "LEFT JOIN cours ON seance.idCours = cours.id " +
                "LEFT JOIN enseignant ON enseignant.id = cours.idEnseignant " +
                "LEFT JOIN salle ON salle.id = seance.idSalle " +
                "WHERE seance.idSalle = ? " +
                "GROUP BY weekdays.weekday) AS salle_schedule " +
                "ON weekdays.weekday = salle_schedule.Day " +
                "ORDER BY FIELD(weekdays.weekday, 'LUNDI', 'MARDI', 'MERCREDI', 'JEUDI', 'VENDREDI', 'SAMEDI');";
        parameters.add(idSalle);
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getMaterialBySalleId(int salleId) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM materielSalle WHERE idSalle = ?";
        parameters.add(salleId);
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getAllCategories() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM categorieSalle";
        return dbClient.executeCommand(true, query, parameters);
    }
    public static ResultSet getSeancesBySalle(int idSalle) throws SQLException {
        Vector<Object> parameters = new Vector<>();
        parameters.add(idSalle);
    
        String query = "SELECT * FROM seance WHERE idSalle = ?";
        return dbClient.executeCommand(true, query, parameters);
    }
// public static int getCapaciteSalleById(int idSalle) throws SQLException {
//     Vector<Object> parameters = new Vector<>();
//     parameters.add(idSalle);

//     String query = "SELECT capacite FROM salle WHERE id = ?";
//     ResultSet resultSet = dbClient.executeCommand(true, query, parameters);

//     // Vérifiez si le résultat contient une ligne
//     if (resultSet.next()) {
//         // Récupérez la capacité de la salle à partir du résultat de la requête
//         return resultSet.getInt("capacite");
//     } else {
//         // Si aucune ligne n'est retournée, retournez 0 ou une valeur par défaut
//         return 0;
//     }
// }



}
