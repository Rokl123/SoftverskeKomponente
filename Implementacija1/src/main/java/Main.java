import klase.Prostorija;
import klase.Raspored;
import specifikacija.ImportExport;
import specifikacija.LocalDateTimeTypeAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws Exception{

        Raspored r = new Raspored();
        ImportExport i = new ImportExport(r.getHourFrom(),r.getHourTo());
//        r.setTermini( i.ucitajRasporedJson("Implementacija1/src/proba.json"));

        r.setTermini(i.ucitajRasporedCsv("Implementacija1/src/termini.csv","Implementacija1/src/config.txt"));
        System.out.println(r);

        Imp1 imp1 = new Imp1();

        imp1.izlisatavnjeZauzetihTermina("UUP",r);


      //  imp1.kreirajTerminUzPk(ltPocetak,ltPocetak,new Prostorija("RAF4",50),r); // implementirati da mogu da se dodaju doatne stvari!


//        i.upisiRasporedUJson("arsa","Implementacija1/src",r);
//        i.upisiRasporedUCsv("arsa","Implementacija1/src",r);
        i.upisiRasporedUPdf("arsa","Implementacija1/src",r);



    }
}
