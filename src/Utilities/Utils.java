package Utilities;

public class Utils {
  private static final String INTEGER_REGEX = "^-?\\d+$"; // Match per gli interi

  public static boolean isInteger(String str) {
    return str != null && str.matches(INTEGER_REGEX);
  }
}
