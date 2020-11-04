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
public class Joueur {
    String Nom ;
    String Couleur ;
    Jeton ListeJetons [] = new Jeton [21] ;
    int nombreDesintegrateurs ;
    int nombreJetonsRestants ;
    // attributs de la classe

    
        Joueur (String UN_Nom) {
        Nom = UN_Nom ;
        nombreDesintegrateurs = 0 ;
        nombreJetonsRestants = 0 ;
        
        String LA_Couleur [] = { "Jaune", "Rouge"};
        Random generateurAleat = new Random();
        int n = generateurAleat.nextInt (1);
        Couleur = LA_Couleur [n];
            //constructeur de notre classe
        }

            // soit faire 
            // void affecterCoueleur (String UNE_Couleur){
            //      Couleur = UNE_Couleur ;
            //  }

        void ajouter_Jeton ( Jeton Jeton_a_ajouter) { 
            ListeJetons [nombreJetonsRestants ++ ] = Jeton_a_ajouter ;
        }
    //    public boolean ajouter_Jeton ( Jeton Jeton_a_ajouter) { 
    //        ListeJetons [nombreJetonsrestants] = Jeton_a_ajouter ;
    //        nombreJetonsrestants++ ;
    //            if(nombreJetonsrestants <= 21){
    //                return true;
    //            }else{
    //                return false;
    //            }
    //    }
        
        Jeton retirerJeton (){
            nombreJetonsRestants -- ;
            return ListeJetons[nombreJetonsRestants] ;
        }
    
        void obtenirDesintegrateur(){
            nombreDesintegrateurs += 1 ;
        }
        
        boolean utiliserDesintegrateur (){
            if (nombreDesintegrateurs == 0) {
                return false ;
            } else {
                nombreDesintegrateurs -- ;
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
