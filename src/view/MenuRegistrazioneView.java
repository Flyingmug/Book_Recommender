package view;

import Utilities.Utils;
import model.Utente;

import java.util.Scanner;
/**
 * Classe View (in base all'architettura MVC - ModelViewController) utilizzata per stampare a video i dati necessari
 * alla registrazione.
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/
public class MenuRegistrazioneView {

  final static Scanner scanner = new Scanner(System.in);

  /**
   * Metodo che preleva i dati per creare un nuovo utente.
   * Ritorna il nuovo utente che verrà aggiunto al file "UtentiRegistrati"
   * @return Utente*/
  public static Utente display() {

    System.out.print("╔ Nome: ");
    String nome = scanner.nextLine();
    System.out.print("╠ Cognome: ");
    String cognome = scanner.nextLine();
    System.out.print("╠ Codice Fiscale: ");
    String codFiscale = scanner.nextLine();

    System.out.println("VERIFICA CODICE FISCALE: " + Utils.verCodiceFiscale(codFiscale));

    System.out.print("╠ Email: ");
    String email = scanner.nextLine();
    System.out.print("╠ User Id: ");
    String userId = scanner.nextLine();
    System.out.print("╚ Password: ");
    String password = scanner.nextLine();

    return new Utente(nome, cognome, codFiscale, email, userId, password);
  }
}
