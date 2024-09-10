package view;

import Utilities.ColoriConsole;
import model.CriterioRicerca;
import model.Libro;

import java.util.List;
import java.util.Scanner;

/**
 * Classe View (in base all'architettura MVC - ModelViewController) utilizzata per stampare a video informazioni utili riguardanti i libri
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/

public class MenuPaginamentoRisultatiView {

  final static String rs = ColoriConsole.RESET;
  final static String gr = ColoriConsole.GREEN;
  final static String bl = ColoriConsole.BLUE;
  final static String bb = ColoriConsole.BLUE_BOLD;
  final static String cb = ColoriConsole.CYAN_BOLD;
  final static String wb = ColoriConsole.WHITE_BOLD;

  final static Scanner scanner = new Scanner(System.in);

  /**
   * Mostra tutto il menu e ritorna l'input dell'utente
   * @param paginaRisultati List<Libro>
   * @param indicePaginaCorrente int
   * @param numOccorrenze int
   * @param numeroPagine int
   * @param dim_pagina int
   * @param criterio CriterioRicerca
   * @return String display*/
  public static String display(List<Libro> paginaRisultati, int indicePaginaCorrente, int numOccorrenze, int numeroPagine, int dim_pagina, CriterioRicerca criterio, String titolo) {

    int cifreNumOcc = String.valueOf(numOccorrenze).length();
    int min_header_size = 37;
    int title_header_size = 14; // larghezza del header della pagina di ricerca
    int total_size = 0;

    // informazioni sulla ricerca
    if (titolo != null) {
      // header centrato
      int header_size = title_header_size + titolo.length();
      total_size = Math.max(title_header_size+titolo.length(), min_header_size);
      int tabSpace = (total_size - header_size) / 2;

      System.out.println("\n" + " ".repeat(tabSpace) + " ╬═══╬ " + bb + titolo + rs + " ╬═══╬ ");
    }
    System.out.println("═".repeat(Math.max(total_size, min_header_size)));
    System.out.println("  Pagina "+gr+(indicePaginaCorrente+1)+rs+" di "+numeroPagine+" ("+numOccorrenze+" risultati)\n");
    System.out.println("╔ " + buildHeader(criterio));


    // visualizzazione dei risultati
    int i = 0;
    String output;
    for (Libro l: paginaRisultati) {
        String indice = String.format("%0" + cifreNumOcc + "d", i + indicePaginaCorrente*dim_pagina+1);
        output = formattaOutputSingolo(l, criterio);
        System.out.println(((i%2)==0?wb:cb)+"╠ "+indice+' '+output);
        i++;
    }
    System.out.println(rs+"╚\n");

    // computazione iterazione seguente
    System.out.println(" ["+gr+"E"+rs+"]sci, ["+bl+"C"+rs+"]erca\n ["+gr+"A"+rs+"]vanti, ["+gr+"I"+rs+"]ndietro," +
            " numero di ["+gr+"P"+rs+"]agina\n oppure "+gr+"indice"+rs+" del libro per visualizzarlo");
    System.out.print(" >> ");
    return scanner.nextLine();
  }

  /**
  * Semplicemente chiede di inserire il numero di pagina
  @return String
  */
  public static String displayPageSelectionInput() {
    System.out.print("\n Numero di pagina\n >> ");
    return scanner.nextLine();
  }


  /**
  * Prende un Libro, il criterio di ricerca, e restituisce l'output formattato come richiesto
  * @param l Libro
  * @param c CriterioRicerca
  * @return String
  */
  static String formattaOutputSingolo(Libro l, CriterioRicerca c) {

    return switch (c) {
        case AUTORE -> l.getAutori() + " - " + l.getTitolo();
        case AUTORE_ANNO -> l.getAutori() + ", " + l.getAnnoPubblicazione() + " - " + l.getTitolo();
        default -> l.toShortHandFullString();
    };
  }

  static String buildHeader(CriterioRicerca c) {
    return switch (c) {
      case AUTORE -> "Autori, Titolo";
      case AUTORE_ANNO -> "Autori, Anno di pubblicazione, Titolo";
      default -> "Titolo, Autori, Anno di pubblicazione";
    };
  }
}
