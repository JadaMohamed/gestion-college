package application.model;

import java.util.Vector;

public class Cours {

    private int id;
    private TypeCours typeCours;
    private String nomCours;
    private Enseignant enseignant;
    private NiveauClasse niveauClasse;
    private Vector<Seance> listSeances;

    public Cours() {

    }

    public Cours(int id, TypeCours typeCours, String nomCours, Enseignant enseignant, NiveauClasse niveauClasse) {
        this.id = id;
        this.typeCours = typeCours;
        this.nomCours = nomCours;
        this.enseignant = enseignant;
        this.niveauClasse = niveauClasse;
        this.listSeances = new Vector<Seance>();
    }

    public void addSeance(Seance seance) {
        this.listSeances.add(seance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeCours getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(TypeCours typeCours) {
        this.typeCours = typeCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public NiveauClasse getNiveauClasse() {
        return niveauClasse;
    }

    public void setNiveauClasse(NiveauClasse niveauClasse) {
        this.niveauClasse = niveauClasse;
    }

    public Vector<Seance> getListSeances() {
        return listSeances;
    }

    public void setListSeances(Vector<Seance> listSeances) {
        this.listSeances = listSeances;
    }
}
