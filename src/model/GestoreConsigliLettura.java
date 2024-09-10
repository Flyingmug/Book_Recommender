package model;

import java.util.LinkedList;
import java.util.List;

public class GestoreConsigliLettura {
  List<ConsiglioLettura> elencoConsigli;
  String path_consigli;

  /**
   * Costruttore GestoreConsigliLettura.
   * Istanzia una lista di libri vuota
   */
  public GestoreConsigliLettura() { elencoConsigli = new LinkedList<>(); }

  public GestoreConsigliLettura(String pathLibri) {
    caricaDati(pathLibri);
    path_consigli = pathLibri;
  }

  /**
   * Crea un'istanza e setta il valore in input come elenco di libri.
   * @param elenco Lista di consigli */
  public GestoreConsigliLettura(List<ConsiglioLettura> elenco) { this.elencoConsigli = elenco; }

  /**
   * Preleva i dati dei libri e delle valutazioni tramite la classe {@link CSVFileManager}
   */
  public void caricaDati(String pathConsigli) {
    path_consigli = pathConsigli;
    elencoConsigli = CSVFileManager.leggiDatiCsv(pathConsigli, ConsiglioLettura.class);
  }

  /**
   * Restituisce tutti i libri consigliati al parametro l
   * @param l Libro
   * @return una lista di Libri
   */
  public List<Libro> cercaConsigliLibro(List<Libro> raccolta, Libro l) {
    // ottieni consigli associati a un libro
    List<ConsiglioLettura> consigliLettura = elencoConsigli.stream()
        .filter(x -> x.getIdLibro().equals(l.getIdLibro())).toList();

    // Inizializza la lista di libri consigliati
    List<Libro> libri = new LinkedList<>();

    // ottieni libri correlati
    for (ConsiglioLettura consiglio: consigliLettura) { // per ogni consiglio
      for (String idLibro: consiglio.getConsigli()) { // per ogni id libro consigliato
        if (idLibro != null && !idLibro.isBlank()) { // controllo validità id
          raccolta.stream()
              .filter(libro -> libro.getIdLibro().equals(idLibro))
              .findFirst().ifPresent(libri::add);
        }
      }
    }

    return libri;
  }

  /**
   * Aggiunge e salva un nuovo consiglio di lettura
   * @param c consiglio di lettura
   */
  public void aggiungi(ConsiglioLettura c) {
    List<ConsiglioLettura> list = new LinkedList<>();
    list.add(c);
    if (path_consigli != null) {
      CSVFileManager.scriviDatiCsv(path_consigli, list, true);
      elencoConsigli.add(c);
    }

  }

  /**
   * Verifica il raggiungimento del limite di consigli di libri per il libro {@code idLibro} dell' utente {@code idUtente}
   * @param idUtente utente
   * @param idLibro libro
   * @return {@code true} - se il limite è raggiunto, {@code false} altrimenti
   */
  public boolean consigliUtenteCompleti(String idUtente, String idLibro, int limite) {
    return elencoConsigli.stream().filter(x -> x.getIdUtente().equals(idUtente)
        && x.getIdLibro().equals(idLibro)).toList().getFirst().getConsigli().length >= limite;
  }

}
