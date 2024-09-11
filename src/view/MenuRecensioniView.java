package view;

import Utilities.ColoriConsole;
import Utilities.Utils;
import model.Valutazione;

import java.util.List;
import java.util.Scanner;

/**
 * @author Selimi Sebian
 * @author Moscatelli Alexander
 */
public class MenuRecensioniView {

  final static String indicatore = "★";

  final static String gr = ColoriConsole.GREEN;
  final static String rs = ColoriConsole.RESET;
  final static String yb = ColoriConsole.YELLOW_BOLD;

  final static Scanner scanner = new Scanner(System.in);
  final static int PAGE_WIDTH = 45;

  public static String display(List<Valutazione> valutazioni) {

    if (valutazioni.isEmpty()) {
      System.out.println("╦╦═══════════════════════\n║╠ Nessuna recensione presente");
    } else {
      List<String> split;

      System.out.println("╦╦═══════════════════════");
      for (Valutazione val: valutazioni) {

        String recensione = val.getRecensione();
        split = Utils.stringSplitter(recensione, PAGE_WIDTH);

        System.out.println("║  " + yb + indicatore.repeat(val.getVotoFinale()) + rs);
        for(String s: split) {
          System.out.println("║  " + s);
        }
        System.out.println("║");

      }
      System.out.println("╩╩");


      // azione successiva
      System.out.println("["+gr+"A"+rs+"]vanti");
    }
    System.out.print( "["+gr+"E"+rs+"]sci\n >>");

    return scanner.nextLine();
  }
}
