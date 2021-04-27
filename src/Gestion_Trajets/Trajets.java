
package Gestion_Trajets;

public class Trajets {
    private String id,pointDepart,pointArrive,hreDepart;
    private Double prixTrajet;

    public Trajets() {
    }

    public Trajets(String id, String pointDepart, String pointArrive, String hreDepart, Double prixTrajet) {
        this.id = id;
        this.pointDepart = pointDepart;
        this.pointArrive = pointArrive;
        this.hreDepart = hreDepart;
        this.prixTrajet = prixTrajet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(String pointDepart) {
        this.pointDepart = pointDepart;
    }

    public String getPointArrive() {
        return pointArrive;
    }

    public void setPointArrive(String pointArrive) {
        this.pointArrive = pointArrive;
    }

    public String getHreDepart() {
        return hreDepart;
    }

    public void setHreDepart(String hreDepart) {
        this.hreDepart = hreDepart;
    }

    public Double getPrixTrajet() {
        return prixTrajet;
    }

    public void setPrixTrajet(Double prixTrajet) {
        this.prixTrajet = prixTrajet;
    }

}
