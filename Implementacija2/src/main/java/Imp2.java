import klase.Prostorija;
import klase.Raspored;
import klase.Termin;
import specifikacija.DodelaTermina;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Imp2 implements DodelaTermina {


    public boolean preklapanjeTermina(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda,LocalTime pocetak,LocalTime kraj,Termin t) {
        // PERIOD: 23.8.2023 - 20.9.2023 8:15 - 12:00
            for (LocalDate datum : t.getVremeOdrzavanja()) {
                if (datum.getDayOfWeek() == day && ( t.getPocetak().toLocalTime() == pocetak || t.getKraj().toLocalTime()==kraj)
                        || (datum.getDayOfWeek()==day && (pocetak.isAfter( t.getPocetak().toLocalTime()) && pocetak.isBefore( t.getKraj().toLocalTime())))){ //Termin koji ima odrzavanja svaki UTORAK u 12:15 do 15:00
                    System.out.println("Vec postoji termin u zadatom periodu! " + t);
                    return true;
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
    public boolean brisanjeTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored) {
        for(Termin t:raspored.getTermini()){
            if(t instanceof Termin){
                if(t.getPocetakPerioda().isAfter(pocetak) && t.getKrajPerioda().isBefore(kraj)){
                    raspored.getTermini().remove(t);
                    System.out.println("Period je uspesno obrisan");
                    return true;
                }
            }
        }
        System.out.println("Period nije obrisan");
        return false;
    }



    @Override
    public boolean premestajTermina(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda, Raspored r, LocalTime start, LocalTime end) {
        for (Termin t : r.getTermini()) {
                if ( t.getPocetakPerioda().isAfter(pocetakPerioda) && t.getKrajPerioda().isBefore(krajPerioda)) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Unesite novi pocetak");
                    LocalDateTime pocetak1 = LocalDateTime.parse(sc.nextLine());
                    System.out.println("Unesite novi kraj");
                    LocalDateTime kraj1 = LocalDateTime.parse(sc.nextLine());
                     t.setPocetakPerioda(pocetak1);
                    t.setKrajPerioda(kraj1);
                    LocalDateTime period = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getMonthValue(), pocetakPerioda.getDayOfMonth(), start.getHour(), start.getMinute()); // period 23.10.2023 do 24.1.2024
                    LocalDateTime periodDo = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getMonthValue(), pocetakPerioda.getDayOfMonth(), end.getHour(), end.getMinute());

                    while (periodDo.compareTo(krajPerioda) < 0) {
                        if(period.getDayOfWeek() == day) {
                            t.getVremeOdrzavanja().add(period.toLocalDate()); // 23.12.2023. 8:15 - 23.12.2023 12:00
                        }
                        period = period.plusDays(1);
                        periodDo = periodDo.plusDays(1);
                    }

                    r.getTermini().add(t);
                    System.out.println("Period je uspesno promenjen");
                    return true;
                }

        }
        System.out.println("Period nije promenjen");
        return false;
    }

    @Override
    public void izlistavanjeSlobodniTermini(String kriterijum, Raspored raspored) {
        boolean flag = true;
        LocalDateTime pocetniDatum = raspored.getTermini().get(0).getPocetak();

        System.out.println("Za DAN " + pocetniDatum.toLocalDate() + " slobodni termini su: \n");

        for (Termin t : raspored.getTermini()) {
            if (t.getDodatneStvari().containsValue(kriterijum) || t.getProstorija().getNaziv().equals(kriterijum)) {
                if (pocetniDatum.getDayOfMonth() != t.getPocetak().getDayOfMonth()) {
                    System.out.println("Za DAN " + t.getPocetak().toLocalDate() + " slobodni termini su: \n");
                    pocetniDatum = t.getPocetak();
                }

                if (raspored.getHourFrom() == t.getPocetak().toLocalTime()) {
                    System.out.println("Od: " + t.getKraj().getHour() + ":" + t.getKraj().getMinute() +
                            " do " + raspored.getTermini().get(raspored.getTermini().indexOf(t) + 1).getPocetak().getHour() +
                            ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) + 1).getPocetak().getMinute());
                } else {
                    try {
                        if (flag) {
                            flag = false;
                            System.out.println("Od: " + raspored.getHourFrom() + " do: " + t.getPocetak().getHour() +
                                    ":" + t.getPocetak().getMinute());
                        } else if (raspored.getTermini().get(raspored.getTermini().indexOf(t) + 1).getPocetak().getDayOfMonth() != t.getKraj().getDayOfMonth()) {
                            flag = true;
                            System.out.println("Od: " + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKraj().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKraj().getMinute() + " do: " +
                                    raspored.getTermini().get(raspored.getTermini().indexOf(t)).getPocetak().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t)).getPocetak().getMinute());
                            System.out.println("Od: " + t.getKraj().getHour() + ":" + t.getKraj().getMinute() + " do: " + raspored.getHourTo());
                        } else if (raspored.getTermini().get(raspored.getTermini().indexOf(t) + 1).getPocetak().toLocalTime() != t.getKraj().toLocalTime()) {
                            System.out.println("Od: " + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKraj().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKraj().getMinute() + " do: " +
                                    raspored.getTermini().get(raspored.getTermini().indexOf(t)).getPocetak().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t)).getPocetak().getMinute());
                        } else {
                            System.out.println("Od: " + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getPocetak().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getPocetak().getMinute() +
                                    " do: " + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKraj().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKraj().getMinute());
                        }
                    } catch (IndexOutOfBoundsException exc) {
                        System.out.println("Od: " + raspored.getHourFrom() + " do: " + t.getPocetak().getHour() + ":" + t.getPocetak().getMinute());
                    }

                    if (raspored.getTermini().indexOf(t) + 1 == raspored.getTermini().size()) {
                        System.out.println("Od: " + t.getKraj().getHour() + ":" + t.getKraj().getMinute() +
                                " do: " + raspored.getHourTo() + "\n");
                    }
                }
            }
        }
    }

    @Override
    public void isProstorijaZauzeta(Prostorija prostorija, Raspored raspored) {
        for(Termin t:raspored.getTermini()){
            if(t.getProstorija().equals(prostorija)){
                System.out.println("Prostorija "+t.getProstorija().getNaziv()+" je zauzeta u terminu "+t.getPocetak().toLocalTime() + " do "+ t.getKraj().toLocalTime());
            }
        }
        System.out.println("Prostorija je slobodna");
    }

    @Override
    public void isTerminSlobodan(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda) {

    }

    @Override
    public void izlisatavnjeZauzetihTermina(String podatak, Raspored raspored) {
        Map<String,String> mapaStvari = new HashMap<>();
        for(Termin t : raspored.getTermini()){
            mapaStvari = t.getDodatneStvari();
            if(mapaStvari.containsValue(podatak) || t.getDodatneStvari().containsValue(podatak)){
                long period = ChronoUnit.DAYS.between(t.getPocetak().toLocalDate(),t.getKraj().toLocalDate());
                System.out.println("Za period: " +t.getPocetak().toLocalDate() +" do " +t.getKraj().toLocalDate() +" u vremenu: "+t.getPocetak().toLocalTime() + " do + "+t.getKraj().toLocalTime()+ " danom " +t.getPocetak().getDayOfWeek());
                for(int i = 1;i<period/7;i++){
                    System.out.println(t.getPocetak().toLocalDate().plusDays(i*7));
                }
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
    public boolean kreirajTerminUzPk(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda, Raspored r, Prostorija p ,String dodatneStvari) {
        LocalDateTime ltPocetak = LocalDateTime.ofInstant(r.getFrom().toInstant(), ZoneId.systemDefault()); // u rasporedu period od kad do kad vazi
        LocalDateTime ltKraj = LocalDateTime.ofInstant(r.getTo().toInstant(), ZoneId.systemDefault()); // takodje treba dodati i za sate proveru!
        LocalTime start = ltPocetak.toLocalTime();
        LocalTime end = ltKraj.toLocalTime();

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
        Termin tP = new Termin(pocetakPerioda,krajPerioda,p);
        tP.setPocetak(pocetakPerioda);
        tP.setKraj(krajPerioda);
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
    @Override
    public boolean preklapanjeTermina(LocalDateTime pocetak1, LocalDateTime kraj1, LocalDateTime pocetak2, LocalDateTime kraj2) {
        return false;
    }
    @Override
    public Termin kreirajTerminPt(LocalDateTime pocetak, int trajanje, Prostorija prostorija, Raspored raspored) {
        return null;
    }
    @Override
    public void isTerminSlobodan(LocalDateTime pocetak1, LocalDateTime kraj1, Raspored raspored) {

    }
    @Override
    public boolean premestajTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored) {
        return false;
    }


}

