package model;

/**
 * Questa classe definisce le caratteristiche dei libri, composti da titolo, autore, editore, categoria,
 * e fornisce i metodi necessari per:
 * - settare alcuni parametri (setEditore / setCategorie)
 * - o reperirli (getTitolo / toShortHandFullString)
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class Libro {

  int annoPubblicazione;
  String titolo;
  String autori;

  String editore;
  String categorie;

  /**
   * Costruttore Libro
   * @param autori String
   * @param titolo String
   * @param annoPubblicazione int
   */
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

  /**
   * Setta il valore in input
   * @param editore String*/
  public void setEditore(String editore) {
    this.editore = editore;
  }

  /**
   * Setta il valore in input
   * @param categorie String*/
  public void setCategorie(String categorie) {
    this.categorie = categorie;
  }

  /**
   * Reperisce la stringa titolo+autore+annoPubblicazione
   * @return String*/
  public String toShortHandFullString() {
    return titolo + ", " + autori + ", " + annoPubblicazione;
  }

}
