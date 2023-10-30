import com.google.gson.GsonBuilder;
import klase.Raspored;
import specifikacija.ImportExport;

public class JsonImplementacija extends ImportExport {

    @Override
    public void ucitajRasporedJson(String fileName) {
        Raspored raspored = new Raspored();
        GsonBuilder gB = new GsonBuilder();

        //Raspored se čuva kao kolekcija konkretnih termina u vremenu i prostoru. Primer jednog termina je
        //sreda, 18.10.2023. 10-12h, soba S1.
        //dan, datum, vreme od - vreme do, prostor


        // VREME I MESTO, 18.10.2023. ,kabinet RAF3
        //

    }

    @Override
    public void ucitajRasporedCsv(String fileName) {

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
