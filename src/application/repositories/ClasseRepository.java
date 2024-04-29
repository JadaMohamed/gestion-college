package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import application.database.dbClient;

public class ClasseRepository {

    public static ResultSet getAllClasses() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT CL.*, NI.nom FROM classe CL, niveau NI WHERE CL.idNiveauClasse = NI.id";
        return dbClient.executeCommand(true, query, parameters);
    }
}
