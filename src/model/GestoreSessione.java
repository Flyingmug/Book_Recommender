package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Offre dei metodi per potersi autenticare, e utilizza CSVFileManager per salvare i dati dell'eventuale nuovo utente.
 * @author Selimi Sebian
 * @author Moscatelli Alexander
 */
public class GestoreSessione {

  String filePathUtenti;
  Utente utenteCorrente;

  /**
   * Costruttore classe.
   * FilePathUtenti è una stringa contenente il percorso del file su cui risiedono i dati degli utenti.
   * @param filePathUtenti path file utenti */
  public GestoreSessione (String filePathUtenti) {
    this.filePathUtenti = filePathUtenti;
  }

  /**
   * @return utente corrente */
  public Utente getUtenteCorrente() {
    return utenteCorrente;
  }

  /**
   * Consente di eseguire il login verificando la presenza di {@code userId} e {@code password} nel file di utenti
   * @param userId id utente
   * @param password oasswird
   */
  public void accedi(String userId, String password) {
    Utente u = cercaUtente(userId, password);
    if (u != null) {
      utenteCorrente = u;
    }
  }

  /**
   * Esegue il logout dell'utente corrente
   */
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
   * Verificare che un dato utente esista, e se esiste ritorna la prima occorrenza.
   * @param password password
   * @param userId id utente
   * @return Utente ricercato, {@code null} se non trovato
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
