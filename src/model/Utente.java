package model;

public class Utente {
  String nome;
  String cognome;
  String codiceFiscale;
  String email;
  String userId;
  String password;

  public Utente(String nome, String cognome, String codiceFiscale, String email, String userId, String password) {
    this.nome = nome;
    this.cognome = cognome;
    this.codiceFiscale = codiceFiscale;
    this.email = email;
    this.userId = userId;
    this.password = password;
  }

  public String getNome() { return nome; }
  public String getCognome() { return cognome; }
  public String getCodiceFiscale() { return codiceFiscale; }
  public String getEmail() { return email; }
  public String getUserId() { return userId; }
  public String getPassword() { return password; }


}
