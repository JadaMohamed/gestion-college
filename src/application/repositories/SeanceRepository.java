package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import application.database.dbClient;
import application.model.Classe;
import application.model.Enseignant;
import application.model.enums.JoursSemaine;

public class SeanceRepository {

    public static ResultSet getSeanceById(int idSeance) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT seance.id AS seance_id, seance.idSalle AS salle_id, seance.jour AS seance_jour, seance.heureDebut AS seance_heureDebut, seance.heureFin AS seance_heureFin, seance.idCours AS cours_id, seance.idClasse AS classe_id, cours.nom AS cours_nom, cours.idtypeCours AS typeCours_id, cours.idEnseignant AS enseignant_id, cours.idNiveau AS niveau_id, salle.idCategorieSalle AS categorie_salle_id, salle.nom AS salle_nom, salle.capacite AS salle_capacite, enseignant.nom AS enseignant_nom, enseignant.prenom AS enseignant_prenom, enseignant.sexe AS enseignant_sexe, enseignant.email AS enseignant_email, enseignant.telephone AS enseignant_telephone, enseignant.dateNaissance AS enseignant_date_naissance, enseignant.photoUrl AS enseignant_photo_url, typeCours.nom AS typeCours_nom FROM seance JOIN cours ON seance.idCours = cours.id JOIN salle ON seance.idSalle = salle.id JOIN enseignant ON cours.idEnseignant = enseignant.id JOIN typeCours ON cours.idtypeCours = typeCours.id WHERE seance.id = ?;";
        parameters.add(idSeance);
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getOccupiedHoraires(JoursSemaine jour, Classe classe, Enseignant enseignant)
            throws SQLException {
        String query = "SELECT s.heureDebut, s.heureFin " +
                "FROM seance s " +
                "INNER JOIN cours c ON s.idCours = c.id " +
                "WHERE (s.jour = ? AND s.idClasse = ?) OR (c.idEnseignant = ? AND s.jour = ?)";
        Vector<Object> parameters = new Vector<Object>();
        parameters.add(jour.name());
        parameters.add(classe.getId());
        parameters.add(enseignant.getId());
        parameters.add(jour.name());
        return dbClient.executeCommand(true, query, parameters);
    }

    public static void supprimerSeanceById(int idSeance) {
        Vector<Object> parameters = new Vector<>();
        String query = "DELETE FROM seance WHERE id = ?";
        parameters.add(idSeance);
        dbClient.executeCommand(false, query, parameters);
    }

    public static void modifierSeance(int idSeance, int idSalle, String jour, String heureDebut, String heureFin,
            int idCours,
            int idClasse) throws SQLException {
        String query = "UPDATE seance SET idSalle = ?, jour = ?, heureDebut = ?, heureFin = ?, idCours =?, idClasse = ? WHERE id = ?";
        Vector<Object> parameters = new Vector<Object>();
        parameters.add(idSalle);
        parameters.add(jour);
        parameters.add(heureDebut);
        parameters.add(heureFin);
        parameters.add(idCours);
        parameters.add(idClasse);
        parameters.add(idSeance);
        dbClient.executeCommand(false, query, parameters);
    }

    public static void insertIntoSeance(int idSalle, String jour, String heureDebut, String heureFin, int idCours,
            int idClasse) throws SQLException {
        String query = "INSERT INTO seance (id, idSalle, jour, heureDebut, heureFin, idCours, idClasse) VALUES (null, ?, ?, ?, ?, ?, ?)";
        Vector<Object> parameters = new Vector<Object>();
        parameters.add(idSalle);
        parameters.add(jour);
        parameters.add(heureDebut);
        parameters.add(heureFin);
        parameters.add(idCours);
        parameters.add(idClasse);
        dbClient.executeCommand(false, query, parameters);
    }

