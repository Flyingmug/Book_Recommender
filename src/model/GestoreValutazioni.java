package model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Classe responsabile di gestire le valutazioni dei libri
 * @author Sebian Selimi
 * @author Alexander Moscatelli
 */
public class GestoreValutazioni {

  List<Valutazione> valutazioni;
  String path_valutazioni;

  /**
   * Costruttore classe GestoreValutazioni.
   * Crea un elenco di valutazioni vuoto
   */
  public GestoreValutazioni() {
    valutazioni = new LinkedList<>();
  }

  /**
   * Costruttore classe GestoreValutazioni.
   * Legge le valutazioni dal file {@code pathValutazioni} e le imposta nell'elenco
   * @param pathValutazioni path file valutazioni
   */
  public GestoreValutazioni(String pathValutazioni) {
    path_valutazioni = pathValutazioni;
    caricaDati(pathValutazioni);
  }

  /**
   * Costruttore classe GestoreValutazioni.
   * Crea un elenco di valutazioni impostando la lista parametro come elenco attuale
   */
  public GestoreValutazioni(List<Valutazione> listaValutazioni) {
    valutazioni = listaValutazioni;
  }

  /**
   * @return lista di valutazioni
   */
  public List<Valutazione> getValutazioni() { return valutazioni; }

  /**
   * Carica i dati dal file di valutazioni tramite la classe {@link CSVFileManager}
   * @param pathValutazioni path file valutazioni
   */
  public void caricaDati(String pathValutazioni) {
    path_valutazioni = pathValutazioni;
    valutazioni = CSVFileManager.leggiDatiCsv(pathValutazioni, Valutazione.class);
  }

  /**
   * @return conteggio delle valutazioni
   */
  public int count() {
    return valutazioni.size();
  }

  /**
   * Restituisce tutte le valutazioni con l'idLibro corrispondente al parametro
   * @param l Libro
   * @return una lista di valutazioni
   */
  public List<Valutazione> cercaValutazioni(Libro l) {
    return valutazioni.stream().filter(x -> x.getIdLibro().equals(l.getIdLibro())).toList();
  }

  /**
   * Aggiunge una valutazione v alla lista di valutazioni e la salva sul file
   * @param v valutazione
   */
  public void aggiungi(Valutazione v) {
    List<Valutazione> list = new LinkedList<>();
    list.add(v);
    if (path_valutazioni != null) {
      CSVFileManager.scriviDatiCsv(path_valutazioni, list, true);
      valutazioni.add(v);
    }
  }

  /**
   * Verifica la presenza di una valutazione per il libro {@code idLibro} dell' utente {@code idUtente}
   * @param idUtente id utente
   * @param idLibro id libro
   * @return {@code true} se presente, {@code false} altrimenti
   */
  public boolean valutazioneUtentePresente(String idUtente, String idLibro) {
    return !valutazioni.stream().filter(x -> x.getIdUtente().equals(idUtente)
        && x.getIdLibro().equals(idLibro)).toList().isEmpty();
  }

  /**
   * Calcola e restituisce una valutazione rappresentante la media di tutte le valutazioni per il libro {@code l}
   * @param l libro
   * @return valutazione media
   */
  public Valutazione valutazioneMedia(Libro l) {
    List<Valutazione> val = cercaValutazioni(l);

    Valutazione valMedia = new Valutazione();
    valMedia.setStile(calcolaMediaValutazione(val, Valutazione::getStile));
    valMedia.setContenuto(calcolaMediaValutazione(val, Valutazione::getContenuto));
    valMedia.setGradevolezza(calcolaMediaValutazione(val, Valutazione::getGradevolezza));
    valMedia.setOriginalita(calcolaMediaValutazione(val, Valutazione::getOriginalita));
    valMedia.setEdizione(calcolaMediaValutazione(val, Valutazione::getEdizione));
    valMedia.setRecensione("");

    return valMedia;
  }

  /**
   * Calcola la valutazione media
   * @param lista valutazioni
   * @param metodo get del campo su cui eseguire il calcolo della media
   * @return la media arrotondata a valore intero
   */
  int calcolaMediaValutazione(List<Valutazione> lista, ToIntFunction<Valutazione> metodo) {
    return (int) lista.stream().mapToInt(metodo).average().orElse(0);
  }

  public List<String> getRecensioni(Libro l) {
    List<Valutazione> valutazioni = cercaValutazioni(l);
    return valutazioni.stream().map(Valutazione::getRecensione).toList();
  }
}
