package controller;

import Utilities.Utils;
import model.CriterioRicerca;
import model.Libro;
import model.RaccoltaLibri;
import model.Utente;
import view.Feedback;
import view.MenuCriterioRicerca;
import view.MenuPaginamentoRisultati;
import view.MenuPrincipale;

import java.util.List;
import java.util.Scanner;

public class GestoreRaccolta {

  RaccoltaLibri raccolta;
  Utente utenteCorrente;
  Scanner scanner;
  final int DIM_PAGINA = 15;

  public GestoreRaccolta(RaccoltaLibri raccolta) {
    // temporary path selection
    /*System.out.println("\n\n\n||||Directory:|||| " + System.getProperty("user.dir") + "\n");*/
    this.raccolta = raccolta;
    raccolta.setElenco(CSVFileManager.leggiFile("./data/Libri.dati.csv"));
    scanner = new Scanner(System.in);
  }


  public void iniziaCicloPrincipale() {
    // costanti -> NOTA: la dimensione deve essere maggiore di 0

    // variabili per il funzionamento del ciclo
    String scelta;
    boolean uscita = false;

    do {

      // DISPLAY deve avere nella descrizione del funzionamento indicata la restituzione della scelta fatta dall'utente
      scelta = MenuPrincipale.display();

      // aggiungere controllo dell'input

      switch (scelta.toLowerCase()) {
        case "c":
          iniziaRicerca();
          break;
        case "r":
          iniziaRegistrazione();
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



  void iniziaRicerca() {
    MenuCriterioRicerca.display();

    /* acquisizione chiavi e selezione del criterio di ricerca */
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
        } catch (NumberFormatException e) {
          System.err.println("Formato numerico per l'anno fornito non valido.");
        }
        break;

      default:
        Feedback.warn("Criterio non valido");
        return;
    }

    RaccoltaLibri risultati = new RaccoltaLibri(raccolta.cercaLibro(titolo, autori, annoPubblicazione, criterio));

    // visualizzazione pagine dei risultati
    iniziaPaginaRisultati(risultati, criterio);
  }



  void iniziaPaginaRisultati(RaccoltaLibri risultati, CriterioRicerca criterio) {
    String sceltaOpzionePagina;
    boolean uscitaPaginaRisultati = false;
    int indicePaginaCorrente = 0;
    int numOccorrenze = risultati.getElenco().size();
    int numeroPagine = (int) Math.max(1, Math.ceil((double) numOccorrenze/DIM_PAGINA));

    do {
      List<Libro> paginaRisultati = raccolta.sottoRaccolta(risultati.getElenco(), indicePaginaCorrente*DIM_PAGINA, DIM_PAGINA);

      sceltaOpzionePagina = MenuPaginamentoRisultati.display(paginaRisultati, indicePaginaCorrente, numOccorrenze, numeroPagine, DIM_PAGINA, criterio);

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
            // displayPageSelectionInput DEVE avere nella descrizione del funzionamento indicata la restituzione della scelta fatta dall'utente
            String pSel = MenuPaginamentoRisultati.displayPageSelectionInput();

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

        /*unfinished*/
        /*unfinished*/
        /*unfinished*/

      }
    } while (!uscitaPaginaRisultati);
  }



  void iniziaRegistrazione() {
    /*funzionalità di registrazione*/
    /*funzionalità di registrazione*/
    /*funzionalità di registrazione*/
    /*funzionalità di registrazione*/
    /*funzionalità di registrazione*/
    /*funzionalità di registrazione*/

    System.out.println("\n\t╠═══════ Registrazione ═══════╣\n\n");
  }
























}