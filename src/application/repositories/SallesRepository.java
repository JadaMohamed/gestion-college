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

}
