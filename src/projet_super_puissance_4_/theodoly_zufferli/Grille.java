/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_super_puissance_4_.theodoly_zufferli;

/**
 *
 * @author clara
 */
public class Grille {
    
    Cellule Cellules[][] = new Cellule[6][7] ;
    // se souvenir que la cellule[0][0] est en bas à gauche
    
    // on initialise la grille avec 42 cellules vides
    Grille(){
        for (int i = 0 ; i < 6 ; i++){
            for (int I = 0 ; I < 6 ; I++){
                Cellules[i][I] = new Cellule ();
            }
        }
    }
    
    Boolean colonne_remplie(int indice_colonne){
        return ( Cellules[5][indice_colonne].recupererJeton() != null ) ;
    }
    
    Boolean ajouterJetonDansColonne (Joueur joueurCourant, int indice_colonne){
        if (colonne_remplie(indice_colonne)) return false ;
        // si la colonne est remplie, on s'arrete et on retourne false
        int i = 0 ;
        while ( Cellules[i][indice_colonne].jetonCourant != null ){
            i++ ;
        }
        Jeton un_jeton = joueurCourant.retirerJeton();
        Cellules[i][indice_colonne].jetonCourant = un_jeton;
        if ( Cellules[i][indice_colonne].presenceDesintegrateur() ) {
            Cellules[i][indice_colonne].recupererDesintegrateur();
            joueurCourant.nombreDesintegrateurs ++ ;
        }
        if( Cellules[i][indice_colonne].presenceTrouNoir() ){
            Cellules[i][indice_colonne].activerTrouNoir();   
        }
        return true ;
     }  

    void activer_trounoir (int une_colonne){
        int i = 5 ;
        while (Cellules[i][une_colonne].jetonCourant == null) {
            i -- ;
            if (i == 0) {
                break ;
            }
        }
        if ( i >= 0 && i < 6) {
            Cellules[i][une_colonne].activerTrouNoir();
        }    
    }

    boolean etreRemplie (){
       int compteur = 0 ; 
       int i = 0 ;
       while ( i != 6 && colonne_remplie(i) ) {
           i ++ ;
       }
       return ( i == 6) ;
    }


    void viderGrille(){
        for (int i = 0 ; i < 6 ; i++){
            for (int I = 0 ; I < 7 ; I++){
                Cellules[i][I] = null ;  
            }
        }
    }

    public void afficherGrilleSurConsole(){
        for (int i = 0 ; i < 6 ; i++){
            for (int I = 0 ; I < 7 ; I++){
                System.out.println(Cellules[i][I]);
            }
        }
    }
    
    public boolean celluleOccupee (int indice_colonne , int indice_ligne ) {
        //
        //
        return true ;
    }
   
   String lireCouleurDuJeton (int indice_colonne , int indice_ligne ) {
    return jetonCourant.Couleur ;
}
}

