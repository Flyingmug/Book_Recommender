package model;

import java.util.LinkedList;
import java.util.List;

public class RaccoltaLibri {

  LinkedList<Libro> elenco;

  public RaccoltaLibri() {
    elenco = new LinkedList<>();
  }

  public RaccoltaLibri(LinkedList<Libro> elenco) {
    this.elenco = elenco;
  }

  public LinkedList<Libro> getElenco() { return elenco; }
  public void setElenco(LinkedList<Libro> e) { elenco = e; }

  public LinkedList<Libro> cercaLibro(String titolo, String autori, int annoPubblicazione, CriterioRicerca criterio) {

    LinkedList<Libro> risultato = new LinkedList<>();

    if (criterio == null) {
      return risultato;
    }
    switch (criterio) {
      case CriterioRicerca.TITOLO:
        risultato = (LinkedList<Libro>) elenco.stream()
                .filter(x -> x.getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                .toList();
        break;
      case CriterioRicerca.AUTORE:
        System.out.println("\nRicerca per autore");
        risultato = (LinkedList<Libro>) elenco.stream()
                .filter(x -> x.getAutori().toLowerCase().contains(autori.toLowerCase()))
                .toList();
        break;
      case CriterioRicerca.AUTORE_ANNO:
        risultato = (LinkedList<Libro>) elenco.stream()
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
