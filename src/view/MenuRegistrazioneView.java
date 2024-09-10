package view;

import Utilities.ColoriConsole;
import Utilities.Utils;
import model.Utente;

import java.util.Scanner;

/**
 * Classe View (in base all'architettura MVC - ModelViewController) utilizzata per acquisire i dati necessari
 * alla registrazione.
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class MenuRegistrazioneView {

  final static String bb = ColoriConsole.BLUE_BOLD;
  final static String rs = ColoriConsole.RESET;

  final static Scanner scanner = new Scanner(System.in);

  /**
   * Metodo che preleva i dati per creare un nuovo utente.
   * @return utente creato o null se si è verificato un errore*/
  public static Utente display() {

    System.out.println("╔ Inserire "+bb+"."+rs+" per uscire\n║");
    System.out.print("╔ Nome: ");
    String nome = leggiValidaInput();
    if (nome == null) return null;

    System.out.print("╠ Cognome: ");
    String cognome = leggiValidaInput();
    if (cognome == null) return null;

    System.out.print("╠ Codice Fiscale: ");
    String codFiscale = leggiValidaInput();
    if (!Utils.verCodiceFiscale(codFiscale) && !(codFiscale == null) && !codFiscale.equals("w")) {
      System.out.println("Codice Fiscale non valido.");
      return null; // Return null if the Codice Fiscale is not valid
    }

    System.out.print("╠ Email: ");
    String email = leggiValidaInput();
    if (email == null) return null;

    System.out.print("╠ User Id: ");
    String userId = leggiValidaInput();
    if (userId == null) return null;

    System.out.print("╚ Password: ");
    String password = leggiValidaInput();
    if (password == null) return null;

    return new Utente(nome, cognome, codFiscale, email, userId, password);
  }

  /**
   * Pone l'utente in un ciclo il quale vieta l'uscita fino all'inserimento
   * di un valore valido o un carattere di uscita prestabilito.
   * @return input utente validato
   */
  private static String leggiValidaInput() {
    while (true) {
      String input = scanner.nextLine().trim();

      // Controlla presenza di "." (carattere di uscita)
      if (input.equals(".")) {
        return null;
      }

      if (input.isBlank()) {
        System.out.println("Input cannot be blank. Please enter a valid value:");
      } else {
        // Normalizza gli spazi tra parole (sostituisce i doppi con singoli)
        return input.replaceAll("\\s+", " ");
      }
    }
  }
}


