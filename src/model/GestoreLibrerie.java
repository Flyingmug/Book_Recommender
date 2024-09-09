package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestoreLibrerie {

  List<Libreria> librerie;
  String path_librerie;

  public GestoreLibrerie(String path_librerie) {
    this.path_librerie = path_librerie;
  }



  public List<Libreria> getLibrerie() { return librerie; }

  public void caricaDatiLibrerie(String userId, List<Libro> raccoltaTotale) {
    /*librerie = new LinkedList<>();

    // lettura tutti i dati delle librerie
    List<EntryLibreria> librerieEntries = CSVFileManager.leggiDatiCsv(path_librerie, EntryLibreria.class);

    // selezione delle entry dell'userId corrispondente
    List<EntryLibreria> userFilteredList = librerieEntries.stream().filter(x -> x.getIdUtente().equals(userId)).toList();

    // selezione delle librerie
    // Crea un map per raggruppare le entries in base al loro idLibreria
    Map<String, List<EntryLibreria>> groupByIdLibreria = userFilteredList.stream()
        .collect(Collectors.groupingBy(EntryLibreria::getIdLibreria));


    // Crea e riempe la lista di Librerie
    for (Map.Entry<String, List<EntryLibreria>> entry : groupByIdLibreria.entrySet()) {
      String idLibreria = entry.getKey();
      List<EntryLibreria> entriesLibreria = entry.getValue();

      // Imposta il nome della libreria come il primo nome trovato
      String nomeLibreria = entriesLibreria.getFirst().getNomeLibreria();

      // Crea una nuova libreria
      Libreria lib = new Libreria(idLibreria, nomeLibreria);

      // Aggiungi libri alla libreria creata
      for (EntryLibreria libraryEntry : entriesLibreria) {
        // Cerca il libro corrispondente tramite l'id del libro
        String idLibro = libraryEntry.getIdLibro();
        Libro l = raccoltaTotale.stream()
            .filter(b -> b.getIdLibro().equals(idLibro))
            .findFirst()
            .orElse(null); // Caso in cui il libro non è trovato

        lib.aggiungiLibro(l);

      }

      // Aggiungi la libreria creata alla lista di librerie
      librerie.add(lib);
    }*/

    librerie = new LinkedList<>();

    // Convert the list of books to a map for faster lookup by book ID
    Map<String, Libro> mapLibro = raccoltaTotale.stream()
        .collect(Collectors.toMap(Libro::getIdLibro, book -> book));

    // Read all library entries from the CSV file
    List<EntryLibreria> librerieEntries = CSVFileManager.leggiDatiCsv(path_librerie, EntryLibreria.class);

    // Filter entries to only include those belonging to the specified user
    List<EntryLibreria> userFilteredList = librerieEntries.stream()
        .filter(x -> x.getIdUtente().equals(userId))
        .toList();

    // Create a map to group entries by their library ID
    Map<String, List<EntryLibreria>> groupedByLibrary = userFilteredList.stream()
        .collect(Collectors.groupingBy(EntryLibreria::getIdLibreria));

    // Create and populate the list of Libreria objects
    for (Map.Entry<String, List<EntryLibreria>> entry : groupedByLibrary.entrySet()) {
      String libraryId = entry.getKey();
      List<EntryLibreria> libraryEntries = entry.getValue();

      // Get the library name from the first entry
      String libraryName = libraryEntries.getFirst().getNomeLibreria();

      // Create a new Libreria object
      Libreria library = new Libreria(libraryId, libraryName);

      // Add books to the library
      for (EntryLibreria libraryEntry : libraryEntries) {
        // Check if the entry has a valid book ID
        String bookId = libraryEntry.getIdLibro();
        if (bookId != null && !bookId.equalsIgnoreCase("NULL") && !bookId.isBlank()) {
          // Find the corresponding Libro object from the map
          Libro book = mapLibro.get(bookId);

          if (book != null) {
            library.aggiungiLibro(book);
          } else {
            System.out.println("Warning: Book with ID " + bookId + " not found in the total collection.");
          }
        }
      }

      // Add the populated library (even if it has no books) to the list of libraries
      librerie.add(library);
    }

  }

  public void aggiungiLibreria(Libreria l, String idUtente) {

    librerie.add(l);
    List<EntryLibreria> entries = new LinkedList<>();
    entries.add(new EntryLibreria(idUtente, l.getIdLibreria(), l.getNomeLibreria(), "NULL"));
    CSVFileManager.scriviDatiCsv(path_librerie, entries, true);
  }

  public void elimina(int indice) {
    librerie.remove(indice);
  }
}
