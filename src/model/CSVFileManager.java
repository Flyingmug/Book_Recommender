package model;

import Utilities.Feedback;
import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSVFileManager {

  /* lettura */

  public static <T> List<T> leggiDatiCsv(String filePath, Class<T> classe) {
    List<T> recordsList = new LinkedList<>();

    CSVFormat csvFormat = CSVFormat.Builder.create()
        .setHeader(getHeaders(classe))
        .setSkipHeaderRecord(true)
        .build();

    try (FileReader reader = new FileReader(filePath);
         CSVParser csvParser = new CSVParser(reader, csvFormat)) {

      for (CSVRecord csvRecord : csvParser) {
        if (classe == Utente.class) {
          Utente u = new Utente(
              csvRecord.get("Nome"),
              csvRecord.get("Cognome"),
              csvRecord.get("Codice Fiscale"),
              csvRecord.get("Email"),
              csvRecord.get("Userid"),
              csvRecord.get("Password")
          );
          recordsList.add(classe.cast(u));
        } else if (classe == Libro.class) {
          Libro l = new Libro(
              csvRecord.get("Titolo"),
              csvRecord.get("Autori"),
              Integer.parseInt(csvRecord.get("Anno Pubblicazione"))
          );
          String editore = csvRecord.get("Editore");
          if (!editore.isBlank()) { l.setEditore(editore); }
          String categorie = csvRecord.get("Categorie");
          if (!categorie.isBlank()) { l.setCategorie(categorie); }
          recordsList.add(classe.cast(l));
        } else if (classe == Valutazione.class) {
          Valutazione v = new Valutazione(
              Integer.parseInt(csvRecord.get("Stile")),
              Integer.parseInt(csvRecord.get("Contenuto")),
              Integer.parseInt(csvRecord.get("Gradevolezza")),
              Integer.parseInt(csvRecord.get("Originalita")),
              Integer.parseInt(csvRecord.get("Edizione"))
          );
          recordsList.add(classe.cast(v));
        }

      }
    } catch (IOException | RuntimeException e) {
      Feedback.err(e.getMessage());
      e.getStackTrace();
    }
    return recordsList;
  }



  /* Scrittura */

  // Metodo generico per scrivere dati in un file CSV
  public static <T> void scriviDatiCsv(String filePath, List<T> recordsList, boolean appendMode) {

    // Definizione formato CSV
    CSVFormat csvFormat = CSVFormat.Builder.create()
        .setSkipHeaderRecord(true)
        .setHeader(getHeaders(recordsList.getFirst()))
        .build();

    try (FileWriter writer = new FileWriter(filePath, appendMode);
         CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {

      for (T record : recordsList) {
        Iterable<?> i = convertToCsvRecord(record);
        csvPrinter.printRecord(i);
      }

      csvPrinter.flush();
    } catch (IOException e) {
      System.err.println("Error writing to CSV file: " + e.getMessage());
    }
  }

  // Metodo per ottenere gli header corrispondenti alla classe usata
  private static String[] getHeaders(Object record) {
    if (record instanceof Utente) {
      return new String[]{"Nome", "Cognome", "Codice Fiscale", "Email", "Userid", "Password"};
    } else if (record instanceof Libro) {
      return new String[]{"Titolo", "Autori", "Anno Pubblicazione", "Editore", "Categorie"};
    } else if (record instanceof Valutazione) {
      return new String[]{"Stile", "Contenuto", "Gradevolezza", "Originalita", "Edizione"};
    }
    return new String[0];
  }

  // Metodo per convertire ogni oggetto in un record csv
  private static Iterable<?> convertToCsvRecord(Object record) {
    if (record instanceof Utente) {
      Utente user = (Utente) record;
      return List.of(user.getNome(), user.getCognome(), user.getCodiceFiscale(), user.getEmail(), user.getUserId(), user.getPassword());
    } else if (record instanceof Libro) {
      Libro lib = (Libro) record;
      return List.of(lib.getTitolo(), lib.getAutori(), lib.getAnnoPubblicazione(), lib.getEditore(), lib.getCategorie());
    } else if (record instanceof Valutazione) {
      Valutazione rating = (Valutazione) record;
      return List.of(rating.getStile(), rating.getContenuto(), rating.getGradevolezza(),
          rating.getOriginalita(), rating.getEdizione());
    }
    return List.of(); // Ritorna una lista vuota se l'oggetto non è riconosciuto
  }
}


