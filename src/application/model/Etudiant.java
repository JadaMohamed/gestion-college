package application.model;

import java.sql.Date;
import java.util.Vector;

public class Etudiant extends Personne {

    private int id;
    private String cne;
    private Classe classe;
    private String emailParent;
    private String telephoneParent;
    private Vector<Absence> listAbsence;

    public Etudiant() {

    }

    public Etudiant(int id, String cne, Classe classe, String emailParent, String telephoneParent,
            Vector<Absence> listAbsence, String nom, String prenom, String email, String telephone,
            Date dateNaissance, String sexe) {
        super(nom, prenom, email, telephone, dateNaissance, sexe);
        this.id = id;
        this.cne = cne;
        this.classe = classe;
        this.emailParent = emailParent;
        this.telephoneParent = telephoneParent;
        this.listAbsence = new Vector<Absence>();
    }

    public void addAbsence(Absence absence) {
        this.listAbsence.add(absence);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String getEmailParent() {
        return emailParent;
    }

    public void setEmailParent(String emailParent) {
        this.emailParent = emailParent;
    }

    public String getTelephoneParent() {
        return telephoneParent;
    }

    public void setTelephoneParent(String telephoneParent) {
        this.telephoneParent = telephoneParent;
    }

    public Vector<Absence> getListAbsence() {
        return listAbsence;
    }

    public void setListAbsence(Vector<Absence> listAbsence) {
        this.listAbsence = listAbsence;
    }
}
