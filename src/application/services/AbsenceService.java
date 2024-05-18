package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import application.repositories.AbsenceRepository;

public class AbsenceService {

    public static Vector<Map<String, String>> getAbsencePourEtudiant(int idEtudiant) {
        Vector<Map<String, String>> rows = new Vector<Map<String, String>>();

        ResultSet result;
        try {
            result = AbsenceRepository.getAbsencePourEtudiant(idEtudiant);
            while (result.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("idAbsence", result.getString("id"));
                row.put("idSeance", result.getString("idSeance"));
                row.put("motif", result.getString("motif"));
                row.put("estExcuse", result.getString("estExcuse"));
                row.put("numSemaine", result.getString("numSemaine"));
                row.put("idEtudiant", result.getString("idEtudiant"));
                row.put("jour", result.getString("jour"));

                rows.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static Vector<Map<String, String>> getListAbsence(int nbrSemaine, String jour, int idClasse) {
        Vector<Map<String, String>> rows = new Vector<Map<String, String>>();

        ResultSet result;
        try {
            result = AbsenceRepository.getListAbsence(nbrSemaine, jour, idClasse);
            while (result.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("etudiant_id", result.getString("etudiant_id"));
                row.put("etudiant_cne", result.getString("etudiant_cne"));
                row.put("etudiant_nom", result.getString("etudiant_nom"));
                row.put("etudiant_prenom", result.getString("etudiant_prenom"));
                row.put("etudiant_photoURL", result.getString("etudiant_photoURL"));
                row.put("seance_8_10_id", result.getString("seance_8_10_id"));
                row.put("seance_8_10_status", result.getString("seance_8_10_status"));
                row.put("seance_8_10_estExcuse", result.getString("seance_8_10_estExcuse"));
                row.put("seance_10_12_id", result.getString("seance_10_12_id"));
                row.put("seance_10_12_status", result.getString("seance_10_12_status"));
                row.put("seance_10_12_estExcuse", result.getString("seance_10_12_estExcuse"));
                row.put("seance_14_16_id", result.getString("seance_14_16_id"));
                row.put("seance_14_16_status", result.getString("seance_14_16_status"));
                row.put("seance_14_16_estExcuse", result.getString("seance_14_16_estExcuse"));
                row.put("seance_16_18_id", result.getString("seance_16_18_id"));
                row.put("seance_16_18_status", result.getString("seance_16_18_status"));
                row.put("seance_16_18_estExcuse", result.getString("seance_16_18_estExcuse"));

                rows.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }
}
