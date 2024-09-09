package model;

public class RichiestaRicerca {
  String titolo;
  String autore;
  int annoPubblicazione;
  CriterioRicerca criterio;

  public RichiestaRicerca(String titolo, String autore, int annoPubblicazione, CriterioRicerca criterio) {
    this.titolo = titolo;
    this.autore = autore;
    this.annoPubblicazione = annoPubblicazione;
    this.criterio = criterio;
  }

  public String getTitolo() {
    return titolo;
  }

  public String getAutore() {
    return autore;
  }

  public int getAnnoPubblicazione() {
    return annoPubblicazione;
  }

  public CriterioRicerca getCriterio() {
    return criterio;
  }
}
