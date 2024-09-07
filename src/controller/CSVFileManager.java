package controller;

import model.Libro;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

public class CSVFileManager {

  public static LinkedList<Libro> leggiFile(String filePath) {
    LinkedList<Libro> elenco = new LinkedList<Libro>();

    try {

      Reader reader = new FileReader(filePath);

      CSVFormat csvFormat = CSVFormat.Builder.create()
          .setHeader()
          .setSkipHeaderRecord(true)
          .build();

      CSVParser csvParser = new CSVParser(reader, csvFormat);

      for (CSVRecord record: csvParser) {

        String titolo = record.get("Title");
        String autori = record.get("Authors");
        int annoPubblicazione = Integer.parseInt(record.get("Publish Date (Year)"));

        Libro l = new Libro(titolo, autori, annoPubblicazione);
        elenco.add(l);
      }

      csvParser.close();

    } catch (IOException e) {
      System.err.println(e);
    }

    return elenco;
  }

  /*public static <T> T leggiRighe(String key, String recordHeaderName) {

  }*/

  public static void scriviFile(Object obj) {



    return;
  }

}
