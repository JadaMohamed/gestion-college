package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import application.database.SqlConnection;
import application.database.dbClient;
import application.model.Classe;
import application.model.Enseignant;
import application.model.Seance;
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

    public static void insertIntoSeance(int idSalle, String jour, String heureDebut, String heureFin, int idCours,
            int idClasse) throws SQLException {
        String query = "INSERT INTO seance (id, idSalle, jour, heureDebut, heureFin, idCours, idClasse) VALUES (null, ?, ?, ?, ?, ?, ?)";
        Vector<Object> parameters = new Vector<Object>();
        parameters.add(idSalle);
        parameters.add(jour);
        parameters.add(heureDebut);
        parameters.add(heureFin);
        parameters.add(idCours);
        parameters.add(idClasse);
        dbClient.executeCommand(false, query, parameters);
    }

    public List<Seance> getSeancesActuelles() {
        java.sql.Connection connectDB = SqlConnection.getConnection();
        List<Seance> seances = new ArrayList<>();
        
        // Obtenez la date actuelle en LocalDate
        LocalDate currentDate = LocalDate.now();
        
        // Obtenez l'heure actuelle en LocalTime
        LocalTime currentTime = LocalTime.now();
        
        // Convertissez LocalDate en java.sql.Date
        java.sql.Date sqlCurrentDate = java.sql.Date.valueOf(currentDate);
        
        // Convertissez LocalTime en java.sql.Time
        Time sqlCurrentTime = Time.valueOf(currentTime);
        
        String query = "SELECT * FROM seance WHERE jour = ? AND heureDebut <= ? AND heureFin >= ?";
        try (java.sql.PreparedStatement statement = connectDB.prepareStatement(query)) {
            // Remplacez les paramètres de la requête par les valeurs actuelles
            statement.setDate(1, sqlCurrentDate);
            statement.setTime(2, sqlCurrentTime);
            statement.setTime(3, sqlCurrentTime);
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Seance seance = new Seance(
                    resultSet.getInt("id"),
                    JoursSemaine.valueOf(resultSet.getString("jour")),
                    resultSet.getTime("heureDebut"),
                    resultSet.getTime("heureFin")
                );
                seances.add(seance);
            }
            // Ajouter une impression de débogage pour afficher les séances récupérées
        System.out.println("Séances actuelles récupérées : " + seances.size());
        for (Seance seance : seances) {
            System.out.println(seance.toString());
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
    }
    

}
