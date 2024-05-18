package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.database.dbClient;
import application.model.Enseignant;
import application.utilities.DateUtil;

public class EnseignantRepository {

    public static ResultSet getAllEnseignants() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM enseignant";
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getEnseignantById(int idEnseignant) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM enseignant WHERE id = ?";
        parameters.add(idEnseignant);
        return dbClient.executeCommand(true, query, parameters);
    }

    public static void modifierEnseignant(Enseignant enseignant) {
        Vector<Object> parameters = new Vector<>();
        String query = "UPDATE enseignant SET nom = ?, prenom = ?, sexe = ?, email = ?, telephone = ?, dateNaissance = ? WHERE id =?";
        parameters.add(enseignant.getNom());
        parameters.add(enseignant.getPrenom());
        parameters.add(enseignant.getSexe());
        parameters.add(enseignant.getEmail());
        parameters.add(enseignant.getTelephone());
        parameters.add(enseignant.getDateNaissance().toString());
        parameters.add(enseignant.getId());
        dbClient.executeCommand(false, query, parameters);
    }

    public static void ajouterEnseignant(Enseignant enseignant) {
        Vector<Object> parameters = new Vector<>();
        String query = "INSERT INTO enseignant (id, nom, prenom, sexe, email, telephone, dateNaissance) "
                + " VALUES (NULL, ?, ?, ?, ?, ?, ?);";
        parameters.add(enseignant.getNom());
        parameters.add(enseignant.getPrenom());
        parameters.add(enseignant.getSexe());
        parameters.add(enseignant.getEmail());
        parameters.add(enseignant.getTelephone());
        parameters.add(enseignant.getDateNaissance().toString());
        dbClient.executeCommand(false, query, parameters);
    }

    public static void supprimerEnseignant(int idEnseignant) {
        Vector<Object> parameters = new Vector<>();
        String query = "DELETE FROM enseignant WHERE enseignant.id = ?";
        parameters.add(idEnseignant);
        dbClient.executeCommand(false, query, parameters);
    }

    public static ResultSet getAllEnseignantsWithEngoingSeances() {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT " +
                "e.id AS enseignant_id, " +
                "e.nom AS enseignant_nom, " +
                "e.prenom AS enseignant_prenom, " +
                "e.email AS enseignant_email, " +
                "e.telephone AS enseignant_telephone, " +
                "e.photoUrl AS enseignant_photoUrl, " +
                "e.sexe AS enseignant_sexe, " +
                "e.dateNaissance AS enseignant_naissance, " +
                "COUNT(DISTINCT s.id) AS total_seances, " +
                "CASE " +
                "    WHEN EXISTS ( " +
                "        SELECT 1 " +
                "        FROM cours c " +
                "        JOIN seance s ON c.id = s.idCours " +
                "        WHERE c.idEnseignant = e.id " +
                "          AND s.jour = ? " +
                "          AND CURTIME() BETWEEN s.heureDebut AND s.heureFin " +
                "    ) THEN 1 " +
                "    ELSE 0 " +
                "END AS statut, " +
                "CASE " +
                "    WHEN EXISTS ( " +
                "        SELECT 1 " +
                "        FROM cours c " +
                "        JOIN seance s ON c.id = s.idCours " +
                "        JOIN salle sl ON s.idSalle = sl.id " +
                "        JOIN classe cl ON s.idClasse = cl.id " +
                "        WHERE c.idEnseignant = e.id " +
                "          AND s.jour = ? " +
                "          AND CURTIME() BETWEEN s.heureDebut AND s.heureFin " +
                "    ) THEN ( " +
                "        SELECT sl.nom " +
                "        FROM cours c " +
                "        JOIN seance s ON c.id = s.idCours " +
                "        JOIN salle sl ON s.idSalle = sl.id " +
                "        JOIN classe cl ON s.idClasse = cl.id " +
                "        WHERE c.idEnseignant = e.id " +
                "          AND s.jour = ? " +
                "          AND CURTIME() BETWEEN s.heureDebut AND s.heureFin " +
                "        LIMIT 1 " +
                "    ) " +
                "    ELSE NULL " +
                "END AS salle_nom, " +
                "CASE " +
                "    WHEN EXISTS ( " +
                "        SELECT 1 " +
                "        FROM cours c " +
                "        JOIN seance s ON c.id = s.idCours " +
                "        JOIN salle sl ON s.idSalle = sl.id " +
                "        JOIN classe cl ON s.idClasse = cl.id " +
                "        WHERE c.idEnseignant = e.id " +
                "          AND s.jour = ? " +
                "          AND CURTIME() BETWEEN s.heureDebut AND s.heureFin " +
                "    ) THEN ( " +
                "        SELECT CONCAT(n.nom, ' ', cl.numero) " +
                "        FROM classe cl " +
                "        JOIN seance s ON cl.id = s.idClasse " +
                "        JOIN cours c ON c.id = s.idCours " +
                "        JOIN niveau n ON n.id = cl.idNiveauClasse " +
                "        WHERE c.idEnseignant = e.id " +
                "          AND s.jour = ? " +
                "          AND CURTIME() BETWEEN s.heureDebut AND s.heureFin " +
                "        LIMIT 1 " +
                "    ) " +
                "    ELSE NULL " +
                "END AS classe_nom, " +
                "CASE " +
                "    WHEN EXISTS ( " +
                "        SELECT 1 " +
                "        FROM cours c " +
                "        JOIN seance s ON c.id = s.idCours " +
                "        JOIN salle sl ON s.idSalle = sl.id " +
                "        JOIN classe cl ON s.idClasse = cl.id " +
                "        WHERE c.idEnseignant = e.id " +
                "          AND s.jour = ? " +
                "          AND CURTIME() BETWEEN s.heureDebut AND s.heureFin " +
                "    ) THEN ( " +
                "        SELECT CONCAT(TIME_FORMAT(s.heureDebut, '%H:%i'), ' - ', TIME_FORMAT(s.heureFin, '%H:%i')) " +
                "        FROM cours c " +
                "        JOIN seance s ON c.id = s.idCours " +
                "        JOIN salle sl ON s.idSalle = sl.id " +
                "        JOIN classe cl ON s.idClasse = cl.id " +
                "        WHERE c.idEnseignant = e.id " +
                "          AND s.jour = ? " +
                "          AND CURTIME() BETWEEN s.heureDebut AND s.heureFin " +
                "        LIMIT 1 " +
                "    ) " +
                "    ELSE NULL " +
                "END AS horaire, " +
                "CASE " +
                "    WHEN EXISTS ( " +
                "        SELECT 1 " +
                "        FROM cours c " +
                "        JOIN seance s ON c.id = s.idCours " +
                "        JOIN salle sl ON s.idSalle = sl.id " +
                "        JOIN classe cl ON s.idClasse = cl.id " +
                "        WHERE c.idEnseignant = e.id " +
                "          AND s.jour = ? " +
                "          AND CURTIME() BETWEEN s.heureDebut AND s.heureFin " +
                "    ) THEN ( " +
                "        SELECT c.nom " +
                "        FROM cours c " +
                "        JOIN seance s ON c.id = s.idCours " +
                "        JOIN salle sl ON s.idSalle = sl.id " +
                "        JOIN classe cl ON s.idClasse = cl.id " +
                "        WHERE c.idEnseignant = e.id " +
                "          AND s.jour= ? " +
                " AND CURTIME() BETWEEN s.heureDebut AND s.heureFin " +
                " LIMIT 1 " +
                " ) " +
                " ELSE NULL " +
                "END AS cours_nom " +
                "FROM " +
                "enseignant e " +
                "LEFT JOIN " +
                "cours c ON e.id = c.idEnseignant " +
                "LEFT JOIN " +
                "seance s ON c.id = s.idCours " +
                "GROUP BY " +
                "e.id, e.nom, e.prenom;";

        for (int i = 0; i < 9; i++) {
            parameters.add(DateUtil.nomDuJour());
        }
        return dbClient.executeCommand(true, query, parameters);

    }

    public static ResultSet getEmploiDeTemps(String idEnseignant) {
        Vector<Object> parameters = new Vector<>();

        String query = "SELECT weekdays.weekday AS Day, COALESCE(seanceId8_10, '-') AS seanceId8_10, COALESCE(seanceId10_12, '-') AS seanceId10_12,COALESCE(seanceId14_16, '-') AS seanceId14_16,COALESCE(seanceId16_18, '-') AS seanceId16_18, COALESCE(coursnom8_10, '-') AS coursnom8_10, COALESCE(nomEnseignant8_10, '-') AS nomEnseignant8_10, COALESCE(prenomEnseignant8_10, '-') AS prenomEnseignant8_10, COALESCE(photoUrlEnseignant8_10, '-') AS photoUrlEnseignant8_10, COALESCE(heureDebut8_10, '-') AS heureDebut8_10, COALESCE(heureFin8_10, '-') AS heureFin8_10, COALESCE(nomSalle8_10, '-') AS nomSalle8_10, COALESCE(coursnom10_12, '-') AS coursnom10_12, COALESCE(nomEnseignant10_12, '-') AS nomEnseignant10_12, COALESCE(prenomEnseignant10_12, '-') AS prenomEnseignant10_12, COALESCE(photoUrlEnseignant10_12, '-') AS photoUrlEnseignant10_12, COALESCE(heureDebut10_12, '-') AS heureDebut10_12, COALESCE(heureFin10_12, '-') AS heureFin10_12, COALESCE(nomSalle10_12, '-') AS nomSalle10_12, COALESCE(coursnom14_16, '-') AS coursnom14_16, COALESCE(nomEnseignant14_16, '-') AS nomEnseignant14_16, COALESCE(prenomEnseignant14_16, '-') AS prenomEnseignant14_16, COALESCE(photoUrlEnseignant14_16, '-') AS photoUrlEnseignant14_16, COALESCE(heureDebut14_16, '-') AS heureDebut14_16, COALESCE(heureFin14_16, '-') AS heureFin14_16, COALESCE(nomSalle14_16, '-') AS nomSalle14_16, COALESCE(coursnom16_18, '-') AS coursnom16_18, COALESCE(nomEnseignant16_18, '-') AS nomEnseignant16_18, COALESCE(prenomEnseignant16_18, '-') AS prenomEnseignant16_18, COALESCE(photoUrlEnseignant16_18, '-') AS photoUrlEnseignant16_18, COALESCE(heureDebut16_18, '-') AS heureDebut16_18, COALESCE(heureFin16_18, '-') AS heureFin16_18, COALESCE(nomSalle16_18, '-') AS nomSalle16_18 FROM (SELECT 'LUNDI'  AS weekday UNION ALL SELECT 'MARDI'  AS weekday UNION ALL SELECT 'MERCREDI'  AS weekday UNION ALL SELECT 'JEUDI'  AS weekday UNION ALL SELECT 'VENDREDI'  AS weekday UNION ALL SELECT 'SAMEDI'  AS weekday) AS weekdays LEFT JOIN (SELECT weekdays.weekday AS Day, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN cours.nom END) AS coursnom8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.nom END) AS nomEnseignant8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.prenom END) AS prenomEnseignant8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.heureDebut END) AS heureDebut8_10,  MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.id END) AS seanceId8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.heureFin END) AS heureFin8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN salle.nom END) AS nomSalle8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN cours.nom END) AS coursnom10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.nom END) AS nomEnseignant10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.prenom END) AS prenomEnseignant10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.heureDebut END) AS heureDebut10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.heureFin END) AS heureFin10_12,MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.id END) AS seanceId10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN salle.nom END) AS nomSalle10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN cours.nom END) AS coursnom14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.nom END) AS nomEnseignant14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.prenom END) AS prenomEnseignant14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.heureDebut END) AS heureDebut14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.id END) AS seanceId14_16 ,MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.heureFin END) AS heureFin14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN salle.nom END) AS nomSalle14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN cours.nom END) AS coursnom16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.nom END) AS nomEnseignant16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.prenom END) AS prenomEnseignant16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.heureDebut END) AS heureDebut16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.id END) AS seanceId16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.heureFin END) AS heureFin16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN salle.nom END) AS nomSalle16_18 FROM (SELECT 'LUNDI' AS weekday UNION ALL SELECT 'MARDI' AS weekday UNION ALL SELECT 'MERCREDI' AS weekday UNION ALL SELECT 'JEUDI' AS weekday UNION ALL SELECT 'VENDREDI' AS weekday UNION ALL SELECT 'SAMEDI' AS weekday) AS weekdays LEFT JOIN seance ON weekdays.weekday  = seance.jour LEFT JOIN cours ON seance.idCours = cours.id LEFT JOIN enseignant ON enseignant.id = cours.idEnseignant LEFT JOIN salle ON salle.id = seance.idSalle WHERE cours.idEnseignant = ?  GROUP BY weekdays.weekday) AS class_schedule ON weekdays.weekday = class_schedule.Day ORDER BY FIELD(weekdays.weekday , 'LUNDI', 'MARDI', 'MERCREDI', 'JEUDI', 'VENDREDI', 'SAMEDI');";
        parameters.add(idEnseignant);
        return dbClient.executeCommand(true, query, parameters);
    }
}
