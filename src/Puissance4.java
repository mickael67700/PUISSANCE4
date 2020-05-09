import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.*;

class Puissance4 extends Exception{

    public static void main(String args[]) throws IOException {
       // test(); // à commenter une fois les tests terminés et valides
        jeuPuissance4();  // ne pas décommenter bêtement, mais compléter cette fonction avant
        //Puissance4.afficherPlateau(new int[6][10]); // Pour test affichage après modification de la méthode
    }

    // pour tester et valider les différentes fonctions créées
    static void test() {
        int[][] uneMatrice = new int[3][5]; // matrice 3 lignes x 5 colonnes d'entiers (init. à 0 avec Java)
        uneMatrice[0][0] = 1; // modif de l'élément « en haut à gauche »
        uneMatrice[2][4] = 2; // modif de l'élément « en bas à droite »
        afficherPlateau(uneMatrice);
    }


    // fonction fournie
    // pour affichage d'un plateau de Puissance 4 où 0 <=> case vide, 1 <=> jeton du joueur1, 2 <=> jeton du joueur2
    // Pas de vérification si le tableau en paramètre est bien une matrice (i.e. même nombre de colonnes pour chaque ligne)
    static void afficherPlateau(int[][] matrice) {
        effacerConsole(); // peut-être commenté. fonction définie ci-dessous
        boolean problemeParametre = false;
        if( matrice != null ) {
            // afficher le contenu
                for(int iLigne = 0 ; iLigne < matrice.length && ! problemeParametre ; iLigne++ ) {
                    if( matrice[iLigne] != null ) {
                        for(int iColonne = 0 ; iColonne < matrice[iLigne].length ; iColonne++ ) {
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
                    }
                    else problemeParametre = true;
                }
            // afficher la numérotation des colonnes (en commençant par 1)
            if( ! problemeParametre ) for (int iColonne = 0; iColonne < matrice[0].length; iColonne++) {
                System.out.print("  ");
                System.out.print(iColonne + 1);
                if (iColonne < iColonne+2) System.out.print(" ");
            }
        }
        else problemeParametre = true;
        if( problemeParametre ) System.out.print("Problème d'affiche car paramètre non valide.");
        System.out.println();
    }

    // fonction fournie
    // pour effacer le contenu de la Console
    static void effacerConsole() {
        //System.out.println(); // pour limiter un décalage possible d'affichage
        System.out.print("\n\033[H\033[2J"); // effacer la Console dans Repl.it
    }


    // Le Jeu
    // À COMPLETER
    static void jeuPuissance4() throws IOException {
        // Etape initiale : créer le plateau puis l'afficher
        int[][] plateau = new int[6][7]; // à modifier
        afficherPlateau(plateau);

        // Déroulement du Jeu :

        int noJoueur = 0;  // numéro du joueur (sera soit 1, soit 2)
        boolean finPartie = false;
        // Etape 1 : à qui le tour ?
        noJoueur = (noJoueur % 2) + 1;
        System.out.print("C'est au tour du joueur ");
        System.out.println(noJoueur);

        // Etape 2 : connaître la colonne choisie par le joueur

        System.out.println("Choisir une colonne entre 1 et 7");
        boolean inputNotNull = true;
        Scanner entree = new Scanner(System.in);
        int choixJoueur;
        while (inputNotNull) {
            try {
                choixJoueur = entree.nextInt();
                System.out.printf("Choix colonne : %d%n", choixJoueur);
                if (choixJoueur <= 7) {
                    inputNotNull = false;
                } else System.out.println("Nombre entier inférieur ou égal à 7");
            } catch (InputMismatchException e) {
                System.err.println("Mauvais entrée!\nSeulement un entier entre 1 et 7\n Réessayer :");
                choixJoueur = new Scanner(System.in).nextInt();
            }finally {
                inputNotNull = false;
            }
        }

        int numcolonne = 0;
        int numligne = 5;
        int casse = 0;

        // Etape 3 : positionner le jeton
// à compléter

        // Etape 4 : mettre à jour la variable finPartie
// à compléter

      //  while (!finPartie) {
            // Etape 1 : à qui le tour ?
            noJoueur = (noJoueur % 2) + 1;
            System.out.print("C'est au tour du joueur ");
            System.out.println(noJoueur);

            // Etape 2 : connaître la colonne choisie par le joueur
            // à compléter

            // Etape 3 : positionner le jeton
            // à compléter

            // Etape 4 : mettre à jour la variable finPartie
            // à compléter

        }

        // Etape finale : afficher la victoire/défaite ou partie nulle
        // à compléter

    }


