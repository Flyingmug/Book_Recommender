import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {


    // inizializzazione gestore della raccolta locale
    GestoreRaccolta manager = new GestoreRaccolta();

    manager.Test();

    // variabili per il funzionamento del ciclo
    String scelta;
    boolean uscita = false;
    Scanner scanner = new Scanner(System.in);

    System.out.println(" (\\¯¯¯¯¯¯¯¯\\");
    System.out.println(" \\ \\.;.;.;.;\\");
    System.out.println("  \\ \\.;.;.;.;\\");
    System.out.println("   \\ \\.;.;.;.;\\");
    System.out.println("    \\(≡≡≡≡≡≡≡≡(");

    // colori menu
    String cy = ColoriConsole.CYAN;
    String rs = ColoriConsole.RESET;

    do {

      // menu iniziale
      System.out.println("\n\t╬╬ Book Recommender ╬╬");
      System.out.println("╔════════════════════════════");
      System.out.println("║\n╠ ["+cy+"C"+rs+"] - Ricerca");
      System.out.println("╠ ["+cy+"A"+rs+"] - Accesso");
      System.out.println("╠ ["+cy+"L"+rs+"] - Login");
      System.out.println("╠ ["+cy+"R"+rs+"] - Registazione");
      System.out.println("╠ ["+cy+"U"+rs+"] - Uscita\n║");
      System.out.println("╠════════════════════════════");
      System.out.print("╠ >> ");
      scelta = scanner.nextLine();

      // aggiungere controllo dell'input

      switch (scelta) {
        case "C":
        case "c":
          System.out.println("\n\t╠═══════ Ricerca ═══════╣\n\n");

          List<Libro> ris = manager.cercaLibro("Christmas", CriterioRicerca.TITOLO);
          System.out.println("Result Size: " + ris.size());

          break;
        case "R":
        case "r":
          System.out.println("\n\t╠═══════ Registrazione ═══════╣\n\n");


          break;
        case "L":
        case "l":
          System.out.println("\n\t╠═══════ Login ═══════╣\n\n");


          break;
        case "A":
        case "a":
          System.out.println("\n\t╠═══════ Accesso ═══════╣\n\n");


          break;
        case "U":
        case "u":
          uscita = true;
          break;
      }

    } while (!uscita);

    // chiusura
    scanner.close();
    System.out.println("Uscita");


  }
}