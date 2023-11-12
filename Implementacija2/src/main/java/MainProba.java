import klase.Prostorija;
import klase.Raspored;
import specifikacija.ImportExport;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MainProba {

    public static void main(String[] args) throws Exception {


        ImportExport i = new ImportExport();

        Raspored r = new Raspored();

        r.setTermini(i.ucitajRasporedCsv("Implementacija1/src/termini.csv", "Implementacija1/src/config.txt"));
        System.out.println(r);

        Imp2 imp2 = new Imp2();


        LocalDateTime ltPocetak = LocalDateTime.of(2023, 10, 31, 8, 15);
        LocalDateTime ltKraj = LocalDateTime.of(2023, 10, 23, 21, 00);

        LocalTime startHour = LocalTime.of(15,15);
        LocalTime endHour = LocalTime.of(17,30);

        imp2.kreirajTerminUzPk(ltPocetak.getDayOfWeek(),ltPocetak,ltKraj,r,new Prostorija("RAF4",50),startHour,endHour);

        imp2.pretrazivanjeVezaniPodaci("UUP",r);

        // imp2.kreirajTerminUzPk(ltPocetak,ltPocetak,new Prostorija("RAF4",50),r); // implementirati da mogu da se dodaju doatne stvari!

        System.out.println(r);



        i.upisiRasporedUJson("arsa", "Implementacija1/src", r);
        i.upisiRasporedUCsv("arsa", "Implementacija1/src", r);
        i.upisiRasporedUPdf("arsa", "Implementacija1/src", r);
    }
}
