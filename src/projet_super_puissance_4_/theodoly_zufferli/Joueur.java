/*
 * super puissance 4
 * Clara THEODOLY
  * Chloé ZUFFERLI
 */
package projet_super_puissance_4_.theodoly_zufferli;

import java.util.Random;

/**
 *
 * @author clara
 */

//CLASSE JOUEUR : DECRIVANT UN JOUEUR.
// UN JOUEUR POSSEDE NOTAMMENT UN NOM
// UN JOUEUR EST ASSOCIE A UNE COULEUR
// UN JOUEUR DISPOSE AU DEBUT DE LA MANCHE UN ENSEMBLE DE JETONS ET SA COULEUR

public class Joueur {
    // attributs de la classe joueur
    String Nom ;
    String Couleur ;
    Jeton ListeJetons [] = new Jeton [21] ;
    int nombreDesintegrateurs ;
    int nombreJetonsRestants ;
    

    // Constructeur de la classe joueur : initialise le nom de joueur avec son paramètre
        Joueur (String UN_Nom) {
        Nom = UN_Nom ;
        nombreDesintegrateurs = 0 ;
        nombreJetonsRestants = 0 ;
        
        String LA_Couleur [] = { "Jaune", "Rouge"};
        Random generateurAleat = new Random();
        int n = generateurAleat.nextInt (1);
        Couleur = LA_Couleur [n];
      
        }

            // soit faire
            // affecte une couleur au jeton prends en paramètre la couleur que doit prendre le joueur
            // void affecterCouleur (String UNE_Couleur){
            //      Couleur = UNE_Couleur ;
            //  }
            // donne la couleur du joueur
            // public String lireCouleur (){
            //      return Couleur ;
            // }

        // ajoute le jeton passé en paramètre  à la liste des jetons : le paramètre est de type Jeton et s'appelle Jeton_a_ajouter
        public boolean ajouter_Jeton ( Jeton Jeton_a_ajouter) { 
           if ( nombreJetonsRestants == ListeJetons.length ) { // le nombre de jetons restants est le même que le nombre total de jeton soit 21
               return false ;
            } else {
               ListeJetons [ nombreJetonsRestants ] = Jeton_a_ajouter ;
               nombreJetonsRestants ++ ; // incrémentation 
               return true ;
           }
        }
         
        // de manière similaire a la méthode "ajouterJeton" je cree la méthode "retirerJeton"
        public boolean retirerJeton (){
            if ( nombreJetonsRestants == 0 ) { // il neste aucun jeton pour jouer
                return false ;
            }else{
                ListeJetons [ nombreJetonsRestants ] = null ;
                nombreJetonsRestants -- ; // décrementation
                return true ;
            }
        }
    
        // incrémente le nombre de désintégrateurs du joueurs
        public void obtenirDesintegrateur() {
            nombreDesintegrateurs ++ ; // incrémentation
        }
        
        // décremente le nombre de désintégrateur et confirme l'utilisation de ce dernier, ou renvoie faux s'il ne restait plus de désintégrateurs
        public boolean utiliserDesintegrateur () {
            if (nombreDesintegrateurs == 0) { // il ne reste pus de désintégrateurs donc il faut renvoyer faux
                return false ;
            } else {
                nombreDesintegrateurs -- ; // décrémentation
                return true ;
            }
        }
        
        @Override
        public String toString() {
            String lireCouleur ;
            lireCouleur = "La couleur du joueur est " +Couleur;
            return lireCouleur ; // car c'est une fonction qui doit retourner qqch
            // méthode to String, sans elle on n'arrive pas a faire fonctionner correctement le system.out.println du main() 
        } 
        
}
