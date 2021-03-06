public class Plateau {

    final static int H = 0 ;// horizontalement
    final static int V = 1 ;// verticalement
    final static int[] hor=new int[] {1, 0} ;// horizontale
    final static int[] ver=new int[] {0, 1} ;// verticale
    final static int[] diagoneUp =new int[] {1, 1} ;// diagonale montante
    final static int[] diagonaleDown=new int[] {1, -1} ;// diagonale descendante
    final static int[][] direction =new int[][]{hor, ver,diagoneUp,diagonaleDown} ;
    static int[][] matrice;

    public Plateau(int[][] matrice) {
        this.matrice = matrice;
    }

    public static int[] getHor() {
        return hor;
    }

    public static int[] getVer() {
        return ver;
    }

    public static int[] getDiagoneUp() {
        return diagoneUp;
    }

    public static int[] getDiagonaleDown() {
        return diagonaleDown;
    }

    public static int[][] getDirection() {
        return direction;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    static void afficherPlateau(int[][]matrice) {
        boolean problemeParametre = false;
        if (matrice != null) {
            // afficher le contenu
            for (int iLigne = 1; iLigne < matrice.length-1 && !problemeParametre; iLigne++) {
                if (matrice[iLigne] != null) {
                    for (int iColonne = 1; iColonne < matrice[iLigne].length-1; iColonne++) {
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
            if (!problemeParametre) for (int iColonne = 1; iColonne < matrice[1].length-1; iColonne++) {
                System.out.print("  ");
                System.out.print(iColonne );
                if (iColonne < iColonne + 2) System.out.print(" ");
            }
        } else problemeParametre = true;
        if (problemeParametre) System.out.print("Problème d'affiche car paramètre non valide.");
        System.out.println();

    }

    static boolean joueurEstVainqueur(int numcolonne, int numligne){
        // Recherche de direction alignant 4
        int i = 0;
        while (i <getDirection().length){
            if (joueurEstVainqueur(numcolonne,numligne,direction[i]))
                return true;
            i++;
        }
        return false;
    }

    private static boolean joueurEstVainqueur(int numcolonne, int numligne, int[] dir){
        int prochaineColonne = numcolonne+dir[H];
        int prochaineLigne = numligne+dir[V];
        int avancer = 0; // nombre de cases du joueur vers l'avant 
        while (matrice[prochaineColonne][prochaineLigne] == Puissance4.getNoJoueur()){
            prochaineColonne += dir[H];
            prochaineLigne += dir[V];
            avancer ++;
        }
        // Recul
        prochaineColonne = numcolonne - dir[H];
        prochaineLigne = numligne - dir[H];
        int reculer = 0;  // nombre de cases du joueur vers l'arrière
        while (matrice[prochaineColonne][prochaineLigne]==Puissance4.getNoJoueur()){
            prochaineColonne -= dir[H];
            prochaineLigne -= dir[V];
            reculer ++;
        }
        return reculer+1 +avancer>=4;
    }


}
