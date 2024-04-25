package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.model.NiveauClasse;
import application.repositories.NiveauRepository;

public class NiveauService {

    public static Vector<NiveauClasse> getAllNiveaus() {

        Vector<NiveauClasse> resNiveau = new Vector<NiveauClasse>();
        ResultSet result = NiveauRepository.getAllNiveaus();

        try {
            while (result.next()) {
                NiveauClasse statNiveau = new NiveauClasse();
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
