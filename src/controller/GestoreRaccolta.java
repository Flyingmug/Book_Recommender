package controller;

import Utilities.Utils;
import model.*;
import Utilities.Feedback;
import view.*;
import java.util.List;
import java.util.Scanner;

/**
 * La classe GestoreRaccolta viene utilizzata per avviare il programma e presentare il menu' con le funzionalita' all'utente
 * */
public class GestoreRaccolta {

  final String path_libri = "./data/Libri.dati.csv";
  final String path_valutazioni = "./data/ValutazioniLibri.dati.csv";
  final String path_utenti_registrati = "./data/UtentiRegistrati.dati.csv";
  final String path_librerie_personali = "./data/Librerie.dati.csv";
  final String path_consigli_lettura = "./data/ConsigliLibri.dati.csv";

  RaccoltaLibri raccolta;
  GestoreSessione sessione;
  Scanner scanner;
  final int DIM_PAGINA = 15;

  /**
   * Costruttore GestoreRaccolta
   * @param raccolta RaccoltaLibri */
  public GestoreRaccolta(RaccoltaLibri raccolta) {
    this.raccolta = raccolta;
    sessione = new GestoreSessione(path_utenti_registrati);
    raccolta.caricaDati();
    scanner = new Scanner(System.in);
  }

/**
 * Metodo che riceve in input l'operazione desiderata dall'utente.
 * Richiama il metodo che gestisce l'operazione corrispondente.*/
  public void iniziaCicloPrincipale() {
    // costanti -> NOTA: la dimensione deve essere maggiore di 0

    // variabili per il funzionamento del ciclo
    String scelta;
    boolean uscita = false;

    do {

      // DISPLAY deve avere nella descrizione del funzionamento indicata la restituzione della scelta fatta dall'utente
      scelta = sessione.getUtenteCorrente() != null ?
          MenuPrincipaleView.displayFull() :
          MenuPrincipaleView.displayMinimal();

      // aggiungere controllo dell'input

      switch (scelta.toLowerCase()) {
        case "c":
          iniziaRicerca();
          break;
        case "r":
          if (sessione.getUtenteCorrente() != null)
            iniziaRegistrazione();
          else
            Feedback.warn("");
          break;
        case "l":
          iniziaLogin();
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


/**
* Metodo utilizzato per ricercare il libro richiesto dall'utente, in base ai vari criteri quali:
* Titolo
* Autore
* Autore e anno
 * */
  void iniziaRicerca() {
    MenuCriterioRicercaView.display();

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
    iniziaPaginazioneRisultati(risultati, criterio);
  }


/**
 * Metodo che mostra i risultati di ricerca (libri) sotto forma di pagine tra cui scorrere
 * @param risultati RaccoltaLibri
 * @param criterio CriterioRicerca */
  void iniziaPaginazioneRisultati(RaccoltaLibri risultati, CriterioRicerca criterio) {
    String sceltaOpzionePagina;
    boolean uscitaPaginaRisultati = false;
    int indicePaginaCorrente = 0;
    int numOccorrenze = risultati.getElenco().size();
    int numeroPagine = (int) Math.max(1, Math.ceil((double) numOccorrenze/DIM_PAGINA));

    do {
      List<Libro> paginaRisultati = raccolta.sottoRaccolta(risultati.getElenco(), indicePaginaCorrente*DIM_PAGINA, DIM_PAGINA);

      sceltaOpzionePagina = MenuPaginamentoRisultatiView.display(paginaRisultati, indicePaginaCorrente, numOccorrenze, numeroPagine, DIM_PAGINA, criterio);


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
            String pSel = MenuPaginamentoRisultatiView.displayPageSelectionInput();

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
            if (Utils.isInteger(sceltaOpzionePagina)) {
              // visualizzazione dati di un libro
              int indice = Integer.parseInt(sceltaOpzionePagina);
              if (indice > 0 && indice <= numOccorrenze) {
                visualizzaLibro(risultati.getElenco().get(indice));
              } else
                Feedback.warn("Indice selezionato non valido");
            } else
              Feedback.warn("Operazione inesistente");

            break;
        }

    } while (!uscitaPaginaRisultati);
  }

/**
 * Metodo utilizzato per stampare a video il/i libro/i richiesto (tramite classe DisplayLibroView)
 * @param l Libro*/
  void visualizzaLibro(Libro l) {

    Valutazione valMedia = raccolta.ottieniValutazioneMediaLibro(l);

    boolean uscitaPaginaLibro = false;
    do {

      String scelta = DisplayLibroView.display(l, valMedia);
      if (scelta.equalsIgnoreCase("c")) {

      } else {
        uscitaPaginaLibro = true;
      }

    } while(!uscitaPaginaLibro);
  }


/**
 * */
  void iniziaRegistrazione() {
    System.out.println("\n\t╠═══════ Registrazione ═══════╣\n\n");

    Utente u = MenuRegistrazioneView.display();
    /*il menu al momento non restituisce nulli*/
    if (u != null)
      try {
        sessione.Registra(u);
      } catch (RuntimeException e) {
        Feedback.err(e.getMessage());
      } finally {
        Feedback.success("Registrazione avvenuta con successo");
      }

  }


/**
 * Semplice stampa a video per indicare la selezione dell'operazione di "login"*/
  void iniziaLogin() {
    Utente u = MenuLoginView.display();
    sessione.Accedi(u.getUserId(), u.getPassword());
    Utente uCorr = sessione.getUtenteCorrente();
    if (uCorr != null) {
      Feedback.success("Accesso eseguito, benvenuto " + uCorr.getUserId());
    }
  }


}