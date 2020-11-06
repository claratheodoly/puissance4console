/*
 * super puissance 4
 * Clara THEODOLY
  * Chloé ZUFFERLI
 */
package projet_super_puissance_4_.theodoly_zufferli;

/**
 * @author clara
 */

// CLASSE JETON : DECRIVANT UN JETON; ESSENTIELLEMENT REPRESENTE PAR UN ATTRIBUT COULEUR

public class Jeton {
    // attributs de la classe
    String Couleur ;
    
    // constructeur de la classe Jeton initialisant la couleur du jeton avec le paramètre
    public Jeton (String UNE_Couleur) {
        Couleur = UNE_Couleur ;
    }
   
    // renvoit la couleur du jeton
    public String lireCouleur() {
        return Couleur ;
    }
    
    @Override
    public String toString() {
        String lireCouleur ;
        lireCouleur = "La couleur du joueur est " +Couleur;
        return lireCouleur ; // car c'est une fonction qui doit retourner qqch
        // méthode to String, sans elle on n'arrive pas a faire fonctionner correctement le system.out.println du main() 
    } 
    
    // Sinon utiliser le code suivant qui est celui du prof
    //  @Override
    //  public String toString() {
    //      if( "Rouge".equals(Couleur))
    //         return "\u001B[31m 0 " ;
    //      return "\u001B[33m 0 " ;
    //  }
}
