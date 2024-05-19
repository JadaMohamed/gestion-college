package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import application.database.dbClient;

public class ClasseRepository {

    public static ResultSet getAllNiveaux() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM niveau";
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getAllClasses() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT CL.*, NI.nom FROM classe CL, niveau NI WHERE CL.idNiveauClasse = NI.id";
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getAllClassesWithCurrentSeances() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT classe.id, classe.numero AS numeroClasse, niveau.nom AS nomNiveau, classe.effectif, CASE WHEN seance.id IS NOT NULL THEN CASE WHEN DAYOFWEEK(CURDATE()) = CASE WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin THEN 1 ELSE 0 END ELSE 0 END AS status, CASE WHEN seance.id IS NOT NULL THEN CASE WHEN DAYOFWEEK(CURDATE()) = CASE WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin THEN salle.nom ELSE '-' END ELSE '-' END AS nomSalle, CASE WHEN seance.id IS NOT NULL THEN COALESCE(cours.nom, '-') ELSE '-' END AS nomCours, CASE WHEN seance.id IS NOT NULL THEN COALESCE(enseignant.nom, '-') ELSE '-' END AS nomEnseignant, CASE WHEN seance.id IS NOT NULL THEN COALESCE(enseignant.prenom, '-') ELSE '-' END AS prenomEnseignant, CASE WHEN seance.id IS NOT NULL THEN COALESCE(enseignant.email, '-') ELSE '-' END AS emailEnseignant, CASE WHEN seance.id IS NOT NULL THEN COALESCE(enseignant.photoUrl, '-') ELSE '-' END AS photoUrlEnseignant FROM classe LEFT JOIN niveau ON niveau.id = classe.idNiveauClasse LEFT JOIN seance ON classe.id = seance.idClasse AND DAYOFWEEK(CURDATE()) = CASE WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin LEFT JOIN salle ON seance.idSalle = salle.id LEFT JOIN cours ON cours.id = seance.idCours LEFT JOIN enseignant ON enseignant.id = cours.idEnseignant;";
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getEmploiDeTemps(String idClasse) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT weekdays.weekday AS Day, COALESCE(seanceId8_10, '-') AS seanceId8_10, COALESCE(seanceId10_12, '-') AS seanceId10_12,COALESCE(seanceId14_16, '-') AS seanceId14_16,COALESCE(seanceId16_18, '-') AS seanceId16_18, COALESCE(coursnom8_10, '-') AS coursnom8_10, COALESCE(nomEnseignant8_10, '-') AS nomEnseignant8_10, COALESCE(prenomEnseignant8_10, '-') AS prenomEnseignant8_10, COALESCE(photoUrlEnseignant8_10, '-') AS photoUrlEnseignant8_10, COALESCE(heureDebut8_10, '-') AS heureDebut8_10, COALESCE(heureFin8_10, '-') AS heureFin8_10, COALESCE(nomSalle8_10, '-') AS nomSalle8_10, COALESCE(coursnom10_12, '-') AS coursnom10_12, COALESCE(nomEnseignant10_12, '-') AS nomEnseignant10_12, COALESCE(prenomEnseignant10_12, '-') AS prenomEnseignant10_12, COALESCE(photoUrlEnseignant10_12, '-') AS photoUrlEnseignant10_12, COALESCE(heureDebut10_12, '-') AS heureDebut10_12, COALESCE(heureFin10_12, '-') AS heureFin10_12, COALESCE(nomSalle10_12, '-') AS nomSalle10_12, COALESCE(coursnom14_16, '-') AS coursnom14_16, COALESCE(nomEnseignant14_16, '-') AS nomEnseignant14_16, COALESCE(prenomEnseignant14_16, '-') AS prenomEnseignant14_16, COALESCE(photoUrlEnseignant14_16, '-') AS photoUrlEnseignant14_16, COALESCE(heureDebut14_16, '-') AS heureDebut14_16, COALESCE(heureFin14_16, '-') AS heureFin14_16, COALESCE(nomSalle14_16, '-') AS nomSalle14_16, COALESCE(coursnom16_18, '-') AS coursnom16_18, COALESCE(nomEnseignant16_18, '-') AS nomEnseignant16_18, COALESCE(prenomEnseignant16_18, '-') AS prenomEnseignant16_18, COALESCE(photoUrlEnseignant16_18, '-') AS photoUrlEnseignant16_18, COALESCE(heureDebut16_18, '-') AS heureDebut16_18, COALESCE(heureFin16_18, '-') AS heureFin16_18, COALESCE(nomSalle16_18, '-') AS nomSalle16_18 FROM (SELECT 'LUNDI'  AS weekday UNION ALL SELECT 'MARDI'  AS weekday UNION ALL SELECT 'MERCREDI'  AS weekday UNION ALL SELECT 'JEUDI'  AS weekday UNION ALL SELECT 'VENDREDI'  AS weekday UNION ALL SELECT 'SAMEDI'  AS weekday) AS weekdays LEFT JOIN (SELECT weekdays.weekday AS Day, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN cours.nom END) AS coursnom8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.nom END) AS nomEnseignant8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.prenom END) AS prenomEnseignant8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.heureDebut END) AS heureDebut8_10,  MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.id END) AS seanceId8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.heureFin END) AS heureFin8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN salle.nom END) AS nomSalle8_10, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN cours.nom END) AS coursnom10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.nom END) AS nomEnseignant10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.prenom END) AS prenomEnseignant10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.heureDebut END) AS heureDebut10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.heureFin END) AS heureFin10_12,MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.id END) AS seanceId10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN salle.nom END) AS nomSalle10_12, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN cours.nom END) AS coursnom14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.nom END) AS nomEnseignant14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.prenom END) AS prenomEnseignant14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.heureDebut END) AS heureDebut14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.id END) AS seanceId14_16 ,MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.heureFin END) AS heureFin14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN salle.nom END) AS nomSalle14_16, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN cours.nom END) AS coursnom16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.nom END) AS nomEnseignant16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.prenom END) AS prenomEnseignant16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.heureDebut END) AS heureDebut16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.id END) AS seanceId16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.heureFin END) AS heureFin16_18, MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN salle.nom END) AS nomSalle16_18 FROM (SELECT 'LUNDI' AS weekday UNION ALL SELECT 'MARDI' AS weekday UNION ALL SELECT 'MERCREDI' AS weekday UNION ALL SELECT 'JEUDI' AS weekday UNION ALL SELECT 'VENDREDI' AS weekday UNION ALL SELECT 'SAMEDI' AS weekday) AS weekdays LEFT JOIN seance ON weekdays.weekday  = seance.jour LEFT JOIN cours ON seance.idCours = cours.id LEFT JOIN enseignant ON enseignant.id = cours.idEnseignant LEFT JOIN salle ON salle.id = seance.idSalle WHERE seance.idClasse = ? GROUP BY weekdays.weekday) AS class_schedule ON weekdays.weekday = class_schedule.Day ORDER BY FIELD(weekdays.weekday , 'LUNDI', 'MARDI', 'MERCREDI', 'JEUDI', 'VENDREDI', 'SAMEDI');";
        parameters.add(idClasse);
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getEtudiantsByClasseId(int classeId) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM etudiant WHERE idClasse = ?";
        parameters.add(classeId);
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getEtudiantsByClasseId_search(int classeId, String searchKey) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM etudiant WHERE idClasse = ? AND (cne LIKE ? OR nom LIKE ? OR prenom LIKE ? OR email LIKE ? OR telephone LIKE ? OR telephoneParent LIKE ? OR emailParent LIKE ?)";
        parameters.add(classeId);
        parameters.add("%" + searchKey + "%");
        parameters.add("%" + searchKey + "%");
        parameters.add("%" + searchKey + "%");
        parameters.add("%" + searchKey + "%");
        parameters.add("%" + searchKey + "%");
        parameters.add("%" + searchKey + "%");
        parameters.add("%" + searchKey + "%");
        return dbClient.executeCommand(true, query, parameters);
    }

    public static void updateClasseEffectif(int classId, int effectif) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "UPDATE classe SET effectif = ? WHERE id = ?";
        parameters.add(effectif);
        parameters.add(classId);
        dbClient.executeCommand(false, query, parameters);
    }

    public static ResultSet getClasses_search(String searchKey) {
        Vector<Object> parameters = new Vector<>();
        String query = "SELECT " +
    "classe.id, " +
    "CONCAT(niveau.nom, ' ', classe.numero) AS classe, " +
    "classe.effectif, " +
    "CASE " +
    "    WHEN seance.id IS NOT NULL THEN " +
    "        CASE " +
    "            WHEN DAYOFWEEK(CURDATE()) = " +
    "                CASE " +
    "                    WHEN seance.jour = 'LUNDI' THEN 2 " +
    "                    WHEN seance.jour = 'MARDI' THEN 3 " +
    "                    WHEN seance.jour = 'MERCREDI' THEN 4 " +
    "                    WHEN seance.jour = 'JEUDI' THEN 5 " +
    "                    WHEN seance.jour = 'VENDREDI' THEN 6 " +
    "                    WHEN seance.jour = 'SAMEDI' THEN 7 " +
    "                    WHEN seance.jour = 'DIMANCHE' THEN 1 " +
    "                END " +
    "            AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin " +
    "            THEN 1 " +
    "            ELSE 0 " +
    "        END " +
    "    ELSE 0 " +
    "END AS status, " +
    "CASE " +
    "    WHEN seance.id IS NOT NULL THEN " +
    "        CASE " +
    "            WHEN DAYOFWEEK(CURDATE()) = " +
    "                CASE " +
    "                    WHEN seance.jour = 'LUNDI' THEN 2 " +
    "                    WHEN seance.jour = 'MARDI' THEN 3 " +
    "                    WHEN seance.jour = 'MERCREDI' THEN 4 " +
    "                    WHEN seance.jour = 'JEUDI' THEN 5 " +
    "                    WHEN seance.jour = 'VENDREDI' THEN 6 " +
    "                    WHEN seance.jour = 'SAMEDI' THEN 7 " +
    "                    WHEN seance.jour = 'DIMANCHE' THEN 1 " +
    "                END " +
    "            AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin " +
    "            THEN salle.nom " +
    "            ELSE '-' " +
    "        END " +
    "    ELSE '-' " +
    "END AS nomSalle, " +
    "CASE " +
    "    WHEN seance.id IS NOT NULL THEN " +
    "        COALESCE(cours.nom, '-') " +
    "    ELSE '-' " +
    "END AS nomCours, " +
    "CASE " +
    "    WHEN seance.id IS NOT NULL THEN " +
    "        COALESCE(enseignant.nom, '-') " +
    "    ELSE '-' " +
    "END AS nomEnseignant, " +
    "CASE " +
    "    WHEN seance.id IS NOT NULL THEN " +
    "        COALESCE(enseignant.prenom, '-') " +
    "    ELSE '-' " +
    "END AS prenomEnseignant, " +
    "CASE " +
    "    WHEN seance.id IS NOT NULL THEN " +
    "        COALESCE(enseignant.email, '-') " +
    "    ELSE '-' " +
    "END AS emailEnseignant, " +
    "CASE " +
    "    WHEN seance.id IS NOT NULL THEN " +
    "        COALESCE(enseignant.photoUrl, '-') " +
    "    ELSE '-' " +
    "END AS photoUrlEnseignant " +
    "FROM " +
    "    classe " +
    "LEFT JOIN niveau ON niveau.id = classe.idNiveauClasse " +
    "LEFT JOIN seance ON classe.id = seance.idClasse " +
    "    AND DAYOFWEEK(CURDATE()) = " +
    "        CASE " +
    "            WHEN seance.jour = 'LUNDI' THEN 2 " +
    "            WHEN seance.jour = 'MARDI' THEN 3 " +
    "            WHEN seance.jour = 'MERCREDI' THEN 4 " +
    "            WHEN seance.jour = 'JEUDI' THEN 5 " +
    "            WHEN seance.jour = 'VENDREDI' THEN 6 " +
    "            WHEN seance.jour = 'SAMEDI' THEN 7 " +
    "            WHEN seance.jour = 'DIMANCHE' THEN 1 " +
    "        END " +
    "    AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin " +
    "LEFT JOIN salle ON seance.idSalle = salle.id " +
    "LEFT JOIN cours ON cours.id = seance.idCours " +
    "LEFT JOIN enseignant ON enseignant.id = cours.idEnseignant " +
    "WHERE ( " +
    "       classe.numero LIKE ? " +
    "       OR classe.effectif LIKE ? " +
    "       OR salle.nom LIKE ? " +
    "       OR cours.nom LIKE ? " +
    "       OR enseignant.nom LIKE ? " +
    "       OR enseignant.prenom LIKE ? " +
    "       OR enseignant.email LIKE ? " +
    "       OR niveau.nom LIKE ? " +
    "      )";
        
        String searchPattern = "%" + searchKey + "%";
        parameters.add(searchPattern);
        parameters.add(searchPattern);
        parameters.add(searchPattern);
        parameters.add(searchPattern);
        parameters.add(searchPattern);
        parameters.add(searchPattern);
        parameters.add(searchPattern);
        parameters.add(searchPattern);
        
        return dbClient.executeCommand(true, query, parameters);
    }
    
}
