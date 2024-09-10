package model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.ToIntFunction;

public class GestoreValutazioni {

  List<Valutazione> valutazioni;
  String path_valutazioni;

  public GestoreValutazioni() {
    valutazioni = new LinkedList<>();
  }

  public GestoreValutazioni(String pathValutazioni) {
    path_valutazioni = pathValutazioni;
    caricaDati(pathValutazioni);
  }

  public GestoreValutazioni(List<Valutazione> listaValutazioni) {
    valutazioni = listaValutazioni;
  }


  public List<Valutazione> getValutazioni() { return valutazioni; }

  public void caricaDati(String pathValutazioni) {
    path_valutazioni = pathValutazioni;
    valutazioni = CSVFileManager.leggiDatiCsv(pathValutazioni, Valutazione.class);
  }

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

  public void aggiungi(Valutazione v) {
    List<Valutazione> list = new LinkedList<>();
    list.add(v);
    CSVFileManager.scriviDatiCsv(path_valutazioni, list, true);
    valutazioni.add(v);
  }

  public boolean valutazioneUtentePresente(String idUtente, String idLibro) {
    return !valutazioni.stream().filter(x -> x.getIdUtente().equals(idUtente)
        && x.getIdLibro().equals(idLibro)).toList().isEmpty();
  }

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

  int calcolaMediaValutazione(List<Valutazione> lista, ToIntFunction<Valutazione> metodo) {
    return (int) lista.stream().mapToInt(metodo).average().orElse(0);
  }

  public List<String> getRecensioni(Libro l) {
    List<Valutazione> valutazioni = cercaValutazioni(l);
    return valutazioni.stream().map(Valutazione::getRecensione).toList();
  }
}
