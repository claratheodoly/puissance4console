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
   public void Grille() {
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
    public boolean colonne_remplie( int j ){
        if ( Cellules [5][j]. recupererJeton () == null ) { // la colonne n'est pas remplie car la cellule du haut est vide
                return false ; // on retourne false
        }else{
            return true ; // notre colonne est bien remplie
        }
    }
    
    
    // vide la grille de tous les jetons en les supprimant et retire aussi les trous noirs et les désintégrateurs
    public void viderGrille(){
        for (int i = 0 ; i < 6 ; i++){
            for (int j = 0 ; j < 7 ; j++){
                Cellules[i][j].supprimerJeton() ;  // on supprimes les jetons
                Cellules[i][j].trouNoir = false ; // on fait disparaître le trou noir
                Cellules[i][j].desintegrateur = false ;  // on fait également disparaître les désinétgrateurs
            }
        }
    }
    
    // renvoie vrai si la cellule de coordonnées données est occupée par un jeton
    public boolean celluleOccupee (int i , int j ) {
        if ( Cellules[i][j].recupererJeton() == null ) { // cellule vide
        return false ; // on retourne faux
    }else{ //cellule occupée
            return true ; // on retourne vrai
        }
    }
    
    
    // ajoute un trou noir à l'endroit indiqué et retourne vrai si l'ajout s'est bien passé, ou faux sinon
    public boolean placerTrouNoir ( int i , int j ) { // prends en paramètres les coordonnées
        if (! Cellules[i][j].presenceTrouNoir() ) { // il y a un trou noir 
            Cellules[i][j].trouNoir = true ; // on ajoute le trou noir
            return true; // on retourne vrai lorsque l'ajout s'est bien passée
        }else{
            return false;
        }
    }
    
    
    // ajoute un desintegrateur à l'endroit indiqué et retourne vrai si l'ajout s'est bien passé, ou faux sinon
    public boolean placerDesintegrateur ( int i , int j ) { // prends en paramètres les coordonnées
        if (! Cellules[i][j].presenceDesintegrateur () ) { // il y a un desintegrateur 
            Cellules[i][j].desintegrateur = true ; // on ajoute le desintegrateur
            return true; // on retourne vrai lorsque l'ajout s'est bien passée
        }else{
            return false;
        }
    }
    
    
    // supprime le jeton de la cellule visée. renvoie vrai si la supression s'est bien déroulée, ou faux autrement
    public boolean supprimerJeton ( int i, int j ) { // prends en paramètres les coordonnées du jeton
        if ( celluleOccupee ( i , j) ) { // celluleOccupee est un booléen donc ici = true
            Cellules[i][j].supprimerJeton() ; // on supprime le jeton présent dans la cellule
            return true ; // on retourne vrai car la supression s'est bien passée
        } else { // dans le cas ou celluleOccupee = false c'est a dire qu'il n'y a pas de jeton sur la cellulle 
            return false ; // on retourne faux car il y a rien a supprimer
        }
    }
    
    
    //  enlève le jeton de la cellule visée et renvoie une référence vers ce jeton
    public Jeton recupererJeton ( int i, int j ) { // prends en paramètres les coordonnées du jeton
        return Cellules [i][j].recupererJeton () ;
    }
   
    
    // renvoie la couleur du jeton de la cellule ciblée
    public String lireCouleurDuJeton ( int i , int j ) {
    return Cellules[i][j]. recupererJeton() . lireCouleur () ;
    }
    
    
    // lorsqu'un jeton est capturé ou détruit, tasse la grille en décalant de une ligne les jetons situés au dessus de la cellule libérée
   public void tasserGrille ( int j ) { 
       for ( int i = 5 ; i >= 1 ; i -- ) { // j --  car on part du bas de la grille, à la ligne 5 (à cause de l'origine du repère qui est tout en bas) le j>=1 repésente le : "ne pas être sur la dernière ligne"
           if ( Cellules[i][j].jetonCourant == null ) {
               Cellules [i][j].jetonCourant = Cellules [ i -1][j].jetonCourant ; //on fait descendre le jeton en enlevant 1 à son indice de ligne
               Cellules [ i -1][j].jetonCourant = null ; // on le passe a null car comme le jeton est tombé il ne faut plus qu'il soit dans l'ancienne cellule
            } //  si on ne fait la ligne 154 le jeton serait dans les 2 cellules celle du haut et celle du bas
        }
    }
    
    // fonction d'affichage de la grille sur la console. 
    // doit faire apparaitre les couleurs, les trous noirs et les desintégrateurs
    public void afficherGrillesurConsole () {
        // test pour toutes les cases de notre tableau
        String espace = " " ; // création d'une variable espace de type string, sera utilise pour espacer les chacunes des cellules lors de l'affichage
        //initialisation des couleurs 
        String jaune = "\033[93m" ;
        String rouge = "\033[91m" ; 
        String noir = "\033[0m" ; // aucune couleur
        
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
                    switch ( Cellules[i][j].lireCouleurDuJeton ()) { // il y a un jeton sur la cellule
                        case "\033[93mjaune\033[0m" : // ce jeton est jaune
                            System.out.print ( jaune+ "J" +noir ) ; // on fait juste un print sans ln pour rester sur la meme ligne : on veut que le "J" apparaisse en jaune
                            break ;
                    case "\033[91mrouge\033[0m": // le jeton présent sur la cellule est rouge
                        System.out.print ( rouge+ "J" +noir ) ; // le "J" de jeton apparaitra en rouge
                    }   
                }
            }
        System.out.println (" ") ; // on revient a la ligne pour la ligne suivante
        }
    
    
    
    
    
    
    }
    
    
    
    
 
   
}

