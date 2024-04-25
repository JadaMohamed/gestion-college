package application.model;

public class Absence {

    private int id;
    private Etudiant etudiant;
    private String motif;
    private Seance seance;
    private int numSemaine;
    private boolean estExcuse;

    public Absence() {
    }

    public Absence(int id, Etudiant etudiant, String motif, Seance seance, int numSemaine, boolean estExcuse) {
        this.id = id;
        this.etudiant = etudiant;
        this.motif = motif;
        this.seance = seance;
        this.numSemaine = numSemaine;
        this.estExcuse = estExcuse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public int getNumSemaine() {
        return numSemaine;
    }

    public void setNumSemaine(int numSemaine) {
        this.numSemaine = numSemaine;
    }

    public boolean isEstExcuse() {
        return estExcuse;
    }

    public void setEstExcuse(boolean estExcuse) {
        this.estExcuse = estExcuse;
    }
}