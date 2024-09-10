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
    String nome = leggiValidaInput(false);
    if (nome == null) return null;

    System.out.print("╠ Cognome: ");
    String cognome = leggiValidaInput(false);
    if (cognome == null) return null;

    System.out.print("╠ Codice Fiscale: ");
    String codFiscale = leggiValidaInput(true);
    if (!Utils.verCodiceFiscale(codFiscale) && !(codFiscale == null) && !codFiscale.equals("w")) {
      System.out.println("--Codice Fiscale non valido--");
      return null; // Return null if the Codice Fiscale is not valid
    }

    System.out.print("╠ Email: ");
    String email = leggiValidaInput(true);
    if (email == null) return null;

    System.out.print("╠ User Id: ");
    String userId = leggiValidaInput(true);
    if (userId == null) return null;

    System.out.print("╚ Password: ");
    String password = leggiValidaInput(true);
    if (password == null) return null;

    return new Utente(nome, cognome, codFiscale, email, userId, password);
  }

  /**
   * Pone l'utente in un ciclo il quale vieta l'uscita fino all'inserimento
   * di un valore valido o un carattere di uscita prestabilito.
   * @param noSpace spazi accettati o meno
   * @return input utente validato
   */
  private static String leggiValidaInput(boolean noSpace) {
    while (true) {
      String input = scanner.nextLine().trim();

      // Controlla presenza di "." (carattere di uscita)
      if (input.equals(".")) {
        return null;
      }

      if (input.isBlank()) {
        System.out.println("--L'input non può essere vuoto--");
      } else if(noSpace && input.contains(" ")) {
        System.out.println("--Spazi non ammessi--");
      } else {
        // Normalizza gli spazi tra parole (sostituisce i doppi con singoli)
        return input.replaceAll("\\s+", " ");
      }
    }
  }

}


