package model;

import java.util.LinkedList;
import java.util.List;

public class GestoreSessione {

  String filePathUtenti;
  Utente utenteCorrente;

  public GestoreSessione (String filePathUtenti) {
    this.filePathUtenti = filePathUtenti;
  }

  public Utente getUtenteCorrente() {
    return utenteCorrente;
  }

  public void Accedi(String userId, String password) {
    Utente u = cercaUtente(userId, password);
    if (u != null) {
      utenteCorrente = u;
    }
  }

  public void Registra(Utente u)
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

  private Utente cercaUtente(String userId, String password) {
    List<Utente> elencoUtentiRegistrati = CSVFileManager.leggiDatiCsv(filePathUtenti, Utente.class);
    List<Utente> risultato = elencoUtentiRegistrati.stream().filter(x -> x.userId.equals(userId) && x.password.equals(password)).toList();
    if (!risultato.isEmpty()) {
      return risultato.getFirst();
    }
    return null;
  }
}
