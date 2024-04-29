package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.model.Classe;
import application.repositories.ClasseRepository;

public class ClasseService {

    public static Vector<Classe> getAllClasses() {

        Vector<Classe> resClasses = new Vector<Classe>();
        ResultSet result;
        try {
            result = ClasseRepository.getAllClasses();
            while (result.next()) {
                Classe statClasse = new Classe();
                statClasse.setId(result.getInt("id"));
                statClasse.setNom(result.getString("nom"));
                statClasse.setEffectif(result.getInt("effectif"));
                statClasse.setNumero(result.getInt("numero"));
                resClasses.add(statClasse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resClasses;
    }
}
