package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.model.Enseignant;
import application.repositories.EnseignantRepository;

public class EnseignantService {

    public static Vector<Enseignant> getAllEnseignants() {

        Vector<Enseignant> resEnseignants = new Vector<Enseignant>();
        ResultSet result;
        try {
            result = EnseignantRepository.getAllEnseignants();
            while (result.next()) {
                Enseignant statEnseignant = new Enseignant();
                statEnseignant.setId(result.getInt("id"));
                statEnseignant.setNom(result.getString("nom"));
                statEnseignant.setPrenom(result.getString("prenom"));
                statEnseignant.setEmail(result.getString("email"));
                statEnseignant.setTelephone(result.getString("telephone"));
                statEnseignant.setPhotoURL(result.getString("photoUrl"));
                statEnseignant.setDateNaissance(result.getDate("dateNaissance"));
                resEnseignants.add(statEnseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resEnseignants;
    }
}
