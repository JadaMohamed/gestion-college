package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import application.database.dbClient;

public class ClasseRepository {

    public static ResultSet getAllClasses() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT CL.*, NI.nom FROM classe CL, niveau NI WHERE CL.idNiveauClasse = NI.id";
        return dbClient.executeCommand(true, query, parameters);
    }

    public static ResultSet getAllClassesWithCurrentSeances() throws SQLException {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT classe.id, classe.numero AS numeroClasse, niveau.nom AS nomNiveau, classe.effectif, CASE WHEN seance.id IS NOT NULL AND DAYOFWEEK(CURDATE()) = CASE WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin THEN 1 ELSE 0 END AS status, CASE WHEN seance.id IS NOT NULL AND DAYOFWEEK(CURDATE()) = CASE WHEN seance.jour = 'LUNDI' THEN 2 WHEN seance.jour = 'MARDI' THEN 3 WHEN seance.jour = 'MERCREDI' THEN 4 WHEN seance.jour = 'JEUDI' THEN 5 WHEN seance.jour = 'VENDREDI' THEN 6 WHEN seance.jour = 'SAMEDI' THEN 7 WHEN seance.jour = 'DIMANCHE' THEN 1 END AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin THEN salle.nom ELSE '-' END AS nomSalle, COALESCE(enseignant.nom, '-') AS nomEnseignant, COALESCE(enseignant.prenom, '-') AS prenomEnseignant, COALESCE(enseignant.email, '-') AS emailEnseignant, COALESCE(enseignant.photoUrl, '-') AS photoUrlEnseignant, COALESCE(cours.nom, '-') AS nomCours FROM classe LEFT JOIN niveau ON niveau.id = classe.idNiveauClasse LEFT JOIN seance ON classe.id = seance.idClasse LEFT JOIN salle ON seance.idSalle = salle.id LEFT JOIN cours ON cours.id = seance.idCours LEFT JOIN enseignant ON enseignant.id = cours.idEnseignant;";
        return dbClient.executeCommand(true, query, parameters);
    }
}
