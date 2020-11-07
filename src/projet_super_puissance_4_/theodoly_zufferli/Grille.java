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
     Cellule[][] Cellules ;
    // se souvenir que la cellule[0][0] est de coordonnées (0,0) et qu'elles est en bas à gauche de la grille. 
    // c'est l'origine de notre repère
    
    
    // on initialise la grille avec 42 cellules vides
   public void Grille() {
       Cellules = new Cellule[6][7] ;
        for (int i = 0 ; i < 6 ; i++) { // i --> pour les lignes
            for (int j = 0 ; j < 7 ; j++) { // j --> pour les colonnes
                Cellules[i][j] = new Cellule () ; // chaque cellules est un objet de type Cellule
            }
        }
    }   
   
   
  
    // ajoute le jeton dans la colonne ciblée, sur la cellule vide la plus basse. renvoie faux si la colonne était pleine
    // en paramètre on a jeton (jeton à ajouter) , le joueurCourant ( le joueur qui effectue l'action), j (indice de la colonne ciblée)
    Boolean ajouterJetonDansColonne (Joueur JoueurCourant, int j){ // rappel : j = indice de la colonne
        if (colonne_remplie( j )) return false ; // si la colonne est remplie, on s'arrete et on retourne false
        int i = 0 ;
        while ( Cellules[i][j].jetonCourant != null){
            i ++ ;
        }
        Jeton UN_jetonCourant = JoueurCourant.retirerJeton () ;
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
        return (Cellules[5][j].recupererJeton () != null ) ; // la cellule ou i = 5 est la cellule la plus haut de la grille
    } // retourne vrai si la colonne d'indice j est complète
    
    
    
   /* // vide la grille de tous les jetons en les supprimant et retire aussi les trous noirs et les désintégrateurs
     public void viderGrille(){
        for (int i = 0 ; i < 6 ; i++){
            for (int j = 0 ; j < 7 ; j++){
                Cellules[i][j].supprimerJeton() ;  // on supprimes les jetons
                Cellules[i][j].trouNoir = false ; // on fait disparaître le trou noir
                Cellules[i][j].desintegrateur = false ;  // on fait également disparaître les désinétgrateurs
            }
        }
    } */  
     
    public void viderGrille(){
        for (int i = 0 ; i < 6 ; i++){
            for (int j = 0 ; j < 7 ; j++){
                Cellules[i][j] = new Cellule () ;  // on supprimes les jetons
                
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
        if (Cellules[i][j] != null) {
            Cellules[i][j] = new Cellule();
        } if (! Cellules[i][j].presenceTrouNoir() ) { // il y a un trou noir 
            Cellules[i][j].trouNoir = true ; // on ajoute le trou noir
            return true; // on retourne vrai lorsque l'ajout s'est bien passée
        }
        return false;
    }
    
    
    // ajoute un desintegrateur à l'endroit indiqué et retourne vrai si l'ajout s'est bien passé, ou faux sinon
    public boolean placerDesintegrateur ( int i , int j ) { // prends en paramètres les coordonnées
        if (Cellules[i][j] != null) {
            Cellules[i][j] = new Cellule() ;
        } if (! Cellules[i][j].presenceDesintegrateur () ) { // il y a un desintegrateur 
            Cellules[i][j].desintegrateur = true ; // on ajoute le desintegrateur
            return true; // on retourne vrai lorsque l'ajout s'est bien passée
        }
        return false ;
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
    return Cellules[i][j].lireCouleurDuJeton () ;
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
        //initialisation des couleurs 
        String jaune = "\033[93m" ;
        String rouge = "\033[91m" ; 
        String noir = "\033[0m" ; // aucune couleur
        
        for (int i = 0 ; i < 6 ; i++) { 
            for (int j = 0 ; j < 7 ; j++) {
                
                if (Cellules[i][j].jetonCourant != null ){
                    System.out.println("J");
                } 
                
                else if (Cellules[i][j].presenceTrouNoir() ){ // il y a un trou noir sur notre cellule
                    System.out.print ("T") ; // le trou noir sur notre grille apparaîtra comme un grand T
                }
                
                else if (Cellules[i][j].presenceDesintegrateur() ){ // il y a un desintegrateur sur notre cellule
                    System.out.print ("D") ; // le trou noir sur notre grille apparaîtra comme un grand D
                } 
                
                else {
                    System.out.print("X");
                }
                
            }
        System.out.print("\n") ; // on revient a la ligne pour la ligne suivante
        }
    }
    
    // renvoie vrai si la grille est gagnate pour le joueur passé en paramètr, c'est à dire que 4 pions de sa couleur sont alignés 
    // EN LIGNE
    // EN COLONNE
    // EN DIAGONALE DESCENDANTE 
    // EN DIAGONALE MONTANTE
    public boolean etreGagnantePourJoueur ( Joueur jetonCourant ) {
    
        // TEST SUR LES LIGNES SI 4 JETONS SONT ALIGNES HORIZONTALEMENT
        // pour ce test on sait qu'on pourra se réduire aux 4 colones de gauches car il n'y aura pas assez de place pour fair un aligment de 4 jetons
        // si le premier jeton se trouvant le plus à gauche se situe sur la colonne 5
        for ( int i = 0 ; i < 6 ; i++ ) { // on prends en compte bien chacuneS des lignes
            for (int j = 0 ; j < 4 ; j++ ){ // 7-3 = 4
                if ( Cellules[i][j].lireCouleurDuJeton () == jetonCourant.Couleur ) { // pour comparer des strings on peut utiliser '==' ou '!='
                    if ( Cellules[i][j+1].lireCouleurDuJeton () == jetonCourant.Couleur ) {
                        if ( Cellules[i][j+2].lireCouleurDuJeton () == jetonCourant.Couleur ) {
                            if ( Cellules[i][j+3].lireCouleurDuJeton () == jetonCourant.Couleur ) {
                                return true ;
                            }
                        }
                    }
                }
            }
        }
        
        // TEST SUR LES COLONNES SI 4 JETONS SONT ALIGNES VERTICALEMENT
        // pour ce test on sait qu'on pourra se réduire aux 3 lignes du bas car il n'y aura pas assez de place pour faire  un alignement de 4 jetons verticalement sinon
        // si le premier jeton se trouvant le plus bas par rapport aux 3 autres
        for ( int j = 0 ; j < 7 ; j++ ) { // on prends en compte bien chacuneS des lignes se situe sur la ligne 4 , le joueur ne pourra pas faire un aligenment vertical de 4 jetons il devra s'arréter a 3 (et perdre)
            for (int i = 0 ; j < 3 ; i++ ){ // 6-3 = 3
                if ( Cellules[i][j].lireCouleurDuJeton () == jetonCourant.Couleur ) { // pour comparer des strings on peut utiliser '==' ou '!='
                    if ( Cellules[i+1][j].lireCouleurDuJeton () == jetonCourant.Couleur ) { 
                        if ( Cellules[i+2][j].lireCouleurDuJeton () == jetonCourant.Couleur ) {
                            if ( Cellules[i+3][j].lireCouleurDuJeton () == jetonCourant.Couleur ) {
                                return true ;
                            }
                        }
                    }
                }
            }
        }
    
    
        // TEST SUR LES DIAGONALES DESCENDANTE : ON CONSIDERE QUE LE 1ER JETON EST LE JETON LE PLUS HAUT  A GAUCHE ET QUE LE QUATRIEME JETON EST LE PLUS BAS 3 COLONNES DECALEES SUR LA DROITE ET 3 LIGNES EN DESSOUS
        // ON PEUT DONC SUPPRIMER LES 3 COLONNES LES PLUS A DROITE AINSI QUE LES LES 3 LIGNES DU BAS
        for ( int i = 0 ; i < 3 ; i++ ) { // on prend en compte que les 3 lignes du haut 
            for (int j = 0 ; j < 4 ; j++ ){ // on prend en compte que les 4 colonnes les plus à gauches 
                if ( Cellules[i][j].lireCouleurDuJeton () == jetonCourant.Couleur ) { // pour comparer des strings on peut utiliser '==' ou '!='
                    if ( Cellules[i+1][j+1].lireCouleurDuJeton () == jetonCourant.Couleur ) { 
                        if ( Cellules[i+2][j+2].lireCouleurDuJeton () == jetonCourant.Couleur ) {
                            if ( Cellules[i+3][j+3].lireCouleurDuJeton () == jetonCourant.Couleur ) {
                                return true ;
                            }
                        }
                    }
                }
            }
        }
        
        // TEST SUR LES DIAGONALES MONTANTES : ON CONSIDERE QUE LE 1ER JETON EST LE JETON LE PLUS HAUT DES 4 ET QUE C'EST AUSSI LE PLUS A DROITE ET QUE LE QUATRIEME JETON EST LE PLUS BAS ET QU'IL SE TROUVE 3 COLONNES DECALEES SUR LA GAUCHE ET 3 LIGNES EN DESSOUS PAR RAPPORT AU PREMIER JETON
        // ON PEUT DONC SUPPRIMER LES 3 PREMIERES COLONNES LES PLUS A DROITE AINSI QUE LES LES 3 LIGNES DU BAS
        for ( int i = 3 ; i < 6 ; i++ ) { // on prend en compte que les 3 lignes du haut 
            for (int j = 0 ; j < 4 ; j++ ){ // on prend en compte que les 4 colonnes les plus à gauches 
                if ( Cellules[i][j].lireCouleurDuJeton () == jetonCourant.Couleur ) { // pour comparer des strings on peut utiliser '==' ou '!='
                    if ( Cellules[i-1][j+1].lireCouleurDuJeton () == jetonCourant.Couleur ) { 
                        if ( Cellules[i-2][j+2].lireCouleurDuJeton () == jetonCourant.Couleur ) {
                            if ( Cellules[i-3][j+3].lireCouleurDuJeton () == jetonCourant.Couleur ) {
                                return true ;
                            }
                        }
                    }
                }
            }
        }
         return false ; // si aucune de ces conditions est remplis alors il n'y a pas de combinaisons gagnantes
}
    
}

