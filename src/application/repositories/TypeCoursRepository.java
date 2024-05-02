package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.database.dbClient;

public class TypeCoursRepository {

    public static ResultSet getAllTypes() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM typeCours";
        return dbClient.executeCommand(true, query, parameters);
    }
}
