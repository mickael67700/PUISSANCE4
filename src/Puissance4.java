import java.io.IOException;
import java.util.*;

class Puissance4 extends Exception {
    final static int largeur = 7;
    final static int hauteur = 6;
    final static int empty= 0; // case vide
    static int numcolonne;
    static int numligne;
    static int noJoueur;
    private static boolean inputNotNull = true;
    private static int choixJoueur;
    private static boolean finDePartie = false;
    private static int compteurJoueur1; // on teste si gagnant à partir du 4ème coup
    private static int compteurJoeur2; // on teste si gagnent à partir du 4ème coup
    private static int[][] matrice;
    private int x;
    private int y;

    // Ghetters et Setters
    public static int getNumcolonne() {return numcolonne;}

    public static void setNumcolonne(int numcolonne) {
        Puissance4.numcolonne = numcolonne; }

    public static int getNumligne() { return numligne;}

    public static void setNumligne(int numligne) {
        Puissance4.numligne = numligne;
    }

    public static int getNoJoueur() { return noJoueur;}

    public static void setNoJoueur(int noJoueur) {
        Puissance4.noJoueur = noJoueur;
    }

   public static int getChoixJoueur() { return choixJoueur; }

    public static void setChoixJoueur(int choixJoueur) {
        Puissance4.choixJoueur = choixJoueur;
    }

    public static boolean isInputNotNull() { return inputNotNull; }

    public static void setInputNotNull(boolean inputNotNull) {
        Puissance4.inputNotNull = inputNotNull; }

    public static boolean isFinDePartie() { return finDePartie; }

    public static void setFinDePartie(boolean finDePartie) {
       Puissance4.finDePartie = finDePartie; }

    public static int getCompteurJoueur1() { return compteurJoueur1; }

    public static void setCompteurJoueur1(int compteurJoueur1) {
       Puissance4.compteurJoueur1 = compteurJoueur1; }

    public static int getCompteurJoeur2() {
        return compteurJoeur2;
    }

    public static void setCompteurJoeur2(int compteurJoeur2) {
        Puissance4.compteurJoeur2 = compteurJoeur2;
    }

    static void effacerConsole() {
        System.out.print("\033[H\033[2J"); // effacer la Console dans Repl.it
    }

