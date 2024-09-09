package Utilities;

public class Feedback {

  final static String yb = ColoriConsole.YELLOW_BOLD;
  final static String rb = ColoriConsole.RED_BOLD;
  final static String gb = ColoriConsole.GREEN_BOLD;
  final static String rs = ColoriConsole.RESET;

  public static void warn(String msg) { System.out.println("\n" + yb + "!! " + msg + " !!" + rs); }

  public static void err(String msg) { System.out.println("\n" + rb + msg + rs); }

  public static void success(String msg) { System.out.println("\n" + gb + "█  " + msg + "  █" + rs); }
}
