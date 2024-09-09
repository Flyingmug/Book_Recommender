package view;

import Utilities.ColoriConsole;
import model.Libro;
import model.Utente;
import model.Valutazione;

import java.util.Scanner;

/**
 * Classe View (in base all'architettura MVC - ModelViewController) utilizzata per stampare a video i dati dei libri.
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class DisplayLibroView {

  final static String indicatore = "★";

  final static String rs = ColoriConsole.RESET;
  final static String cy = ColoriConsole.CYAN;
  final static String gr = ColoriConsole.GREEN;
  final static String pr = ColoriConsole.PURPLE;
  final static String yb = ColoriConsole.YELLOW_BOLD;

  final static Scanner scanner = new Scanner(System.in);

  /**
   * Visualizzazione video
   * @param l
   * @param valMedia
   * @return String
   */
  public static String display(Libro l, Valutazione valMedia) {

    String editore = l.getEditore();
    String categorie = l.getCategorie();

    System.out.println("╦╦");
    System.out.println("║╔ Titolo: " + l.getTitolo());
    System.out.println("║╠ Autori: " + l.getAutori());
    System.out.println("║╠ Anno di pubblicazione: " + l.getAnnoPubblicazione());

    if (editore != null && !editore.isBlank())
      System.out.println("║╠ Editore: " + editore);
    if (categorie != null && !categorie.isBlank())
      System.out.println("║╠ Categorie: " + categorie);
    System.out.println("║╚══════════════════════════\n║");

    System.out.println("║\t════╣ "+cy+"Valutazione media"+rs+" ╠════\n║╠");
    System.out.println("║╠ Voto Finale   " + yb + indicatore.repeat(valMedia.getVotoFinale()) + rs);
    System.out.println("║╠ Stile         " + yb + indicatore.repeat(valMedia.getStile()) + rs);
    System.out.println("║╠ Contenuto     " + yb + indicatore.repeat(valMedia.getContenuto()) + rs);
    System.out.println("║╠ Gradevolezza  " + yb + indicatore.repeat(valMedia.getGradevolezza()) + rs);
    System.out.println("║╠ Originalita   " + yb + indicatore.repeat(valMedia.getOriginalita()) + rs);
    System.out.println("║╠ Edizione      " + yb + indicatore.repeat(valMedia.getEdizione()) + rs);
    System.out.println("╩╩");

    System.out.println("\n ["+gr+"E"+rs+"]sci");

    System.out.println(" ["+pr+"V"+rs+"]aluta il libro");
    System.out.print(" ["+pr+"S"+rs+"]uggerisci libri\n >> ");


    return scanner.nextLine();
  }
}
