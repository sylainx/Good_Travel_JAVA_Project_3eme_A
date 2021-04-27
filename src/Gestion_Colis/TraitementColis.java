
package Gestion_Colis;

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

class TraitementColis implements InterfaceOperation<Colis> {
    
    //création de l'objet de type File
    File fichier = new File("colis.txt");
    FileWriter fw = null;
    
    ArrayList<Colis> arc = new ArrayList<Colis>();
    StringTokenizer st = null;
    
    
    /* ------- Enregistrer -------*/
    @Override
    public void enregistrer(Colis o) {
        if(o.getExpediteur().isEmpty() || o.getDestinataire().isEmpty() || o.getTelExp().isEmpty() || o.getTelDest().isEmpty()
                || String.valueOf(o.getMontant()).isEmpty() )
        {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "ERREUR DE SAISIE", JOptionPane.ERROR_MESSAGE);
        }
        else if(o.getMontant()<=0){
            JOptionPane.showMessageDialog(null, "Veuillez ajouter un montant correct","ERRREUR DE SAISIE",JOptionPane.ERROR_MESSAGE);
        }
        else{
            Random r = new Random();
            
            String code= o.getExpediteur().substring(0,1) + o.getDestinataire().substring(0,1) + (r.nextInt(999) + arc.size()+1);
             o.setId(code);
             
            //récuperer la valeur tapee par l'user
            String ligne = o.getId()+"|"+o.getExpediteur()+"|"+o.getDestinataire()+"|"+o.getTelExp()+"|"+o.getTelDest()
                    +"|"+o.getMontant()+"\r\n";
            
            try {
                fw=new FileWriter(fichier,true);
                fw.write(ligne.toUpperCase());
                fw.close();
                
                //message confirmation
                JOptionPane.showMessageDialog(null,"Enregistrement réussi ! ", "Enregistrement" ,JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(TraitementColis.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    /* ------- Modifier -------*/
    @Override
    public void modifier(Colis o) {
        
    }

    /* ------- Afficher -------*/
    @Override
    public ArrayList<Colis> afficher() {
         arc.clear();
        try {
            for(String i:Files.readAllLines(fichier.toPath() )){
                st=new StringTokenizer(i,"|");
                 arc.add(new Colis(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), Double.parseDouble(st.nextToken()) ) );
            }
            
        } catch (IOException ex) {
            Logger.getLogger(TraitementColis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arc;
    }

    /* ------- Supprimer -------*/
    @Override
    public void supprimer(String id) {
        
        try {
            JOptionPane.showMessageDialog(null,"Les donnes selectionnee seront effacées","Supprimer",JOptionPane.OK_CANCEL_OPTION );
           
            for(Colis c:arc){
                if(c.getId().equalsIgnoreCase(id)){
                    arc.remove(c);
                    break;
                }
            }
            fw=new FileWriter(fichier);
            for(Colis c:arc){
                String ligne = c.getId()+"|"+c.getExpediteur()+"|"+c.getDestinataire()+"|"+c.getTelExp()+"|"+c.getTelDest()
                    +"|"+c.getMontant()+"\r\n";
                
                fw.write(ligne.toUpperCase());
            }
            fw.close();
            
            JOptionPane.showMessageDialog(null,"Suppréssion terminé !" );
        } catch (IOException ex) {
            Logger.getLogger(TraitementColis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /* ------- Creer trajet -------*/
    @Override
    public void creerTrajet(Colis o) {
        
    }
    
}
