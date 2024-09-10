package model;

/**
 * @author Selimi Sebian
 */
public class ConsiglioLettura {
  String idLibro;
  String idUtente;
  String[] consigli;

  /**
   *
   * @param idLibro
   * @param idUtente
   * @param idConsiglio1
   * @param idConsiglio2
   * @param idConsiglio3
   */
  public ConsiglioLettura(String idLibro, String idUtente, String idConsiglio1, String idConsiglio2, String idConsiglio3) {
    this.idLibro = idLibro;
    this.idUtente = idUtente;
    consigli = new String[3];
    consigli[0] = idConsiglio1;
    consigli[1] = idConsiglio2;
    consigli[2] = idConsiglio3;
  }


  public ConsiglioLettura() { consigli = new String[3]; }

  /**
   * @return String
   */
  public String getIdLibro() { return idLibro; }
  /**
   * @return String
   */
  public String getIdUtente() { return idUtente; }
  /**
   * @return String[]
   */
  public String[] getConsigli() { return consigli; }

  /**
   * @param idLibro
   */
  public void setIdLibro(String idLibro) { this.idLibro = idLibro; }

  /**
   * @param idUtente
   */
  public void setIdUtente(String idUtente) { this.idUtente = idUtente; }
  public void setIdConsiglio1(String id) { consigli[0] = id; }
  public void setIdConsiglio2(String id) { consigli[1] = id; }
  public void setIdConsiglio3(String id) { consigli[2] = id; }

  /**
   * @param ids
   */
  public void setIdConsigli(String[] ids) {
    if (ids == null) {
      throw new IllegalArgumentException("L'array di consigli non può essere nullo");
    }

    int numElementiDaCopiare = Math.min(ids.length, consigli.length);

    System.arraycopy(ids, 0, consigli, 0, numElementiDaCopiare);
  }
}
