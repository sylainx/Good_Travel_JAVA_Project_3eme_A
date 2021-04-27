
package Gestion_Trajets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TrajetView extends JPanel implements ActionListener,MouseListener{
    
       //variable Globale
        JTextField tId = null;
        JTextField tPointDepart = null;
        JTextField tPointArrive = null;
        
        //JTextField threDepart = null;
        JComboBox<String> cHreDepart =null;
        JComboBox<String> cMneDepart =null;
        
        JTextField tPrixTrajet=null;
        
        JTable table;
                //rendre gloable boutom
         JButton btN=null;
         JButton btC=null;
         JButton btA=null;
         JButton btS=null;
        
        TrajetsTraitement trt= new TrajetsTraitement();
        Trajets t=new Trajets();
    
    /* ---------------------- CONSTRUCTEUR -----------------------*/
    public TrajetView(){ 
    
        this.setLayout(new BorderLayout());
        // Creation de deux JPanel
        // Utiliser GridBagLayout
        GridBagLayout gb = new GridBagLayout();
        JPanel pform = new JPanel(gb);

        GridBagConstraints gbc = new GridBagConstraints();
        // ajouter un formulaire dans le pform
        //ID
        JLabel lId = new JLabel("ID");
         tId = new JTextField(40);
         tId.setEditable(false);
         //Point de Depat
        JLabel lPointDepart = new JLabel("Point de depart: ");
       tPointDepart = new JTextField(40);
       //Point d'Arriver
        JLabel lPointArrive = new JLabel("Point d'arriver: ");
         tPointArrive = new JTextField(40);
         
          JLabel lhreDepart = new JLabel("Heure de depart");
        String[]hre={ "00h", "01h", "02h", "03h", "04h", "05h", "06h", "07h", "08h", "09h", "10h",
            "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h", "19h", "20h", "21h", "22h", "23h"};
        
        String[]mn={"00","15" , "30", "45"};
        
        //creation de panel contenant les combobox
        JPanel cpane= new JPanel();
        cHreDepart =new JComboBox<String>(hre);
        cMneDepart =new JComboBox<String>(mn);
        
        //Ajouter les ComboBox dans le panel
        cpane.add(cHreDepart);
        cpane.add(cMneDepart);
         //Prix Trajet
          JLabel lPrixTrajet = new JLabel("Prix Trajet");
         tPrixTrajet = new JTextField(40);

        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(4,4,4,4); 
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        pform.add(lId, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pform.add(tId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pform.add(lPointDepart, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pform.add(tPointDepart, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        pform.add(lPointArrive,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        pform.add(tPointArrive,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        pform.add(lhreDepart,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3; 
        pform.add(cpane,gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        pform.add(lPrixTrajet,gbc);
        gbc.gridx = 1;
        gbc.gridy = 4; 
        pform.add(tPrixTrajet,gbc);
        
         btC=new JButton("Créer Trajet");
         btA=new JButton("Afficher");
         btS=new JButton("Supprimer");
         btN=new JButton("Réinitialiser");
         
        //Ajouter les bouton dans le JPAnel
        JPanel pbouton=new JPanel();
        
        pbouton.add(btN);
        //Ajouter une action sur le bouton clear
        btN.addActionListener(this);
        
        pbouton.add(btC);
        //Ajouter une action sur le bouton clear
        btC.addActionListener(this);
       
        pbouton.add(btA);
         //Ajouter une action sur le bouton Afficher
        btA.addActionListener(this);
        
        pbouton.add(btS);
         //Ajouter une action sur le bouton Afficher
        btS.addActionListener(this);
        btS.setEnabled(false);
        
        // Entendre le pbouton sur 2colonnes
        gbc.gridwidth=2;
         gbc.gridx = 1;
        gbc.gridy = 5;
        pform.add(pbouton,gbc);
        
        JPanel panetb = new JPanel();
         
         table = new JTable();
         table.setAutoCreateRowSorter(true);
         table.addMouseListener(this);  //a l'ecoute d'un évenement        
        load();
        
       
        panetb.add(new JScrollPane(table));
        //pform.setBorder(BorderFactory.createLineBorder(Color.cyan,2));
        pform.setBorder(BorderFactory.createLineBorder(Color.cyan,2));
        // ajouter les JPanel dans le Jframe
        this.add(pform, BorderLayout.WEST);
        this.add(panetb,BorderLayout.EAST);

   }

   /* ---------------------- METHODE ACTIONLISTENER -----------------------*/
    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getSource()==btN){
           initiate();
           
       }else if(ae.getSource()==btC){
          t.setPointDepart(tPointDepart.getText());
            t.setPointArrive(tPointArrive.getText());
            t.setHreDepart(String.valueOf(cHreDepart.getSelectedItem())+" "+ String.valueOf(cMneDepart.getSelectedItem()));
             
            t.setPrixTrajet(Double.parseDouble(tPrixTrajet.getText()));
            
            if( tId.getText() == null || tId.getText().isEmpty())
                trt.creerTrajet(t);
            else{
                t.setId(tId.getText());
                trt.modifier(t);
            }
            
            load(); // permet d'actualiser le tableau automatiquement
            initiate();
            
       }else if(ae.getSource()==btS){
          
          int ligne = table.getSelectedRow();
            String id = ""+table.getValueAt(ligne, 0); 
            //JOptionPane.showMessageDialog(null, id);
            trt.supprimer(id);
            load();
            initiate();
       }
       else{
           try {
               table.print();
           } catch (PrinterException ex) {
               Logger.getLogger(TrajetView.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
   /* ---------------------- METHODE REINITIALISER -----------------------*/
     void initiate()
     {
         tId.setText(null);
         tPointDepart.setText(null);
         tPointArrive.setText(null);
         cHreDepart.setSelectedIndex(0);
         cMneDepart.setSelectedIndex(0);
         tPrixTrajet.setText(null);
         //bouton desactiver
         //desactiver pr ne pas pouvoir supprimer
        btS.setEnabled(false);
        btC.setText("Creer Trajet");
     }
     
   /* ---------------------- METHODE LOAD -----------------------*/
     void load(){
        Object[][] ob=new Object[trt.afficher().size()][5];
        int i=0;
        for(Trajets t: trt.afficher()){
            ob[i][0]=t.getId();
            ob[i][1]=t.getPointDepart();
            ob[i][2]=t.getPointArrive();
            ob[i][3]=t.getHreDepart();
            ob[i][4]=t.getPrixTrajet();
            i++;
        }
        //entete
        String h[]={"ID","POINT DEPART","POINT ARRIVE","HRE DEPART","PRIX TRAJET"};
        table.setModel(new DefaultTableModel(ob,h)); 
    }
     
   /* ---------------------- METHODES MOUSELISTENER-----------------------*/
     
      public void mouseClicked(MouseEvent me) {
        int ligne=table.getSelectedRow();
        if(ligne >= 0){
            btS.setEnabled(true);
            btC.setText("Modifier");
            
            tId.setText(""+table.getValueAt(ligne, 0));
            tPointDepart.setText(""+table.getValueAt(ligne, 1));
            tPointArrive.setText(""+table.getValueAt(ligne, 2));
            //Recuperation JComBoBox
            cHreDepart.setSelectedItem(""+(String.valueOf(table.getValueAt(ligne, 3)).substring(0,3)));
            cMneDepart.setSelectedItem(""+(String.valueOf(table.getValueAt(ligne, 3)).substring(4,7)));
            
            tPrixTrajet.setText(""+table.getValueAt(ligne, 4));
        }
    }
      

    @Override
    public void mousePressed(MouseEvent me) {
       
    }

    @Override
    public void mouseReleased(MouseEvent me) {
       
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }
   
}
