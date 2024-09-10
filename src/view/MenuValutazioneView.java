package view;

import Utilities.ColoriConsole;
import Utilities.Utils;
import model.Valutazione;

import java.util.Scanner;

public class MenuValutazioneView {

  final static String bb = ColoriConsole.BLUE_BOLD;
  final static String rs = ColoriConsole.RESET;
  final static Scanner scanner = new Scanner(System.in);

  public static Valutazione display() {

    int[] pt = new int[5];
    String[] val = {"Stile","Contenuto","Gradevolezza","Originalita'","Edizione"};

    System.out.println("╔ Inserire "+bb+"."+rs+" per uscire\n║");
    for (int i = 0; i < val.length; i++) {

      int input = leggiValidaInput(val[i]);
      if (input != 0) {
        pt[i] = input;
      } else
        return null;
    }
    System.out.print("╠ Nota (max 256 caratteri), opzionale\n║ >> ");
    String recensione = scanner.nextLine().replaceAll("\\s+", " ");

    return new Valutazione("", "", pt[0], pt[1], pt[2], pt[3], pt[4], recensione.trim());

  }

  /**
   * Pone l'utente in un ciclo il quale vieta l'uscita fino all'inserimento
   * di un valore valido o un carattere di uscita prestabilito.
   * @return input utente validato
   */
  private static int leggiValidaInput(String campo) {
    while (true) {
      System.out.print("╠ " + campo + " (1-5):\n║ >> ");
      String input = scanner.nextLine().trim();

      input = input.replaceAll("\\s+", "");

      // Controlla presenza di "." (carattere di uscita)
      if (input.equals(".")) {
        return 0;
      }

      if (input.isBlank()) {
        System.out.println("--L'input non può essere vuoto--");
      } if (Utils.isInteger(input)) {
        int n = Integer.parseInt(input);
        if (n > 0 && n < 6)
          return n;
        else
          System.out.println("--Solo numeri da 1 a 5 ammessi--");
      } else {
        System.out.println("--Solo numeri ammessi--");
      }
    }
    }
}
