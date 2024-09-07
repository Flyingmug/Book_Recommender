package model;

public class Valutazione {
  int stile;
  int contenuto;
  int gradevolezza;
  int originalita;
  int edizione;
  int votoFinale;

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
  public int getEdizione() { return originalita; }
  public int getVotoFinale() { return votoFinale; }

}
