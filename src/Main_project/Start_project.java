
package Main_project;

import Gestion_Bus.BusVue;
import Gestion_Colis.ColisVue;
import Gestion_Reservations.ReservationsVue;
import Gestion_Trajets.TrajetView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Start_project extends JFrame{
    
    public Start_project(){
        
        // parametrage
        this.setTitle("Good Travel S.A");
        this.setMinimumSize(new Dimension(400, 400));
        this.setExtendedState(6);
        
        /*--------------------- JTabbedPane ------------------------*/
        
        JTabbedPane tabbedPane = new JTabbedPane(); //TabbedPane principal
        
        tabbedPane.setFont(new Font("Playfair Display", Font.BOLD, 20));
        tabbedPane.setForeground(Color.blue);

        //ajouter les éléments de JTabbedPane 
        TrajetView trajV = new TrajetView();
        tabbedPane.addTab("Gestion des Trajets", trajV); //ajouter Trajets dans le tabbedPane
        
        BusVue busV = new BusVue();
        tabbedPane.addTab("Gestion des Bus", busV); //ajouter Bus dans le tabbedPane
        
        ReservationsVue resV = new ReservationsVue();
        tabbedPane.addTab("Gestion des réservations", resV);   //ajouter Reservations dans le tabbedPane 
        
        ColisVue colV = new ColisVue();
        tabbedPane.addTab("Gestion des Colis", colV);   //ajouter Reservations dans le tabbedPane 

        
        /*---------------------------------*/
        
        //ajouts le JTabbedPane dans JFrame
        this.add(tabbedPane);

        //fermeture
        this.setDefaultCloseOperation(3);

    }
    
    public static void main(String[] args) {
        new Start_project().setVisible(true);
    }
    
}
