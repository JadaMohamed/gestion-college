package application.repositories;

import java.sql.ResultSet;
import java.util.Vector;
import application.database.dbClient;
import application.model.Etudiant;

public class EtudiantRepository {

    public static void deleteEtudiantById(int classeId) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "DELETE FROM etudiant WHERE id = ?";
        parameters.add(classeId);
        dbClient.executeCommand(false, query, parameters);
    }

    public static void ajouterEtudiant(Etudiant e) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "INSERT INTO `etudiant` "
                + "(`id`,"
                + " `cne`,"
                + " `nom`, "
                + "`prenom`,"
                + " `sexe`,"
                + " `email`,"
                + " `telephone`,"
                + " `telephoneParent`,"
                + " `emailParent`,"
                + " `dateNaissance`,"
                + " `idClasse`,"
                + " `photoUrl`)"
                + "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '/resources/images/profiles/default.png');";

        parameters.add(e.getCne());
        parameters.add(e.getNom());
        parameters.add(e.getPrenom());
        parameters.add(e.getSexe());
        parameters.add(e.getEmail());
        parameters.add(e.getTelephone());
        parameters.add(e.getTelephoneParent());
        parameters.add(e.getEmailParent());
        parameters.add(e.getDateNaissance().toString());
        parameters.add(e.getClasse().getId());

        dbClient.executeCommand(false, query, parameters);
    }

    public static void updateEtudiant(Etudiant e) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "UPDATE etudiant SET "
                + "cne = ?, "
                + "nom = ?, "
                + "prenom = ?, "
                + "sexe = ?, "
                + "email = ?, "
                + "telephone = ?, "
                + "telephoneParent = ?, "
                + "emailParent = ?, "
                + "dateNaissance = ?, "
                + "idClasse = ? "
                + "WHERE id = ?";

        parameters.add(e.getCne());
        parameters.add(e.getNom());
        parameters.add(e.getPrenom());
        parameters.add(e.getSexe());
        parameters.add(e.getEmail());
        parameters.add(e.getTelephone());
        parameters.add(e.getTelephoneParent());
        parameters.add(e.getEmailParent());
        parameters.add(e.getDateNaissance()); // Format date appropriately
        parameters.add(e.getClasse().getId());
        parameters.add(e.getId());

        System.out.println(e.getId());

        // Assuming dbClient is an instance of some database client class capable of
        // executing SQL commands
        dbClient.executeCommand(false, query, parameters);
    }

    public static ResultSet getEtudiantById(int idEtudiant) {
        Vector<Object> parameters = new Vector<Object>();
        String query = "SELECT * FROM etudiant WHERE id = ?";
        parameters.add(idEtudiant);
        return dbClient.executeCommand(true, query, parameters);
    }
}
