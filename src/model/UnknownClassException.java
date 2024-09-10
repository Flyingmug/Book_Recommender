package model;

/**
 * Errore di "Classe sconosciuta"
 */
public class UnknownClassException extends RuntimeException {
  public UnknownClassException(String message) {
    super(message);
  }
}
