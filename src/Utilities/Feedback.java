package Utilities;

/**
 * CLasse utilizzata per fornire messaggi di errore/avvisi
 */
public class Feedback {

  final static String yb = ColoriConsole.YELLOW_BOLD;
  final static String rb = ColoriConsole.RED_BOLD;
  final static String gb = ColoriConsole.GREEN_BOLD;
  final static String bb = ColoriConsole.BLUE_BOLD;
  final static String rs = ColoriConsole.RESET;

  /**
   *
   * @param msg
   */
  public static void warn(String msg) { System.out.println("\n" + yb + "!! " + msg + " !!" + rs); }

  /**
   *
   * @param msg
   */
  public static void err(String msg) { System.out.println("\n" + rb + msg + rs); }

  /**
   *
   * @param msg
   */
  public static void success(String msg) { System.out.println("\n" + gb + "█  " + msg + "  █" + rs); }

  public static void info(String msg) { System.out.println("\n" + bb + "■  " + msg + "  ■" + rs); }
}
