package model;

import java.util.LinkedList;
import java.util.List;

public class RaccoltaLibri {

  List<Libro> elenco;

  public RaccoltaLibri() {
    elenco = new LinkedList<>();
  }

  public RaccoltaLibri(List<Libro> elenco) {
    this.elenco = elenco;
  }

  public List<Libro> getElenco() { return elenco; }
  public void setElenco(LinkedList<Libro> e) { elenco = e; }

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
}
