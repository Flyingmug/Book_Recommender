import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GestoreRaccolta {

  LinkedList<Libro> raccolta;
  Utente utenteCorrente;

  public GestoreRaccolta() {
    // temporary path selection
    System.out.println("\n\n\n||||Directory:|||| " + System.getProperty("user.dir") + "\n");
    raccolta = CSVFileManager.leggiFile("./data/BooksDatasetClean.csv");
  }

  public void Test() {
    System.out.println("||||Gestore libreria||||");
    System.out.println("||||Dimensione libreria||||:" + raccolta.size());
  }

  public List<Libro> cercaLibro(String chiave, CriterioRicerca criterio) {

    List<Libro> risultato;

    switch (criterio) {
      case TITOLO:
        System.out.println("\nRicerca per titolo");
        risultato = raccolta.stream()
            .filter(x -> x.getTitolo().toLowerCase().contains(chiave.toLowerCase()))
            .toList();
        break;
      case AUTORE:
        System.out.println("\nRicerca per autore");
        risultato = raccolta.stream()
            .filter(x -> x.getAutori().toLowerCase().contains(chiave.toLowerCase()))
            .toList();
        break;
      case AUTORE_ANNO:
        System.out.println("\nRicerca per autore e anno di pubblicazione");
        risultato = raccolta.stream()
            .filter(x -> x.getTitolo().toLowerCase().contains(chiave.toLowerCase()))
            .toList();
        break;
      default:
        risultato = new LinkedList<Libro>();
        break;
    }

    return risultato;
  }

  public List<Libro> cercaLibro(String autore, int anno) {
    return raccolta.stream()
        .filter(x -> x.getAutori().toLowerCase().contains(autore)
            && x.getAnnoPubblicazione() == anno)
        .toList();
  }

  public void StampaSottoLista() {

  }

}
