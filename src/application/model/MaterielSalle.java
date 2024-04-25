package application.model;

public class MaterielSalle {

    private int id;
    private String nom;
    private Salle salle;
    private int quantite;

    public MaterielSalle() {
    }

    public MaterielSalle(int id, String nom, Salle salle, int quantite) {
        this.id = id;
        this.nom = nom;
        this.salle = salle;
        this.quantite = quantite;
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

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}
