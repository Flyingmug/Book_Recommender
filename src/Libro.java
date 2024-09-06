public class Libro {

  int annoPubblicazione;
  String titolo;
  String autori;

  public Libro(String titolo, String autori, int annoPubblicazione) {
    this.titolo = titolo;
    this.autori = autori;
    this.annoPubblicazione = annoPubblicazione;
  }

  public String getTitolo() { return titolo; }
  public String getAutori() { return autori; }
  public int getAnnoPubblicazione() { return annoPubblicazione; }

  /*public String getAutoriString() {
    String s = "";
    for(int i = 0; i < autori.length; i++) {
      s.concat(autori[i] + (i+1 < autori.length ? ", " : ""));
    }
    return s;
  }*/

  public void Stampa() {
    System.out.println("|aaa aaa aaa|");
  }

}
