
package Gestion_Bus;

public class Bus {
    private String id,marque,modele,typeTransmission,plaque;
    private int annee,nbCylindre;

    public Bus() {
    }

    public Bus(String id, String marque, String modele, String typeTransmission, String plaque, int annee, int nbCylindre) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.typeTransmission = typeTransmission;
        this.plaque = plaque;
        this.annee = annee;
        this.nbCylindre = nbCylindre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getTypeTransmission() {
        return typeTransmission;
    }

    public void setTypeTransmission(String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNbCylindre() {
        return nbCylindre;
    }

    public void setNbCylindre(int nbCylindre) {
        this.nbCylindre = nbCylindre;
    }
    
    
}
