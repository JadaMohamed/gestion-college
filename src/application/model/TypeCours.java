package application.model;

public class TypeCours {

    private int id;
    private String nom;

    public TypeCours() {

    }

    public TypeCours(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String toString() {
        return nom;
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
}
