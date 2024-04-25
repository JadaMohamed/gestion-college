package application.repositories;

import java.sql.ResultSet;
import java.util.Vector;

import application.database.dbClient;

public class NiveauRepository {

    public static ResultSet getAllNiveaus() {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM niveau";
        return dbClient.executeCommand(true, query, parameters);
    }
}
