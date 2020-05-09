import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.*;

class Puissance4 extends Exception {
   public static boolean inputNotNull = true;
   public static int choixJoueur;

    public static int getChoixJoueur() {
        return choixJoueur;
    }

    public static void setChoixJoueur(int choixJoueur) {
        Puissance4.choixJoueur = choixJoueur;
    }

    public static boolean isInputNotNull() {
        return inputNotNull;
    }

    public static void setInputNotNull(boolean inputNotNull) {
        Puissance4.inputNotNull = inputNotNull;
    }
    // fonction fournie
    // pour affichage d'un plateau de Puissance 4 où 0 <=> case vide, 1 <=> jeton du joueur1, 2 <=> jeton du joueur2
    // Pas de vérification si le tableau en paramètre est bien une matrice (i.e. même nombre de colonnes pour chaque ligne)


    // fonction fournie
    // pour effacer le contenu de la Console
    static void effacerConsole() {
        //System.out.println(); // pour limiter un décalage possible d'affichage
        System.out.print("\n\033[H\033[2J"); // effacer la Console dans Repl.it
    }
    public static void choix() throws ColonneException {
         /*
         Etape 2 : connaître la colonne choisie par le joueur à compléter
        On inclut dans un bloc try les instructions qui risquent de déclencher une éventuelle
        exception.
         */
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

    // Le Jeu À COMPLETER
    static void jeuPuissance4() throws ColonneException {
        // Etape initiale : créer le plateau puis l'afficher
        Plateau plateau = new Plateau(new int[6][7]);
        Plateau.afficherPlateau(plateau.getMatrice());
        boolean finPartie = false;

        // Déroulement du Jeu :
        int noJoueur = 0;
        do {
             // numéro du joueur (sera soit 1, soit 2)
            // Etape 1 : à qui le tour ?
            noJoueur = (noJoueur % 2) + 1;
            System.out.print("C'est au tour du joueur ");
            System.out.println(noJoueur);
            // Etape 2 : connaître la colonne choisie par le joueur
            Puissance4.setInputNotNull(true);
            Puissance4.choix();
            int numcolonne = 0;
            int numligne = 5;
            int casse = 0;
            int somme = -1;
            int gagnant =0;
            if(plateau.matrice[0][numcolonne] != 0) {
                System.out.println("Colonne pleine, choisissez une autre colonne");
                Puissance4.choix();
            }
            numcolonne = Puissance4.getChoixJoueur()-1;
            System.out.println(numcolonne);

            // Etape 3 : positionner le jeton
            //for (  ligne = 0; ligne < plateau.length; ligne ++){
            while (plateau.matrice[numligne][numcolonne] != 0)
            {
                numligne--;
            }
            if (noJoueur == 1 && plateau.matrice[numligne][numcolonne] == 0 ){
                plateau.matrice[numligne][numcolonne] = 1;
                Plateau.afficherPlateau(plateau.getMatrice());
            }
            if (noJoueur == 2 && plateau.matrice[numligne][numcolonne] == 0) {
                plateau.matrice[numligne][numcolonne] = 2;
                Plateau.afficherPlateau(plateau.getMatrice());
            }

            // Etape 4 : mettre à jour la variable finPartie
            // à compléter
            int max=0;
            int x = numligne;
            int y = numcolonne;
            somme = -1;
            gagnant =0;

            //-->  diagonale HG-BD
            //x = numligne; y = nomcolonne; somme=-1;
            while(y >= 0 && x >= 0 && plateau.matrice[x][y] != 0){ y--; x--; somme++;}
            //x = numligne; y = nomcolonne;
            while(y < 7 && x < 6 && plateau.matrice[x][y] != 0){ y++; x++; somme++;}
            if(somme > max) max= somme;

            //-->  diagonale HD-BG
            //x = numligne; y = nomcolonne; somme=-1;
            while(y >= 0 && x < 6 && plateau.matrice[x][y] != 0){ y--; x++; somme++;}
            //x = numligne; y = nomcolonne;
            while(y < 7 && x >= 0 && plateau.matrice[x][y] != 0){ y++; x--; somme++;}
            if(somme > max) max= somme;

            //-->  verticale:
            //x = numligne; y = nomcolonne; somme=-1;
            while(y >= 0 && plateau.matrice[x][y] != 0){ y--; somme++;}
            //y = nomcolonne;
            while(y < 7 && plateau.matrice[x][y] != 0){ y++; somme++;}
            if(somme > max) max= somme;

            //-->  horizontale:
            //x = numligne; y = nomcolonne;
            somme=-1;
            while(x >= 0 && plateau.matrice[x][y] != 0){ x--; somme++;}
            //x = numligne;
            while(x < 6 && plateau.matrice[x][y]!= 0){ x++; somme++;}
            if(somme > max) {max= somme;}


            if(max >= 4){
                gagnant = noJoueur;
                System.out.println(gagnant);
            }

        }
        while( ! finPartie );
        // Etape finale : afficher la victoire/défaite ou partie nulle
        // à compléter

    }
    public static void main(String args[]) throws IOException, ColonneException {
        jeuPuissance4();  // ne pas décommenter bêtement, mais compléter cette fonction avant
        //Puissance4.afficherPlateau(new int[6][10]); // Pour test affichage après modification de la méthode
    }

}