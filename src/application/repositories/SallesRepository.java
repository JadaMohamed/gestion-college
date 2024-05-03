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
        String query = "SELECT " +
        "    salle.id, " +
        "    salle.nom, " +
        "    salle.capacite, " +
        "    IFNULL(statut, '0') AS statut, " +
        "    IFNULL(nomCours, '-') AS nomCours, " +
        "    IFNULL(numeroClasse, '-') AS numeroClasse " +
        "FROM " +
        "    salle " +
        "LEFT JOIN ( " +
        "    SELECT " +
        "        idSalle, " +
        "        CASE " +
        "            WHEN CURTIME() BETWEEN seance.heureDebut AND seance.heureFin THEN '1' " +
        "            ELSE '0' " +
        "        END AS statut, " +
        "        COALESCE(cours.nom, '-') AS nomCours, " +
        "        CONCAT(niveau.nom, ' ', classe.numero) AS numeroClasse " +
        "    FROM " +
        "        seance " +
        "    LEFT JOIN cours ON cours.id = seance.idCours " +
        "    LEFT JOIN classe ON classe.id = seance.idClasse " +
        "    LEFT JOIN niveau ON niveau.id = classe.idNiveauClasse " +
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
        ") AS seance_info ON salle.id = seance_info.idSalle " +
        "GROUP BY salle.id;";


        return dbClient.executeCommand(true, query, parameters);
    }
}
