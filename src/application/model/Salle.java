package application.model;

public class Salle {

    private int idSalle;
    private String nomSalle;
    private int capaciteSalle;
    private int idCategorieSalle;

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public int getCapaciteSalle() {
        return capaciteSalle;
    }

    public void setCapaciteSalle(int capaciteSalle) {
        this.capaciteSalle = capaciteSalle;
    }

    public int getIdCategorieSalle() {
        return idCategorieSalle;
    }

    public void setIdCategorieSalle(int idCategorieSalle) {
        this.idCategorieSalle = idCategorieSalle;
    }
}
