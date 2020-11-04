/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_super_puissance_4_.theodoly_zufferli;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author clara
 */
public class partie {
    // attributs de la classe partie
    Joueur Listejoueurs[] = new Joueur [2] ;
    Joueur JoueurCourant ;
    Grille grilleDeJeu = new Grille () ;
    
    
    void attribuerCoueleursAuxJoueurs () {
        Random x = new Random () ;
        boolean couleurDujoueur ;
        couleurDujoueur = x.nextBoolean () ;
        if (couleurDujoueur) {
            Listejoueurs [0].Couleur = "Rouge" ;
            Listejoueurs [1].Couleur = "Jaune" ;
        }else{
            Listejoueurs [0].Couleur = "Jaune" ;
            Listejoueurs [1].Couleur = "Rouge" ;
        }
    }
     
    Joueur ProchainJoueur (Joueur un_joueur) {
        if (Listejoueurs [0] == JoueurCourant) {
            return Listejoueurs [1] ;
        }
        return Listejoueurs [0] ;
    }
    
    void initialiserPartie (){
        grilleDeJeu.viderGrille ();
        
        // il faut créer nos deux joueurs
        Scanner sc = new Scanner (System.in) ; // utilisation de la focntion scanner pour permettre à 'lutilisateur de choisir les pseudos des joueurs
        System.out.println("choissisez le pseudo du premier Joueur : ") ; // fonction println pour afficher le message d'instructions
        Joueur UNJoueur = new Joueur (sc.nextLine()) ; // on crée le 1er joueur auquel on peut rentrer le pseudo
       
        System.out.println("choissisez le pseudo du deuxième Joueur : ") ; // message d'instructions pour rentrer le pseudo du 2e joueur
        Joueur AUTREJoueur = new Joueur (sc.nextLine()) ; // creation du 2e joueur auquel on rentre le second pseudo
        
        Listejoueurs [0] = UNJoueur ; // on affecte nos joueurs à la liste qu'on a crée pour les différencier
        Listejoueurs [1] = AUTREJoueur ;
        attribuerCouleursAuxJoueurs () ; // on appelle cette fonction
    
        // on affiche une phrase d'instruction permettant de donner le pseudo du joueur ainsi que la couleur qui lui est attribuée
        System.out.println ( UNJoueur.Nom + "a la couleur " + UNJoueur.Couleur) ; // pour le premier joueur
        System.out.println ( AUTREJoueur.Nom + "a la couleur " + AUTREJoueur.Couleur) ; // pour le second joueur
    
        // on distribue le nombre nécessaire (21 jetons) de jetons à chaque joueurs
        for ( int i = 0 ; i < 21 ; i++ ) {
            UNJoueur.ajouter_Jeton (new Jeton (UNJoueur.Couleur)) ;
        }   AUTREJoueur.ajouter_Jeton (new Jeton (AUTREJoueur.Couleur)) ;
        
        // test aléatoire qui permettra de choisir lequel des deux joueurs (UNJoueur ou AUTREJoueur) commencera par jouer et placer ses pions
        Random r = new Random () ; // fonction aléatoire
        boolean premierJoueur ;
        premierJoueur = r.nextBoolean () ;
        if ( premierJoueur ) { // on fait un if pour que que si premierjoueur == true  
            JoueurCourant = Listejoueurs [0] ; // UNJoueur deveint le joueur courant soit le 1er a jouer
        } else { // sinon
            JoueurCourant = Listejoueurs [1] ; // AUTREJoueur devient le JoueurCourant soit le 1er a jouer
        }
    
        // on initialise les Trous noirs aléatoirement qui sont au nombre de 5
        int compteur = 0 ; // initialisation ud compteur à 0
        for ( int i = 0 ; i < 5 ; i++ ) {
        int lignedutrounoir ; // initialisation de la variable lignetrounoir qui prendra comme valeur le numéro de la ligne sur laquelle se trouvera le trou noir
        int colonnedutrounoir ; // initialisation de la variable colonnetrounoir qui prendra comme valeur le numéro de la colonne sur laquelle se trouvera le trou noir
        lignedutrounoir = r.nextInt(6); // la ligne du trou noir est choisi aléatoirement entre la ligne 1 et la ligne 6
        colonnedutrounoir = r.nextInt(7); // la colonne du trou noir est choisi aléatoirement entre la colonne 1 et la colonne 7
            if (compteur < 2 ) {
                if ( !grilleDeJeu.placerDesintegrateur (lignedutrounoir , colonnedutrounoir) ) {
                    compteur -- ;
                }
                compteur ++ ;
            }
            if ( !grilleDeJeu.placerTrouNoir (lignedutrounoir , colonnedutrounoir) ) {
                i-- ;
            }
        } 
    
        // il reste donc plus qu'à placer trois désintégrateurs car 2 sont déja sur des trous noirs
        for ( int i = 0 ; i < 3 ; i ++ ) { // on fait tourner la boucle 2 fois pour bien avoir 2 désintégrateurs
            int lignedudesintegrateur ;
            int colonnedudesintegrateur ;
            lignedudesintegrateur = r.nextInt(6); // la ligne du desintegrateur est choisi aléatoirement entre la ligne 1 et la ligne 6
            colonnedudesintegrateur = r.nextInt(7); // la colonne du desintegrateur est choisi aléatoirement entre la colonne 1 et la colonne 7
        if ( !grilleDeJeu.placerDesintegrateur (lignedudesintegrateur , colonnedudesintegrateur) ) {
                i-- ;
            }
        }
        grilleDeJeu.afficherGrilleSurConsole () ; // une fois tous les trous noirs et désintégrateurs placés on re affiche la grille pour la mettre à jour
    }
    
