package model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.ToIntFunction;

public class RaccoltaLibri {

  List<Libro> elenco;
  List<Valutazione> valutazioni;

  public RaccoltaLibri() {
    elenco = new LinkedList<>();
    valutazioni = new LinkedList<>();
  }


  public RaccoltaLibri(List<Libro> elenco) {
    this.elenco = elenco;
  }

  public List<Libro> getElenco() { return elenco; }



  public void caricaDati() {
    elenco = CSVFileManager.leggiDatiCsv("./data/Libri.dati.csv", Libro.class);
    valutazioni = CSVFileManager.leggiDatiCsv("./data/ValutazioniLibri.dati.csv", Valutazione.class);
  }


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

  public List<Libro> sottoRaccolta(List<Libro> elenco, int salto, int limite) {
    return elenco.subList(salto, Math.min(salto + limite, elenco.size()));
  }

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
