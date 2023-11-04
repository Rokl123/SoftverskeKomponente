package specifikacija;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import klase.ConfigMapping;
import klase.Prostorija;
import klase.Raspored;
import klase.Termin;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ImportExport {
    public Raspored ucitajRasporedJson(String fileName) {
        Raspored raspored = new Raspored();
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();
            List<Termin> termini = gson.fromJson(new FileReader(fileName), new TypeToken<List<Termin>>(){}.getType());
            raspored.setTermini(termini);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return raspored;
    }


    public Raspored ucitajRasporedCsv(String filepath,String ConfigFile) throws Exception {
        Raspored raspored = new Raspored();
        raspored.setTermini(loadApache(filepath,ConfigFile));
        return raspored;
    }

    private List<Termin> loadApache(String filePath, String configPath) throws IOException {
        List<ConfigMapping> columnMappings = readConfig(configPath);
        Map<Integer, String> mappings = new HashMap<>();
        for(ConfigMapping configMapping : columnMappings) {
            mappings.put(configMapping.getIndex(), configMapping.getOriginal());
        }

        FileReader fileReader = new FileReader(filePath);
        CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(fileReader);


        List<Termin> termini = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(mappings.get(-1));

        for (CSVRecord record : parser) {
            Termin termin = new Termin();

            for (ConfigMapping entry : columnMappings) {
                int columnIndex = entry.getIndex();

                if(columnIndex == -1) continue;

                String columnName = entry.getCustom();

                switch (mappings.get(columnIndex)) {
                    case "place":
                        termin.setProstorija(new Prostorija(record.get(columnIndex),2));
                        break;
                    case "start":
                        LocalDateTime startDateTime = LocalDateTime.parse(record.get(columnIndex), formatter);
                        termin.setPocetak(startDateTime);
                        break;
                    case "end":
                        LocalDateTime endDateTime = LocalDateTime.parse(record.get(columnIndex), formatter);
                        termin.setKraj(endDateTime);
                        break;
                    case "additional":
                        termin.getDodatneStvari().put(columnName, record.get(columnIndex));
                        break;
                }
            }
            termini.add(termin);
        }
        return termini;
    }

    private static List<ConfigMapping>  readConfig(String filePath) throws FileNotFoundException {
        List<ConfigMapping> mappings = new ArrayList<>();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ", 3);

            mappings.add(new ConfigMapping(Integer.valueOf(splitLine[0]), splitLine[1], splitLine[2]));
        }

        scanner.close();


        return mappings;
    }



    public void upisiRasporedUJson(String fileName, String path,Raspored raspored) {
        ObjectMapper objectMapper = new ObjectMapper();
        String outputPath = path + File.separator + fileName + ".json";

        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writeValue(new File(outputPath), raspored.getTermini());
            System.out.println("JSON fajl je uspešno kreiran i popunjen.");
        } catch (IOException e) {
            System.out.println("JSON fajl nije uspešno kreiran i nije popunjen.");
            e.printStackTrace();
        }
    }


    public void upisiRasporedUCsv(String fileName, String path,Raspored raspored) {
        try (FileWriter fileWriter = new FileWriter(path + "/" + fileName+".csv");
             CSVWriter csvWriter = (CSVWriter) new CSVWriterBuilder(fileWriter)
                     .withSeparator(',')
                     .withQuoteChar('"')
                     .build()) {

            String[] header = {"pocetak", "kraj", "prostorija","Dodatna Oprema"};
            csvWriter.writeNext(header);


            for (Termin termin : raspored.getTermini()) {
                String[] data = {String.valueOf(termin.getPocetak()), String.valueOf(termin.getKraj()), termin.getProstorija().getNaziv(), String.valueOf(termin.getDodatneStvari())};
                csvWriter.writeNext(data);
            }

            System.out.println("CSV fajl je uspešno kreiran i popunjen.");
        } catch (IOException e) {
            System.out.println("CSV fajl nije uspešno kreiran i nije popunjen.");
            e.printStackTrace();
        }
    }


    public void upisiRasporedUPdf(String fileName, String path,Raspored raspored) {
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(path + "/" + fileName+".pdf"));
            document.open();

            for (Termin termin : raspored.getTermini()) {

                document.add(new Paragraph("Pocetak: " + termin.getPocetak()));
                document.add(new Paragraph("Kraj: " + termin.getKraj()));
                document.add(new Paragraph("Prostorija: " + termin.getProstorija()));
                document.add(new Paragraph("Dodatna oprema: " +termin.getDodatneStvari()));
            }

            System.out.println("PDF fajl je uspešno kreiran i popunjen.");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
