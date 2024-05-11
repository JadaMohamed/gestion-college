package application.model;

import java.sql.Date;

public class Administrateur extends Personne {

    private int id;
    private String role;
    private String motDePass;

    public Administrateur() {
    }

    public Administrateur(int id, String nom, String prenom, String email, String telephone, Date dateNaissance,
            String role, String motDePass, String sexe) {
        super(nom, prenom, email, telephone, dateNaissance, sexe);
        this.id = id;
        this.role = role;
        this.motDePass = motDePass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }
}