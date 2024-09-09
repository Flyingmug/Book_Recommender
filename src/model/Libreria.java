package model;

import java.util.LinkedList;
import java.util.List;

public class Libreria {

  List<Libro> elenco;
  String idLibreria;
  String nome;

  public Libreria(String idLibreria, String nome) {
    elenco = new LinkedList<>();
    this.idLibreria = idLibreria;
    this.nome = nome;
  }

  public String getIdLibreria() { return idLibreria; }
  public String getNomeLibreria() { return nome; }
  public List<Libro> getElencoLibri() { return elenco; }
  public int getConteggio() { return elenco.size(); }

  public void aggiungiLibro(Libro libro) {
    if (!elenco.contains(libro)) {
      elenco.add(libro);
    }
  }

  @Override
  public boolean equals(Object obj) {
    return obj.getClass() == Libreria.class && this.nome.equals(((Libreria) obj).getNomeLibreria());
  }
}
