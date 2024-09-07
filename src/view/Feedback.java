package view;

import Utilities.ColoriConsole;

public class Feedback {

  final static String yb = ColoriConsole.YELLOW_BOLD;
  final static String rs = ColoriConsole.RESET;

  public static void warn(String msg) {
    System.out.println(yb+"!! " + msg + " !!"+rs);
  }

}
