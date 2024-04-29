package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.model.CategorieSalle;
import application.model.Salle;
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
}
