package model;

import Utilities.Feedback;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe responsabile della gestione lettura/scrittura nei file di tipo CSV
 * @author Selimi Sebian
 * @author Moscatelli Alexander*/

public class CSVFileManager {

  /**
   * Reperisce il file {@code filePath} e legge gli elementi convertiti alla classe {@code classe}
   * @param filePath filepath
   * @param classe classe dell'elemento da leggere
   * @return lista di tutti gli elementi prelevati dal file*/
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
            recordsList = new ArrayList<>();
            Libro l = new Libro(
                csvRecord.get("IdLibro"),
                csvRecord.get("Titolo"),
                csvRecord.get("Autori"),
                Integer.parseInt(csvRecord.get("Anno Pubblicazione")),
                csvRecord.get("Editore"),
                csvRecord.get("Categorie")
            );
            recordsList.add(classe.cast(l));
          } else if (classe == Valutazione.class) {
            Valutazione v = new Valutazione(
                csvRecord.get("IdLibro"),
                csvRecord.get("IdUtente"),
                Integer.parseInt(csvRecord.get("Stile")),
                Integer.parseInt(csvRecord.get("Contenuto")),
                Integer.parseInt(csvRecord.get("Gradevolezza")),
                Integer.parseInt(csvRecord.get("Originalita")),
                Integer.parseInt(csvRecord.get("Edizione")),
                csvRecord.get("Recensione")
            );
            recordsList.add(classe.cast(v));
          } else if (classe == EntryLibreria.class) {
            EntryLibreria el = new EntryLibreria(
                csvRecord.get("IdUtente"),
                csvRecord.get("IdLibreria"),
                csvRecord.get("Nome Libreria"),
                csvRecord.get("IdLibro")
            );
            recordsList.add(classe.cast(el));
          } else if (classe == ConsiglioLettura.class) {
            ConsiglioLettura c = new ConsiglioLettura(
                csvRecord.get("IdLibro"),
                csvRecord.get("IdUtente"),
                csvRecord.get("IdConsiglio1"),
                csvRecord.get("IdConsiglio2"),
                csvRecord.get("IdConsiglio3")
            );
            recordsList.add(classe.cast(c));
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



  /**
   * Gestisce la scrittura di una lista di elementi, verificando che la classe degli elementi sia utilizzabile.
   * @param filePath filepath
   * @param recordsList lista di elementi
   * @param appendMode modalita' append
   * @param <T> tipo di elementi della lista
   */
  public static <T> void scriviDatiCsv(String filePath, List<T> recordsList, boolean appendMode) {

    // Aggiungi i header al file se non esiste
    File file = new File(filePath);
    boolean fileExists = file.exists();
    boolean scriviHeader = !fileExists;

    // Se il file esiste e si sta appendendo, avviene il controllo degli header
    if (fileExists && appendMode) {
      // Controlla la presenza e la correttezza degli header
      scriviHeader = hasHeaders(file, recordsList.getFirst().getClass());
    }

    // Definizione formato CSV
    CSVFormat csvFormat = CSVFormat.Builder.create()
        .setHeader(getHeaders(recordsList.getFirst().getClass()))
        .setSkipHeaderRecord(scriviHeader)
        .build();

    try (FileWriter writer = new FileWriter(filePath, appendMode);
         CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {

      int recordConversionErrorCount = 0;
      int recordDefinitionErrorCount = 0;
      for (T record : recordsList) {
        try {
          Iterable<?> i = convertToCsvRecord(record);
          csvPrinter.printRecord(i);
        } catch (UnknownClassException e) {
          recordConversionErrorCount++;
        } catch (IOException e) {
          recordDefinitionErrorCount++;
        }
      }

      if (recordConversionErrorCount > 0)
        Feedback.err(recordConversionErrorCount + " errori nella conversione");
      if (recordDefinitionErrorCount > 0)
        Feedback.err(recordDefinitionErrorCount + " oggetti non rispettano le condizioni di definizione");

      csvPrinter.flush();
    } catch (IOException e) {
      System.err.println("Error writing to CSV file: " + e.getMessage());
    }
  }


  /**
   * Restituisce gli header csv corrispondenti alla classe {@code classe}
   * @param classe classe richiesta
   * @return un vettore di stringhe rappresentanti gli header
   * @param <T> tipo della classe
   * @throws UnknownClassException header non definiti per la classe richiesta
   */
  private static <T> String[] getHeaders(Class<T> classe) throws UnknownClassException {
    if (classe == Utente.class) {
      return new String[]{"Nome", "Cognome", "Codice Fiscale", "Email", "Userid", "Password"};
    } else if (classe == Libro.class) {
      return new String[]{"IdLibro", "Titolo", "Autori", "Categorie", "Editore", "Anno Pubblicazione"};
    } else if (classe == Valutazione.class) {
      return new String[]{"IdLibro", "IdUtente", "Stile", "Contenuto", "Gradevolezza", "Originalita", "Edizione", "Recensione"};
    } else if (classe == EntryLibreria.class) {
      return new String[]{"IdUtente", "IdLibreria", "Nome Libreria", "IdLibro"};
    } else if (classe == ConsiglioLettura.class) {
      return new String[]{"IdLibro", "IdUtente", "IdConsiglio1", "IdConsiglio2", "IdConsiglio3"};
    }
    throw new UnknownClassException("La classe utilizzata non è gestita dal sistema");
  }

  // Method to convert each object to a CSV record

  /**
   * Converte un oggetto nella sua rappresentazione iterabile, utilizzabile per la scrittura su file csv.
   * @param record oggetto da convertire
   * @return oggetto iterabile contenente le get dei campi dell'oggetto
   */
  private static Iterable<?> convertToCsvRecord(Object record) {
    if (record instanceof Utente user) {
      return List.of(user.getNome(), user.getCognome(), user.getCodiceFiscale(), user.getEmail(), user.getUserId(), user.getPassword());
    } else if (record instanceof Libro lib) {
      return List.of(lib.getIdLibro(), lib.getTitolo(), lib.getAutori(), lib.getEditore(), lib.getCategorie(), lib.getAnnoPubblicazione());
    } else if (record instanceof Valutazione rating) {
      return List.of(rating.getIdLibro(), rating.getIdUtente(), rating.getStile(), rating.getContenuto(),
          rating.getGradevolezza(), rating.getOriginalita(), rating.getEdizione(), rating.getRecensione());
    } else if (record instanceof EntryLibreria entry) {
      return List.of(entry.getIdUtente(), entry.getIdLibreria(), entry.getNomeLibreria(), entry.getIdLibro());
    } else if (record instanceof ConsiglioLettura con) {
      return List.of(con.getIdLibro(), con.getIdUtente(), con.getConsigli()[0], con.getConsigli()[1], con.getConsigli()[2]);
    }
    return List.of(); // Return an empty list if the object type is not recognized
  }

  /**
   * Verifica la presenza dei corrispondenti header csv della classe {@code classe} nel file {@code f}
   * @param file file da ispezionare
   * @param classe classe di verifica degli header
   * @return {@code true} se gli header sono presenti e corretti, {@code false} se gli header non sono presenti
   */
  private static boolean hasHeaders(File file, Class<?> classe) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String primaLinea = reader.readLine();
      if (primaLinea != null) {
        String[] headers = getHeaders(classe);
        // Check if the first line matches the expected headers
        return primaLinea.equals(String.join(",", headers)); // Confronta la prima riga con gli headers
      }
    } catch (IOException e) {
      System.err.println("Error reading the file to check for headers: " + e.getMessage());
    }
    return false; // If unable to read the file, assume headers are missing
  }
}


