/*
 * super puissance 4
 * Clara THEODOLY
  * Chloé ZUFFERLI
 */
package projet_super_puissance_4_.theodoly_zufferli;

/**
 *
 * @author clara
 */
public class Cellule {
    Jeton jetonCourant ;
    boolean trouNoir ;
    boolean desintegrateur ;
    // attributs de notre classe
    
    // constructeur de notre classe cellule
     public Cellule () {
        trouNoir = false ; // aucun trou noir
        desintegrateur = false ; // Aucun désintégrateur
        jetonCourant = null; // Lors de la création de notre cellule il n'y a pas de jeton
            //constructeur de notre classe
        }
     
    boolean affecterJeton (Jeton UN_jetonCourant){ //Un_jetonCourant est le paramètre
         if (jetonCourant == null){ // dans le cas ou mon jeton n'est pas sur une cellule
            jetonCourant = UN_jetonCourant; // je lui affecte une cellule
            return true;
        }else{
            return false;
        }         
    } 
     // la méthode récupererJeton permet de récupérer le jeton de la cellule
     // si il n'y avait pas de jeton elle renvoit juste null
    Jeton recupererJeton (){
         Jeton jetonRetour = jetonCourant ; // création d'un objet appelé jetonRetour auquel on affecte jetonCourant
         jetonCourant = null ;
         return jetonRetour;
     }
   
    
    Boolean supprimerJeton(){
          if (jetonCourant != null ){
          jetonCourant = null;
            return true;
        }else{
            return false;
        }
     }
    Boolean placerTrouNoir (){
        if (trouNoir){// on est pas oblige d'écrire trouNoir == true car c'est un boolean
            return false ;
        }else{
            trouNoir = true ;
            return true ;
        }
    }
    Boolean presenceTrouNoir (){
        return trouNoir ;
    }
     
    Boolean activerTrouNoir (){
        if (trouNoir){
            jetonCourant = null ;
            trouNoir = false ;
            System.out.println("Votre pion vient d'être aspirer aspirer par le trou noir !");
            return true;
        }else{
            return false ;
        }
    }
    
    
    Boolean placerDesintegrateur (){
        if (desintegrateur){
            return false ;
        }else{
            desintegrateur = true ;
            return true ;
        }
    }

    Boolean presenceDesintegrateur (){
        return desintegrateur ;
     }
    
    // soit renvoit le mot "rien"
    // ou renvoit la couleur du jeton qui a été affecté à la cellule
    String lireCouleurDuJeton (){
        if (jetonCourant == null){
            return "rien" ;
        }else{
            return jetonCourant.Couleur ;
    }
  
    }
    Boolean recupererDesintegrateur(){
        if ( presenceDesintegrateur() ){
            desintegrateur = false;
            return true;
        }else{
            return false;
        }
    }
  
    
    
    
    //ouille
}    
