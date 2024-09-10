package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  Classe responsabile della gestione delle librerie
 */
public class GestoreLibrerie {

  List<Libreria> librerie;
  String path_librerie;

  /**
   * Crea un'istanza e assegna il filepath {@code path_librerie} delle librerie
   * @param path_librerie path librerie
   */
  public GestoreLibrerie(String path_librerie) {
    librerie = new LinkedList<>();
    this.path_librerie = path_librerie;
  }

  /**
   * @return lista di librerie
   */
  public List<Libreria> getLibrerie() { return librerie; }

  /**
   * Legge i dati delle entries delle librerie al path impostato
   * e le converte in Librerie contenenti i relativi libri
   * @param userId id utente
   * @param raccoltaTotale raccolta totale di libri
   */
  public void caricaDatiLibrerie(String userId, List<Libro> raccoltaTotale) {

    librerie = new LinkedList<>();

    // Converti la lista di libri in un map, per salvare gli id
    Map<String, Libro> mapLibro = raccoltaTotale.stream()
        .collect(Collectors.toMap(Libro::getIdLibro, book -> book));

    // Leggi tutte le entries della libreria dal file csv
    List<EntryLibreria> librerieEntries = CSVFileManager.leggiDatiCsv(path_librerie, EntryLibreria.class);

    // Filtra le entries cosi' da avere solo quelle riguardanti l'utente corrente
    List<EntryLibreria> userFilteredList = librerieEntries.stream()
        .filter(x -> x.getIdUtente().equals(userId))
        .toList();

    // Raggruppa le entries per id libreria
    Map<String, List<EntryLibreria>> groupedByLibrary = userFilteredList.stream()
        .collect(Collectors.groupingBy(EntryLibreria::getIdLibreria));

    // Crea e riempi la lista di librerie
    for (Map.Entry<String, List<EntryLibreria>> entry : groupedByLibrary.entrySet()) {
      String libraryId = entry.getKey();
      List<EntryLibreria> libraryEntries = entry.getValue();

      // Ottieni il nome della libreria dalla prima entry
      String libraryName = libraryEntries.getFirst().getNomeLibreria();

      // Crea una nuova libreria
      Libreria library = new Libreria(libraryId, libraryName);

      // Add books to the library
      for (EntryLibreria libraryEntry : libraryEntries) {
        // Controlla se l' entry ha un idLibro valido
        String bookId = libraryEntry.getIdLibro();
        if (bookId != null && !bookId.equalsIgnoreCase("NULL") && !bookId.isBlank()) {
          // Trova il libro corrispondente dal map
          Libro book = mapLibro.get(bookId);

          if (book != null) {
            library.aggiungiLibro(book);
          } else {
            System.out.println("Warning: Book with ID " + bookId + " not found in the total collection.");
          }
        }
      }

      // Aggiungi la nuova libreria alla lista di librerie
      librerie.add(library);
    }

  }

  /**
   * Registra una nuova libreria convertendola in una lista di {@link EntryLibreria},
   * e la salva su file.
   * @param l libreria
   * @param idUtente id utente
   */
  public void registraLibreria(Libreria l, String idUtente) {
    List<EntryLibreria> entries = new LinkedList<>();
    for (Libro libro: l.getElencoLibri()) {
      entries.add(new EntryLibreria(idUtente, l.getIdLibreria(), l.getNomeLibreria(), libro.getIdLibro()));
    }
    librerie.add(l);
    CSVFileManager.scriviDatiCsv(path_librerie, entries, true);
  }

  /**
   * Elimina una libreria, rimuovendo ogni {@link EntryLibreria} dal file di salvataggio.
   * @param indice indice libreria da rimuovere
   * @param idUtente id utente
   */
  public void elimina(int indice, String idUtente) {
    Libreria libRimossa = librerie.remove(indice);
    List<EntryLibreria> entries = CSVFileManager.leggiDatiCsv(path_librerie, EntryLibreria.class);
    entries.removeAll(entries.stream().filter(x -> x.getNomeLibreria().equals(libRimossa.nome) && x.getIdUtente().equals(idUtente)).toList());
    CSVFileManager.scriviDatiCsv(path_librerie, entries, false);
  }
}
