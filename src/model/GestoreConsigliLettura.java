package model;

import java.util.LinkedList;
import java.util.List;

public class GestoreConsigliLettura {
  List<ConsiglioLettura> elencoConsigli;
  String path_consigli;

  /**
   * Costruttore GestoreConsigliLettura.
   * Istanzia una lista di libri vuota,*/
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
   * Preleva i dati dei libri e delle valutazioni tramite la classe CSVFileManager
   */
  public void caricaDati(String pathConsigli) {
    path_consigli = pathConsigli;
    elencoConsigli = CSVFileManager.leggiDatiCsv(pathConsigli, ConsiglioLettura.class);
  }

  /**
   * Restituisce tutti i consigli con l'idLibro corrispondente al parametro
   * @param l Libro
   * @return una lista di valutazioni
   */
  public List<ConsiglioLettura> cercaConsigliLibro(Libro l) {
    return elencoConsigli.stream().filter(x -> x.getIdLibro().equals(l.getIdLibro())).toList();
  }

  public void aggiungi(ConsiglioLettura c) {
    List<ConsiglioLettura> list = new LinkedList<>();
    list.add(c);
    CSVFileManager.scriviDatiCsv(path_consigli, list, true);
    elencoConsigli.add(c);
  }

  public boolean consigliUtenteCompleti(String idUtente, String idLibro) {
    return elencoConsigli.stream().filter(x -> x.getIdUtente().equals(idUtente)
        && x.getIdLibro().equals(idLibro)).toList().size() >= 3;
  }

}
