package application.model;

public class Absence {

    private int idAbsence;
    private int idSeance;
    private int idEtudiant;
    private int numeroSemaine;
    private String motifAbsence;
    private Boolean estAbsenceExcuse;

    public int getIdAbsence() {
        return idAbsence;
    }

    public void setIdAbsence(int idAbsence) {
        this.idAbsence = idAbsence;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setNumeroSemaine(int numeroSemaine) {
        this.numeroSemaine = numeroSemaine;
    }

    public int getNumeroSemaine() {
        return numeroSemaine;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getMotifAbsence() {
        return motifAbsence;
    }

    public void setMotifAbsence(String motifAbsence) {
        this.motifAbsence = motifAbsence;
    }

    public Boolean getEstAbsenceExcuse() {
        return estAbsenceExcuse;
    }

    public void setEstAbsenceExcuse(Boolean estAbsenceExcuse) {
        this.estAbsenceExcuse = estAbsenceExcuse;
    }
}
