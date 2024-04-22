package application.model;

public class Enseignant {

    private int idEnseignant;
    private String nomEnseignant;
    private String prenomEnseignant;
    private String emailEnseignant;
    private String telephoneEnseignant;

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public String getPrenomEnseignant() {
        return prenomEnseignant;
    }

    public void setPrenomEnseignant(String prenomEnseignant) {
        this.prenomEnseignant = prenomEnseignant;
    }

    public String getEmailEnseignant() {
        return emailEnseignant;
    }

    public void setEmailEnseignant(String emailEnseignant) {
        this.emailEnseignant = emailEnseignant;
    }

    public String getTelephoneEnseignant() {
        return telephoneEnseignant;
    }

    public void setTelephoneEnseignant(String telephoneEnseignant) {
        this.telephoneEnseignant = telephoneEnseignant;
    }

}
