package controller;

import Utilities.Utils;
import model.*;
import Utilities.Feedback;
import view.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe Controller viene utilizzata per avviare il programma e presentare il menu' con le funzionalita' all'utente
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class Controller {

  final String path_libri = "./data/Libri.dati.csv";
  final String path_valutazioni = "./data/ValutazioniLibri.dati.csv";
  final String path_utenti_registrati = "./data/UtentiRegistrati.dati.csv";
  final String path_librerie_personali = "./data/Librerie.dati.csv";
  final String path_consigli_lettura = "./data/ConsigliLibri.dati.csv";

  GestoreRaccolta raccolta;
  GestoreSessione sessione;
  GestoreValutazioni valutazioni;
  GestoreConsigliLettura consigliLettura;
  Scanner scanner;
  final int DIM_PAGINA = 15;
  final int SELECTION_LIMIT = 3;

  /**
   * Costruttore Controller del sistema */
  public Controller() {
    raccolta = new GestoreRaccolta(path_libri);
    sessione = new GestoreSessione(path_utenti_registrati);
    valutazioni = new GestoreValutazioni(path_valutazioni);
    consigliLettura = new GestoreConsigliLettura(path_consigli_lettura);
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

      boolean accessoEseguito = sessione.getUtenteCorrente() != null;

      // DISPLAY deve avere nella descrizione del funzionamento indicata la restituzione della scelta fatta dall'utente
      scelta = accessoEseguito ?
          MenuPrincipaleView.displayFull() :
          MenuPrincipaleView.displayMinimal();

      switch (scelta.toLowerCase()) {
        case "c":
          iniziaRicerca(raccolta, ModalitaAccesso.READING, null);
          break;
        case "r":
          if (accessoEseguito)
            Feedback.warn("L'accesso è già stato eseguito");
          else
            iniziaRegistrazione();
          break;
        case "l":
          if (accessoEseguito)
            iniziaGestioneLibrerie();
          else
            iniziaLogin();
          break;
        case "o":
          if (accessoEseguito) {
            sessione.esci();
            Feedback.success("Utente disconnesso");
          } else
            Feedback.warn("L'accesso non è stato eseguito");
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
   * @param raccoltaLibri GestoreRaccolta
   * @param modalita ModalitaAccesso
   * @param richiesta RichiestaRicerca
   * @return List<Libro>
   */
  List<Libro> iniziaRicerca(GestoreRaccolta raccoltaLibri, ModalitaAccesso modalita, RichiestaRicerca richiesta) {

    boolean richiestaNuovaRicerca;
    List<Libro> libriSelezionati = new LinkedList<>();

    do {
      // Verifica la presenza di una richiesta iniziale
      if (richiesta != null) {
        List<Libro> risultati = raccoltaLibri.cercaLibro(richiesta);

        // mostra risultati di ricerca
        richiestaNuovaRicerca = iniziaPaginazioneRisultati(risultati, richiesta.getCriterio(), modalita, libriSelezionati);

        richiesta = null;
      } else {
        // Richiedi nuova chiave e criterio
        richiesta = MenuRicercaView.display();
        if (richiesta == null) return null;
        richiestaNuovaRicerca = true;
      }



    } while (richiestaNuovaRicerca);

    if (modalita.equals(ModalitaAccesso.SELECTING) ||
        (modalita.equals(ModalitaAccesso.LIMITED_SELECTING) && libriSelezionati.size() <= 3)) {
      return libriSelezionati;
    }
    return null;
  }



/**
 * Metodo che mostra i risultati di ricerca (libri) sotto forma di pagine tra cui scorrere
 * @param risultati RaccoltaLibri
 * @param criterio CriterioRicerca
 * @return true per una nuova richiesta di ricerca, false per richiedere di uscire */
  boolean iniziaPaginazioneRisultati(List<Libro> risultati, CriterioRicerca criterio, ModalitaAccesso modalita, List<Libro> libriSelezionati) {
    String sceltaOpzionePagina;
    int indicePaginaCorrente = 0;
    int numOccorrenze = risultati.size();
    int numeroPagine = (int) Math.max(1, Math.ceil((double) numOccorrenze/DIM_PAGINA));

    do {
      List<Libro> paginaRisultati = GestoreRaccolta.sottoRaccolta(risultati, indicePaginaCorrente*DIM_PAGINA, DIM_PAGINA);

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
                indicePaginaCorrente = numPaginaSel - 1;
              else
                Feedback.warn("Pagina inesistente");

            } else
              Feedback.warn("Solo numeri interi ammessi");
            break;

          case "c":
            return true;

          case "e":
            return false;

          default:

            // aggiunta di libri alla libreria
             if (Utils.isInteger(sceltaOpzionePagina)) {
              // visualizzazione dati di un libro
              int indice = Integer.parseInt(sceltaOpzionePagina);
              if (modalita == ModalitaAccesso.SELECTING || modalita == ModalitaAccesso.LIMITED_SELECTING) {
                libriSelezionati.add(risultati.get(indice));
                Feedback.info("Libro selezionato");

                if (modalita == ModalitaAccesso.LIMITED_SELECTING && libriSelezionati.size() == 3) {
                  Feedback.info("3 libri selezionati");
                  return false;
                }
              } else if (indice > 0 && indice <= numOccorrenze) {
                visualizzaLibro(risultati.get(indice-1), modalita);
              } else
                Feedback.warn("Indice selezionato non valido");
            } else
              Feedback.warn("Operazione inesistente");
            break;
        }

    } while (true);

  }



/**
 * Metodo utilizzato per stampare a video il/i libro/i richiesto (tramite classe DisplayLibroView)
 * @param l Libro
 */
  void visualizzaLibro(Libro l, ModalitaAccesso modalita) {

    Valutazione valMedia = valutazioni.valutazioneMedia(l);

    boolean uscitaPaginaLibro = false;
    do {

      String scelta = DisplayLibroView.display(l, valMedia, modalita);
      switch (scelta.toLowerCase()) {
        case "s":
          if (modalita.equals(ModalitaAccesso.OPERATING)) {
            if (!consigliLettura.consigliUtenteCompleti(sessione.getUtenteCorrente().getUserId(), l.getIdLibro())) {
              inserisciSuggerimentoLibro(l);
              Feedback.success("Suggerimento inserito");
            } else {
              Feedback.warn("Sono già stati inseriti 3 consigli per questo libro");
            }
          }
          break;
        case "v":
          if (modalita.equals(ModalitaAccesso.OPERATING)) {
            if (!valutazioni.valutazioneUtentePresente(sessione.getUtenteCorrente().getUserId(), l.getIdLibro())) {
              inserisciValutazioneLibro(l);
              Feedback.success("Valutazione inserita");
            } else {
              Feedback.warn("La valutazione per il libro è già stata inserita");
            }
          }
          break;
        case "e":
          break;
      }

      if (scelta.equalsIgnoreCase("c")) {
        System.out.println("Placeholder");
        System.out.println("Placeholder");  // mostra consigli di libri
        System.out.println("Placeholder");
      } else {
        uscitaPaginaLibro = true;
      }

    } while(!uscitaPaginaLibro);
  }

  /**
   * Metodo che aggiunge la valutazione di un determinato libro nel file.
   * @param l Libro
   */
  void inserisciValutazioneLibro(Libro l) {

    Valutazione v = MenuValutazioneView.display();
    if (v != null) {
      v.setIdLibro(l.getIdLibro());
      v.setIdUtente(sessione.getUtenteCorrente().getUserId());
      valutazioni.aggiungi(v);
    } else
      Feedback.warn("Il valore inserito non rispetta le condizioni indicate");
  }

  /**
   *
   * @param l Libro
   */
  void inserisciSuggerimentoLibro(Libro l) {

    MenuConsigliLetturaView.display();
    List<Libro> libriSelezionati = iniziaRicerca(raccolta, ModalitaAccesso.LIMITED_SELECTING, null);
    if (!libriSelezionati.isEmpty()) {
      // estrazione id libri
      String[] idLibri = new String[SELECTION_LIMIT];
      for(int i = 0; i < Math.min(idLibri.length, libriSelezionati.size()); i++) {
        idLibri[i] = libriSelezionati.get(i).getIdLibro();
      }
      ConsiglioLettura c = new ConsiglioLettura();
      c.setIdLibro(l.getIdLibro());
      c.setIdUtente(sessione.getUtenteCorrente().getUserId());
      try {
        c.setIdConsigli(idLibri);
      } catch (IllegalArgumentException | NullPointerException e) {
        Feedback.err(e.getMessage());
      }
      // aggiunta consiglio di lettura
      consigliLettura.aggiungi(c);
    } else
      Feedback.warn("Non sono stati selezionati consigli");
  }



  void iniziaGestioneLibrerie() {

    String idUtenteCorrente = sessione.getUtenteCorrente().getUserId();

    GestoreLibrerie libreriePersonali = new GestoreLibrerie(path_librerie_personali);
    libreriePersonali.caricaDatiLibrerie(idUtenteCorrente, raccolta.getElenco());

    String sceltaOperazioneLibreria;
    boolean uscitaGestioneLibrerie = false;
    int indicePaginaCorrente = 0;
    int numOccorrenze = libreriePersonali.getLibrerie().size();
    int numeroPagine = (int) Math.max(1, Math.ceil((double) numOccorrenze/DIM_PAGINA));


    do {
      // aggiungere paginazione delle librerie o limite di librerie per utente
      sceltaOperazioneLibreria = MenuLibreriePersonali.display(libreriePersonali.getLibrerie(), numOccorrenze);

      switch (sceltaOperazioneLibreria.toLowerCase()) {
        case "n":
          Libreria lib = MenuLibreriePersonali.displayCreazione();
          if (!libreriePersonali.getLibrerie().contains(lib)) {

            Feedback.info("Seleziona i libri da aggiungere alla libreria");
            List<Libro> selezioni = iniziaRicerca(raccolta, ModalitaAccesso.SELECTING, null);
            lib.aggiungiListaLibri(selezioni);
            libreriePersonali.registraLibreria(lib, idUtenteCorrente);
            numOccorrenze++;
            Feedback.success("Libreria creata.");
          } else
            Feedback.warn("La libreria " + lib.getNomeLibreria() + " è già presente");
          break;

        case "r":
          String indiceEliminazione = MenuLibreriePersonali.displayEliminazione();
          if (Utils.isInteger(indiceEliminazione)) {
            // visualizzazione dati di un libro
            int indice = Integer.parseInt(indiceEliminazione);
            if (indice > 0 && indice <= numOccorrenze) {
              libreriePersonali.elimina(--indice, idUtenteCorrente);
              numOccorrenze--;
              Feedback.success("Libreria eliminata");
            } else
              Feedback.warn("Indice selezionato non valido");
          } else
            Feedback.warn("Solo numeri interi ammessi");
          break;

        case "e":
          uscitaGestioneLibrerie = true;

        default:
          if (Utils.isInteger(sceltaOperazioneLibreria)) {
            // visualizzazione dati di un libro
            int indice = Integer.parseInt(sceltaOperazioneLibreria);
            if (indice > 0 && indice <= numOccorrenze) {
              visualizzaLibreria(libreriePersonali.getLibrerie().get(indice-1));
            } else
              Feedback.warn("Indice selezionato non valido");
          } else
            Feedback.warn("Operazione inesistente");
          break;
      }


    } while (!uscitaGestioneLibrerie);

  }



  public void visualizzaLibreria(Libreria libreria) {

    // crea una richiesta di ricerca nulla (restituisce tutti i risultati)
    RichiestaRicerca richiestaInit =
        new RichiestaRicerca("", "", 0, CriterioRicerca.TITOLO);

    GestoreRaccolta raccoltaParziale = new GestoreRaccolta(libreria.getElencoLibri());
    iniziaRicerca(raccoltaParziale, ModalitaAccesso.OPERATING, richiestaInit);

  }



  void iniziaRegistrazione() {
    System.out.println("\n\t╠═══════ Registrazione ═══════╣\n\n");

    Utente u = MenuRegistrazioneView.display();
    if (u != null) {
      try {
        sessione.registra(u);
      } catch (RuntimeException e) {
        Feedback.err(e.getMessage());
      } finally {
        Feedback.success("Registrazione avvenuta con successo");
      }
    } else {
      Feedback.warn("Registrazione annullata");
    }


  }


/**
 * Semplice stampa a video per indicare la selezione dell'operazione di "login"*/
  void iniziaLogin() {
    Utente u = MenuLoginView.display();
    sessione.accedi(u.getUserId(), u.getPassword());
    Utente uCorr = sessione.getUtenteCorrente();
    if (uCorr != null) {
      Feedback.success("Accesso eseguito, benvenuto " + uCorr.getUserId());
    } else {
      Feedback.warn("Utente non registrato");
    }
  }


}