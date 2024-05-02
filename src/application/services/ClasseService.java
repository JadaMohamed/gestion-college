package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import application.model.Classe;
import application.model.NiveauClasse;
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
                statClasse.setNiveau(new NiveauClasse(result.getInt("idNiveauClasse"), result.getString("nom")));
                resClasses.add(statClasse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resClasses;
    }

    public static Vector<Map<String, String>> getAllClassesWithCurrentSeances() {
        Vector<Map<String, String>> seances = new Vector<Map<String, String>>();
        ResultSet result;
        try {
            result = ClasseRepository.getAllClassesWithCurrentSeances();
            while (result.next()) {
                Map<String, String> seance = new HashMap<>();
                seance.put("enseignantFullName",
                        result.getString("nomEnseignant") + " " + result.getString("prenomEnseignant"));
                seance.put("enseignantEmail", result.getString("emailEnseignant"));
                seance.put("enseignantPhotoUrl", result.getString("photoUrlEnseignant"));
                seance.put("coursNom", seance.get("enseignantFullName").contains("-") ? "-"
                        : result.getString("nomCours"));
                seance.put("classeNom", result.getString("nomNiveau") + " " + result.getString("numeroClasse"));
                seance.put("salleNom", result.getString("nomSalle"));
                seance.put("effectif", result.getString("effectif"));
                seance.put("classeId", result.getString("id"));
                seance.put("status", result.getString("status"));
                seances.add(seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seances;
    }

}
