package view;

import Utilities.ColoriConsole;
import model.CriterioRicerca;
import model.Libro;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuPaginamentoRisultatiView {

    final static String rs = ColoriConsole.RESET;
    final static String gr = ColoriConsole.GREEN;
    final static String cb = ColoriConsole.CYAN_BOLD;
    final static String wb = ColoriConsole.WHITE_BOLD;

    final static Scanner scanner = new Scanner(System.in);


    public static String display(List<Libro> paginaRisultati, int indicePaginaCorrente, int numOccorrenze, int numeroPagine, int dim_pagina, CriterioRicerca criterio) {

        int cifreNumOcc = String.valueOf(numOccorrenze).length();

        // informazioni sulla ricerca
        System.out.println("\n═════════════════════════════════");
        System.out.println("  Pagina "+gr+(indicePaginaCorrente+1)+rs+" di "+numeroPagine+" ("+numOccorrenze+" risultati)\n");
        System.out.println("╔ Titolo, Autori, Anno di pubblicazione");


        // visualizzazione dei risultati
        int i = 0;
        String output;
        for (Libro l: paginaRisultati) {
            String indice = String.format("%0" + cifreNumOcc + "d", i + indicePaginaCorrente*dim_pagina+1);
            output = formattaOutputSingolo(l, criterio);
            System.out.println(((i%2)==0?wb:cb)+"╠ "+indice+' '+output);
            i++;
        }
        System.out.println(rs+"╚\n");

        // computazione iterazione seguente
        System.out.println(" ["+gr+"E"+rs+"]sci\n ["+gr+"A"+rs+"]vanti, ["+gr+"I"+rs+"]ndietro," +
                " numero di ["+gr+"P"+rs+"]agina\n oppure "+gr+"indice"+rs+" del libro per visualizzarlo");
        System.out.print(" >> ");
        return scanner.nextLine();
    }

    public static String displayPageSelectionInput() {
        System.out.print("\n Numero di pagina\n >> ");
        return scanner.nextLine();
    }

    static String formattaOutputSingolo(Libro l, CriterioRicerca c) {

        if (Objects.requireNonNull(c) == CriterioRicerca.AUTORE) {
            return l.getAutori() + " - " + l.getTitolo();
        } else if (c == CriterioRicerca.AUTORE_ANNO) {
            return l.getAutori() + ", " + l.getAnnoPubblicazione() + " - " + l.getTitolo();
        }
        return l.toShortHandFullString();
    }
}
