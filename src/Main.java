import controller.GestoreRaccolta;
import model.RaccoltaLibri;

public class Main {
  public static void main(String[] args) {


    // inizializzazione gestore della raccolta locale
    RaccoltaLibri raccolta = new RaccoltaLibri();
    GestoreRaccolta manager = new GestoreRaccolta(raccolta);



    System.out.println("\n\n\n\t (\\¯¯¯¯¯¯¯¯\\");
    System.out.println("\t \\ \\.;.;.;.;\\");
    System.out.println("\t  \\ \\.;.;.;.;\\");
    System.out.println("\t   \\ \\.;.;.;.;\\");
    System.out.println("\t    \\(≡≡≡≡≡≡≡≡(");

    manager.iniziaCicloPrincipale();



  }
}