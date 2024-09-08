package model;

import java.io.Serializable;

/**
 * Classe utilizzata per definire le caratteristiche degli utenti: nome, cognome, codFisc, email, userId, password.
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class Utente implements Serializable {
  String nome;
  String cognome;
  String codiceFiscale;
  String email;
  String userId;
  String password;


  /**
   * Costruttore.
   * I parametri passati vengono utilizzati per creare un'istanza
   * @param nome String
   * @param cognome String
   * @param codiceFiscale String
   * @param email String
   * @param userId String
   * @param password String
   */
  public Utente(String nome, String cognome, String codiceFiscale, String email, String userId, String password) {
    this.nome = nome;
    this.cognome = cognome;
    this.codiceFiscale = codiceFiscale;
    this.email = email;
    this.userId = userId;
    this.password = password;
  }


  /**
   * Metodo per reperire il campo: nome.
   * @return String*/
  public String getNome() { return nome; }
  /**
   * Metodo per reperire il campo: cognome.
   * @return String*/
  public String getCognome() { return cognome; }
  /**
   * Metodo per reperire il campo: codiceFiscale.
   * @return String*/
  public String getCodiceFiscale() { return codiceFiscale; }
  /**
   * Metodo per reperire il campo: email.
   * @return String*/
  public String getEmail() { return email; }
  /**
   * Metodo per reperire il campo: userId.
   * @return String*/
  public String getUserId() { return userId; }
  /**
   * Metodo per reperire il campo: password.
   * @return String*/
  public String getPassword() { return password; }


}
