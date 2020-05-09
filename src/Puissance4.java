import java.io.IOException;
import java.util.*;

class Puissance4 extends Exception {
    private static boolean inputNotNull = true;
    private static int choixJoueur;
    private static boolean finDePartie = false;
    private static int compteurJoueur1; // on teste si gagnant à partir du 4ème coup
    private static int compteurJoeur2; // on teste si gagnent à partir du 4ème coup
    final static int largeur = 7;
    final static int hauteur = 6;
    private static int[][] matrice;
    private int x;
    private int y;
    static int numcolonne;
    static int numligne;
    static int noJoueur;


    public void Plateau(int[][] matrice) {
        this.matrice = matrice;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    final static int empty= 0; // case vide
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
    public static void setFinDePartie(boolean finDePartie) {
       Puissance4.finDePartie = finDePartie; }
    public static boolean isFinDePartie() { return finDePartie; }
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


    static void afficherPlateau(int[][]matrice) {
        boolean problemeParametre = false;
        if (matrice != null) {
            // afficher le contenu
            for (int iLigne = 0; iLigne < matrice.length && !problemeParametre; iLigne++) {
                if (matrice[iLigne] != null) {
                    for (int iColonne = 0; iColonne < matrice[iLigne].length; iColonne++) {
                        System.out.print("| ");
                        if (matrice[iLigne][iColonne] == 0) {
                            System.out.print(" ");
                        } else if (matrice[iLigne][iColonne] == 1) {
                            System.out.print("x");
                        } else if (matrice[iLigne][iColonne] == 2) {
                            System.out.print("o");
                        } else {
                            System.out.print("?");
                        }
                        System.out.print(" ");
                    }
                    System.out.println("|");
                } else problemeParametre = true;
            }
            // afficher la numérotation des colonnes (en commençant par 1)
            if (!problemeParametre) for (int iColonne = 0; iColonne < matrice[0].length; iColonne++) {
                System.out.print("  ");
                System.out.print(iColonne + 1);
                if (iColonne < iColonne + 2) System.out.print(" ");
            }
        } else problemeParametre = true;
        if (problemeParametre) System.out.print("Problème d'affiche car paramètre non valide.");
        System.out.println();

    }
    public static void choix() throws ColonneException {
         /*
         Etape 2 : connaître la colonne choisie par le joueur à compléter
        On inclut dans un bloc try les instructions qui risquent de déclencher une éventuelle
        exception.
         */
        Puissance4.setInputNotNull(true);
        while (inputNotNull) {
            try {
                Puissance4.setChoixJoueur(new Scanner(System.in).nextInt());
            } catch (InputMismatchException e) {
                System.err.println("Mauvais entrée!\nSeulement un entier entre 1 et 7\n Réessayer :");
                Puissance4.setChoixJoueur(new Scanner(System.in).nextInt());
            }
            System.out.println(Puissance4.getChoixJoueur());
            if (Puissance4.getChoixJoueur() > 7) throw new ColonneException();
            else {
                System.out.printf("Choix colonne : %d%n", Puissance4.getChoixJoueur());
            }Puissance4.setInputNotNull(false);
        }
    }


    public static void finDePartie(){
       if (getCompteurJoueur1() ==21|| getCompteurJoeur2() == 21)
           System.out.println("Fin de partie\nPlateau rempli\nPartie nulle");
    }
    boolean estLibre(int numcolonne){
        return matrice[numcolonne][hauteur] == 0;
    }



    // Le Jeu À COMPLETER
    static void jeuPuissance4() throws ColonneException {
        // Variables
        setFinDePartie(false);


        // Etape initiale : créer le plateau puis l'afficher
        Plateau plateau = new Plateau(new int[6][7]);
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
            numcolonne = Puissance4.getChoixJoueur() - 1;

            // Etape 3 : positionner le jeton

            if (Plateau.matrice[numligne][numcolonne] != 0) {
                do if (numligne == 0) {
                    System.out.println("Colonne pleine, faites un autre choix: ");
                    Puissance4.setChoixJoueur(new Scanner(System.in).nextInt());
                    numcolonne = Puissance4.getChoixJoueur() - 1;
                } else numligne--;
                while (Plateau.matrice[numligne][numcolonne] != 0);
            }
            /**
             *  remise à 5 de l'index numLigne à cause de numligne --
             */
            for (int y = 5 ; y > 0 ;y --){
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
                    if(Plateau.joueur1EstVainqueur(numligne, numcolonne)){
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
                    if(Plateau.joueur2EstVainqueur(numligne, numcolonne)){
                        System.out.println("Joueur 2 gagne");
                        setFinDePartie(true);
                    };
                    finDePartie(); // Vérification si le nombre de coup est = à 21 , la moitié du plateau.
                }
                System.out.println("numéro de ligne" + numligne);
                //Vérification verticale
                if (numligne <= 5 && numligne > 0 && Plateau.matrice[numligne][numcolonne] != 0) {
                    for (int y = numligne; y <= 5; y++) {
                        if (Plateau.matrice[y][numcolonne] == Plateau.matrice[y - 1][numcolonne] && Plateau.matrice[y][numcolonne] == 1) {
                            System.out.println("nb1 " + nbUn);
                            nbUn++;
                            if (nbUn == 4) {
                                System.out.println("Partie Terminée\nJoueur1 gagne");
                                setFinDePartie(true);
                            }
                        }
                        if (Plateau.matrice[y][numcolonne] == Plateau.matrice[y - 1][numcolonne] && Plateau.matrice[y][numcolonne] == 2) {
                            nbDeux++;
                            System.out.println(nbDeux);
                            if (nbDeux == 4) {
                                System.out.println("Partie Terminée\nJoeur2 gagne");
                                setFinDePartie(true);
                            }
                        }
                    }
                }
            System.out.println("Joueur1 : " + getCompteurJoueur1() + " coups\n" + "joueur2 : " + getCompteurJoeur2() + " coups\n");
        }
        while (!isFinDePartie());
    }


    public static void main(String args[]) throws IOException, ColonneException {
        jeuPuissance4();
    }

}