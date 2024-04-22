package application.model;

import java.sql.Time;

import application.model.enums.JoursSemaine;

public class Seance {

    private int idSeance;
    private int idSalle;
    private JoursSemaine jourSeance;
    private Time heureDebutSeance;
    private Time heureFinSeance;
    private int idCours;
    private int idClasse;

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public JoursSemaine getJourSeance() {
        return jourSeance;
    }

    public void setJourSeance(JoursSemaine jourSeance) {
        this.jourSeance = jourSeance;
    }

    public Time getHeureDebutSeance() {
        return heureDebutSeance;
    }

    public void setHeureDebutSeance(Time heureDebutSeance) {
        this.heureDebutSeance = heureDebutSeance;
    }

    public Time getHeureFinSeance() {
        return heureFinSeance;
    }

    public void setHeureFinSeance(Time heureFinSeance) {
        this.heureFinSeance = heureFinSeance;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }
}
