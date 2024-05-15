package application.model;

import java.util.Vector;

public class Salle {

    private int id;
    private CategorieSalle categorieSalle;
    private String nomSalle;
    private int capacite;
    private Vector<MaterielSalle> listMateriels;
    private Vector<Seance> listSeances;

    public Salle() {
    }

    public Salle(int id, CategorieSalle categorieSalle, String nomSalle, int capacite) {
        this.id = id;
        this.categorieSalle = categorieSalle;
        this.nomSalle = nomSalle;
        this.capacite = capacite;
        this.listMateriels = new Vector<MaterielSalle>();
        this.listSeances = new Vector<Seance>();
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.nomSalle + " - " + this.categorieSalle.getNom();
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategorieSalle getCategorieSalle() {
        return categorieSalle;
    }

    public void setCategorieSalle(CategorieSalle categorieSalle) {
        this.categorieSalle = categorieSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Vector<Seance> getListSeance() {
        return listSeances;
    }

    public void setListSeance(Vector<Seance> listSeance) {
        this.listSeances = listSeance;
    }

    public Vector<MaterielSalle> getListMaterielSalle() {
        return listMateriels;
    }

    public void setListMaterielSalle(Vector<MaterielSalle> listMaterielSalle) {
        this.listMateriels = listMaterielSalle;
    }
}
