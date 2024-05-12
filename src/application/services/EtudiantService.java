package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Classe;
import application.model.Etudiant;
import application.repositories.EtudiantRepository;

public class EtudiantService {
    public static Etudiant getEtudiantById(int idEtudiant) {
        Etudiant res = new Etudiant();
        ResultSet result;
        try {
            result = EtudiantRepository.getEtudiantById(idEtudiant);
            while (result.next()) {
                res.setId(result.getInt("id"));
                res.setCne(result.getString("cne"));
                res.setClasse(new Classe());
                res.getClasse().setId(result.getInt("idClasse"));
                res.setEmailParent(result.getString("emailParent"));
                res.setTelephoneParent(result.getString("telephoneParent"));
                res.setNom(result.getString("nom"));
                res.setPrenom(result.getString("prenom"));
                res.setEmail(result.getString("email"));
                res.setTelephone(result.getString("telephone"));
                res.setDateNaissance(result.getDate("dateNaissance"));
                res.setPhotoURL(result.getString("photoUrl"));
                res.setSexe(result.getString("sexe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
