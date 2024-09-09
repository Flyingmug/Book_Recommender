package model;

public class EntryLibreria {

  String idUtente;
  String idLibreria;
  String nomeLibreria;
  String idLibro;

  public EntryLibreria(String idUtente, String idLibreria, String nomeLibreria, String idLibro) {
    this.idUtente = idUtente;
    this.idLibreria = idLibreria;
    this.nomeLibreria = nomeLibreria;
    this.idLibro = idLibro;
  }

  public String getIdUtente() { return idUtente; }
  public String getIdLibreria() { return idLibreria; }
  public String getNomeLibreria() {  return nomeLibreria; }
  public String getIdLibro() { return idLibro; }

  

}
