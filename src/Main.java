import Utilities.ColoriConsole;
import Utilities.Utils;
import controller.GestoreRaccolta;
import model.CriterioRicerca;
import model.Libro;
import model.Raccolta;
import view.Feedback;

import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {


    // inizializzazione gestore della raccolta locale
    GestoreRaccolta manager = new GestoreRaccolta();
    Raccolta raccolta = new Raccolta();

    // rimovibile
    /*manager.Test();*/

    // costanti -> NOTA: la dimensione deve essere maggiore di 0
    final int DIM_PAGINA = 15;

    // variabili per il funzionamento del ciclo
    String scelta;
    boolean uscita = false;
    Scanner scanner = new Scanner(System.in);

    System.out.println("\n\n\n\t (\\¯¯¯¯¯¯¯¯\\");
    System.out.println("\t \\ \\.;.;.;.;\\");
    System.out.println("\t  \\ \\.;.;.;.;\\");
    System.out.println("\t   \\ \\.;.;.;.;\\");
    System.out.println("\t    \\(≡≡≡≡≡≡≡≡(");

    // colori menu
    String cy = ColoriConsole.CYAN;
    String rs = ColoriConsole.RESET;
    String gr = ColoriConsole.GREEN;
    String bl = ColoriConsole.BLUE;
    String cb = ColoriConsole.CYAN_BOLD;
    String wb = ColoriConsole.WHITE_BOLD;
    String yb = ColoriConsole.YELLOW_BOLD;

    do {

      // menu principale
      System.out.println("\n\t╬╬ Book Recommender ╬╬");
      System.out.println("╔════════════════════════════");
      System.out.println("║\n╠ ["+cy+"C"+rs+"] - Ricerca");
      System.out.println("╠ ["+cy+"A"+rs+"] - Accesso");
      System.out.println("╠ ["+cy+"L"+rs+"] - Login");
      System.out.println("╠ ["+cy+"R"+rs+"] - Registazione");
      System.out.println("╠ ["+cy+"E"+rs+"] - Esci\n║");
      System.out.println("╠════════════════════════════");
      System.out.print("╠ >> ");
      scelta = scanner.nextLine();

      // aggiungere controllo dell'input

      switch (scelta.toLowerCase()) {
        case "c":
          System.out.println("\n\t═══════╣ Ricerca ╠═══════");
          System.out.println("║ Scegliere un criterio di ricerca");
          System.out.println("╠ ["+cy+"T"+rs+"] Titolo");
          System.out.println("╠ ["+cy+"A"+rs+"] Autore");
          System.out.println("╠ ["+cy+"AA"+rs+"] Autore e anno di pubblicazione");
          System.out.print("╠ >> ");

          String titolo = "", autori= "";
          int annoPubblicazione = 0;
          CriterioRicerca criterio;
          String c = scanner.nextLine();
          switch (c.toLowerCase()) {
            case "t":
              criterio = CriterioRicerca.TITOLO;
              System.out.print("║\n║ Titolo\n╠ >> ");
              titolo = scanner.nextLine();
              break;
            case "a":
              criterio = CriterioRicerca.AUTORE;
              System.out.print("║\n║ Autore\n╠ >> ");
              autori = scanner.nextLine();
              break;
            case "aa":
              criterio = CriterioRicerca.AUTORE_ANNO;
              System.out.print("║\n║ Autore\n╠ >> ");
              autori = scanner.nextLine();
              System.out.print("║ Anno\n╠ >> ");
              try {
                annoPubblicazione = Integer.parseInt(scanner.nextLine());
              } catch(NumberFormatException e) {
                System.err.println("Formato numerico per l'anno fornito non valido.");
              }
              break;

            default:
              Feedback.warn("Criterio non valido");
              continue;
          }

          List<Libro> risultati = manager.cercaLibro(titolo, autori, annoPubblicazione, criterio);

          // visualizzazione pagine dei risultati
          String sceltaOpzionePagina;
          boolean uscitaPaginaRisultati = false;
          int indicePaginaCorrente = 0;
          int numOccorrenze = risultati.size();
          int numeroPagine = (int) Math.max(1, Math.ceil((double) numOccorrenze/DIM_PAGINA));
          int cifreNumOcc = String.valueOf(numOccorrenze).length();
          do {
            // informazioni sulla ricerca
            System.out.println("\n═════════════════════════════════");
            System.out.println("  Pagina "+gr+(indicePaginaCorrente+1)+rs+" di "+numeroPagine+" ("+numOccorrenze+" risultati)\n");
            List<Libro> paginaRisultati = manager.sottoRaccolta(risultati, indicePaginaCorrente*DIM_PAGINA, DIM_PAGINA);
            System.out.println("╔ Titolo, Autori, Anno di pubblicazione");

            // visualizzazione dei risultati
            int i = 0;
            String output;
            for (Libro l: paginaRisultati) {
              String indice = String.format("%0" + cifreNumOcc + "d", i + indicePaginaCorrente*DIM_PAGINA+1);
              output = manager.formattaOutputSingolo(l, criterio);
              System.out.println(((i%2)==0?wb:cb)+"╠ "+indice+' '+output);
              i++;
            }
            System.out.println(rs+"╚\n");

            // computazione iterazione seguente
            System.out.println(" ["+gr+"E"+rs+"]sci\n ["+gr+"A"+rs+"]vanti, ["+gr+"I"+rs+"]ndietro," +
                " numero di ["+gr+"P"+rs+"]agina\n oppure "+gr+"indice"+rs+" del libro per visualizzarlo");
            System.out.print(" >> ");
            sceltaOpzionePagina = scanner.nextLine();

            if(!Utils.isInteger(sceltaOpzionePagina)) {
              // verifica opzione selezionata
              switch (sceltaOpzionePagina.toLowerCase()) {
                case "a":
                  if (indicePaginaCorrente < numeroPagine - 1) indicePaginaCorrente++;
                  break;
                case "i":
                  if (indicePaginaCorrente > 0) indicePaginaCorrente--;
                  break;
                case "p":
                  System.out.print("\n Numero di pagina\n >> ");
                  String pSel = scanner.nextLine();
                  if (Utils.isInteger(pSel)) {
                    int numPaginaSel = Integer.parseInt(pSel);
                    if(numPaginaSel > 0 && numPaginaSel <= numeroPagine)
                      indicePaginaCorrente = numPaginaSel-1;
                    else
                      Feedback.warn("Pagina inesistente");
                  } else
                    Feedback.warn("Solo numeri interi ammessi");

                  break;
                case "e":
                  uscitaPaginaRisultati = true;
                default:
                  Feedback.warn("Operazione inesistente");
                  break;
              }
            } else {

              // visualizzazione dati di un libro
              int indice = Integer.parseInt(sceltaOpzionePagina);



            }



          } while (!uscitaPaginaRisultati);

          break;
        case "r":
          System.out.println("\n\t╠═══════ Registrazione ═══════╣\n\n");


          break;
        case "l":
          System.out.println("\n\t╠═══════ Login ═══════╣\n\n");


          break;
        case "a":
          System.out.println("\n\t╠═══════ Accesso ═══════╣\n\n");


          break;
        case "e":
          uscita = true;
          break;
        default:
          Feedback.warn("Operazione inesistente");
      }

    } while (!uscita);

    // chiusura
    scanner.close();
    System.out.println("╚════════════════════════════");


  }
}