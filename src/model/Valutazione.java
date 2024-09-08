package model;

/**
 * Classe utilizzata per definire le caratteristiche delle valutazioni, in termini di stile, contenuto, gradevolezza, originalita' ed edizione.
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class Valutazione {
  int stile;
  int contenuto;
  int gradevolezza;
  int originalita;
  int edizione;

  public Valutazione() { }

  /**
   * Costruttore.
   * I parametri passati vengono utilizzati per creare un'istanza
   * @param stile int
   * @param contenuto int
   * @param gradevolezza int
   * @param originalita int
   * @param edizione int
   */
  public Valutazione(int stile, int contenuto, int gradevolezza, int originalita, int edizione) {
    this.stile = stile;
    this.contenuto = contenuto;
    this.gradevolezza = gradevolezza;
    this.originalita = originalita;
    this.edizione = edizione;
  }

  /**
   * Metodo per reperire il campo: stile.
   * @return int*/
  public int getStile() { return stile; }
  /**
   * Metodo per reperire il campo: contenuto.
   * @return int*/
  public int getContenuto() { return contenuto; }
  /**
   * Metodo per reperire il campo: gradevolezza.
   * @return int*/
  public int getGradevolezza() { return gradevolezza; }
  /**
   * Metodo per reperire il campo: originalita'.
   * @return int*/
  public int getOriginalita() { return originalita; }
  /**
   * Metodo per reperire il campo: edizione.
   * @return int*/
  public int getEdizione() { return edizione; }
  /**
   * Metodo per reperire il voto finale.
   * @return int*/
  public int getVotoFinale() { return (stile+contenuto+gradevolezza+originalita+edizione)/5; }

  /**
   * Metodo per settare il campo: stile.
   * @param stile int*/
  public void setStile(int stile) { this.stile = stile; }
  /**
   * Metodo per settare il campo: contenuto.
   * @param contenuto int*/
  public void setContenuto(int contenuto) { this.contenuto = contenuto; }
  /**
   * Metodo per settare il campo: gradevolezza.
   * @param gradevolezza int*/
  public void setGradevolezza(int gradevolezza) { this.gradevolezza = gradevolezza; }
  /**
   * Metodo per settare il campo: originalita.
   * @param originalita int*/
  public void setOriginalita(int originalita) { this.originalita = originalita; }
  /**
   * Metodo per settare il campo: edizione.
   * @param edizione int*/
  public void setEdizione(int edizione) { this.edizione = edizione; }

}
