package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Offre dei metodi per potersi autenticare, e utilizza CSVFileManager per salvare i dati dell'eventuale nuovo utente.
 * @author Selimi Sebian*/

public class GestoreSessione {

  String filePathUtenti;
  Utente utenteCorrente;

  /**
   * Costruttore classe.
   * FilePathUtenti è una stringa contenente il percorso del file su cui risiedono i dati degli utenti.
   * @param filePathUtenti String*/
  public GestoreSessione (String filePathUtenti) {
    this.filePathUtenti = filePathUtenti;
  }

  /**
   *
   * @return utenteCorrente */
  public Utente getUtenteCorrente() {
    return utenteCorrente;
  }

  public void accedi(String userId, String password) {
    Utente u = cercaUtente(userId, password);
    if (u != null) {
      utenteCorrente = u;
    }
  }

  public void esci() {
    utenteCorrente = null;
  }

  /**
   * Verifica se il nome utente selezionato è già utilizzato; se non esiste lo aggiunge e lo imposta come utente corrente
   * @param u Utente*/
  public void registra(Utente u)
      throws UserIdUnavailableException {

    Utente utenteRis = cercaUtente(u.getUserId(), u.getPassword());
    if (utenteRis != null)
      throw new UserIdUnavailableException("L'id utente scelto è già presente");
    else {
      List<Utente> userList = new LinkedList<>();
      userList.add(u);
      CSVFileManager.scriviDatiCsv(filePathUtenti, userList, true);
      utenteCorrente = u;
    }
  }
/**
 *Metodo privato, serve a cercare se un utente esiste tra gli utenti, e se esiste ritorna la prima occorrenza.
 @param password String
 @param userId String
 @return Utente
 */
  private Utente cercaUtente(String userId, String password) {
    List<Utente> elencoUtentiRegistrati = CSVFileManager.leggiDatiCsv(filePathUtenti, Utente.class);
    List<Utente> risultato = elencoUtentiRegistrati.stream().filter(x -> x.userId.equals(userId) && x.password.equals(password)).toList();
    if (!risultato.isEmpty()) {
      return risultato.getFirst();
    }
    return null;
  }
}
