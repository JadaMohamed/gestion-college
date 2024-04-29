package application.model;

import java.util.Vector;

public class Classe {

    private int id;
    private int numero;
    private NiveauClasse niveau;
    private int effectif;
    private String nom;
    private Vector<Seance> listSeances;
    private Vector<Etudiant> listEtudiants;

    public Classe() {

    }

    public Classe(int id, int numero, NiveauClasse niveau, int effectif) {
        this.id = id;
        this.numero = numero;
        this.niveau = niveau;
        this.effectif = effectif;
        this.listSeances = new Vector<Seance>();
        this.listEtudiants = new Vector<Etudiant>();
    }

    public void addSeance(Seance seance) {
        listSeances.add(seance);
    }

    @Override
    public String toString() {
        return nom + " " + numero;
    }

    public void addEtudiant(Etudiant etudiant) {
        listEtudiants.add(etudiant);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public NiveauClasse getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauClasse niveau) {
        this.niveau = niveau;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public Vector<Seance> getListSeances() {
        return listSeances;
    }

    public void setListSeances(Vector<Seance> listSeances) {
        this.listSeances = listSeances;
    }

    public Vector<Etudiant> getListEtudiants() {
        return listEtudiants;
    }

    public void setListEtudiants(Vector<Etudiant> listEtudiants) {
        this.listEtudiants = listEtudiants;
    }
}
