package model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class GestoreRaccolta {

  List<Libro> elenco;
  List<Valutazione> valutazioni;

  /**
   * Costruttore RaccoltaLibri.
   * Istanzia una lista di libri e una lista di valutazioni vuoti*/
  public GestoreRaccolta() {
    elenco = new LinkedList<>();
    valutazioni = new LinkedList<>();
  }

  /**
   * Setta il valore in input.
   * @param elenco List<Libro> */
  public GestoreRaccolta(List<Libro> elenco) {
    this.elenco = elenco;
  }

  /**
   * Restituisce la lista di libri.
   * @return String*/
  public List<Libro> getElenco() { return elenco; }


  /**
   * Preleva i dati dei libri e delle valutazioni tramite la classe CSVFileManager
   */
  public void caricaDati(String pathLibri, String pathValutazioni) {
    elenco = CSVFileManager.leggiDatiCsv(pathLibri, Libro.class);
    valutazioni = CSVFileManager.leggiDatiCsv(pathValutazioni, Valutazione.class);
  }

/**
 * Dopo aver verificato che l'utente abbia scelto un criterio adeguato, viene effettuata la ricerca del libro corrispondente.
 * @param titolo String
 * @param autori String
 * @param annoPubblicazione int
 * @param criterio CriterioRicerca
 * @return List<Libro>
 */
  public List<Libro> cercaLibro(String titolo, String autori, int annoPubblicazione, CriterioRicerca criterio) {

    List<Libro> risultato = new LinkedList<>();

    if (criterio == null) {
      return risultato;
    }
    switch (criterio) {
      case CriterioRicerca.TITOLO:
        risultato = elenco.stream()
                .filter(x -> x.getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                .toList();
        break;
      case CriterioRicerca.AUTORE:
        System.out.println("\nRicerca per autore");
        risultato = elenco.stream()
                .filter(x -> x.getAutori().toLowerCase().contains(autori.toLowerCase()))
                .toList();
        break;
      case CriterioRicerca.AUTORE_ANNO:
        risultato =  elenco.stream()
                .filter(x -> x.getAutori().toLowerCase().contains(autori.toLowerCase())
                        && x.getAnnoPubblicazione() == annoPubblicazione)
                .toList();
        break;
      default:
        break;
    }
    return risultato;

  }

  /**
   * Metodo utilizzato per ottenere un sottoinsieme di una lista di libri.
   * Viene usato nella paginazione dei risultati, per mostrare solo X libri alla volta.
   * @param elenco List<Libro>
   * @param salto int
   * @param limite int
   * @return List<Libro>
   */
  public List<Libro> sottoRaccolta(List<Libro> elenco, int salto, int limite) {
    return elenco.subList(salto, Math.min(salto + limite, elenco.size()));
  }

  /**
   * @param l Libro
   * @return Valutazione
   */
  public Valutazione ottieniValutazioneMediaLibro(Libro l) {

    /*la valutazione media qui funziona solo con la media di tutte le valutazioni?*/
    Valutazione valMedia = new Valutazione();
    valMedia.setStile(calcolaMediaValutazione(valutazioni, Valutazione::getStile));
    valMedia.setContenuto(calcolaMediaValutazione(valutazioni, Valutazione::getContenuto));
    valMedia.setGradevolezza(calcolaMediaValutazione(valutazioni, Valutazione::getGradevolezza));
    valMedia.setOriginalita(calcolaMediaValutazione(valutazioni, Valutazione::getOriginalita));
    valMedia.setEdizione(calcolaMediaValutazione(valutazioni, Valutazione::getEdizione));

    return valMedia;
  }
  int calcolaMediaValutazione(List<Valutazione> lista, ToIntFunction<Valutazione> metodo) {
    return (int) lista.stream().mapToInt(metodo).average().orElse(0);
  }

}
