package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.database.dbClient;

public class EnseignantRepository {

    public static ResultSet getAllEnseignants() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM enseignant";
        return dbClient.executeCommand(true, query, parameters);
    }
}
