package view;

import model.Utente;
import java.util.Scanner;

/**
 * Classe View utilizzata per stampare a video la finestra di login
 */
public class MenuLoginView {

  final static Scanner scanner = new Scanner(System.in);

  /**
   *
   * @return Utente
   */
  public static Utente display() {
    System.out.println("\n\t╠═══════ Login ═══════╣\n");
    System.out.print("╠ UserId: \n╠ >> ");
    String userId = scanner.nextLine();
    System.out.print("╠ Password: \n╠ >> ");
    String password = scanner.nextLine();

    return new Utente("","","","", userId, password);
  }
}
