package model;

public class Libro {

  int annoPubblicazione;
  String titolo;
  String autori;

  String editore;
  String categorie;

  public Libro(String titolo, String autori, int annoPubblicazione) {
    this.titolo = titolo;
    this.autori = autori;
    this.annoPubblicazione = annoPubblicazione;
  }

  public String getTitolo() { return titolo; }
  public String getAutori() { return autori; }
  public int getAnnoPubblicazione() { return annoPubblicazione; }
  public String getEditore() { return editore; }
  public String getCategorie() { return categorie; }

  /*public String getAutoriString() {
    String s = "";
    for(int i = 0; i < autori.length; i++) {
      s.concat(autori[i] + (i+1 < autori.length ? ", " : ""));
    }
    return s;
  }*/

  public String toShortHandFullString() {
    return titolo + ", " + autori + ", " + annoPubblicazione;
  }

}
