package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import application.database.dbClient;
import application.model.Classe;
import application.model.Enseignant;
import application.model.enums.JoursSemaine;

public class SeanceRepository {

    public static ResultSet getOccupiedHoraires(JoursSemaine jour, Classe classe, Enseignant enseignant)
            throws SQLException {
        String query = "SELECT s.heureDebut, s.heureFin " +
                "FROM seance s " +
                "INNER JOIN cours c ON s.idCours = c.id " +
                "WHERE (s.jour = ? AND s.idClasse = ?) OR (c.idEnseignant = ? AND s.jour = ?)";
        Vector<Object> parameters = new Vector<Object>();
        parameters.add(jour.name());
        parameters.add(classe.getId());
        parameters.add(enseignant.getId());
        parameters.add(jour.name());
        return dbClient.executeCommand(true, query, parameters);
    }
}
