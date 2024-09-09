package Utilities;

/**
 * Classe utilizzata per verificare la correttezza del codice fiscale inserito
 */
public class Utils {
  private static final String INTEGER_REGEX = "^-?\\d+$"; // Match per gli interi
  private static final String FISCAL_REGEX = "^[A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1}$\n";

  /**
   *
   * @param str
   * @return boolean
   */
  public static boolean isInteger(String str) {
    return str != null && str.matches(INTEGER_REGEX);
  }

  /**
   *
   * @param str
   * @return boolean
   */
  public static boolean verCodiceFiscale(String str) {
    return str != null && str.matches(FISCAL_REGEX);
  }
}