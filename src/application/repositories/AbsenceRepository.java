package application.repositories;

import java.sql.ResultSet;
import java.util.Vector;

import application.database.dbClient;

public class AbsenceRepository {

    public static ResultSet getListAbsence(int nbrSemaine, String jour, int idClasse) {
        Vector<Object> parameters = new Vector<>();
        String query = "SELECT " +
                "etudiant.id AS etudiant_id, " +
                "etudiant.cne AS etudiant_cne, " +
                "etudiant.nom AS etudiant_nom, " +
                "etudiant.prenom AS etudiant_prenom, " +
                "etudiant.email AS etudiant_email, " +
                "etudiant.photoUrl AS etudiant_photoURL, " +
                "seance_8_10.id AS seance_8_10_id, " +
                "CASE " +
                "    WHEN seance_8_10.id IS NOT NULL THEN " +
                "        CASE WHEN absence_8_10.idEtudiant IS NULL THEN 'present' ELSE 'absent' END " +
                "    ELSE 'none' " +
                "END AS seance_8_10_status, " +
                "COALESCE(absence_8_10.estExcuse, 0) AS seance_8_10_estExcuse, " +
                "seance_10_12.id AS seance_10_12_id, " +
                "CASE " +
                "    WHEN seance_10_12.id IS NOT NULL THEN " +
                "        CASE WHEN absence_10_12.idEtudiant IS NULL THEN 'present' ELSE 'absent' END " +
                "    ELSE 'none' " +
                "END AS seance_10_12_status, " +
                "COALESCE(absence_10_12.estExcuse, 0) AS seance_10_12_estExcuse, " +
                "seance_14_16.id AS seance_14_16_id, " +
                "CASE " +
                "    WHEN seance_14_16.id IS NOT NULL THEN " +
                "        CASE WHEN absence_14_16.idEtudiant IS NULL THEN 'present' ELSE 'absent' END " +
                "    ELSE 'none' " +
                "END AS seance_14_16_status, " +
                "COALESCE(absence_14_16.estExcuse, 0) AS seance_14_16_estExcuse, " +
                "seance_16_18.id AS seance_16_18_id, " +
                "CASE " +
                "    WHEN seance_16_18.id IS NOT NULL THEN " +
                "        CASE WHEN absence_16_18.idEtudiant IS NULL THEN 'present' ELSE 'absent' END " +
                "    ELSE 'none' " +
                "END AS seance_16_18_status, " +
                "COALESCE(absence_16_18.estExcuse, 0) AS seance_16_18_estExcuse " +
                "FROM " +
                "etudiant " +
                "LEFT JOIN " +
                "seance AS seance_8_10 ON etudiant.idClasse = seance_8_10.idClasse AND seance_8_10.jour = ? AND seance_8_10.heureDebut BETWEEN '08:00:00' AND '09:59:00' "
                +
                "LEFT JOIN " +
                "seance AS seance_10_12 ON etudiant.idClasse = seance_10_12.idClasse AND seance_10_12.jour = ? AND seance_10_12.heureDebut BETWEEN '10:00:00' AND '11:59:00' "
                +
                "LEFT JOIN " +
                "seance AS seance_14_16 ON etudiant.idClasse = seance_14_16.idClasse AND seance_14_16.jour = ? AND seance_14_16.heureDebut BETWEEN '14:00:00' AND '15:59:00' "
                +
                "LEFT JOIN " +
                "seance AS seance_16_18 ON etudiant.idClasse = seance_16_18.idClasse AND seance_16_18.jour = ? AND seance_16_18.heureDebut BETWEEN '16:00:00' AND '17:59:00' "
                +
                "LEFT JOIN " +
                "absence AS absence_8_10 ON etudiant.id = absence_8_10.idEtudiant AND seance_8_10.id = absence_8_10.idSeance AND absence_8_10.numSemaine = ? "
                +
                "LEFT JOIN " +
                "absence AS absence_10_12 ON etudiant.id = absence_10_12.idEtudiant AND seance_10_12.id = absence_10_12.idSeance AND absence_10_12.numSemaine = ? "
                +
                "LEFT JOIN " +
                "absence AS absence_14_16 ON etudiant.id = absence_14_16.idEtudiant AND seance_14_16.id = absence_14_16.idSeance AND absence_14_16.numSemaine = ? "
                +
                "LEFT JOIN " +
                "absence AS absence_16_18 ON etudiant.id = absence_16_18.idEtudiant AND seance_16_18.id = absence_16_18.idSeance AND absence_16_18.numSemaine = ? "
                +
                "WHERE " +
                "etudiant.idClasse = ?;";

        for (int i = 0; i < 4; i++) {
            parameters.add(jour);
        }
        for (int i = 0; i < 4; i++) {
            parameters.add(nbrSemaine);
        }
        parameters.add(idClasse);

        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getAbsencePourEtudiant(int idEtudiant) {
        Vector<Object> parameters = new Vector<>();
        String query = "SELECT absence.*, seance.jour FROM absence, seance WHERE absence.idSeance = seance.id AND idEtudiant = ?";
        parameters.add(idEtudiant);
        return dbClient.executeCommand(true, query, parameters);
    }

    public static void ajouterAbsencePourEtudiant(int numSemaine, int idSeance, int idEtudiant) {
        Vector<Object> parameters = new Vector<>();
        String query = "INSERT INTO absence"
                + " (id, idSeance, idEtudiant, motif, estExcuse, numSemaine)"
                + " VALUES (NULL, ?, ?, 'none', 0, ?);";
        parameters.add(idSeance);
        parameters.add(idEtudiant);
        parameters.add(numSemaine);
        dbClient.executeCommand(false, query, parameters);
    }

    public static void supprimerAbsencePourEtudiant(int numSemaine, int idSeance, int idEtudiant) {
        Vector<Object> parameters = new Vector<>();
        String query = "DELETE FROM absence"
                + " WHERE idEtudiant = ? AND idSeance = ? AND numSemaine = ?";
        parameters.add(idEtudiant);
        parameters.add(idSeance);
        parameters.add(numSemaine);
        dbClient.executeCommand(false, query, parameters);
    }

    public static void excuserAbsencePourEtudiant(int numSemaine, int idSeance, int idEtudiant, String motif) {
        Vector<Object> parameters = new Vector<>();
        String query = "UPDATE absence"
                + " SET estExcuse = 1, motif = ?"
                + " WHERE idEtudiant = ? AND idSeance = ? AND numSemaine = ?";
        parameters.add(motif);
        parameters.add(idEtudiant);
        parameters.add(idSeance);
        parameters.add(numSemaine);
        dbClient.executeCommand(false, query, parameters);
    }

    public static void deexcuserAbsencePourEtudiant(int numSemaine, int idSeance, int idEtudiant) {
        Vector<Object> parameters = new Vector<>();
        String query = "UPDATE absence"
                + " SET estExcuse = 0, motif = 'none'"
                + " WHERE idEtudiant = ? AND idSeance = ? AND numSemaine = ?";
        parameters.add(idEtudiant);
        parameters.add(idSeance);
        parameters.add(numSemaine);
        dbClient.executeCommand(false, query, parameters);
    }
}
