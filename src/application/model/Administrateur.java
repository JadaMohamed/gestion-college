package application.model;

public class Administrateur {
    private int idAdministrateur;
    private String nomAdministrateur;
    private String emailAdministrateur;
    private String passwordAdministrateur;
    private String roleAdministrateur;

    public int getIdAdministrateur() {
        return idAdministrateur;
    }

    public void setIdAdministrateur(int idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    }

    public String getNomAdministrateur() {
        return nomAdministrateur;
    }

    public void setNomAdministrateur(String nomAdministrateur) {
        this.nomAdministrateur = nomAdministrateur;
    }

    public String getEmailAdministrateur() {
        return emailAdministrateur;
    }

    public void setEmailAdministrateur(String emailAdministrateur) {
        this.emailAdministrateur = emailAdministrateur;
    }

    public String getPasswordAdministrateur() {
        return passwordAdministrateur;
    }

    public void setPasswordAdministrateur(String passwordAdministrateur) {
        this.passwordAdministrateur = passwordAdministrateur;
    }

    public String getRoleAdministrateur() {
        return roleAdministrateur;
    }

    public void setRoleAdministrateur(String roleAdministrateur) {
        this.roleAdministrateur = roleAdministrateur;
    }
}