    public static ResultSet getNombreSeanceEnCours() throws SQLException {
        String query = "SELECT count(id) as seancesencours " +
                "FROM seance " +
                "WHERE DAYOFWEEK(CURDATE()) = CASE " +
                "WHEN seance.jour = 'LUNDI' THEN 2 " +
                "WHEN seance.jour = 'MARDI' THEN 3 " +
                "WHEN seance.jour = 'MERCREDI' THEN 4 " +
                "WHEN seance.jour = 'JEUDI' THEN 5 " +
                "WHEN seance.jour = 'VENDREDI' THEN 6 " +
                "WHEN seance.jour = 'SAMEDI' THEN 7 " +
                "WHEN seance.jour = 'DIMANCHE' THEN 1 " +
                "END " +
                "AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin";
        Vector<Object> parameters = new Vector<>();
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getNombreSallesOccupes() throws SQLException {
        String query = "SELECT " +
                "(SELECT COUNT(DISTINCT idSalle) " +
                " FROM seance " +
                " JOIN salle ON seance.idSalle = salle.id " +
                " WHERE salle.idCategorieSalle = 1 " +
                " AND DAYOFWEEK(CURDATE()) = CASE " +
                "                              WHEN seance.jour = 'LUNDI' THEN 2 " +
                "                              WHEN seance.jour = 'MARDI' THEN 3 " +
                "                              WHEN seance.jour = 'MERCREDI' THEN 4 " +
                "                              WHEN seance.jour = 'JEUDI' THEN 5 " +
                "                              WHEN seance.jour = 'VENDREDI' THEN 6 " +
                "                              WHEN seance.jour = 'SAMEDI' THEN 7 " +
                "                              WHEN seance.jour = 'DIMANCHE' THEN 1 " +
                "                             END " +
                " AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreLaboratoiresOccupes, " +

                "(SELECT COUNT(DISTINCT idSalle) " +
                " FROM seance " +
                " JOIN salle ON seance.idSalle = salle.id " +
                " WHERE salle.idCategorieSalle = 2 " +
                " AND DAYOFWEEK(CURDATE()) = CASE " +
                "                              WHEN seance.jour = 'LUNDI' THEN 2 " +
                "                              WHEN seance.jour = 'MARDI' THEN 3 " +
                "                              WHEN seance.jour = 'MERCREDI' THEN 4 " +
                "                              WHEN seance.jour = 'JEUDI' THEN 5 " +
                "                              WHEN seance.jour = 'VENDREDI' THEN 6 " +
                "                              WHEN seance.jour = 'SAMEDI' THEN 7 " +
                "                              WHEN seance.jour = 'DIMANCHE' THEN 1 " +
                "                             END " +
                " AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreSalleCoursOccupes, " +

                "(SELECT COUNT(DISTINCT idSalle) " +
                " FROM seance " +
                " JOIN salle ON seance.idSalle = salle.id " +
                " WHERE salle.idCategorieSalle = 3 " +
                " AND DAYOFWEEK(CURDATE()) = CASE " +
                "                              WHEN seance.jour = 'LUNDI' THEN 2 " +
                "                              WHEN seance.jour = 'MARDI' THEN 3 " +
                "                              WHEN seance.jour = 'MERCREDI' THEN 4 " +
                "                              WHEN seance.jour = 'JEUDI' THEN 5 " +
                "                              WHEN seance.jour = 'VENDREDI' THEN 6 " +
                "                              WHEN seance.jour = 'SAMEDI' THEN 7 " +
                "                              WHEN seance.jour = 'DIMANCHE' THEN 1 " +
                "                             END " +
                " AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreSalleDeSportOccupes;";
        Vector<Object> parameters = new Vector<>();
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getNombreCoursParNiveau() throws SQLException {
        List<Object> parameters = new ArrayList<>();
        String query = "SELECT "
                + "(SELECT COUNT(seance.id) FROM seance JOIN classe ON seance.idClasse = classe.id WHERE classe.idNiveauClasse = 1 AND DAYOFWEEK(CURDATE()) = CASE "
                + "WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 "
                + "WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 "
                + "WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 "
                + "WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreCours6eme, "
                + "(SELECT COUNT(seance.id) FROM seance JOIN classe ON seance.idClasse = classe.id WHERE classe.idNiveauClasse = 2 AND DAYOFWEEK(CURDATE()) = CASE "
                + "WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 "
                + "WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 "
                + "WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 "
                + "WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreCours5eme, "
                + "(SELECT COUNT(seance.id) FROM seance JOIN classe ON seance.idClasse = classe.id WHERE classe.idNiveauClasse = 3 AND DAYOFWEEK(CURDATE()) = CASE "
                + "WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 "
                + "WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 "
                + "WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 "
                + "WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreCours4eme, "
                + "(SELECT COUNT(seance.id) FROM seance JOIN classe ON seance.idClasse = classe.id WHERE classe.idNiveauClasse = 4 AND DAYOFWEEK(CURDATE()) = CASE "
                + "WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 "
                + "WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 "
                + "WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 "
                + "WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreCours3eme "
                + "FROM dual";
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getEffectifEnCours() throws SQLException {
        List<Object> parameters = new ArrayList<>();
        String query = "SELECT SUM(C.effectif) AS effectifEnCours " +
                "FROM classe C " +
                "JOIN seance S ON C.id = S.idClasse " +
                "WHERE DAYOFWEEK(CURDATE()) = CASE " +
                "    WHEN S.jour = 'LUNDI' THEN 2 " +
                "    WHEN S.jour = 'MARDI' THEN 3 " +
                "    WHEN S.jour = 'MERCREDI' THEN 4 " +
                "    WHEN S.jour = 'JEUDI' THEN 5 " +
                "    WHEN S.jour = 'VENDREDI' THEN 6 " +
                "    WHEN S.jour = 'SAMEDI' THEN 7 " +
                "    WHEN S.jour = 'DIMANCHE' THEN 1 " +
                "END " +
                "AND CURTIME() BETWEEN S.heureDebut AND S.heureFin";
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getSeancesEnCours() {
        String query = "SELECT salle.nom AS nomSalle, cours.nom AS nomCours, niveau.nom AS nomNiveau,classe.effectif AS effectif, classe.numero AS numeroClasse, enseignant.nom AS nomEnseignant, enseignant.prenom AS prenomEnseignant, enseignant.email AS emailEnseignant, enseignant.photoUrl AS photoUrlEnseignant, seance.heureDebut, seance.heureFin "
                +
                "FROM salle, cours, classe, enseignant, seance, niveau " +
                "WHERE salle.id = seance.idSalle " +
                "AND enseignant.id = cours.idEnseignant " +
                "AND seance.idCours = cours.id " +
                "AND seance.idClasse = classe.id " +
                "AND niveau.id = classe.idNiveauClasse " +
                "AND DAYOFWEEK(CURDATE()) = CASE " +
                "WHEN seance.jour = 'LUNDI' THEN 2 " +
                "WHEN seance.jour = 'MARDI' THEN 3 " +
                "WHEN seance.jour = 'MERCREDI' THEN 4 " +
                "WHEN seance.jour = 'JEUDI' THEN 5 " +
                "WHEN seance.jour = 'VENDREDI' THEN 6 " +
                "WHEN seance.jour = 'SAMEDI' THEN 7 " +
                "WHEN seance.jour = 'DIMANCHE' THEN 1 " +
                "END " +
                "AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin;";
        Vector<Object> parameters = new Vector<Object>();
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getSeances_search(String searchKey) {
        Vector<Object> parameters = new Vector<>();
        String query = "SELECT seance.id AS idSeance, " +
                "salle.nom AS nomSalle," +
                "cours.nom AS nomCours, " +
                "niveau.nom AS nomNiveau, " +
                "classe.effectif AS effectif, " +
                "classe.numero AS numeroClasse, " +
                "enseignant.nom AS nomEnseignant, " +
                "enseignant.prenom AS prenomEnseignant, " +
                "enseignant.email AS emailEnseignant, " +
                "enseignant.photoUrl AS photoUrlEnseignant, " +
                "seance.heureDebut, " +
                "seance.heureFin " +
                "FROM salle, cours, classe, enseignant, seance, niveau " +
                "WHERE salle.id = seance.idSalle " +
                "AND enseignant.id = cours.idEnseignant " +
                "AND seance.idCours = cours.id " +
                "AND seance.idClasse = classe.id " +
                "AND niveau.id = classe.idNiveauClasse " +
                "AND DAYOFWEEK(CURDATE()) = CASE " +
                "    WHEN seance.jour = 'LUNDI' THEN 2 " +
                "    WHEN seance.jour = 'MARDI' THEN 3 " +
                "    WHEN seance.jour = 'MERCREDI' THEN 4 " +
                "    WHEN seance.jour = 'JEUDI' THEN 5 " +
                "    WHEN seance.jour = 'VENDREDI' THEN 6 " +
                "    WHEN seance.jour = 'SAMEDI' THEN 7 " +
                "    WHEN seance.jour = 'DIMANCHE' THEN 1 " +
                "END " +
                "AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin " +
                "AND (seance.heureDebut LIKE ? OR " +
                "     seance.heureFin LIKE ? OR " +
                "     classe.effectif LIKE ? OR " +
                "     salle.nom LIKE ? OR " +
                "     cours.nom LIKE ? OR " +
                "     enseignant.nom LIKE ? OR " +
                "     classe.numero LIKE ? OR " +
                "     enseignant.prenom LIKE ? OR " +
                "     enseignant.email LIKE ? OR " +
                "     niveau.nom LIKE ? );";

        String searchPattern = "%" + searchKey + "%";
        parameters.add(searchPattern);
        parameters.add(searchPattern);
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
    // public static ResultSet getseancesBySalleId(int idSalle) throws SQLException {
    //     List<Object> parameters = new ArrayList<>();
    //     String query = "SELECT * FROM seance WHERE  idSalle= ?";
    //     parameters.add(idSalle);
    //     return dbClient.executeCommand(true, query, parameters);
    // }
}
