package view;

import model.Utente;

import java.util.Scanner;

public class MenuRegistrazioneView {

  final static Scanner scanner = new Scanner(System.in);

  public static Utente display() {

    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Cognome: ");
    String cognome = scanner.nextLine();
    System.out.print("Codice Fiscale: ");
    String codFiscale = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    System.out.print("User Id: ");
    String userId = scanner.nextLine();
    System.out.print("Password: ");
    String password = scanner.nextLine();

    return new Utente(nome, cognome, codFiscale, email, userId, password);

  }
}
