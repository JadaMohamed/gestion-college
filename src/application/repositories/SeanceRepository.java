package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import application.database.SqlConnection;
import application.database.dbClient;
import application.model.Classe;
import application.model.Enseignant;
import application.model.enums.JoursSemaine;

public class SeanceRepository {

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

    public static int getNombreSeancesEnCours() throws SQLException {
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
        int nombreSeancesEnCours = 0;
        try (java.sql.Connection connectDB = SqlConnection.getConnection();
            java.sql.PreparedStatement statement = connectDB.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
    
            if (resultSet.next()) {
                nombreSeancesEnCours = resultSet.getInt("seancesencours");
                System.out.println("nombreSeancesEnCours est : " + nombreSeancesEnCours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return nombreSeancesEnCours;
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
        String query = "SELECT "
                + "(SELECT COUNT(DISTINCT idSalle) FROM seance JOIN salle ON seance.idSalle = salle.id "
                + "WHERE salle.idCategorieSalle = 2 AND DAYOFWEEK(CURDATE()) = CASE "
                + "WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 "
                + "WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 "
                + "WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 "
                + "WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreLaboratoiresOccupes, "
                + "(SELECT COUNT(DISTINCT idSalle) FROM seance JOIN salle ON seance.idSalle = salle.id "
                + "WHERE salle.idCategorieSalle = 3 AND DAYOFWEEK(CURDATE()) = CASE "
                + "WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 "
                + "WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 "
                + "WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 "
                + "WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreSalleCoursOccupes, "
                + "(SELECT COUNT(DISTINCT idSalle) FROM seance JOIN salle ON seance.idSalle = salle.id "
                + "WHERE salle.idCategorieSalle = 1 AND DAYOFWEEK(CURDATE()) = CASE "
                + "WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 "
                + "WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 "
                + "WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 "
                + "WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin) AS nombreSalleDeSportOccupes;";

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
        "AND CURTIME() BETWEEN S.heureDebut AND S.heureFin"
        ;
        return dbClient.executeCommand(true, query, parameters);
    }


}
