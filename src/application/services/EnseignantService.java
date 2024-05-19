package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import application.model.Enseignant;
import application.repositories.EnseignantRepository;

public class EnseignantService {
    public static Enseignant getEnseignantById(int idEnseignant) {

        Enseignant resEnseignant = new Enseignant();
        ResultSet result;
        try {
            result = EnseignantRepository.getEnseignantById(idEnseignant);
            while (result.next()) {
                resEnseignant.setId(result.getInt("id"));
                resEnseignant.setNom(result.getString("nom"));
                resEnseignant.setPrenom(result.getString("prenom"));
                resEnseignant.setSexe(result.getString("sexe"));
                resEnseignant.setEmail(result.getString("email"));
                resEnseignant.setTelephone(result.getString("telephone"));
                resEnseignant.setPhotoURL(result.getString("photoUrl"));
                resEnseignant.setDateNaissance(result.getDate("dateNaissance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resEnseignant;
    }

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

    public static Vector<Map<String, String>> getAllEnseignantsWithEngoingSeances() {
        Vector<Map<String, String>> ensignantsAvecSeances = new Vector<Map<String, String>>();
        ResultSet result;
        try {
            result = EnseignantRepository.getAllEnseignantsWithEngoingSeances();
            while (result.next()) {
                Map<String, String> stat = new HashMap<>();
                stat.put("enseignantId", result.getString("enseignant_id"));
                stat.put("enseignantFullName",
                        result.getString("enseignant_nom") + " " + result.getString("enseignant_prenom"));
                stat.put("enseignantEmail", result.getString("enseignant_email"));
                stat.put("enseignantTelephone", result.getString("enseignant_telephone"));
                stat.put("enseignantPhotoUrl", result.getString("enseignant_photoUrl"));
                stat.put("enseignantSexe", result.getString("enseignant_sexe"));
                stat.put("enseignantTotalSeances", result.getString("total_seances"));
                stat.put("enseignantDateNaissance", result.getString("enseignant_naissance"));
                stat.put("coursNom", result.getString("statut").equals("1") ? result.getString("cours_nom") : "");
                stat.put("classeNom",
                        result.getString("statut").equals("1") ? result.getString("classe_nom") : "");
                stat.put("salleNom",
                        result.getString("statut").equals("1") ? result.getString("salle_nom") : "");
                stat.put("horaire",
                        result.getString("statut").equals("1") ? result.getString("horaire") : "");
                stat.put("statut", result.getString("statut"));
                ensignantsAvecSeances.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ensignantsAvecSeances;
    }

    public static Vector<Map<String, String>> getEmploiDeTemps(String idEnseignant) {
        Vector<Map<String, String>> res = new Vector<>();
        ResultSet result;
        try {
            result = EnseignantRepository.getEmploiDeTemps(idEnseignant);
            while (result.next()) {
                Map<String, String> stat = new HashMap<>();
                stat.put("Day", result.getString("Day"));
                stat.put("coursnom8_10", result.getString("coursnom8_10"));
                stat.put("enseignantFullName8_10",
                        result.getString("nomEnseignant8_10") + " " + result.getString("prenomEnseignant8_10"));
                stat.put("photoUrlEnseignant8_10", result.getString("photoUrlEnseignant8_10"));
                stat.put("heureDebut8_10", result.getString("heureDebut8_10"));
                stat.put("heureFin8_10", result.getString("heureFin8_10"));
                stat.put("nomSalle8_10", result.getString("nomSalle8_10"));
                stat.put("coursnom10_12", result.getString("coursnom10_12"));
                stat.put("seanceId8_10", result.getString("seanceId8_10"));
                stat.put("seanceId10_12", result.getString("seanceId10_12"));
                stat.put("seanceId14_16", result.getString("seanceId14_16"));
                stat.put("seanceId16_18", result.getString("seanceId16_18"));
                stat.put("enseignantFullName10_12",
                        result.getString("nomEnseignant10_12") + " " + result.getString("prenomEnseignant10_12"));
                stat.put("photoUrlEnseignant10_12", result.getString("photoUrlEnseignant10_12"));
                stat.put("heureDebut10_12", result.getString("heureDebut10_12"));
                stat.put("heureFin10_12", result.getString("heureFin10_12"));
                stat.put("nomSalle10_12", result.getString("nomSalle10_12"));
                stat.put("coursnom14_16", result.getString("coursnom14_16"));
                stat.put("enseignantFullName14_16",
                        result.getString("nomEnseignant14_16") + " " + result.getString("prenomEnseignant14_16"));
                stat.put("photoUrlEnseignant14_16", result.getString("photoUrlEnseignant14_16"));
                stat.put("heureDebut14_16", result.getString("heureDebut14_16"));
                stat.put("heureFin14_16", result.getString("heureFin14_16"));
                stat.put("nomSalle14_16", result.getString("nomSalle14_16"));
                stat.put("coursnom16_18", result.getString("coursnom16_18"));
                stat.put("enseignantFullName16_18",
                        result.getString("nomEnseignant16_18") + " " + result.getString("prenomEnseignant16_18"));
                stat.put("photoUrlEnseignant16_18", result.getString("photoUrlEnseignant16_18"));
                stat.put("heureDebut16_18", result.getString("heureDebut16_18"));
                stat.put("heureFin16_18", result.getString("heureFin16_18"));
                stat.put("nomSalle16_18", result.getString("nomSalle16_18"));
                res.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
