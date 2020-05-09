public class Plateau {
    int[][] matrice;

    public Plateau(int[][] matrice) {
        this.matrice = matrice;
    }

    public int[][] getMatrice() {
        return matrice;
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

}
