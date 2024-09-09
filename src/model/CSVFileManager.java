package model;

import Utilities.Feedback;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
        .setHeader(getHeaders(classe))
        .setSkipHeaderRecord(true)
        .build();

    try (FileReader reader = new FileReader(filePath);
         CSVParser csvParser = new CSVParser(reader, csvFormat)) {

      int headerErrorCount = 0;
      int recordErrorCount = 0;

      for (CSVRecord csvRecord : csvParser) {

        // controllo di eventuali errori nella composizione dei record del file
        try {
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
        } catch (IllegalStateException e) {
          headerErrorCount++;
        } catch (IllegalArgumentException e) {
          recordErrorCount++;
        }

      }

      // verifica di presenza errori cumulati
      if (headerErrorCount > 0)
        Feedback.err("Header non definiti correttamente");
      if (recordErrorCount > 0)
        Feedback.err(recordErrorCount + " record non definiti correttamente in \"" + filePath + "\"");

    } catch (IOException | RuntimeException e) {
      Feedback.err(e.getMessage());
      Feedback.err(Arrays.toString(e.getStackTrace()));
    }
    return recordsList;
  }



  /* Scrittura */

  // Generic method to write data to a CSV file
  public static <T> void scriviDatiCsv(String filePath, List<T> recordsList, boolean appendMode) {
    // Define CSV format
    CSVFormat csvFormat = CSVFormat.Builder.create()
        .setHeader(getHeaders(recordsList.getFirst().getClass()))
        .setSkipHeaderRecord(true)
        .build();

    try (FileWriter writer = new FileWriter(filePath, appendMode);
         CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {

      int recordConversionErrorCount = 0;
      int recordDefinitionErrorCOunt = 0;
      for (T record : recordsList) {
        try {
          csvPrinter.printRecord(convertToCsvRecord(record));
        } catch (UnknownClassException e) {
          recordConversionErrorCount++;
        } catch (IOException e) {
          recordDefinitionErrorCOunt++;
        }
      }

      if (recordConversionErrorCount > 0)
        Feedback.err(recordConversionErrorCount + " errori nella conversione");
      if (recordDefinitionErrorCOunt > 0)
        Feedback.err(recordDefinitionErrorCOunt + " oggetti non rispettano le condizioni di definizione");

      csvPrinter.flush();
    } catch (IOException e) {
      System.err.println("Error writing to CSV file: " + e.getMessage());
    }
  }

  // Method to get the headers for the CSV file based on the class type
  private static <T> String[] getHeaders(Class<T> classe) throws UnknownClassException {
    if (classe == Utente.class) {
      return new String[]{"Nome", "Cognome", "Codice Fiscale", "Email", "Userid", "Password"};
    } else if (classe == Libro.class) {
      return new String[]{"Titolo", "Autori", "Categorie", "Editore", "Anno Pubblicazione"};
    } else if (classe == Valutazione.class) {
      return new String[]{"Stile", "Contenuto", "Gradevolezza", "Originalita", "Edizione"};
    }
    throw new UnknownClassException("La classe utilizzata non è gestita dal sistema");
  }

  // Method to convert each object to a CSV record
  private static Iterable<?> convertToCsvRecord(Object record) {
    if (record instanceof Utente user) {
      return List.of(user.getNome(), user.getCognome(), user.getCodiceFiscale(), user.getEmail(), user.getUserId(), user.getPassword());
    } else if (record instanceof Libro lib) {
      return List.of(lib.getTitolo(), lib.getAutori(), lib.getEditore(), lib.getCategorie(), lib.getAnnoPubblicazione());
    } else if (record instanceof Valutazione rating) {
      return List.of(rating.getStile(), rating.getContenuto(), rating.getGradevolezza(),
          rating.getOriginalita(), rating.getEdizione());
    }
    return List.of(); // Return an empty list if the object type is not recognized
  }
}


