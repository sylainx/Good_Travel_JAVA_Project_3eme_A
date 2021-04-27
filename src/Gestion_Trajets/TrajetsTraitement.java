package Gestion_Trajets;

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

public class TrajetsTraitement implements InterfaceOperation<Trajets> {
    File f=new File("trajets.txt");
    FileWriter fw=null;
    ArrayList<Trajets> tj=new ArrayList();

    StringTokenizer st = null;
    
    /* ------- creer trajet -------*/
    @Override
    public void creerTrajet(Trajets o) {
        
        if(o.getPointDepart().isEmpty() || String.valueOf(o.getPointArrive()).isEmpty() || String.valueOf(o.getHreDepart()).isEmpty()
                || String.valueOf(o.getPrixTrajet()).isEmpty()){
           JOptionPane.showMessageDialog(null, "Il faut saisir des donnees dans toues les cases","ERREUR DE SAISIE",JOptionPane.ERROR_MESSAGE);  
        }
        else if(o.getPrixTrajet() <= 0){
            JOptionPane.showMessageDialog(null, "La quantité doit etre suppérieure a zéro", "ERREUR DE SAISIE",JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
            Random r =new Random();
             String code= o.getPointDepart().substring(0,1) + o.getPointArrive().substring(0,1) + (r.nextInt(999) + tj.size()+1);
             o.setId(code);
             
            //récuperer la valeur saisi par l'user
            String ligne = o.getId()+":"+o.getPointDepart()+":"+o.getPointArrive()+":"+o.getHreDepart()+":"+o.getPrixTrajet()+"\r\n";
            
            try {
                fw=new FileWriter(f,true);
                fw.write(ligne.toUpperCase());
                fw.close();
                 //message confirmation
                JOptionPane.showMessageDialog(null,"Vous venez de créer un trajet avec succès! ", "CRÉATION TRAJET" ,JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(TrajetsTraitement.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }    
    }
   
    /* ------- modifier -------*/
    @Override
    public void modifier(Trajets o) {

        //variable
        try {
            JOptionPane.showMessageDialog(null, "Voulez-vous vraiment modifer le trajet "+o.getId()+" ? ","MODIFICATION",JOptionPane.YES_NO_OPTION);

            for(Trajets trajet:tj){
                trajet.setPointDepart(o.getPointDepart());
                trajet.setPointArrive(o.getPointArrive());
                trajet.setHreDepart(o.getHreDepart());
                trajet.setPrixTrajet(o.getPrixTrajet());
                break;
            }

            fw=new FileWriter(f);
            for(Trajets t:tj){
                String ligne = t.getId()+":"+t.getPointDepart()+":"+t.getPointArrive()+":"+t.getHreDepart()+":"+t.getPrixTrajet()+"\r\n";
                fw.write(ligne.toUpperCase());
            }
            fw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(TrajetsTraitement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* ------- supprimer -------*/
    @Override
    public void supprimer(String id)  {
        try {
            JOptionPane.showMessageDialog(null,"les donnes selectionnee seront efface","Supprimer",JOptionPane.OK_CANCEL_OPTION );
           
            for(Trajets trajet:tj){
                if(trajet.getId().equalsIgnoreCase(id)){
                    tj.remove(trajet);
                    break;
                }
            }
            fw=new FileWriter(f);
            for(Trajets t:tj){
                String ligne = t.getId()+":"+t.getPointDepart()+":"+t.getPointArrive()+":"+t.getHreDepart()+":"+t.getPrixTrajet()+"\r\n";
                fw.write(ligne.toUpperCase());
            }
            fw.close();
            JOptionPane.showMessageDialog(null,"les donnes selectionnee sont effaces" );
        } catch (IOException ex) {
            Logger.getLogger(TrajetsTraitement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* ------- afficher -------*/
    @Override
    public ArrayList<Trajets> afficher() {
        tj.clear();
        try {
            for(String i:Files.readAllLines(f.toPath())){
                st=new StringTokenizer(i,":");
                tj.add(new Trajets(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(), Double.parseDouble(st.nextToken()) ));
            }
        } catch (IOException ex) {
            Logger.getLogger(TrajetsTraitement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tj;
       
    }

    /* ------- Enregistrer -------*/
    @Override
    public void enregistrer(Trajets o) {
        
    }
   
}
