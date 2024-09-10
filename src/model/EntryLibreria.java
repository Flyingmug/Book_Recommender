package model;

/**
 *Classe che mette in relazione i libri, gli utenti, e le librerie
 */
public class EntryLibreria {

  String idUtente;
  String idLibreria;
  String nomeLibreria;
  String idLibro;

  /**
   * Costruttore classe EntryLibreria.
   * @param idUtente String
   * @param idLibreria String
   * @param nomeLibreria String
   * @param idLibro String
   */
  public EntryLibreria(String idUtente, String idLibreria, String nomeLibreria, String idLibro) {
    this.idUtente = idUtente;
    this.idLibreria = idLibreria;
    this.nomeLibreria = nomeLibreria;
    this.idLibro = idLibro;
  }

  /**
   * @return String
   */
  public String getIdUtente() { return idUtente; }
  /**
   * @return String
   */
  public String getIdLibreria() { return idLibreria; }
  /**
   * @return String
   */
  public String getNomeLibreria() {  return nomeLibreria; }
  /**
   * @return String
   */
  public String getIdLibro() { return idLibro; }

  

}
