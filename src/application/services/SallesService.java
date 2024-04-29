package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.model.CategorieSalle;
import application.model.Horaires;
import application.model.Salle;
import application.model.enums.JoursSemaine;
import application.repositories.SallesRepository;

public class SallesService {

    public static Vector<Salle> getAllSalles() {

        Vector<Salle> resSalle = new Vector<Salle>();
        ResultSet result;
        try {
            result = SallesRepository.getAllSalles();
            while (result.next()) {
                Salle statSalle = new Salle();
                statSalle.setId(result.getInt("id"));
                statSalle.setCapacite(result.getInt("capacite"));
                statSalle.setNomSalle(result.getString("nom"));
                statSalle.setCategorieSalle(
                        new CategorieSalle(result.getInt("idCategorieSalle"), result.getString("nomCategorieSalle")));
                resSalle.add(statSalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSalle;
    }

    public static Vector<Salle> getAvailableSallesByHoraire(JoursSemaine jour, Horaires horaire) {

        Vector<Salle> resSalle = new Vector<Salle>();
        ResultSet result;
        try {
            result = SallesRepository.getAvailableSallesByHoraire(jour, horaire);
            while (result.next()) {
                Salle statSalle = new Salle();
                statSalle.setId(result.getInt("id"));
                statSalle.setCapacite(result.getInt("capacite"));
                statSalle.setNomSalle(result.getString("nom"));
                statSalle.setCategorieSalle(
                        new CategorieSalle(result.getInt("idCategorieSalle"), result.getString("nomCategorieSalle")));
                resSalle.add(statSalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSalle;
    }
}
