package view;

import Utilities.ColoriConsole;

import java.util.Scanner;

public class MenuPrincipaleView {

    final static String cy = ColoriConsole.CYAN;
    final static String rs = ColoriConsole.RESET;

    final static Scanner scanner = new Scanner(System.in);


    public static String displayMinimal() {
        // menu principale
        System.out.println("\n\t╬╬ Book Recommender ╬╬");
        System.out.println("╔════════════════════════════");
        System.out.println("║\n╠ ["+cy+"C"+rs+"] - Ricerca");
        System.out.println("╠ ["+cy+"L"+rs+"] - Login");
        System.out.println("╠ ["+cy+"R"+rs+"] - Registrazione");
        System.out.println("╠ ["+cy+"E"+rs+"] - Esci\n║");
        System.out.println("╠════════════════════════════");
        System.out.print("╠ >> ");

        return scanner.nextLine();
    }

    public static String displayFull() {
        // menu principale
        System.out.println("\n\t╬╬ Book Recommender ╬╬");
        System.out.println("╔════════════════════════════");
        System.out.println("║\n╠ ["+cy+"C"+rs+"] - Ricerca");
        System.out.println("╠ ["+cy+"O"+rs+"] - Logout");
        System.out.println("╠ ["+cy+"temp"+rs+"] - opzione 1");
        System.out.println("╠ ["+cy+"temp"+rs+"] - opzione 1");
        System.out.println("╠ ["+cy+"temp"+rs+"] - opzione 1");
        System.out.println("╠ ["+cy+"E"+rs+"] - Esci\n║");
        System.out.println("╠════════════════════════════");
        System.out.print("╠ >> ");

        return scanner.nextLine();
    }

}
