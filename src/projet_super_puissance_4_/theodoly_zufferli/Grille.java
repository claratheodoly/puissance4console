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

// CLASSE GRILLE : MODDELISE LA GRILLE DE JEU DE 6 LIGNES PAR 7 COLONNES
// CETTE GRILLE EST COMPOSEE D'UN TABLEAU DE CELLULES, CHAQUE CELLULE ETANT UN OBJET DE TYPE CELLULE
// LA CLASSE GRILLE POSSEDE L'ENSEMBLE DES METHODES PERMETTANT DE VERIFIER UN COUP PAR RAPPORT A L'ETAT DE LA GRILLE
// LA CLASSE GRILLE POSSEDE L'ENSEMBLE DES METHODES PERMETTANT D'INITIALISER LA GRILLE EN PLACANT DES DESINTEGRATUERS ET DES TROUS NOIRS


public class Grille {
    // attributs de la classe
    final int nombre_lignes = 6 ;
    final int nombre_colonnes = 7 ;
    Cellule Cellules[][] = new Cellule[6][7] ;
    // se souvenir que la cellule[0][0] est de coordonnées (0,0) et qu'elles est en bas à gauche de la grille. 
    // c'est l'origine de notre repère
    
    // on initialise la grille avec 42 cellules vides
    Grille(){
        for (int i = 0 ; i < 6 ; i++) { // i --> pour les lignes
            for (int j = 0 ; j < 7 ; j++) { // j --> pour les colonnes
                Cellules[i][j] = new Cellule () ; // chaque cellules est un objet de type Cellule
            }
        }
    }
    
    // ajoute le jeton dans la colonne ciblée, sur la cellule vide la plus basse. renvoie faux si la colonne était pleine
    // en paramètre on a jeton (jeton à ajouter) , le joueurCourant ( le joueur qui effectue l'action), j (indice de la colonne ciblée)
    Boolean ajouterJetonDansColonne (Jeton UN_jetonCourant, Joueur joueurCourant, int j){ // rappel : j = indice de la colonne
        if (colonne_remplie( j )) return false ; // si la colonne est remplie, on s'arrete et on retourne false
        int i = nombre_lignes -1 ;
        while ( celluleOccupee (i,j)){
            i -- ;
        }
        Cellules[i][j].jetonCourant = UN_jetonCourant ;
        
        // si il y a un trou noir au niveau du jeton qu'on ajoute, il faut activer ce trou noir
        if ( Cellules[i][j].presenceTrouNoir()) { // comme c'est un boolean : sur cette ligne c'est dans le cas ou presenceTrouNoir return vrai et qu'il y a donc bien un trou noir
             Cellules[i][j].activerTrouNoir () ; // activation du trou noir en rappelant la méthode activerTrouNoir ()
        }
        // ON FAIT LA MEME CHOSE POUR LES DESINTEGRATEURS
        
        // si il y a un desintegrateur au niveau du jeton qu'on ajoute, il faut le récuperer
        if ( Cellules[i][j].presenceDesintegrateur()) { // comme c'est un boolean : sur cette ligne c'est dans le cas ou presenceDesintegrateur return vrai et qu'il y a donc bien un Desintegrateur
             Cellules[i][j].recupererDesintegrateur () ; // on recupère le désintégrateur en rappelant la méthode recupererDesintegrateur qui avait été crée dans la classe Cellule
        }
        return true ; 
    }
    
    
    // renvoie vrai si la grille est pleine
    public boolean etreRemplie () {
        for ( int j = 0 ; j < nombre_colonnes ; j ++ ) {
            if ( ! colonne_remplie (j)) {
                return false ;
            }
        } return true ;
    }
    
    // la colonne est remplie si après avoir été tassée la cellule tout en haut est occupée
    Boolean colonne_remplie( int j ){
        return ( Cellules[5][j].recupererJeton() != null ) ;
        // ou
        // return celluleOccupee(0,j) ;
    }
    
    // fonction d'affichage de la grille sur la console. 
    // doit faire apparaitre les couleurs, les trous noirs et les desintégrateurs
    public void afficherGrillesurConsole () {
        // test pour toutes les cases de notre tableau
        String espace = " " ; // création d'une variable espace de type string, sera utilise pour espacer les chacunes des cellules lors de l'affichage
        for (int i = 0 ; i < 6 ; i++) { 
            for (int j = 0 ; j < 7 ; j++) {
                System.out.print (espace) ;
            
                if (Cellules[i][j].presenceTrouNoir() ){ // il y a un trou noir sur notre cellule
                    System.out.print ("T") ; // le trou noir sur notre grille apparaîtra comme un grand T
                }
                else if (Cellules[i][j].presenceDesintegrateur() ){ // il y a un desintegrateur sur notre cellule
                    System.out.print ("D") ; // le trou noir sur notre grille apparaîtra comme un grand D
                }
                else if (celluleOccupee (i,j) ){
                    
                }
            }
        }
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

