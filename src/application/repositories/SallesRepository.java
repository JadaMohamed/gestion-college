package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.database.dbClient;

public class SallesRepository {

    public static ResultSet getAllSalles() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT salle.*, categoriesalle.nom AS nomcategoriesalle from salle, categoriesalle WHERE categoriesalle.id = salle.idCategorieSalle";
        return dbClient.executeCommand(true, query, parameters);
    }
}
