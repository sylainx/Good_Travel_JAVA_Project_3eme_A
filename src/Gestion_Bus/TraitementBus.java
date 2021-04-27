
package Gestion_Bus;

import Main_project.InterfaceOperation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TraitementBus implements InterfaceOperation<Bus> {
    
    // variables
    File fichier = new File("bus.txt");
    FileWriter fw = null;
    ArrayList<Bus> arb = new ArrayList<Bus>();
    StringTokenizer st = null;

    
    /* ----- Enregistrer -----*/
    @Override
    public void enregistrer(Bus o) {
        
        if(o.getMarque().isEmpty() || o.getModele().isEmpty() || String.valueOf(o.getAnnee()).isEmpty() 
                || String.valueOf(o.getNbCylindre()).isEmpty() || o.getTypeTransmission().isEmpty() || o.getPlaque().isEmpty() )
        {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "ERREUR DE SAISIE", JOptionPane.ERROR_MESSAGE);
        }
        else if(o.getAnnee() < 1900 || o.getAnnee() > 2030){
            
            JOptionPane.showMessageDialog(null, "Veuillez entrer une année correct", "ERREUR DE SAISIE", JOptionPane.ERROR_MESSAGE);
        }
        
        else if(o.getNbCylindre() <= 0){
            
            JOptionPane.showMessageDialog(null, "Veuillez entrer une année correct", "ERREUR DE SAISIE", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Random r =new Random();
            String code= o.getMarque().substring(0,1) + o.getModele().substring(0,1)+ "-" + o.getTypeTransmission().substring(0,1)
                    + (r.nextInt(999) + arb.size()+1);
            
             o.setId(code);
             
            //récuperer la valeur tapee par l'user
            String ligne = o.getId()+"|" + o.getMarque()+"|" + o.getModele()+"|" + o.getTypeTransmission()+"|" + o.getPlaque()
                    +"|" + o.getAnnee()+"|" + o.getNbCylindre()+"\r\n";
            
            try {
                fw=new FileWriter(fichier,true);
                fw.write(ligne.toUpperCase());
                fw.close();
                
                //message confirmation
                JOptionPane.showMessageDialog(null,"Enregistrement réussi ! ", "Enregistrement" ,JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(TraitementBus.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
        
    }

    /* ----- Modifier -----*/
    @Override
    public void modifier(Bus o) {
         
    }


    /* ----- Supprimer -----*/
    @Override
    public void supprimer(String id) {
         
        try {
            JOptionPane.showMessageDialog(null,"les donnes selectionnee seront efface","Supprimer",JOptionPane.OK_CANCEL_OPTION );
           
            for(Bus b: arb){
                if(b.getId().equalsIgnoreCase(id)){
                    arb.remove(b);
                    break;
                }
            }
            fw=new FileWriter(fichier);
            for(Bus b:arb){
                String ligne = b.getId()+"|" + b.getMarque()+"|" + b.getModele()+"|" + b.getTypeTransmission()+"|" + b.getPlaque()
                    +"|" + b.getAnnee()+"|" + b.getNbCylindre()+"\r\n";
                
                fw.write(ligne.toUpperCase());
            }
            fw.close();
            JOptionPane.showMessageDialog(null,"Suppression terminée" );
        } catch (IOException ex) {
            Logger.getLogger(TraitementBus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* ----- Afficher -----*/
    @Override
    public ArrayList<Bus> afficher() {
        arb.clear();
        try {
            for(String i:Files.readAllLines(fichier.toPath())){
                st=new StringTokenizer(i,"|");
                arb.add( new Bus( st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken() ) ) );
            }
            
        } catch (IOException ex) {
            Logger.getLogger(TraitementBus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arb;
    }
    
    /* ----- Créer trajet -----*/
    @Override
    public void creerTrajet(Bus o) {
         
    }
}
