package application.model;

import java.sql.Time;
import java.util.Vector;

import application.model.enums.JoursSemaine;

public class Seance {

    private int id;
    private Salle salle;
    private JoursSemaine jour;
    private Time heureDebut;
    private Time heureFin;
    private Cours cours;
    private Classe classe;
    private Vector<Absence> listAbsence;

    public Seance() {

    }

    public Seance(int id, JoursSemaine jour, Time heureDebut, Time heureFin) {
        this.id = id;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public Seance(int id, Salle salle, JoursSemaine jour, Time heureDebut, Time heureFin, Cours cours, Classe classe) {
        this.id = id;
        this.salle = salle;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.cours = cours;
        this.classe = classe;
        this.listAbsence = new Vector<Absence>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public JoursSemaine getJour() {
        return jour;
    }

    public void setJour(JoursSemaine jour) {
        this.jour = jour;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Vector<Absence> getListAbsences() {
        return listAbsence;
    }

    public void setListAbsences(Vector<Absence> listAbsences) {
        this.listAbsence = listAbsences;
    }

    public int getIdSalle() {
        return salle.getId();
    }
}
