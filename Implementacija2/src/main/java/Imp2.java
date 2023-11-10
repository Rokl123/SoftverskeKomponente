import klase.Prostorija;
import klase.Raspored;
import klase.Termin;
import specifikacija.DodelaTermina;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class Imp2 implements DodelaTermina {
    @Override
    public boolean preklapanjeTermina(LocalDateTime pocetak1, LocalDateTime kraj1, LocalDateTime pocetak2, LocalDateTime kraj2) {
        return false;
    }

    @Override
    public Prostorija dodavanjeProstorijaSaOsobinama(String naziv, int kapacitet) {
        return null;
    }


    @Override
    public Termin kreirajTerminPt(LocalDateTime pocetak, int trajanje, Prostorija prostorija, Raspored raspored) {
        return null;
    }

    @Override
    public boolean brisanjeTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored) {
        return false;
    }

    @Override
    public boolean premestajTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored) {
        return false;
    }

    @Override
    public void izlistavanjeSlobodniTermini(String kriterijum, Raspored raspored) {

    }

    @Override
    public void pretrazivanjeVezaniPodaci(String podatak, Raspored raspored) {

    }

    @Override
    public boolean kreirajTerminUzPk(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda, Raspored r, Prostorija p, LocalTime start, LocalTime end) {

        LocalDateTime ltPocetak = LocalDateTime.ofInstant(r.getFrom().toInstant(), ZoneId.systemDefault());

        LocalDateTime ltKraj = LocalDateTime.ofInstant(r.getTo().toInstant(), ZoneId.systemDefault());

        if (pocetakPerioda.isBefore(ltPocetak) || krajPerioda.isAfter(ltKraj) || (pocetakPerioda.isAfter(ltKraj) || krajPerioda.isBefore(ltPocetak))) { //Od 10.10.2023 Do 1.1.2024  odSati 8-21
            //   9.10.2023 Do 1.5.2024

            System.out.println("PERIOD JE NEVAZECI!!!");
            return false;
        }

        for (Termin t : r.getTermini()) {
            //Ako termin ima isti dan, isti sat i iste minute i da je izmedju pocetka perioda i kraja onda ne mozemo da napravimo periodicni termin
            if (t.getPocetak().getDayOfWeek() == day && t.getPocetak().getHour() == pocetakPerioda.getHour()
                    && t.getPocetak().getMinute() == pocetakPerioda.getMinute() && (t.getPocetak().isAfter(pocetakPerioda) && t.getPocetak().isBefore(krajPerioda))) { //Termin 10.12.2023 8:15 TUE    10.11.2023 - 1.1.2024 8:15 TUE


                System.out.println("Vec postoji termin u zadatom periodu! " + t);
                return false;
            }
        }

        LocalDateTime period = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getDayOfMonth(), pocetakPerioda.getMonthValue(), start.getHour(), start.getMinute()); // period 23.10.2023 do 24.1.2024
        LocalDateTime periodDo = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getDayOfMonth(), pocetakPerioda.getMonthValue(), end.getHour(), end.getMinute());
        while (periodDo.compareTo(krajPerioda) < 0) {
            Termin t = new Termin(period, periodDo, p);
            period = period.plusDays(7);
            periodDo = periodDo.plusDays(7);
            r.getTermini().add(t);

        }
        System.out.println("Termin u zadatom periodu je uspesno kreiran!");
        return true;
    }

    @Override
    public Termin kreirajTerminUzPk(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija, Raspored raspored) {
        return null;
    }

}

