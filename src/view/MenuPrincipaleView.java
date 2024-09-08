package view;

import Utilities.ColoriConsole;

import java.util.Scanner;

/**
 * Classe View (in base all'architettura MVC - ModelViewController) utilizzata per stampare a video il menu' principale
 * con l'elenco di operazioni.
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/

public class MenuPrincipaleView {

    final static String cy = ColoriConsole.CYAN;
    final static String rs = ColoriConsole.RESET;

    final static Scanner scanner = new Scanner(System.in);

    /**
     * Stampa a video il menu' principale con l'elenco di operazioni
     * @return String*/

    public static String display() {
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
}
