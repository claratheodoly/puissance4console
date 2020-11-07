/*
 * super puissance 4
 * Clara THEODOLY
 * Chloé ZUFFERLI
 */
package projet_super_puissance_4_.theodoly_zufferli;

import java.util.Random; // se fait automatiquement pour faire fonctionner ces deux focntions
import java.util.Scanner;

/**
  * @author clara
 */
 
// LA CLASSE PARTIE LIE LES OBJETX ENTRES-EUX
// ELLE CONTIENT 2 JOUEURS QUI S'AFFRONTENT  ET UNE GRILLE DE JEU
// ELLE POSSEDE L'ENSEMBLE DES METHODES PERMETTANT DE VERIFIER SI LE COUP D'UN JOUEUR EST VALIDE; ET S4APPUIE NOTAMMENT SUR LES METHODES PROPOSEES PAR LA CLASSE GRILLE
// ELLE PERMET AUSSI DE LANCER LA PARTIE APRES AVOIR INITIALISE LE GRILLE ET AUTRES ELEMENTS


public class partie {
    // attributs de la classe partie
    Joueur Listejoueurs[] = new Joueur [2] ;
    Joueur JoueurCourant ;
    Grille grilleDeJeu = new Grille () ;
    
    
    public void attribuerCoueleursAuxJoueurs () {
        
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
        
        grilleDeJeu.viderGrille (); // il est important de toujours bien vider la grille avant de commencer
        
        // il faut créer nos deux joueurs
        Scanner sc = new Scanner (System.in) ; // utilisation de la focntion scanner pour permettre à 'lutilisateur de choisir les pseudos des joueurs
        System.out.println("choissisez le pseudo du premier Joueur : ") ; // fonction println pour afficher le message d'instructions
        Joueur UNJoueur = new Joueur (sc.nextLine()) ; // on crée le 1er joueur auquel on peut rentrer le pseudo
        Listejoueurs [0] = UNJoueur ;
        
        System.out.println("choissisez le pseudo du deuxième Joueur : ") ; // message d'instructions pour rentrer le pseudo du 2e joueur
        Joueur AUTREJoueur = new Joueur (sc.nextLine()) ; // creation du 2e joueur auquel on rentre le second pseudo
        Listejoueurs [1] = AUTREJoueur ;
        
        attribuerCoueleursAuxJoueurs () ; // on appelle la fonction attribuer couleurs aux joueurs
    
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
        grilleDeJeu.afficherGrillesurConsole () ; // une fois tous les trous noirs et désintégrateurs placés on re affiche la grille pour la mettre à jour
    }
    
    int option_joueur () {
        // on crée un menu de diverses options pour laisser choisir au joueur ce qu'il souhaite faire
        // tout d'abord on présente à l'écran les possibilités qui s'offrent à lui
        Scanner sc = new Scanner (System.in) ; // introdution de la fonction scanner pour intergair avec l'utilisateur (ou les utilisateurs)
        System.out.println (" Plusieurs options s'offrent à vous, que voulez-vous faire ?") ;
        System.out.println (" 1ère option : Vous voulez jouer un jeton") ;
        System.out.println (" 2ème option : Vous voulez récupérer un jeton") ;
        System.out.println (" 3ème option : Vous voulez utiliser un désintégrateur et désintegrer un jeton") ;
        System.out.println (" Pour choisir correctement l'option que vous souhaitez, veuillez entrer soit 1 pour la 1ère option, ou 2, ou encore 3. ") ;
        
        // maintenant on lui peermet de choisir l'option qu'il souhaite en rentrant un chiffre
        int optionchoisi = sc.nextInt() ;
        
        // création d'un switch/case pour faire intervenir les différentes méthodes en fonction du choix du joueur
        switch (optionchoisi) { // les différents "cas", c'est-à-dire a réponse choisie par le joueur
            // le joueurCourant a choisi de jouer un jeton en rentrant 1
            case 1:
               jouerJeton (); // effectuer la méthode jouerJeton
               break;  // Utilisation du break pour mettre fin au programme
            // le joueurCourant a choisi de récupérer un jeton en rentrant 2
            case 2:
                recuperer_jeton ();
                break;
            // le joueurCourant a choisi de désintégrer un jeton en rentrant 3
            case 3:
                desintegrer_jeton ();
                break;
            // le joueurCourant a choisi une muavaise option : une option qu n'existe pas et qui n'est pas reconnue
            default: 
            System.out.println (" OUPS... il semblerait que vous ayez choisit une option qui n'existe pas :/ ") ;
            System.out.println (" Veuillez entrer une option entre la 1, la 2 et la 3 : ") ;
            optionchoisi = sc.nextInt() ; // l'utilisateur peut rentrer une option qui cette fois existe
                break;
        }
        return optionchoisi ; // la méthode menu_joueur retourne le numéro de l'option choisi dans la variable optionchoisi
    }
              
       // while ( optionchoisi < 1 || optionchoisi > 3 ) {
        //     System.out.println (" OUPS... il semblerait que vous ayez choisit une option qui n'existe pas :/ ") ;
        //     System.out.println (" Veuillez entrer une option entre la 1, la 2 et la 3 : ") ;
        //     optionchoisi = sc.nextInt() ; // l'utilisateur peut rentrer une option qui cette fois existe
        // }
       //return optionchoisi ; // la méthode menu_joueur retourne le numéro de l'option choisi dans la variable optionchoisi
    
