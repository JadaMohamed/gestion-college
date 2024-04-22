package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.model.Niveau;
import application.repositories.NiveauRepository;

public class NiveauService {

    public static Vector<Niveau> getAllNiveaus() {

        Vector<Niveau> resNiveau = new Vector<Niveau>();
        ResultSet result = NiveauRepository.getAllNiveaus();

        try {
            while (result.next()) {
                Niveau statNiveau = new Niveau();
                statNiveau.setIdNiveau(result.getInt("idNiveau"));
                statNiveau.setNomNiveau(result.getString("nomNiveau"));
                resNiveau.add(statNiveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resNiveau;
    }
}
