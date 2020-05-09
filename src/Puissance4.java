import java.io.IOException;
import java.util.*;

class Puissance4 extends Exception {
   public static boolean inputNotNull = true;
   public static int choixJoueur;
   public static boolean finDePartie = false;
   public static int compteurJoueur1; // on teste si gagnant à partir du 4ème coup
   public static int compteurJoeur2; // on teste si gagnent à partir du 4ème coup
    private int x;
    private int y;
    static int numcolonne;
    static int numligne;
    static int noJoueur;

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

    // Ghetters et Setters
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
        int nbUn = 1;
        int nbDeux = 1;
        int compteur =0;
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

            while (plateau.matrice[numligne][numcolonne] != 0)
                if (numligne == 0) {
                    System.out.println("Colonne pleine, faites un autre choix: ");
                    Puissance4.setChoixJoueur(new Scanner(System.in).nextInt());
                    numcolonne = Puissance4.getChoixJoueur() - 1;
                    numligne = 5;
                } else numligne--;
            if (noJoueur == 1 && plateau.matrice[numligne][numcolonne] == 0) {
                plateau.matrice[numligne][numcolonne] = 1;
                effacerConsole();
                Plateau.afficherPlateau(plateau.getMatrice());
                setCompteurJoueur1(compteurJoueur1 + 1);
                finDePartie();
            }
            if (noJoueur == 2 && plateau.matrice[numligne][numcolonne] == 0) {
                plateau.matrice[numligne][numcolonne] = 2;
                effacerConsole();
                Plateau.afficherPlateau(plateau.getMatrice());
                setCompteurJoeur2(compteurJoeur2 + 1);
                finDePartie();
            }
            if (numligne<=5 && numligne>0 && plateau.matrice[numligne][numcolonne]!=0){
                    for (int y = numligne; y <= 5-numligne ; y++) {
                        System.out.println(y);
                            if (plateau.matrice[y][numcolonne] == plateau.matrice[y][numcolonne] && plateau.matrice[y+1][numcolonne] ==1) {
                                System.out.println(nbUn);
                                nbUn ++;
                                if (nbUn == 4) {
                                    System.out.println("Partie Terminée\nJoueur1 gagne");
                                    setFinDePartie(true);
                                }
                            }

                        if (plateau.matrice[y][numcolonne] == plateau.matrice[numligne+y][numcolonne] && plateau.matrice[numligne][numcolonne] ==2) {
                            nbDeux ++;
                            System.out.println(nbDeux);
                            if (nbDeux == 4) {
                                System.out.println("Partie Terminée\nJoeur2 gagne");
                                setFinDePartie(true);
                            }
                        }
                    }
                }
                    System.out.println("Joueur1 : " +getCompteurJoueur1() + " coups\n" + "joueur2 : " +getCompteurJoeur2() + " coups\n");
            }
            while (!isFinDePartie()) ;
        }

    public static void main(String args[]) throws IOException, ColonneException {
        jeuPuissance4();
    }

}