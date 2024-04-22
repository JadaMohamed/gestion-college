package application.model;

import java.sql.Date;

public class Etudiant {

    private int idEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    private String telephoneParentEtudiant;
    private String emailParentEtudiant;
    private Date dateNaissanceEtudiant;
    private int idClasse;

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }

    public String getTelephoneParentEtudiant() {
        return telephoneParentEtudiant;
    }

    public void setTelephoneParentEtudiant(String telephoneParentEtudiant) {
        this.telephoneParentEtudiant = telephoneParentEtudiant;
    }

    public String getEmailParentEtudiant() {
        return emailParentEtudiant;
    }

    public void setEmailParentEtudiant(String emailParentEtudiant) {
        this.emailParentEtudiant = emailParentEtudiant;
    }

    public Date getDateNaissanceEtudiant() {
        return dateNaissanceEtudiant;
    }

    public void setDateNaissanceEtudiant(Date dateNaissanceEtudiant) {
        this.dateNaissanceEtudiant = dateNaissanceEtudiant;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

}
