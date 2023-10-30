import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import klase.Prostorija;
import klase.Raspored;
import klase.Termin;
import specifikacija.ImportExport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ImportExportS1 extends ImportExport {

    @Override
    public Raspored ucitajRasporedJson(String fileName) {
        Raspored raspored = new Raspored();
        //List<Termin> termini = new ArrayList<>();
        try {
            // Kreirajte Gson objekat
            Gson gson = new Gson();
            String jsonString = "{\"Termin\":\"Mahesh\", \"Datum\":21, \"Kapacitet\":21 , \"Učionica\":21 }";
            // Učitaj podatke iz JSON fajla u odgovarajuću Java strukturu
            List<Termin> termini = gson.fromJson(new FileReader(fileName), new TypeToken<List<Termin>>(){}.getType());
           // termini = gson.fromJson(new FileReader(fileName), new TypeToken<List<Termin>>(){}.getType());

            raspored.setTermini(termini);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return raspored;
    }

    @Override
    public Raspored ucitajRasporedCsv(String fileName) {

        String line = "";
        String csvSplitBy = ",";
        List<Termin> termini= new ArrayList<>();
        Raspored raspored = new Raspored();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                String[] row = line.split(csvSplitBy); // Predmet, Tip, Nastavnik, Grupe, Dan , Termin, Mesto

                // prostorija, vreme, datum, kapacitet, potrebne stvari
                // soba S1, 10-12,datum,kapacitet
                String prostorija = row[0];
                String vreme = row[1]; // 18-10-2023.
                String[] pocetakIKraj = vreme.split("-");
                int pocetak = Integer.parseInt(pocetakIKraj[0]);
                int kraj = Integer.parseInt(pocetakIKraj[1]);

                String[] datum = row[2].split("\\.");

                int dan = Integer.parseInt(datum[0]);
                int mesec = Integer.parseInt(datum[1]);
                int godina =Integer.parseInt(datum[2]);
                LocalDateTime datumDesavanja = LocalDateTime.of(godina,mesec,dan,pocetak,0); // godina, mesec ,dan ,sat
                LocalDateTime datumDesavanjaKraj = LocalDateTime.of(godina,mesec,dan,kraj,0);

                int kapacitet = Integer.parseInt(row[3]);
                Prostorija p = new Prostorija(prostorija,kapacitet,null);


                Termin t = new Termin(datumDesavanja,datumDesavanjaKraj,p);

                termini.add(t);
                //termini.add(row);
            }
        }
        catch(Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
        }

        raspored.setTermini(termini);

        return raspored;

    }


    @Override
    public void upisiRasporedUJson(String fileName, String path) {

    }

    @Override
    public void upisiRasporedUCsv(String fileName, String path) {

    }

    @Override
    public void upisiRasporedUPdf(String fileName, String path) {

    }
}
