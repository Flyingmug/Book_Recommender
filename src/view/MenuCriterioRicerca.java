package view;

import Utilities.ColoriConsole;

public class MenuCriterioRicerca {

    final static String cy = ColoriConsole.CYAN;
    final static String rs = ColoriConsole.RESET;


    public static void display() {

        System.out.println("\n\t═══════╣ Ricerca ╠═══════");
        System.out.println("║ Scegliere un criterio di ricerca");
        System.out.println("╠ ["+cy+"T"+rs+"] Titolo");
        System.out.println("╠ ["+cy+"A"+rs+"] Autore");
        System.out.println("╠ ["+cy+"AA"+rs+"] Autore e anno di pubblicazione");
        System.out.print("╠ >> ");
    }
}
