package application.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import application.database.dbClient;

public class NiveauRepository {

    public static ResultSet getAllNiveaus() {
        List<Object> parameters = new ArrayList<>();
        String query = "SELECT * FROM Niveau";
        return dbClient.executeCommand(true, query, parameters);
    }
}
