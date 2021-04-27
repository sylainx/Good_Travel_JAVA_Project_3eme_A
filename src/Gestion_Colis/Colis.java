/*
 * Liste champ : ID (code colis), Expéditeur, destinataire, téléphone expéditeur, téléphone
 *  destinataire, montant payé.
 *  Opération : Enregistrer, modifié, affiché
 */
package Gestion_Colis;

public class Colis {
    
    private String id,expediteur,destinataire,telExp,telDest;
    private Double montant;

    public Colis() {
    }

    public Colis(String id, String expediteur, String destinataire, String telExp, String telDest, Double montant) {
        this.id = id;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.telExp = telExp;
        this.telDest = telDest;
        this.montant = montant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getTelExp() {
        return telExp;
    }

    public void setTelExp(String telExp) {
        this.telExp = telExp;
    }

    public String getTelDest() {
        return telDest;
    }

    public void setTelDest(String telDest) {
        this.telDest = telDest;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
    
    
    
}
