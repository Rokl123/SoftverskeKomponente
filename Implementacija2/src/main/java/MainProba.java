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
        System.out.println(r);

        Imp2 imp2 = new Imp2();


        LocalDateTime ltPocetak = LocalDateTime.of(2023, 8, 23, 8, 15); // Vreme u periodu nista ne znaci!
        LocalDateTime ltKraj = LocalDateTime.of(2023, 10, 30, 21, 00);

        LocalTime startHour = LocalTime.of(15,15);
        LocalTime endHour = LocalTime.of(17,30);

        imp2.kreirajTerminUzPk(DayOfWeek.TUESDAY,ltPocetak,ltKraj,r,new Prostorija("RAF4",50),startHour,endHour,"Profesor:Arsenije Petrovic,Racunar:DA,Predmet:UUP");

       // imp2.pretrazivanjeVezaniPodaci("UUP",r);

        // imp2.kreirajTerminUzPk(ltPocetak,ltPocetak,new Prostorija("RAF4",50),r); // implementirati da mogu da se dodaju doatne stvari!

        System.out.println(r);



        i.upisiRasporedUJson("arsa", "Implementacija2/src", r);
        i.upisiRasporedUCsv("arsa", "Implementacija2/src", r);
        i.upisiRasporedUPdf("arsa", "Implementacija1/src", r);
    }
}
