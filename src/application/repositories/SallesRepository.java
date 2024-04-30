package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import application.database.SqlConnection;
import application.database.dbClient;
import application.model.Horaires;
import application.model.enums.JoursSemaine;

public class SallesRepository {

    public static ResultSet getAllSalles() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT salle.*, categoriesalle.nom AS nomcategoriesalle from salle, categoriesalle WHERE categoriesalle.id = salle.idCategorieSalle";
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getAvailableSallesByHoraire(JoursSemaine jour, Horaires horaire) throws SQLException {
        Vector<Object> parameters = new Vector<>();
        String query = "SELECT s.id, cs.nom AS nomcategoriesalle, s.idCategorieSalle, s.nom, s.capacite FROM salle s, categoriesalle cs WHERE cs.id = s.idCategorieSalle AND s.id NOT IN ( SELECT se.idSalle FROM seance se WHERE se.jour = ? AND ( (se.heureDebut >= ? AND se.heureDebut < ?) OR (se.heureFin > ? AND se.heureFin <= ?) OR (se.heureDebut <= ? AND se.heureFin >= ?) ) )";
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

    public List<Integer> getIdCategoriesByIdSalles(List<Integer> idSalles) {
        java.sql.Connection connectDB = SqlConnection.getConnection();

        List<Integer> idCategories = new ArrayList<>();
        String query = "SELECT idCategorieSalle FROM salle WHERE idSalle IN (";
        for (int i = 0; i < idSalles.size(); i++) {
            query += "?";
            if (i < idSalles.size() - 1) {
                query += ",";
            }
        }
        query += ")";
        try (java.sql.PreparedStatement statement = connectDB.prepareStatement(query)) {
            for (int i = 0; i < idSalles.size(); i++) {
                statement.setInt(i + 1, idSalles.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                idCategories.add(resultSet.getInt("idCategorieSalle"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idCategories;
    }

    public int getTotalSallesByCategory(String category) {
        java.sql.Connection connectDB = SqlConnection.getConnection();
        int total = 0;
        String query = "SELECT COUNT(*) AS total FROM salle WHERE idCategorieSalle = (SELECT id FROM categoriesalle WHERE nom = ?)";
        try (java.sql.PreparedStatement statement = connectDB.prepareStatement(query)) {
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<String> getNomsCategoriesByIdCategories(List<Integer> idCategories) {
        java.sql.Connection connectDB = SqlConnection.getConnection();
        List<String> nomsCategories = new ArrayList<>();
        String query = "SELECT nom FROM categoriesalle WHERE id IN (";
        for (int i = 0; i < idCategories.size(); i++) {
            query += (i == 0 ? "?" : ", ?");
        }
        query += ")";
        try (java.sql.PreparedStatement statement = connectDB.prepareStatement(query)) {
            for (int i = 0; i < idCategories.size(); i++) {
                statement.setInt(i + 1, idCategories.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                nomsCategories.add(resultSet.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomsCategories;
    }
}
