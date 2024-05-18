package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import application.database.SqlConnection;

public class CategorieSalleRepository {
    public List<String> getNomsCategoriesByIdCategories(List<Integer> idCategories) {
        List<String> nomsCategories = new ArrayList<>();
        if (idCategories.isEmpty()) {
            System.out.println("IdCategories is empty. Returning empty list.");
            return nomsCategories; // Return an empty list if idCategories is empty
        }
        // Création de la chaîne de paramètres pour la clause IN
        String params = idCategories.stream()
                .map(id -> "?")
                .collect(Collectors.joining(", "));
        String query = "SELECT nom FROM categorieSalle WHERE idCategoriSSalle IN (" + params + ")";

        System.out.println("Query: " + query); // Print the query string for debugging

        try (java.sql.Connection connection = SqlConnection.getConnection();
                java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Définition des valeurs des paramètres
            for (int i = 0; i < idCategories.size(); i++) {
                preparedStatement.setInt(i + 1, idCategories.get(i));
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    nomsCategories.add(resultSet.getString("nom"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomsCategories;
    }
 
}
