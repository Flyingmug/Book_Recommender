package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe che gestisce le librerie
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class GestoreRaccolta {

  List<Libro> elenco;

  /**
   * Costruttore RaccoltaLibri.
   * Istanzia una lista di libri vuota,*/
  public GestoreRaccolta() {
    elenco = new LinkedList<>();
  }

  public GestoreRaccolta(String pathLibri) {
    caricaDati(pathLibri);
  }

  /**
   * Crea un'istanza e setta il valore in input come elenco di libri.
   * @param elenco Lista di libri */
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
  public void caricaDati(String pathLibri) {
    elenco = CSVFileManager.leggiDatiCsv(pathLibri, Libro.class);
    /*valutazioni = CSVFileManager.leggiDatiCsv(pathValutazioni, Valutazione.class);*/
  }

/**
 * Dopo aver verificato che l'utente abbia scelto un criterio adeguato, viene effettuata la ricerca del libro corrispondente.
 * @param richiesta RichiestaRicerca
 * @return List<Libro>
 */
  public List<Libro> cercaLibro(RichiestaRicerca richiesta) {

    List<Libro> risultato = new LinkedList<>();

    if (richiesta.getCriterio() == null) {
      return risultato;
    }
    switch (richiesta.getCriterio()) {
      case CriterioRicerca.TITOLO:
        risultato = elenco.stream()
                .filter(x -> x.getTitolo().toLowerCase().contains(richiesta.getTitolo().toLowerCase()))
                .toList();
        break;
      case CriterioRicerca.AUTORE:
        System.out.println("\nRicerca per autore");
        risultato = elenco.stream()
                .filter(x -> x.getAutori().toLowerCase().contains(richiesta.getAutore().toLowerCase()))
                .toList();
        break;
      case CriterioRicerca.AUTORE_ANNO:
        risultato =  elenco.stream()
                .filter(x -> x.getAutori().toLowerCase().contains(richiesta.getAutore().toLowerCase())
                        && x.getAnnoPubblicazione() == richiesta.getAnnoPubblicazione())
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
  public static List<Libro> sottoRaccolta(List<Libro> elenco, int salto, int limite) {
    return elenco.subList(salto, Math.min(salto + limite, elenco.size()));
  }

}
