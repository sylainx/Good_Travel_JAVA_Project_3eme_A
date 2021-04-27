
package Gestion_Bus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BusVue extends JPanel implements ActionListener, MouseListener {
    
    //variables 
    JTextField tId=null;
    JTextField tMarque=null;
    JTextField tModele=null;
    JTextField tAnnee=null;
    JComboBox<String> cbNbCylindre=null;
    JComboBox<String> cbTypeTransmission = null;
    JTextField tPlaque=null;
    
    //bouton
    JButton btN=null;
    JButton btE=null;
    JButton btS=null;
    JButton btP=null;
    
    // tableau
    JTable tableau= null;
    
    TraitementBus btm=new TraitementBus();
    Bus b = new Bus();

    /* ---------- CONSTRUCTEUR DE LA CLASSE ---------*/
    public BusVue(){
        
        //parametres
        this.setLayout(new BorderLayout());
        
        /* ------------ CORPS ---------------*/
        GridBagLayout gb = new GridBagLayout();
        JPanel pFormulaire = new JPanel(gb);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        /* ------- Formulaire ------- */
        
        JLabel lId = new JLabel("ID: ");
        tId = new JTextField(40);
        tId.setEditable(false);
        JLabel lMarque = new JLabel("Marque: ");
        tMarque = new JTextField(40);
        JLabel lModele = new JLabel("Model: ");
        tModele = new JTextField(40);
        JLabel lAnnee = new JLabel("Annee: ");
        tAnnee = new JTextField(40);
        JLabel lNbCylindre = new JLabel("Nb Cylindre: ");
        String nbCylindre[] = { "4","6", "8", "10", "12"};
        cbNbCylindre = new JComboBox<String>(nbCylindre);
        JLabel lTypeTransmission = new JLabel("Type Transmission: ");
        String typeTransmission[] = { "Automatique", "Manuel"};
        cbTypeTransmission = new JComboBox<String>(typeTransmission);
        JLabel lPlaque = new JLabel("Plaque: ");
        tPlaque = new JTextField(40);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        pFormulaire.add(lId, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pFormulaire.add(tId, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        pFormulaire.add(lMarque, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pFormulaire.add(tMarque, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        pFormulaire.add(lModele, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        pFormulaire.add(tModele, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        pFormulaire.add(lAnnee, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pFormulaire.add(tAnnee, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        pFormulaire.add(lNbCylindre, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        pFormulaire.add(cbNbCylindre, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        pFormulaire.add(lTypeTransmission, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        pFormulaire.add(cbTypeTransmission, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        pFormulaire.add(lPlaque, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        pFormulaire.add(tPlaque, gbc);
        
        /* ------- Bouton ------- */
        
        JPanel pBouton = new JPanel();  // panel pour contenir les boutons
        
        btE = new JButton("Enregistrer");
        btS = new JButton("Supprimer");
        btP = new JButton("Imprimer");
        btN = new JButton("Reinitialiser");
        
        pBouton.add(btE);
        btE.addActionListener(this);
        
        pBouton.add(btP);
        btP.addActionListener(this);
        
        pBouton.add(btS);
        btS.addActionListener(this);
        btS.setEnabled(false);
        
        pBouton.add(btN);
        btN.addActionListener(this);
        
        gbc.gridwidth = 2;  // etendre sur 2 colonnees
        gbc.gridx = 1;
        gbc.gridy = 7;
        pFormulaire.add(pBouton, gbc);
        
        /* ------- Tableau ------- */
        JPanel pTableau = new JPanel();
        
        tableau = new JTable();
        tableau.setAutoCreateRowSorter(true);  // triage
        tableau.addMouseListener(this);
        load(); // raffraichir le tableau automatiquement
        
        pTableau.add(new JScrollPane(tableau));
        
        /* ------- ajouter le formulaire et le tableau dans JPanel------- */
        this.add(pTableau, BorderLayout.EAST);
        this.add(pFormulaire, BorderLayout.WEST);
        
        
    }   // fin constructeur
    
    
    /* ---------- METHODE INITIALISER ---------*/
    void initialiser(){
        
        tId.setText(null);
        tMarque.setText(null);
        tModele.setText(null);
        tAnnee.setText(null);
        cbNbCylindre.setSelectedIndex(0);
        cbTypeTransmission.setSelectedIndex(0);
        tPlaque.setText(null);
        
        //desactiver pr ne pas pouvoir supprimer
        btS.setEnabled(false);
        btE.setText("Enregistrer");
    }
    
    /* ---------- METHODE LOAD ---------*/
    void load(){
        Object[][] ob = new Object[btm.afficher().size()][7];
        int i=0;
        for(Bus b : btm.afficher() ){
            ob[i][0]=b.getId();
            ob[i][1]=b.getMarque();
            ob[i][2]=b.getModele();
            ob[i][3]=b.getTypeTransmission();
            ob[i][4]=b.getPlaque();
            ob[i][5]=b.getAnnee();
            ob[i][6]=b.getNbCylindre();
            
            i++;
        }
        
        //entete tableau
        String entete[]={"ID","MARQUE","MODELE","ANNEE","NB CYLINDRE","TYPE TRANSM.","No PLAQUE"};
        
        tableau.setModel(new DefaultTableModel(ob,entete));
        
    }

    /* ---------- METHODE ACTIONLISTENER ---------*/
    @Override
    public void actionPerformed(ActionEvent e) {
         
        if(e.getSource() == btN){
            initialiser();
        }
        else if(e.getSource() == btE){
            b.setMarque(tMarque.getText());
            b.setModele(tModele.getText());
            b.setAnnee(Integer.parseInt(tAnnee.getText() ));
            b.setNbCylindre(Integer.parseInt( String.valueOf(cbNbCylindre.getSelectedItem() ) ) );
            b.setTypeTransmission(String.valueOf(cbTypeTransmission.getSelectedItem() ) );
            b.setPlaque(tPlaque.getText());
            
            if( tId.getText() == null || tId.getText().isEmpty())
                btm.enregistrer(b);
            else{
                b.setId(tId.getText() );
                btm.modifier(b);
            }
            
            load(); //actualiser le tableau automatiquement
            initialiser();
        }
        else if(e.getSource() == btS){
            int ligne = tableau.getSelectedRow();
            
            String id = ""+tableau.getValueAt(ligne, 0); 
            JOptionPane.showMessageDialog(null, id); //ces 2 dernieres lignes sont des exemples
            
            btm.supprimer(id);
            load();
            
        }
        else{
            try {
                tableau.print();
            } catch (PrinterException ex) {
                Logger.getLogger(BusVue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* ---------- METHODES MOUSELISTENER ---------*/
    @Override
    public void mouseClicked(MouseEvent e) {
         int ligne=tableau.getSelectedRow();
        if(ligne >= 0){
            btS.setEnabled(true);
            btE.setText("Modifier");
            
            tId.setText(""+tableau.getValueAt(ligne, 0));
            tMarque.setText(""+tableau.getValueAt(ligne, 1));
            tModele.setText(""+tableau.getValueAt(ligne, 2));
            tAnnee.setText(""+tableau.getValueAt(ligne, 3));
            cbNbCylindre.setSelectedItem(""+tableau.getValueAt(ligne, 4));
            cbTypeTransmission.setSelectedItem(""+tableau.getValueAt(ligne, 5));
            tPlaque.setText(""+tableau.getValueAt(ligne, 6));
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
         
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         
    }

    @Override
    public void mouseExited(MouseEvent e) {
         
    }
    
    
    
}
