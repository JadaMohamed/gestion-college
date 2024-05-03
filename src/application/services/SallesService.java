package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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

    public static Vector<Map<String, String>> getAllSallesWithCurrentSeances() {
        Vector<Map<String, String>> salles = new Vector<>();
        ResultSet result;
        try {
            result = SallesRepository.getAllSallesWithCurrentSeances();
            while (result.next()) {
                Map<String, String> salle = new HashMap<>();
                salle.put("nom", result.getString("nom"));
                salle.put("capacite", result.getString("capacite"));
                salle.put("statut", result.getString("statut"));
                salle.put("cours", result.getString("nomCours"));
                salle.put("classe", result.getString("numeroClasse"));
                salles.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salles;
    }
}
