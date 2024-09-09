import controller.Controller;

/**
 * La classe Main istanzia un oggetto di tipo RaccoltaLibri, per la gestione dei libri, e un oggetto di tipo GestoreRaccolta, che richiama il
 * metodo "iniziaCicloPrincipale()". Quest'ultimo mantiene il programma in funzione finche' l'utente decide di uscire inserendo il comando "E".
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/

public class Main {
  public static void main(String[] args) {


    // inizializzazione gestore della raccolta locale
    Controller manager = new Controller();


    System.out.println("\n\n\n\t (\\¯¯¯¯¯¯¯¯\\");
    System.out.println("\t \\ \\.;.;.;.;\\");
    System.out.println("\t  \\ \\.;.;.;.;\\");
    System.out.println("\t   \\ \\.;.;.;.;\\");
    System.out.println("\t    \\(≡≡≡≡≡≡≡≡(");

    manager.iniziaCicloPrincipale();

  }
}