    int option_joueur () {
        Scanner sc = new Scanner (System.in) ; // introdution de la fonction scanner pour intergair avec l'utilisateur (ou les utilisateurs)
        System.out.println (" Plusieurs options s'offrent à vous, que voulez-vous faire ?") ;
        System.out.println (" 1ère option : Vous voulez jouer un jeton") ;
        System.out.println (" 2ème option : Vous voulez récupérer un jeton") ;
        System.out.println (" 3ème option : Vous voulez utiliser un désintégrateur et désintegrer un jeton") ;
        int optionchoisi = sc.nextInt() ;
        while ( optionchoisi < 1 || optionchoisi > 3 ) {
             System.out.println (" OUPS... il semblerait que vous ayez choisit une option qui n'existe pas :/ ") ;
             System.out.println (" Veuillez entrer une option entre la 1, la 2 et la 3 : ") ;
             optionchoisi = sc.nextInt() ; // l'utilisateur peut rentrer une option qui cette fois existe
        }
       return optionchoisi ; // la méthode menu_joueur retourne le numéro de l'option choisi dans la variable optionchoisi
    }
    
    void jouerJeton () {
        Scanner sc = new Scanner (System.in) ; // introdution de la fonction scanner pour intergair avec l'utilisateur (ou les utilisateurs)
        boolean jetonjouee ; // initialisation d'un boolean appelé jetonjouee
        System.out.println (" Saisissez la colonne dans laquelle vous voulez jouer votre jeton") ;
        int colonnejeton = sc.nextInt (); // l'utilisateur écrit le numéro de la colone dans laquelle il veut jouer son jeton
        colonnejeton = colonnejeton - 1 ; // on enlève un entier au numéro que l'utilisateur à entrer car rappelons que les colonnes de notre tableau commmencent toujours à 0
        while ( colonnejeton < 0 || colonnejeton > 6 ){
            System.out.println (" Mince... il semblerait que vous ayez choisit un numéro de colonne qui n'existe pas") ;
             System.out.println (" Veuillez entrer un numéro de colonne qui existe : ") ;
             colonnejeton = sc.nextInt () - 1 ;
        }
        
        jetonjouee = grilleDeJeu.ajouterJetonDansColonne(JoueurCourant, colonnejeton) ;
        while ( !jetonjouee ) {
            System.out.println ( " Aïe aïe aïe, la colonne dans laquelle vous voulez jouer votre jeton est déjà pleine ....") ;
            System.out.println ( " Veuillez saisir une autre colonne qui n'est pas encore remplie : " ) ;
            colonnejeton = sc.nextInt () - 1 ;
            jetonjouee = grilleDeJeu.ajouterJetonDansColonne ( JoueurCourant , colonnejeton ) ; // on ajoute le jeton jouée dans la grille de jeu
       }
    }
    
    boolean recup_jeton () {
        int colonnejeton ;
        int lignejeton ; 
        Scanner sc = new Scanner (System.in) ;
        System.out.println ( " Veuillez saisir le numéro de la colonne du jeton que vous voulez récupérer : ") ;
        colonnejeton = sc.nextInt () - 1 ;
         while ( colonnejeton < 0 || colonnejeton > 6 ){
            System.out.println (" Mince... il semblerait que vous ayez choisit un numéro de colonne qui n'existe pas") ;
             System.out.println (" Veuillez entrer un numéro de colonne qui existe : ") ;
             colonnejeton = sc.nextInt () - 1 ;
        }
        System.out.println ( "Maintenant que nous avons le numéro de la colonne, veuillez entrer le numéro de la ligne du jeton que vous voulez récupérer : ") ;
        lignejeton = sc.nextInt () - 1 ;
        while ( colonnejeton < 0 || colonnejeton > 5 ){ //  on change bien le 6 en 5 car on a affaire avec les lignes et non plus les colonnes
            System.out.println (" Zut... il semblerait que vous ayez choisit un numéro de ligne qui n'existe pas") ;
             System.out.println (" Veuillez entrer un numéro de ligne qui existe : ") ;
             colonnejeton = sc.nextInt () - 1 ;
        }
        if ( grilleDeJeu.Cellules[lignejeton][colonnejeton].jetonCourant != null && grilleDeJeu.Cellules[lignejeton][colonnejeton].lireCouleur == JoueurCourant.lireCouleur) { // Gros souci ici
            JoueurCourant.ajouterJeton (grilleDeJeu.recupererJeton(lignejeton, colonnejeton)) ;
            grilleDeJeu.tasserGrille( lignejeton , colonnejeton ) ;
            return true ;
        } else {
            return false ;
        }
    }
    
    void debuterPartie () ;
    
     // 3eme commentaire
    
     
}
