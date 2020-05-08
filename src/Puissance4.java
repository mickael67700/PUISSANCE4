import java.util.*;

class Puissance4 {

    public static void main(String args[]) {
        test(); // à commenter une fois les tests terminés et valides
        //jeuPuissance4();  // ne pas décommenter bêtement, mais compléter cette fonction avant
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
                if (iColonne < 9) System.out.print(" ");
            }
        }
        else problemeParametre = true;
        if( problemeParametre ) System.out.print("Problème d'affiche car paramètre non valide.");
        System.out.println();
    }

    // fonction fournie
    // pour effacer le contenu de la Console
    static void effacerConsole() {
        System.out.println(); // pour limiter un décalage possible d'affichage
        System.out.print("\033[H\033[2J"); // effacer la Console dans Repl.it
    }


    // Le Jeu
    // À COMPLETER
    static void jeuPuissance4() {
        // Etape initiale : créer le plateau puis l'afficher
        int[][] plateau = null; // à modifier
        afficherPlateau(plateau);

        // Déroulement du Jeu :

        int noJoueur = 0;  // numéro du joueur (sera soit 1, soit 2)
        boolean finPartie = false;
        do {
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
        while( ! finPartie );

        // Etape finale : afficher la victoire/défaite ou partie nulle
        // à compléter

    }

}

