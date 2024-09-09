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
   * @param idUtente
   * @param idLibreria
   * @param nomeLibreria
   * @param idLibro
   */
  public EntryLibreria(String idUtente, String idLibreria, String nomeLibreria, String idLibro) {
    this.idUtente = idUtente;
    this.idLibreria = idLibreria;
    this.nomeLibreria = nomeLibreria;
    this.idLibro = idLibro;
  }

  /**
   * @return
   */
  public String getIdUtente() { return idUtente; }
  /**
   * @return
   */
  public String getIdLibreria() { return idLibreria; }
  /**
   * @return
   */
  public String getNomeLibreria() {  return nomeLibreria; }
  /**
   * @return
   */
  public String getIdLibro() { return idLibro; }

  

}