    public void Plateau(int[][] matrice) {
        this.matrice = matrice;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    boolean estLibre(int numcolonne){
        return matrice[numcolonne][hauteur] == 0;
    }
    public static void choix() throws ColonneException {
         /*
         Etape 2 : connaître la colonne choisie par le joueur
        On inclut dans un bloc try les instructions qui risquent de déclencher une éventuelle
        exception.
         */
        Puissance4.setInputNotNull(true);
        while (inputNotNull) {
            try {
                Puissance4.setChoixJoueur(new Scanner(System.in).nextInt());
            } catch (InputMismatchException e) {
                System.err.println("Mauvaise entrée!\nSeulement un entier entre 1 et 7\n Réessayer :");
                Puissance4.setChoixJoueur(new Scanner(System.in).nextInt());
            }
            System.out.println(Puissance4.getChoixJoueur());
            if (Puissance4.getChoixJoueur() > 7) throw new ColonneException();
            else {
                System.out.printf("Choix colonne : %d%n", Puissance4.getChoixJoueur());
            }Puissance4.setInputNotNull(false);
        }
    }

    /**
     * Plateeau de 6*7 = 42 cases,
     * Chaque joueurs peut jouer 42/2 coups.
     * Si le compteur = 21 c'est que la partie est nulle si aucun alignement n'est trouvé.
     */
    public static void finDePartie(){
       if (getCompteurJoueur1() ==21|| getCompteurJoeur2() == 21)
           System.out.println("Fin de partie\nPlateau rempli\nPartie nulle");
    }

    // Le Jeu À COMPLETER
    static void jeuPuissance4() throws ColonneException {
        // Variables
        setFinDePartie(false);


        /**
         *  Etape initiale : créer le plateau puis l'afficher.
         *  Initialisation du tableau avec lignes +2 et colonnes +2 pour permettre la recherche
         *  d'alignenment sans levée de "index out of bounds". C'est en quelque sorte les limites du plateau
         *  mais elles ne sont pas sélectionnables par le joueur.
         *  Les colonnes pour le joueur sélectionnables pour le joueur => 1 à 7
         *  Les lignes pour le joueur  => 1 à 6
         */

        Plateau plateau = new Plateau(new int[8][9]);
        Plateau.afficherPlateau(plateau.getMatrice());
        // Déroulement du Jeu :
        setCompteurJoueur1(0);
        setCompteurJoeur2(0);
        setNumcolonne(0);
        setNumligne(5);
        setNoJoueur(0);
        int nbUn = 0; //compteur pour les alignements de 1 pour déterminer si joueur1 gagne
        int nbDeux = 0;// compteur pour les alignements de 2 pour déterminer si joueur 2 gagne
        int compteur = 0;
        do {
            // numéro du joueur (sera soit 1, soit 2)
            // Etape 1 : à qui le tour ?
            setNoJoueur(noJoueur % 2 + 1);
            System.out.print("C'est au tour du joueur ");
            System.out.println(noJoueur);
            // Etape 2 : connaître la colonne choisie par le joueur
            Puissance4.choix();
            numcolonne = Puissance4.getChoixJoueur() ;

            /**
             *  Etape 3 : positionner le jeton
             *  Vérification que la colonne n'est pas pleine.
             *
              */
            if (Plateau.matrice[numligne][numcolonne] != 0) {
                do if (numligne == 1) {
                    System.out.println("Colonne pleine, faites un autre choix: ");
                    Puissance4.setChoixJoueur(new Scanner(System.in).nextInt());
                    numcolonne = Puissance4.getChoixJoueur();
                } else numligne--;
                while (Plateau.matrice[numligne][numcolonne] != 0);
            }
            /**
             *  remise à 5 de l'index numLigne à cause de numligne --
             */
            for (int y = 6 ; y > 1 ;y --){
                if(Plateau.matrice[y][numcolonne] ==0){
                    setNumligne(y);
                    break;
                }
            }
                if (noJoueur == 1 && Plateau.matrice[numligne][numcolonne] == 0) {
                    Plateau.matrice[numligne][numcolonne] = 1;
                    effacerConsole();
                    Plateau.afficherPlateau(plateau.getMatrice());
                    setCompteurJoueur1(compteurJoueur1 + 1); //Compte le nombre de coups joués par joueur1
                    if(Plateau.joueurEstVainqueur(numligne, numcolonne)){
                        System.out.println("Joueur 1 gagne");
                        setFinDePartie(true);
                    };
                    finDePartie(); //Vérification si le nombre de coup est = à 21 , la moitié du plateau.
                }
                if (noJoueur == 2 && Plateau.matrice[numligne][numcolonne] == 0) {
                    Plateau.matrice[numligne][numcolonne] = 2;
                    effacerConsole();
                    Plateau.afficherPlateau(plateau.getMatrice());
                    setCompteurJoeur2(compteurJoeur2 + 1); //Compte le nombre de coups joués par joueur2
                    if(Plateau.joueurEstVainqueur(numligne, numcolonne)){
                        System.out.println("Joueur 2 gagne");
                        setFinDePartie(true);
                    };
                    finDePartie(); // Vérification si le nombre de coup est = à 21 , la moitié du plateau.
                }
                // Pour vérification, peut être suppriméé
                System.out.println("numéro de ligne : " + getNumligne() + " | numéro de colonne : " +getNumcolonne());
            // Afiichage compteur nombre de coups des joueurs
            System.out.println("Joueur1 : " + getCompteurJoueur1() + " coups\n" + "joueur2 : " + getCompteurJoeur2() + " coups\n");
        }
        while (!isFinDePartie());
    }

    public static void main(String args[]) throws IOException, ColonneException {
        jeuPuissance4();
    }
}