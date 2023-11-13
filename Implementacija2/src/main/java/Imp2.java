import klase.Prostorija;
import klase.Raspored;
import klase.Termin;
import klase.TerminPeriod;
import specifikacija.DodelaTermina;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

public class Imp2 implements DodelaTermina {
    @Override
    public boolean preklapanjeTermina(LocalDateTime pocetak1, LocalDateTime kraj1, LocalDateTime pocetak2, LocalDateTime kraj2) {
        return false;
    }

    public boolean preklapanjeTermina(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda,LocalTime pocetak,LocalTime kraj,Termin t) {
        // PERIOD: 23.8.2023 - 20.9.2023 8:15 - 12:00
        if(t instanceof TerminPeriod) {

            for (LocalDate datum : ((TerminPeriod) t).getVremeOdrzavanja()) {
                if (datum.getDayOfWeek() == day && (((TerminPeriod) t).getPocetakTermina() == pocetak || ((TerminPeriod) t).getKrajTermina()==kraj)
                        || (datum.getDayOfWeek()==day && (pocetak.isAfter(((TerminPeriod) t).getPocetakTermina()) && pocetak.isBefore(((TerminPeriod) t).getKrajTermina())))){ //Termin koji ima odrzavanja svaki UTORAK u 12:15 do 15:00
                    System.out.println("Vec postoji termin u zadatom periodu! " + t);
                    return true;
                }

            }
        }

        if ((t.getPocetak().getDayOfWeek() == day && t.getPocetak().getHour() == pocetak.getHour()
                && t.getPocetak().getMinute() == pocetak.getMinute()) ||
                (t.getPocetak().getDayOfWeek() == day && t.getPocetak().getHour() >= pocetak.getHour() && t.getPocetak().getHour()<kraj.getHour()
                && t.getPocetak().getMinute() == pocetak.getMinute()) && (t.getPocetak().isAfter(pocetakPerioda) && t.getPocetak().isBefore(krajPerioda))) { //Termin 10.12.2023 8:15 TUE    10.11.2023 - 1.1.2024 8:15 TUE

            System.out.println("Vec postoji termin u zadatom periodu! " + t);
            return true;
        }




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
        Map<String,String> mapaStvari = new HashMap<>();
        for(Termin t : raspored.getTermini()){
            mapaStvari = t.getDodatneStvari();
            if(mapaStvari.containsValue(podatak) || t.getProstorija().getNaziv().equals(podatak)){
                System.out.println("Termin sa zadatim podatkom: " + t + "\n");
            }

        }

    }
    private void addAdditional(Map<String,String> dodatneStvari,String s){

        String[] info = s.split(","); //Znaci string izgleda kao : Profesor:Arsenije Petrovic,Racunar=DA,Predmet="UUP"

        String finale = "";
        int i=0;
        while(i < info.length){
            finale = info[i]; // Profesor:Arsenije Petrovic
            String[] keyValue = finale.split(":");
            dodatneStvari.put(keyValue[0],keyValue[1]);
            i++;
        }

    }
    @Override
    public boolean kreirajTerminUzPk(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda, Raspored r, Prostorija p, LocalTime start, LocalTime end, String dodatneStvari) {

        LocalDateTime ltPocetak = LocalDateTime.ofInstant(r.getFrom().toInstant(), ZoneId.systemDefault()); // u rasporedu period od kad do kad vazi

        LocalDateTime ltKraj = LocalDateTime.ofInstant(r.getTo().toInstant(), ZoneId.systemDefault()); // takodje treba dodati i za sate proveru!

        if (pocetakPerioda.isBefore(ltPocetak) || krajPerioda.isAfter(ltKraj) || (pocetakPerioda.isAfter(ltKraj) || krajPerioda.isBefore(ltPocetak))) { //Od 10.10.2023 Do 1.1.2024  odSati 8-21
            //   9.10.2023 Do 1.5.2024

            System.out.println("PERIOD JE NEVAZECI!!!");
            return false;
        }

        for (Termin t : r.getTermini()) {
            //Ako termin ima isti dan, isti sat i iste minute i da je izmedju pocetka perioda i kraja onda ne mozemo da napravimo periodicni termin
           if(preklapanjeTermina(day,pocetakPerioda,krajPerioda,start,end,t)){
               return false;
           }

        }

        LocalDateTime period = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getMonthValue(), pocetakPerioda.getDayOfMonth(), start.getHour(), start.getMinute()); // period 23.10.2023 do 24.1.2024
        LocalDateTime periodDo = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getMonthValue(), pocetakPerioda.getDayOfMonth(), end.getHour(), end.getMinute());
        TerminPeriod tP = new TerminPeriod(pocetakPerioda,krajPerioda,p);
        tP.setPocetakTermina(start);
        tP.setKrajTermina(end);
        while (periodDo.compareTo(krajPerioda) < 0) {
            if(period.getDayOfWeek() == day) {
                tP.getVremeOdrzavanja().add(period.toLocalDate()); // 23.12.2023. 8:15 - 23.12.2023 12:00
            }
            period = period.plusDays(1);
            periodDo = periodDo.plusDays(1);
        }
        addAdditional(tP.getDodatneStvari(),dodatneStvari);
        r.getTermini().add(tP); // postoji Termin sa Periodom sada!
        System.out.println("Termin u zadatom periodu je uspesno kreiran!");
        return true;
    }

    @Override
    public Termin kreirajTerminUzPk(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija, Raspored raspored) {
        return null;
    }

}

