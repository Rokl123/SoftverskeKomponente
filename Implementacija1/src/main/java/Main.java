import klase.Raspored;
import specifikacija.ImportExport;

public class Main {
    public static void main(String[] args) throws Exception{
        ImportExport i = new ImportExport();
        Raspored r = new Raspored();
//         r = i.ucitajRasporedJson("Implementacija1/src/proba.json");
        r = i.ucitajRasporedCsv("Implementacija1/src/termini.csv","Implementacija1/src/config.txt");
        System.out.println(r);


        i.upisiRasporedUJson("arsa","Implementacija1/src",r);
        i.upisiRasporedUCsv("arsa","Implementacija1/src",r);
       i.upisiRasporedUPdf("arsa","Implementacija1/src",r);

    }
}
