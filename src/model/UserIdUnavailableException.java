package model;

/**
 * Errore di "id utente non disponibile"
 */
public class UserIdUnavailableException extends RuntimeException {
  public UserIdUnavailableException(String message) {
    super(message);
  }
}
