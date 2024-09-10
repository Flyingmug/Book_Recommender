package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Elenca le caratteristiche delle librerie e fornisce un metodo per aggiungere libri
 */
public class Libreria {

  List<Libro> elenco;
  String idLibreria;
  String nome;

  /**
   *
   * @param idLibreria
   * @param nome
   */
  public Libreria(String idLibreria, String nome) {
    elenco = new LinkedList<>();
    this.idLibreria = idLibreria;
    this.nome = nome;
  }

  public String getIdLibreria() { return idLibreria; }
  public String getNomeLibreria() { return nome; }
  public List<Libro> getElencoLibri() { return elenco; }
  public int getConteggio() { return elenco.size(); }

  /**
   *
   * @param libro
   */
  public void aggiungiLibro(Libro libro) {
    if (!elenco.contains(libro)) {
      elenco.add(libro);
    }
  }

  public void aggiungiListaLibri(List<Libro> lista) {
    if (!lista.isEmpty()) {
      elenco.addAll(lista);
    }
  }

  /**
   *
   * @param obj
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    return obj.getClass() == Libreria.class && this.nome.equals(((Libreria) obj).getNomeLibreria());
  }
}
