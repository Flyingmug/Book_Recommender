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

  public void setEditore(String editore) {
    this.editore = editore;
  }

  public void setCategorie(String categorie) {
    this.categorie = categorie;
  }

  public String toShortHandFullString() {
    return titolo + ", " + autori + ", " + annoPubblicazione;
  }

}
