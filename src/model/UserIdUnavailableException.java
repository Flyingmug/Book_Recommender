package model;

public class UserIdUnavailableException extends RuntimeException {
  public UserIdUnavailableException(String message) {
    super(message);
  }
}
