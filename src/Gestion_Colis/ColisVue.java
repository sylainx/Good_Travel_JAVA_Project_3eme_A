
package Gestion_Colis;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ColisVue extends JPanel implements ActionListener,MouseListener {
    
    //variables
    JTextField tId = null;
    JTextField tExpediteur = null;
    JTextField tDestinataire = null;
    JTextField tTelExp = null;
    JTextField tTelDest = null;
    JTextField tMontant = null;
    
    JTable tableau = null;
    
    JButton btN = null;
    JButton btE = null;
    JButton btA = null;
    
    TraitementColis ctm = new TraitementColis();
    Colis c = new Colis();
    
    public ColisVue(){
        
        //parametrage
        this.setLayout(new BorderLayout());
        
        GridBagLayout gb = new GridBagLayout();
        JPanel pFormulaire = new JPanel(gb);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        /* ----- FORMULAIRE -----*/
        
        JLabel lId = new JLabel("ID Colis");
        tId = new JTextField(40);
        tId.setEditable(false);
        
        JLabel lExpediteur = new JLabel("Expediteur: ");
        tExpediteur = new JTextField(40);
        
        JLabel lDestinataire = new JLabel("Destinataire: ");
        tDestinataire = new JTextField(40);
        
        JLabel lTelExp = new JLabel("Tel Exp.: ");
        tTelExp = new JTextField(40);
        
        JLabel lTelDest = new JLabel("Tel Dest.: ");
        tTelDest = new JTextField(40);
        
        JLabel lMontant = new JLabel("Montant: ");
        tMontant = new JTextField(40);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        pFormulaire.add(lId,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pFormulaire.add(tId,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        pFormulaire.add(lExpediteur,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pFormulaire.add(tExpediteur,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        pFormulaire.add(lDestinataire,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        pFormulaire.add(tDestinataire,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        pFormulaire.add(lTelExp,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pFormulaire.add(tTelExp,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        pFormulaire.add(lTelDest,gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        pFormulaire.add(tTelDest,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        pFormulaire.add(lMontant,gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        pFormulaire.add(tMontant,gbc);
        
        /* ----- BOUTON -----*/
        
        btN = new JButton("Réinitialiser");
        btE = new JButton("Enregistrer");
        btA = new JButton("Afficher");
        
        JPanel pBouton = new JPanel();  //panel pour Bouton
        
        pBouton.add(btE);
        btE.addActionListener(this);
        
        pBouton.add(btA);
        btA.addActionListener(this);
        
        pBouton.add(btN);
        btN.addActionListener(this);
        
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 6;
        pFormulaire.add(pBouton,gbc);
        
        /* ----- TABLEAU -----*/
        
        JPanel pTableau = new JPanel();     // panel Tableau
        
        tableau = new JTable();
        tableau.setAutoCreateRowSorter(true);
        tableau.addMouseListener(this); 
        load(); //automatiser le tableau(afficher toute de suite)
        
        pTableau.add(new JScrollPane(tableau));
        
        pFormulaire.setBorder(BorderFactory.createLineBorder(Color.red, 1) );
        
        // ajouter le formulaire et le tableau dans le JPanel
        this.add(pTableau, BorderLayout.EAST);
        this.add(pFormulaire, BorderLayout.WEST);
        
    }
    
    /* ---------------------- METHODE INITIALISER -----------------------*/
    void initialiser(){
        tId.setText(null);
        tExpediteur.setText(null);
        tDestinataire.setText(null);
        tTelExp.setText(null);
        tTelDest.setText(null);
        tMontant.setText(null);
        
        //desactiver pr ne pas pouvoir supprimer
        //btS.setEnabled(false);
        
        btE.setText("Enregistrer");
        
    }
    
    /* ---------------------- METHODE INITIALISER -----------------------*/
    void load(){
        Object[][] ob = new Object[ctm.afficher().size()][6];
        int i=0;
        for(Colis m: ctm.afficher()){
            ob[i][0]=m.getId();
            ob[i][1]=m.getExpediteur();
            ob[i][2]=m.getDestinataire();
            ob[i][3]=m.getTelExp();
            ob[i][4]=m.getTelDest();
            ob[i][5]=m.getMontant();
            
            i++;
        }
        
        //entete tableau
        String entete[]={"ID","EXPÉDITEUR","DESTINATAIRE","TEL EXPEDITEUR","TEL DESTINATAIRE","MONT. PAYÉ"};
        
        tableau.setModel(new DefaultTableModel(ob,entete));
        
    }

    /* ---------------------- METHODES ACTIONlISTENER -----------------------*/
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btN){
            initialiser();
        }
        else if( e.getSource() == btE){
            c.setExpediteur(tExpediteur.getText());
            c.setDestinataire(tDestinataire.getText());
            c.setTelExp(tTelExp.getText());
            c.setTelDest(tDestinataire.getText());
            c.setMontant(Double.parseDouble(tMontant.getText()));
            
            if(tId.getText() == null || tId.getText().isEmpty())
                ctm.enregistrer(c);
            else{
                c.setId(tId.getText());
                ctm.modifier(c);
            }
            
            load(); //actualiser le tableau automatiquement
            initialiser();
            
        }
        else{
            
            try {
                tableau.print();
            } catch (PrinterException ex) {
                Logger.getLogger(ColisVue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    /* ---------------------- METHODES MOUSElISTENER -----------------------*/
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int ligne=tableau.getSelectedRow();
        if(ligne >= 0){
            //btS.setEnabled(true);
            btE.setText("Modifier");
            
            tId.setText(""+tableau.getValueAt(ligne, 0));
            tExpediteur.setText(""+tableau.getValueAt(ligne, 1));
            tDestinataire.setText(""+tableau.getValueAt(ligne, 2));
            tTelExp.setText(""+tableau.getValueAt(ligne, 3));
            tTelDest.setText(""+tableau.getValueAt(ligne, 4));
            tMontant.setText(""+tableau.getValueAt(ligne, 5));
            
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
