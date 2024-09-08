package view;

import Utilities.ColoriConsole;

/**
 * Classe View (in base all'architettura MVC - ModelViewController) utilizzata per stampare a video le informazioni
 * necessarie a impostare la ricerca.
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/

public class MenuCriterioRicercaView {

    final static String cy = ColoriConsole.CYAN;
    final static String rs = ColoriConsole.RESET;

    /**
     * Metodo contenente una serie di stampe per guidare l'utente nella scelta. */
    public static void display() {

        System.out.println("\n\t═══════╣ Ricerca ╠═══════");
        System.out.println("║ Scegliere un criterio di ricerca");
        System.out.println("╠ ["+cy+"T"+rs+"] Titolo");
        System.out.println("╠ ["+cy+"A"+rs+"] Autore");
        System.out.println("╠ ["+cy+"AA"+rs+"] Autore e anno di pubblicazione");
        System.out.print("╠ >> ");
    }
}
