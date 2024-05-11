package application.model;

import java.sql.Date;
import java.util.Vector;

public class Enseignant extends Personne {

    private int id;
    private Vector<Cours> listCours;

    public Enseignant() {
    }

    public Enseignant(int id, Vector<Cours> listCours, String nom, String prenom, String email, String telephone,
            Date dateNaissance, String sexe) {
        super(nom, prenom, email, telephone, dateNaissance, sexe);
        this.id = id;
        this.listCours = new Vector<Cours>();
    }

    public String toString() {
        return super.getNom() + " " + super.getPrenom();
    }

    public void addCours(Cours cours) {
        this.listCours.add(cours);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector<Cours> getListCours() {
        return listCours;
    }

    public void setListCours(Vector<Cours> listCours) {
        this.listCours = listCours;
    }
}