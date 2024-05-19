package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import application.database.dbClient;

public class CoursRepository {

    public static ResultSet insertIntoCours(int idTypeCours, String nom, int idEnseignant, int idNiveau)
            throws SQLException {
        String insertQuery = "INSERT INTO cours (id, idTypeCours, nom, idEnseignant, idNiveau) VALUES (null, ?, ?, ?, ?)";
        Vector<Object> insertParameters = new Vector<Object>();
        insertParameters.add(idTypeCours);
        insertParameters.add(nom);
        insertParameters.add(idEnseignant);
        insertParameters.add(idNiveau);
        dbClient.executeCommand(false, insertQuery, insertParameters);
        Vector<Object> selectParameters = new Vector<Object>();
        String selectQuery = "SELECT LAST_INSERT_ID()";
        return dbClient.executeCommand(true, selectQuery, selectParameters);
    }

    public static void modifierCours(int idCours, int idTypeCours, String nom, int idEnseignant, int idNiveau)
            throws SQLException {
        String insertQuery = "UPDATE cours SET idTypeCours = ?, nom = ?, idEnseignant = ?, idNiveau = ? WHERE id = ?";
        Vector<Object> insertParameters = new Vector<Object>();
        insertParameters.add(idTypeCours);
        insertParameters.add(nom);
        insertParameters.add(idEnseignant);
        insertParameters.add(idNiveau);
        insertParameters.add(idCours);
        dbClient.executeCommand(false, insertQuery, insertParameters);
    }

    public static void supprimerCoursById(int idCours) {
        Vector<Object> parameters = new Vector<>();
        String query = "DELETE FROM cours WHERE id = ?";
        parameters.add(idCours);
        dbClient.executeCommand(false, query, parameters);
    }

}
