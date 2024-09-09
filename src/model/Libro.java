package model;

import java.util.UUID;

/**
 * Questa classe definisce le caratteristiche dei libri, composti da titolo, autore, editore, categoria,
 * e fornisce i metodi necessari per:
 * - settare alcuni parametri (setEditore / setCategorie)
 * - o reperirli (getTitolo / toShortHandFullString)
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class Libro {

  String idLibro;
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
  public Libro(String idLibro, String titolo, String autori, int annoPubblicazione, String editore, String categorie) {
    this.idLibro = idLibro;
    this.titolo = titolo;
    this.autori = autori;
    this.annoPubblicazione = annoPubblicazione;
    this.editore = editore;
    this.categorie = categorie;
  }

  public String getIdLibro() { return idLibro; }
  public String getTitolo() { return titolo; }
  public String getAutori() { return autori; }
  public int getAnnoPubblicazione() { return annoPubblicazione; }
  public String getEditore() { return editore; }
  public String getCategorie() { return categorie; }

  /**
   * Imposta un nuovo id al libro, eventualmente sovrascrivendo quello presente
   */
  public void setNewId() {
    UUID uuid = UUID.randomUUID();
    this.idLibro = uuid.toString();
  }

  /**
   * Reperisce la stringa titolo+autore+annoPubblicazione
   * @return String*/
  public String toShortHandFullString() {
    return titolo + ", " + autori + ", " + annoPubblicazione;
  }

  @Override
  public boolean equals(Object obj) {
    return obj.getClass() == Libro.class && this.idLibro.equals(((Libro) obj).getIdLibro());
  }
}
