package application.model;

public class MaterielSalle {

    private int idMateriel;
    private String legendeMateriel;
    private int quantiteDiponibleMateriel;

    public int getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getLegendeMateriel() {
        return legendeMateriel;
    }

    public void setLegendeMateriel(String legendeMateriel) {
        this.legendeMateriel = legendeMateriel;
    }

    public int getQuantiteDiponibleMateriel() {
        return quantiteDiponibleMateriel;
    }

    public void setQuantiteDiponibleMateriel(int quantiteDiponibleMateriel) {
        this.quantiteDiponibleMateriel = quantiteDiponibleMateriel;
    }
}