    // méthode permettant de jouer le jeton
    public boolean jouerJeton () {
        
        Scanner sc = new Scanner (System.in) ; // introdution de la fonction scanner pour intergair avec l'utilisateur (ou les utilisateurs)
        
        Jeton jetonajouer ; // initialisation de jetonajouer de type jeton
        boolean jetonjouee ; // initialisation d'un boolean appelé jetonjouee 
        boolean partieremportee ; // initialisation d'un boolean partieremportée pour voir si il y a un vaiqueur
        
        System.out.println (" Saisissez la colonne dans laquelle vous voulez jouer votre jeton") ;
        int colonnejeton = sc.nextInt () -1 ; // l'utilisateur écrit le numéro de la colone dans laquelle il veut jouer son jeton ET on enlève 1 au numéro que l'utilisateur à entrer car rappelons que les colonnes de notre tableau commmencent toujours à 0
        
        if ( colonnejeton  > 6 ){
                System.out.println (" Mince... il semblerait que vous ayez choisit un numéro de colonne qui n'existe pas") ;
                System.out.println (" Veuillez entrer un numéro de colonne qui existe : ") ;
                colonnejeton = sc.nextInt () - 1 ;
            } else if (JoueurCourant.nombreJetonsRestants == 0) { // le jouer n'a plus de jeton à jouer (mais normalement la grille est déja plein et le jue s'arrête)
                System.out.println("Il semblerait que vous n'ayez plus de jetons. ") ;
        } else {
                jetonajouer = JoueurCourant.ListeJetons[JoueurCourant.nombreJetonsRestants -1] ;
                jetonjouee = grilleDeJeu.ajouterJetonDansColonne(jetonajouer, JoueurCourant, colonnejeton) ;
            if ( ! jetonjouee ) {
                System.out.println ( " Aïe aïe aïe, la colonne dans laquelle vous voulez jouer votre jeton est déjà pleine ....") ;
                System.out.println ( " C'était une erreure fatale, vous venez de perdre instantanément votre jeton, passez votre tour et faite plus attention ! " ) ;
                return false ;
            } else { 
                JoueurCourant.retirerJeton () ;
                grilleDeJeu.tasserGrille(colonnejeton);
                grilleDeJeu.afficherGrillesurConsole ();
                partieremportee = grilleDeJeu.etreGagnantePourJoueur(JoueurCourant);
                if ( partieremportee ) { // c'est à dire qu'il  == à true
                        System.out.println ("BRAVO " +JoueurCourant.Nom+ "! Vous avez remporté la partie !");
                }
                return partieremportee ;
            }
        } 
    return true ;
    }
    
    
    boolean recuperer_jeton () {
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
       if ( grilleDeJeu.Cellules[lignejeton][colonnejeton].jetonCourant != null && grilleDeJeu.Cellules[lignejeton][colonnejeton].lireCouleurDuJeton() == JoueurCourant.Couleur ) { 
           JoueurCourant.ajouter_Jeton( grilleDeJeu.recupererJeton(lignejeton, colonnejeton)) ;
           grilleDeJeu.tasserGrille(colonnejeton) ;
           return true;
       } else {
           return false ;
       }
    }
            
  
    public boolean desintegrer_jeton () {
        boolean partieremp ;
        
    if(JoueurCourant.nombreDesintegrateurs == 0) {
            return false;
        }
            int lignejeton;
            int colonnejeton;
             Scanner sc = new Scanner(System.in);
        System.out.println("Quelle est la ligne du jeton à désintégrer ? ");
        System.out.println("Quelle est la colonne du jeton à désintégrer ? ");
        
        lignejeton = sc.nextInt() - 1;
        while (lignejeton>5 || lignejeton<0 ) {
            System.out.println("Choix non valide");
        }
        
        colonnejeton = sc.nextInt() - 1;
        while (colonnejeton>6 || lignejeton<0) {
            System.out.println("Choix non valide");
        }
        if (grilleDeJeu.Cellules[lignejeton][colonnejeton].jetonCourant != null &&  grilleDeJeu.Cellules[lignejeton][colonnejeton].lireCouleurDuJeton() == JoueurCourant.Couleur ) { 
            grilleDeJeu.supprimerJeton (lignejeton, colonnejeton) ;
            JoueurCourant.utiliserDesintegrateur() ;
            grilleDeJeu.tasserGrille(colonnejeton) ;
            grilleDeJeu.afficherGrillesurConsole() ;
            partieremp = grilleDeJeu.etreGagnantePourJoueur(JoueurCourant);
            
            return true;    
        } else {
            return false;
        }
    }
    public void debuterPartie () {
        
        Random r = new Random () ;
        boolean partiefinie ;
        partiefinie = false ;
        int alea ;
        
        attribuerCoueleursAuxJoueurs();
        initialiserPartie();
           
        alea = r.nextInt(2) ;
        JoueurCourant = Listejoueurs[alea] ;
        

    }
    
}


