package view;

import Utilities.ColoriConsole;
import model.CriterioRicerca;
import model.GestoreLibrerie;
import model.Libreria;
import model.Libro;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class MenuLibreriePersonali {

  final static String rs = ColoriConsole.RESET;
  final static String gr = ColoriConsole.GREEN;
  final static String pr = ColoriConsole.PURPLE;
  final static String cb = ColoriConsole.CYAN_BOLD;
  final static String wb = ColoriConsole.WHITE_BOLD;

  final static Scanner scanner = new Scanner(System.in);



  public static String display(List<Libreria> librerie, int numOccorrenze) {

    int cifreNumOcc = String.valueOf(numOccorrenze).length();


    System.out.println("\n\t╠═══════════ Librerie ═══════════╣\n\n");

    // visualizzazione dei risultati
    System.out.println("╔ Indice, Nome libreria, libri contenuti");
    int i = 0;
    String output = "";
    for (Libreria l: librerie) {
      String indice = String.format("%0" + cifreNumOcc + "d", i/* + indicePaginaCorrente*dim_pagina+1*/);
      output = l.getNomeLibreria();
      System.out.println(((i%2)==0?wb:cb)+"╠ "+indice+' '+output+rs+" ("+l.getConteggio()+')');
      i++;
    }
    if (output.isBlank()) System.out.println("\t■ Nessuna libreria presente ■");
    System.out.println(rs+"╚\n");

    // computazione iterazione seguente
    System.out.println(" ["+gr+"E"+rs+"]sci, ["+pr+"N"+rs+"]uova, ["+pr+"C"+rs+"]ancella,\n ["+gr+"A"+rs+"]vanti, ["+gr+"I"+rs+"]ndietro," +
        " numero di ["+gr+"P"+rs+"]agina\n oppure "+gr+"indice"+rs+" della libreria per visualizzarla");
    System.out.print(" >> ");
    return scanner.nextLine();
  }


  public static Libreria displayCreazione() {

    System.out.println("Nome libreria: ");
    String nome = scanner.nextLine();

    UUID uuid = UUID.randomUUID();
    return new Libreria(uuid.toString(), nome);

  }
}
