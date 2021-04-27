
package Main_project;

import java.util.ArrayList;


public interface InterfaceOperation<T> {
    
    void creerTrajet(T o);
    void enregistrer(T o);
    void modifier(T o);
    ArrayList<T> afficher();
    void supprimer(String id);
    //<T> rechercher(String id);
    
}
