package view;

import Utilities.ColoriConsole;
import Utilities.Utils;
import model.Valutazione;

import java.util.Scanner;

public class MenuValutazioneView {

  final static String wb = ColoriConsole.WHITE_BOLD;
  final static String rs = ColoriConsole.RESET;
  final static Scanner scanner = new Scanner(System.in);

  public static Valutazione display() {

    int[] pt = new int[5];
    String[] val = {"Stile","Contenuto","Gradevolezza","Originalita'","Edizione"};

    for (int i = 0; i < val.length; i++) {
      System.out.print("╠ " + val[i] + " (1-5):\n║ >> ");
      String input = scanner.nextLine();
      if (Utils.isInteger(input)) {
        int n = Integer.parseInt(input);
        if (n > 0 && n < 6)
          pt[i] = n;
      } else {
        return null;
      }
    }
    System.out.print("╠ Nota (max 256 caratteri), opzionale\n║ >> ");
    String recensione = scanner.nextLine();

    return new Valutazione("", "", pt[0], pt[1], pt[2], pt[3], pt[4], recensione.trim());

  }
}
