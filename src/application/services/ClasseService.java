package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import application.model.Classe;
import application.model.Etudiant;
import application.model.NiveauClasse;
import application.repositories.ClasseRepository;

public class ClasseService {

    public static List<String> getAllNiveauNames() {
        List<NiveauClasse> niveaux = getAllNiveaux();
        return niveaux.stream().map(NiveauClasse::getNom).collect(Collectors.toList());
    }

    public static List<NiveauClasse> getAllNiveaux() {
        List<NiveauClasse> resNiveaux = new ArrayList<>();
        ResultSet result;
        try {
            result = ClasseRepository.getAllNiveaux();
            while (result.next()) {
                NiveauClasse statNiveau = new NiveauClasse();
                statNiveau.setNom(result.getString("nom"));
                resNiveaux.add(statNiveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resNiveaux;
    }

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

    public static Vector<Map<String, String>> getAllClassesWithCurrentSeancesBIS() {
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

    public static Vector<Map<String, String>> getEmploiDeTemps(String idClasse) {
        Vector<Map<String, String>> res = new Vector<>();
        ResultSet result;
        try {
            result = ClasseRepository.getEmploiDeTemps(idClasse);
            while (result.next()) {
                Map<String, String> stat = new HashMap<>();
                stat.put("Day", result.getString("Day"));
                stat.put("coursnom8_10", result.getString("coursnom8_10"));
                stat.put("seanceId8_10", result.getString("seanceId8_10"));
                stat.put("enseignantFullName8_10",
                        result.getString("nomEnseignant8_10") + " " + result.getString("prenomEnseignant8_10"));
                stat.put("photoUrlEnseignant8_10", result.getString("photoUrlEnseignant8_10"));
                stat.put("heureDebut8_10", result.getString("heureDebut8_10"));
                stat.put("heureFin8_10", result.getString("heureFin8_10"));
                stat.put("nomSalle8_10", result.getString("nomSalle8_10"));
                stat.put("coursnom10_12", result.getString("coursnom10_12"));
                stat.put("seanceId10_12", result.getString("seanceId10_12"));
                stat.put("enseignantFullName10_12",
                        result.getString("nomEnseignant10_12") + " " + result.getString("prenomEnseignant10_12"));
                stat.put("photoUrlEnseignant10_12", result.getString("photoUrlEnseignant10_12"));
                stat.put("heureDebut10_12", result.getString("heureDebut10_12"));
                stat.put("heureFin10_12", result.getString("heureFin10_12"));
                stat.put("nomSalle10_12", result.getString("nomSalle10_12"));
                stat.put("coursnom14_16", result.getString("coursnom14_16"));
                stat.put("seanceId14_16", result.getString("seanceId14_16"));
                stat.put("enseignantFullName14_16",
                        result.getString("nomEnseignant14_16") + " " + result.getString("prenomEnseignant14_16"));
                stat.put("photoUrlEnseignant14_16", result.getString("photoUrlEnseignant14_16"));
                stat.put("heureDebut14_16", result.getString("heureDebut14_16"));
                stat.put("heureFin14_16", result.getString("heureFin14_16"));
                stat.put("nomSalle14_16", result.getString("nomSalle14_16"));
                stat.put("coursnom16_18", result.getString("coursnom16_18"));
                stat.put("seanceId16_18", result.getString("seanceId16_18"));
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

    public static Vector<Etudiant> getEtudiantsByClasseId(int classeId) {
        Vector<Etudiant> res = new Vector<>();
        ResultSet result;
        try {
            result = ClasseRepository.getEtudiantsByClasseId(classeId);
            while (result.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(result.getInt("id"));
                etudiant.setCne(result.getString("cne"));
                etudiant.setEmailParent(result.getString("emailParent"));
                etudiant.setTelephoneParent(result.getString("telephoneParent"));
                etudiant.setNom(result.getString("nom"));
                etudiant.setPrenom(result.getString("prenom"));
                etudiant.setEmail(result.getString("email"));
                etudiant.setTelephone(result.getString("telephone"));
                etudiant.setDateNaissance(result.getDate("dateNaissance"));
                etudiant.setPhotoURL(result.getString("photoUrl"));
                etudiant.setSexe(result.getString("sexe"));
                res.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Vector<Etudiant> getEtudiantsByClasseId_search(int classeId, String searchKey) {
        Vector<Etudiant> res = new Vector<>();
        ResultSet result;
        try {
            result = ClasseRepository.getEtudiantsByClasseId_search(classeId, searchKey);
            while (result.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(result.getInt("id"));
                etudiant.setCne(result.getString("cne"));
                etudiant.setClasse(new Classe());
                etudiant.getClasse().setId(classeId);
                etudiant.setEmailParent(result.getString("emailParent"));
                etudiant.setTelephoneParent(result.getString("telephoneParent"));
                etudiant.setNom(result.getString("nom"));
                etudiant.setPrenom(result.getString("prenom"));
                etudiant.setEmail(result.getString("email"));
                etudiant.setTelephone(result.getString("telephone"));
                etudiant.setDateNaissance(result.getDate("dateNaissance"));
                etudiant.setPhotoURL(result.getString("photoUrl"));
                etudiant.setSexe(result.getString("sexe"));
                res.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Vector<Map<String, String>> getClasses_search(String searchKey) {
    Vector<Map<String, String>> res = new Vector<>();
    ResultSet result;
    try {
        result = ClasseRepository.getClasses_search(searchKey);
        while (result.next()) {
            Map<String, String> classe = new HashMap<>();
            classe.put("classeId", String.valueOf(result.getInt(1)));
            classe.put("classeNom", result.getString(2));
            classe.put("effectif", String.valueOf(result.getInt(3)));
            classe.put("status", result.getString(4));
            classe.put("salleNom", result.getString(5));
            classe.put("coursNom", result.getString(6));
            classe.put("enseignantFullName", result.getString(7)+' '+result.getString(8));
            classe.put("enseignantEmail", result.getString(9));
            classe.put("enseignantPhotoUrl", result.getString(10));
            res.add(classe);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return res;
}
}
