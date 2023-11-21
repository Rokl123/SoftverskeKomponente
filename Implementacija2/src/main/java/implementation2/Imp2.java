package implementation2;

import exceptions.DatumUIzuzetomDanuException;
import exceptions.NeVazeciPeriodException;
import klase.Manager;
import klase.Prostorija;
import klase.Raspored;
import klase.Termin;
import specifikacija.DodelaTermina;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Imp2 implements DodelaTermina {

    static {
        Imp2 imp2 = new Imp2();
        Manager.setObject(imp2);
    }

    private boolean preklapanjeTermina(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda,Termin t) {
        // PERIOD: 23.8.2023 - 20.9.2023 8:15 - 12:00
        LocalTime pocetak = pocetakPerioda.toLocalTime();
        LocalTime kraj = krajPerioda.toLocalTime();

            for (LocalDate datum : t.getVremeOdrzavanja()) {
                if (datum.getDayOfWeek() == day && ( t.getPocetakPerioda().toLocalTime() == pocetak || t.getKrajPerioda().toLocalTime()==kraj)
                        || (datum.getDayOfWeek()==day && (pocetak.isAfter( t.getPocetakPerioda().toLocalTime()) && pocetak.isBefore( t.getKrajPerioda().toLocalTime())))){ //Termin koji ima odrzavanja svaki UTORAK u 12:15 do 15:00
                    System.out.println("Vec postoji termin u zadatom periodu! " + t);
                    return true;
                }

            }

        if ((t.getPocetakPerioda().getDayOfWeek() == day && t.getPocetakPerioda().getHour() == pocetak.getHour()
                && t.getPocetakPerioda().getMinute() == pocetak.getMinute()) ||
                (t.getPocetakPerioda().getDayOfWeek() == day && t.getPocetakPerioda().getHour() >= pocetak.getHour() && t.getPocetakPerioda().getHour()<kraj.getHour()
                && t.getPocetakPerioda().getMinute() == pocetak.getMinute()) && (t.getPocetakPerioda().isAfter(pocetakPerioda) && t.getPocetakPerioda().isBefore(krajPerioda))) { //Termin 10.12.2023 8:15 TUE    10.11.2023 - 1.1.2024 8:15 TUE

            System.out.println("Vec postoji termin u zadatom periodu! " + t);
            return true;
        }
        return false;
    }


    @Override
    public boolean brisanjeTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored) {
        for(Termin t:raspored.getTermini()){
                if(t.getPocetakPerioda().toLocalDate().isAfter(pocetak.toLocalDate()) && t.getKrajPerioda().toLocalDate().isBefore(kraj.toLocalDate())
                || (t.getPocetakPerioda().toLocalDate().isEqual(pocetak.toLocalDate()) && t.getKrajPerioda().toLocalDate().isEqual(kraj.toLocalDate()))){
                    raspored.getTermini().remove(t);
                    System.out.println("Period je uspesno obrisan");
                    return true;
                }
        }
        System.out.println("Period nije obrisan");
        return false;
    }



    @Override
    public boolean premestajTermina(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda, Raspored r) {
        LocalTime start = pocetakPerioda.toLocalTime();
        LocalTime end = krajPerioda.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.uuuu H:mm");
        for (Termin t : r.getTermini()) {
                if (t.getPocetakPerioda().isAfter(pocetakPerioda) && t.getKrajPerioda().isBefore(krajPerioda)
                || (t.getPocetakPerioda().isEqual(pocetakPerioda) && t.getKrajPerioda().isEqual(krajPerioda))) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Unesite novi pocetak");
                    LocalDateTime pocetak1 = LocalDateTime.parse(sc.nextLine(),formatter);
                    System.out.println("Unesite novi kraj");
                    LocalDateTime kraj1 = LocalDateTime.parse(sc.nextLine(),formatter);
                    t.setPocetakPerioda(pocetak1);
                    t.setKrajPerioda(kraj1);
                    LocalDateTime period = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getMonthValue(), pocetakPerioda.getDayOfMonth(), start.getHour(), start.getMinute()); // period 23.10.2023 do 24.1.2024
                    LocalDateTime periodDo = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getMonthValue(), pocetakPerioda.getDayOfMonth(), end.getHour(), end.getMinute());
                    t.getVremeOdrzavanja().clear();
                    while (periodDo.compareTo(krajPerioda) < 0) {
                        if(period.getDayOfWeek() == day) {
                            t.getVremeOdrzavanja().add(period.toLocalDate()); // 23.12.2023. 8:15 - 23.12.2023 12:00
                        }
                        period = period.plusDays(1);
                        periodDo = periodDo.plusDays(1);
                    }
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
        LocalDateTime pocetniDatum = raspored.getTermini().get(0).getPocetakPerioda();

        System.out.println("Za DAN " + pocetniDatum.toLocalDate() + " slobodni termini su: \n");

        for (Termin t : raspored.getTermini()) {
            if (t.getDodatneStvari().containsValue(kriterijum) || t.getProstorija().getNaziv().equals(kriterijum)) {
                if (pocetniDatum.getDayOfMonth() != t.getPocetakPerioda().getDayOfMonth()) {
                    System.out.println("Za DAN " + t.getPocetakPerioda().toLocalDate() + " slobodni termini su: \n");
                    pocetniDatum = t.getPocetakPerioda();
                }

                if (raspored.getHourFrom() == t.getPocetakPerioda().toLocalTime()) {
                    System.out.println("Od: " + t.getKrajPerioda().getHour() + ":" + t.getKrajPerioda().getMinute() +
                            " do " + raspored.getTermini().get(raspored.getTermini().indexOf(t) + 1).getPocetakPerioda().getHour() +
                            ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) + 1).getPocetakPerioda().getMinute());
                } else {
                    try {
                        if (flag) {
                            flag = false;
                            System.out.println("Od: " + raspored.getHourFrom() + " do: " + t.getPocetakPerioda().getHour() +
                                    ":" + t.getPocetakPerioda().getMinute());
                        } else if (raspored.getTermini().get(raspored.getTermini().indexOf(t) + 1).getPocetakPerioda().getDayOfMonth() != t.getKrajPerioda().getDayOfMonth()) {
                            flag = true;
                            System.out.println("Od: " + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKrajPerioda().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKrajPerioda().getMinute() + " do: " +
                                    raspored.getTermini().get(raspored.getTermini().indexOf(t)).getPocetakPerioda().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t)).getPocetakPerioda().getMinute());
                            System.out.println("Od: " + t.getKrajPerioda().getHour() + ":" + t.getKrajPerioda().getMinute() + " do: " + raspored.getHourTo());
                        } else if (raspored.getTermini().get(raspored.getTermini().indexOf(t) + 1).getPocetakPerioda().toLocalTime() != t.getKrajPerioda().toLocalTime()) {
                            System.out.println("Od: " + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKrajPerioda().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKrajPerioda().getMinute() + " do: " +
                                    raspored.getTermini().get(raspored.getTermini().indexOf(t)).getPocetakPerioda().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t)).getPocetakPerioda().getMinute());
                        } else {
                            System.out.println("Od: " + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getPocetakPerioda().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getPocetakPerioda().getMinute() +
                                    " do: " + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKrajPerioda().getHour() +
                                    ":" + raspored.getTermini().get(raspored.getTermini().indexOf(t) - 1).getKrajPerioda().getMinute());
                        }
                    } catch (IndexOutOfBoundsException exc) {
                        System.out.println("Od: " + raspored.getHourFrom() + " do: " + t.getPocetakPerioda().getHour() + ":" + t.getPocetakPerioda().getMinute());
                    }

                    if (raspored.getTermini().indexOf(t) + 1 == raspored.getTermini().size()) {
                        System.out.println("Od: " + t.getKrajPerioda().getHour() + ":" + t.getKrajPerioda().getMinute() +
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
                System.out.println("Prostorija "+t.getProstorija().getNaziv()+" je zauzeta u terminu "+t.getPocetakPerioda().toLocalTime() + " do "+ t.getKrajPerioda().toLocalTime());
            }
        }
        System.out.println("Prostorija je slobodna");
    }

    // Korisnik unosi period
    @Override
    public void isTerminSlobodan(LocalDateTime pocetak1, LocalDateTime kraj1, Raspored raspored) {
        for(Termin t:raspored.getTermini()){
            if((t.getPocetakPerioda().isAfter(pocetak1) || t.getPocetakPerioda()==pocetak1) && (t.getKrajPerioda() == kraj1 || t.getKrajPerioda().isBefore(kraj1))){
                System.out.println("Zadati termin je zauzet");
            }
        }
        System.out.println("Termin je slobodan");
    }




    @Override
    public void izlisatavnjeZauzetihTermina(String podatak, Raspored raspored) {
        Map<String,String> mapaStvari = new HashMap<>();
        for(Termin t : raspored.getTermini()){
            mapaStvari = t.getDodatneStvari();
            if(mapaStvari.containsValue(podatak) || t.getDodatneStvari().containsValue(podatak)){

                long period = ChronoUnit.DAYS.between(t.getPocetakPerioda().toLocalDate(),t.getKrajPerioda().toLocalDate());
                System.out.println("Za period: " +t.getPocetakPerioda().toLocalDate() +" do " +t.getKrajPerioda().toLocalDate() +" u vremenu: "+t.getPocetakPerioda().toLocalTime() + " do + "+t.getKrajPerioda().toLocalTime()+ " danom " +t.getPocetakPerioda().getDayOfWeek());
                for(int i = 1;i<period/7;i++){
                    System.out.println(t.getPocetakPerioda().toLocalDate().plusDays(i*7));
                }
            }
        }

    }



    private void addAdditional(Map<String,String> dodatneStvari,String s){
        if(!s.isEmpty()) {
            String[] info = s.split(","); //Znaci string izgleda kao : Profesor:Arsenije Petrovic,Racunar=DA,Predmet="UUP"

            String finale = "";
            int i = 0;
            while (i < info.length) {
                finale = info[i]; // Profesor:Arsenije Petrovic
                String[] keyValue = finale.split(":");
                dodatneStvari.put(keyValue[0], keyValue[1]);
                i++;
            }
        }

    }
    @Override
    public boolean kreirajTerminUzPk(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda, Raspored r, Prostorija p ,String dodatneStvari) throws Exception {
       // LocalDateTime ltPocetak = LocalDateTime.ofInstant(r.getFrom().toInstant(), ZoneId.systemDefault()); // u rasporedu period od kad do kad vazi
       // LocalDateTime ltKraj = LocalDateTime.ofInstant(r.getTo().toInstant(), ZoneId.systemDefault()); // takodje treba dodati i za sate proveru!

        LocalDate ltPocetak = r.getFrom();
        LocalDate ltKraj = r.getTo();

        LocalTime start = pocetakPerioda.toLocalTime();
        LocalTime end = krajPerioda.toLocalTime();
       // LocalTime start = ltPocetak.toLocalTime();
        // LocalTime end = ltKraj.toLocalTime();

        if (pocetakPerioda.toLocalDate().isBefore(ltPocetak) || krajPerioda.toLocalDate().isAfter(ltKraj) || (pocetakPerioda.toLocalDate().isAfter(ltKraj) || krajPerioda.toLocalDate().isBefore(ltPocetak))) { //Od 10.10.2023 Do 1.1.2024  odSati 8-21
            //   9.10.2023 Do 1.5.2024
            //System.out.println("PERIOD JE NEVAZECI!!!");
            throw new NeVazeciPeriodException();

        }

        for (Termin t : r.getTermini()) {
            //Ako termin ima isti dan, isti sat i iste minute i da je izmedju pocetka perioda i kraja onda ne mozemo da napravimo periodicni termin
           if(preklapanjeTermina(day,pocetakPerioda,krajPerioda,t)){
               return false;
           }

        }

        LocalDateTime period = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getMonthValue(), pocetakPerioda.getDayOfMonth(), start.getHour(), start.getMinute()); // period 23.10.2023 do 24.1.2024
        LocalDateTime periodDo = LocalDateTime.of(pocetakPerioda.getYear(), pocetakPerioda.getMonthValue(), pocetakPerioda.getDayOfMonth(), end.getHour(), end.getMinute());
        Termin tP = new Termin(pocetakPerioda,krajPerioda,p);
        tP.setPocetakPerioda(pocetakPerioda);
        tP.setKrajPerioda(krajPerioda);
        while (periodDo.compareTo(krajPerioda) < 0) {
            if(period.getDayOfWeek() == day) {
                if(r.getIzuzetiDani().contains(period.toLocalDate())){
                    throw new DatumUIzuzetomDanuException();
                }
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
    public Termin kreirajTerminPt(LocalDateTime pocetak, int trajanje, Prostorija prostorija, Raspored raspored) {
        return null;
    }




}

