import java.util.Scanner;

public class ColonneException extends Exception {
    public ColonneException(){
        System.out.println("Choix de colonne hors plateau.\n Réessayer");
        Puissance4.setChoixJoueur(new Scanner(System.in).nextInt());
        if (Puissance4.getChoixJoueur()<= 7)
            Puissance4.setInputNotNull(false);
    }
}
