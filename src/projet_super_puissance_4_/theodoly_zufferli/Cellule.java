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

// CLASSE CELLULE : DECRIT UNE DES 42 CELLULES DE LA GRILLE. 
// C'EST DANS CETTE CELLULE QU'IL EST POSSIBLE DE PLACER UN JETON, UN DESNTEGRATEUR/ET/OU UN TROU NOIR
// CETTE CLASSE POSSEDE LES METHODES PERMETTANT LA GESTION DE CES ENTITES

public class Cellule {
    // attributs de notre classe
    Jeton jetonCourant ;
    boolean trouNoir ;
    boolean desintegrateur ;
    
    
    // constructeur de notre classe cellule initialisant les  valeurs par défauts et les attributs
     public Cellule () {
        trouNoir = false ; // aucun trou noir
        desintegrateur = false ; // Aucun désintégrateur
        jetonCourant = null; // Lors de la création de notre cellule il n'y a pas de jeton
        }
     
     // ajoute le jeton en paramètre à la cellule et retourne vrai si l'ajout s'est bien passé, ou faux sinon
    public boolean affecterJeton (Jeton UN_jetonCourant){ //Un_jetonCourant est le paramètre
         if (jetonCourant == null){ // dans le cas ou mon jeton n'est pas sur une cellule
            jetonCourant = UN_jetonCourant; // je lui affecte une cellule
            return true;
        }else{
            return false;
        }         
    } 
    
     // la méthode récupérerjeotn renvoie une référence vers le jetojn de la cellule
    public Jeton recupererJeton (){
         Jeton jetonRetour = jetonCourant ; // création d'un objet appelé jetonRetour auquel on affecte jetonCourant
         jetonCourant = null ;
         return jetonRetour;
     }
   
    // supprime le jeton et renvoie vrai si la supression s'est bien passée, ou faux autrement
    public boolean supprimerJeton(){
          if (jetonCourant == null ) {
            return false;
        }else{
            jetonCourant = null;
            return true;
        }
     }
    
    // TOUT CE QUI CONCERNE LES TROUS NOIRS
    
    // ajoute un trou noir à l'endroit indiqué et retorne vrai si l'ajout s'est bien passé, ou faux sinon
    public boolean placerTrouNoir (){
        if (trouNoir){// on est pas oblige d'écrire trouNoir == true car c'est un boolean
            return false ;
        }else{
            trouNoir = true ; 
            return true ;
        }
    }
    
    // renvoie vrai si un trou noir est présent sur la cellule
    public boolean presenceTrouNoir (){
        return trouNoir ;
    }
    
    // active le trou noir : le trou noir aspire le jeton et disparait.
    // retourne vrai si tout s'est correctement déroulé , ou faux sinon
    public boolean activerTrouNoir (){
        if (trouNoir){
            jetonCourant = null ;
            trouNoir = false ;
                System.out.println("Votre jeton vient d'être aspirer aspirer par le trou noir !");
            return true;
        }else{
            return false ;
        }
    }
    
    // TOUT CE QUI CONCERNE LES DESINTEGRATEURS
    
    // ajoute un désintégrateur à l'endroit indiqué et retourne vrai si l'ajout s'est bien passé, ou faux sinon
    public boolean placerDesintegrateur (){
        if (desintegrateur){
            return false ;
        }else{
            desintegrateur = true ;
            return true ;
        }
    }

    // vérifie la présence d'un désintégrateur sur la cellule
    public boolean presenceDesintegrateur (){
        return desintegrateur ;
     }
    
    
    //supprime le désintégrateur présent sur la cellule, et renvoie vrai , ou faux sinon 
    public boolean recupererDesintegrateur(){
        if ( presenceDesintegrateur() ){ // on teste la méthode presenceDesintégrateur
            desintegrateur = false;
                System.out.println(" Vous venez de récupérer le désintégrateur avec succès ! ");
            return true;
        }else{
            return false;
        }
    }
    

    
   
    // ou renvoit la couleur du jeton occupant la cellule
    public String lireCouleurDuJeton (){
        return jetonCourant.Couleur ;
    }
    
}    

