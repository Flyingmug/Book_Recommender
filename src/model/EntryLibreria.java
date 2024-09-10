package model;

/**
 * Classe che mette in relazione i libri, gli utenti, e le librerie
 */
public class EntryLibreria {

  String idUtente;
  String idLibreria;
  String nomeLibreria;
  String idLibro;

  /**
   * Costruttore classe EntryLibreria.
   * @param idUtente id dell'utente correlato
   * @param idLibreria id della libreria
   * @param nomeLibreria nome della libreria
   * @param idLibro id del libro
   */
  public EntryLibreria(String idUtente, String idLibreria, String nomeLibreria, String idLibro) {
    this.idUtente = idUtente;
    this.idLibreria = idLibreria;
    this.nomeLibreria = nomeLibreria;
    this.idLibro = idLibro;
  }

  /**
   * @return idUtente
   */
  public String getIdUtente() { return idUtente; }
  /**
   * @return idLibreria
   */
  public String getIdLibreria() { return idLibreria; }
  /**
   * @return nomeLibreria
   */
  public String getNomeLibreria() {  return nomeLibreria; }
  /**
   * @return idLibro
   */
  public String getIdLibro() { return idLibro; }

  

}
