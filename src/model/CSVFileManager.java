package model;

import Utilities.Feedback;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe responsabile della gestione lettura/scrittura nei file di tipo CSV (database utenti)
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/

public class CSVFileManager {

  /* lettura */


  /**
   * Reperisce il file
   * @param filePath String
   * @param classe Class<T>
   * @return  <T> List<T>*/

  public static <T> List<T> leggiDatiCsv(String filePath, Class<T> classe) {
    List<T> recordsList = new LinkedList<>();

    CSVFormat csvFormat = CSVFormat.Builder.create()
        .setHeader()
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
      Feedback.err(e.getMessage() + " " + e.getStackTrace());
    }
    return recordsList;
  }



  /* Scrittura */

  // Generic method to write data to a CSV file
  public static <T> void scriviDatiCsv(String filePath, List<T> recordsList, boolean appendMode) {
    // Define CSV format
    CSVFormat csvFormat = CSVFormat.Builder.create()
        .setHeader(getHeaders(recordsList.getFirst()))
        .build();

    try (FileWriter writer = new FileWriter(filePath, appendMode);
         CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {

      for (T record : recordsList) {
        csvPrinter.printRecord(convertToCsvRecord(record));
      }

      csvPrinter.flush();
    } catch (IOException e) {
      System.err.println("Error writing to CSV file: " + e.getMessage());
    }
  }

  // Method to get the headers for the CSV file based on the class type
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

  // Method to convert each object to a CSV record
  private static Iterable<?> convertToCsvRecord(Object record) {
    if (record instanceof Utente) {
      Utente user = (Utente) record;
      return List.of(user.getNome(), user.getCognome(), user.getEmail(), user.getUserId(), user.getPassword());
    } else if (record instanceof Libro) {
      Libro lib = (Libro) record;
      return List.of(lib.getTitolo(), lib.getAutori(), lib.getAnnoPubblicazione(), lib.getEditore(), lib.getCategorie());
    } else if (record instanceof Valutazione) {
      Valutazione rating = (Valutazione) record;
      return List.of(rating.getStile(), rating.getContenuto(), rating.getGradevolezza(),
          rating.getOriginalita(), rating.getEdizione());
    }
    return List.of(); // Return an empty list if the object type is not recognized
  }
}


