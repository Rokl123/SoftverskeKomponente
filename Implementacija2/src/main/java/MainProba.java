import klase.Prostorija;
import klase.Raspored;
import specifikacija.ImportExport;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MainProba {

    public static void main(String[] args) throws Exception {




        Raspored r = new Raspored();
        ImportExport i = new ImportExport(r.getHourFrom(),r.getHourTo());
        r.setTermini(i.ucitajRasporedCsv("Implementacija1/src/termini.csv", "Implementacija1/src/config.txt"));

//        r.setTermini(i.ucitajRasporedJson("Implementacija1/src/proba.json"));
        //System.out.println(r);

        Imp2 imp2 = new Imp2();
        LocalDateTime ltPocetak = LocalDateTime.of(2023, 8, 23, 8, 15);
        LocalDateTime ltKraj = LocalDateTime.of(2023, 10, 30, 21, 00);
        imp2.kreirajTerminUzPk(DayOfWeek.MONDAY,ltPocetak,ltKraj,r,new Prostorija("raf5",20,false),"Predemet:UUP");

        System.out.println(r);

//        imp2.izlistavanjeSlobodniTermini("UUP",r);
        imp2.izlisatavnjeZauzetihTermina("UUP",r);

//        i.upisiRasporedUJson("arsa", "Implementacija2/src", r);
        i.upisiRasporedUCsv("arsa", "Implementacija2/src", r);
//        i.upisiRasporedUPdf("arsa", "Implementacija1/src", r);
    }
}
