package model;

import Utilities.Feedback;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSVFileManager {

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
              csvRecord.get("Name"),
              csvRecord.get("Surname"),
              csvRecord.get("Email"),
              csvRecord.get("UserId"),
              csvRecord.get("Password")
          );
          recordsList.add(classe.cast(u));
        } else if (classe == Libro.class) {
          Libro l = new Libro(
              csvRecord.get("Title"),
              csvRecord.get("Authors"),
              Integer.parseInt(csvRecord.get("Publish Date (Year)"))
          );
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
    } catch (IOException e) {
      Feedback.err(e.getMessage() + " " + e.getStackTrace());
    }
    return recordsList;
  }

}


