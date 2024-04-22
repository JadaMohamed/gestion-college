package application.model;

public class Cours {

    private int idCours;
    private int idTypeCours;
    private String nomCours;
    private int idEnseignant;
    private int idNiveau;

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getIdTypeCours() {
        return idTypeCours;
    }

    public void setIdTypeCours(int idTypeCours) {
        this.idTypeCours = idTypeCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

}
