package model;

public class Valutazione {
  int stile;
  int contenuto;
  int gradevolezza;
  int originalita;
  int edizione;

  public Valutazione() { }

  public Valutazione(int stile, int contenuto, int gradevolezza, int originalita, int edizione) {
    this.stile = stile;
    this.contenuto = contenuto;
    this.gradevolezza = gradevolezza;
    this.originalita = originalita;
    this.edizione = edizione;
  }

  public int getStile() { return stile; }
  public int getContenuto() { return contenuto; }
  public int getGradevolezza() { return gradevolezza; }
  public int getOriginalita() { return originalita; }
  public int getEdizione() { return edizione; }
  public int getVotoFinale() { return (stile+contenuto+gradevolezza+originalita+edizione)/5; }

  public void setStile(int stile) { this.stile = stile; }
  public void setContenuto(int contenuto) { this.contenuto = contenuto; }
  public void setGradevolezza(int gradevolezza) { this.gradevolezza = gradevolezza; }
  public void setOriginalita(int originalita) { this.originalita = originalita; }
  public void setEdizione(int edizione) { this.edizione = edizione; }

}
