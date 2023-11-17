package klase;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UcitavanjeProstorija {
    public static List<Prostorija> ucitajProstorije(String putanjaDoFajla) {
        List<Prostorija> prostorije = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(putanjaDoFajla))) {
            String[] header = reader.readNext(); // Uzmi zaglavlje
            String[] line;

            while ((line = reader.readNext()) != null) {
                String imeProstorije = line[0];
                int kapacitet = Integer.parseInt(line[1]);
                boolean imaLiracunare = Boolean.parseBoolean(line[2]);

                Prostorija prostorija = new Prostorija(imeProstorije, kapacitet, imaLiracunare);
                prostorije.add(prostorija);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return prostorije;
    }
}
