package view;

import Utilities.ColoriConsole;
import Utilities.Feedback;
import model.CriterioRicerca;
import model.RichiestaRicerca;

import java.util.Scanner;

/**
 * Classe View (in base all'architettura MVC - ModelViewController) utilizzata per stampare a video le informazioni
 * necessarie a impostare la ricerca.
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/

public class MenuRicercaView {

    final static String cy = ColoriConsole.CYAN;
    final static String rs = ColoriConsole.RESET;
    final static Scanner scanner = new Scanner(System.in);

    /**
     * Metodo contenente una serie di stampe per guidare l'utente nella scelta. */
    public static RichiestaRicerca display() {

        System.out.println("\n\t═══════╣ Ricerca ╠═══════");
        System.out.println("║ Scegliere un criterio di ricerca");
        System.out.println("╠ ["+cy+"T"+rs+"] Titolo");
        System.out.println("╠ ["+cy+"A"+rs+"] Autore");
        System.out.println("╠ ["+cy+"AA"+rs+"] Autore e anno di pubblicazione");
        System.out.print("╠ >> ");

        String titolo = "", autori= "";
        int annoPubblicazione = 0;
        CriterioRicerca criterio;
        String c = scanner.nextLine();
        switch (c.toLowerCase()) {
            case "t":
                criterio = CriterioRicerca.TITOLO;
                System.out.print("║\n║ Titolo\n╠ >> ");
                titolo = scanner.nextLine();
                break;
            case "a":
                criterio = CriterioRicerca.AUTORE;
                System.out.print("║\n║ Autore\n╠ >> ");
                autori = scanner.nextLine();
                break;
            case "aa":
                criterio = CriterioRicerca.AUTORE_ANNO;
                System.out.print("║\n║ Autore\n╠ >> ");
                autori = scanner.nextLine();
                System.out.print("║ Anno\n╠ >> ");
                try {
                    annoPubblicazione = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("Formato numerico per l'anno fornito non valido.");
                }
                break;

            default:
                Feedback.warn("Criterio non valido");
                return null;
        }
        return new RichiestaRicerca(titolo, autori, annoPubblicazione, criterio);
    }
}
