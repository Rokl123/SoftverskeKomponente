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
//         r = i.ucitajRasporedJson("Implementacija1/src/proba.json");

    //    r = i.ucitajRasporedCsv("Implementacija1/src/termini.csv","Implementacija1/src/config.txt");
        r.setTermini(i.ucitajRasporedCsv("Implementacija1/src/termini.csv","Implementacija1/src/config.txt"));
        System.out.println(r);

        Imp1 imp1 = new Imp1();

        imp1.izlistavanjeSlobodniTermini("UUP",r);



        LocalDateTime ltPocetak = LocalDateTime.of(2023,10,2,10,0);
        LocalDateTime ltKraj = LocalDateTime.of(2023,10,2,10,45);
      //  imp1.kreirajTerminUzPk(ltPocetak,ltPocetak,new Prostorija("RAF4",50),r); // implementirati da mogu da se dodaju doatne stvari!


        i.upisiRasporedUJson("arsa","Implementacija1/src",r);
        i.upisiRasporedUCsv("arsa","Implementacija1/src",r);
        i.upisiRasporedUPdf("arsa","Implementacija1/src",r);



    }
}
