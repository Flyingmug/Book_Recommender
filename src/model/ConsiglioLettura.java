package model;

/**
 * Classe rappresentante i consigli di lettura di un utente per un libro
 */
public class ConsiglioLettura {
  String idLibro;
  String idUtente;
  String[] consigli;

  /**
   * Costruttore della classe ConsiglioLettura.
   * Assegna i consigli al vettore nelle rispettive posizioni
   * @param idLibro id libro
   * @param idUtente id utente
   * @param idConsiglio1 id libro consigliato
   * @param idConsiglio2 id libro consigliato
   * @param idConsiglio3 id libro consigliato
   */
  public ConsiglioLettura(String idLibro, String idUtente, String idConsiglio1, String idConsiglio2, String idConsiglio3) {
    this.idLibro = idLibro;
    this.idUtente = idUtente;
    consigli = new String[3];
    consigli[0] = idConsiglio1;
    consigli[1] = idConsiglio2;
    consigli[2] = idConsiglio3;
  }

  /**
   * Costruttore della classe ConsiglioLettura.
   * Crea un'istanza vuota.
   */
  public ConsiglioLettura() { consigli = new String[3]; }

  /**
   * @return id libro
   */
  public String getIdLibro() { return idLibro; }
  /**
   * @return id libro
   */
  public String getIdUtente() { return idUtente; }
  /**
   * @return id libro
   */
  public String[] getConsigli() { return consigli; }

  /**
   * @param idLibro id libro
   */
  public void setIdLibro(String idLibro) { this.idLibro = idLibro; }
  /**
   * @param idUtente id libro
   */
  public void setIdUtente(String idUtente) { this.idUtente = idUtente; }
  /**
   * @param id id libro
   */
  public void setIdConsiglio1(String id) { consigli[0] = id; }
  /**
   * @param id id primo libro consigliato
   */
  public void setIdConsiglio2(String id) { consigli[1] = id; }
  /**
   * @param id id secondo libro consigliato
   */
  public void setIdConsiglio3(String id) { consigli[2] = id; }
  /**
   * @param ids vettore di id di consigli
   */
  public void setIdConsigli(String[] ids) {
    if (ids == null) {
      throw new IllegalArgumentException("L'array di consigli non può essere nullo");
    }

    int numElementiDaCopiare = Math.min(ids.length, consigli.length);

    System.arraycopy(ids, 0, consigli, 0, numElementiDaCopiare);
  }
}
