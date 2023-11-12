package klase;


import specifikacija.DodelaTermina;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Raspored  {

   private List<Termin> termini;

   private Date from;

   private Date to;

   private int hourFrom; //-------> ovo sve oznacava od kad do kad ce trajati raspored

   private int hourTo;

   // promenjen konstruktor rasporeda za inicijalizaciju samog raspored
    // Raspored sada ima od kad i do kad traje kao i od koliko i do koliko sati svakoga dana

    // Drugi konstruktor se samo koristi u svrhe metoda da se ne bi pravila nova trajanja


   public Raspored(){

       try {
           initializeSchedule();
       }
       catch(Exception e) {
           e.printStackTrace();
         }

   }


   public void initializeSchedule() throws ParseException {

       System.out.println("Unesite od kad do kad ce trajati raspored: ");
       // 12.10.2023 12.12.2023 8 - 10  00-24h format
       Scanner sc = new Scanner(System.in); //
       String[] datum = sc.nextLine().split(" ");

       this.from = new SimpleDateFormat("dd.MM.yyyy").parse(datum[0]);

       this.to = new SimpleDateFormat("dd.MM.yyyy").parse(datum[1]);

       this.hourFrom = Integer.parseInt(datum[2]);

       this.hourTo = Integer.parseInt(datum[3]);

   }


    public void addTermin(Termin t){
        termini.add(t);
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }

    @Override
    public String toString() {
        return "Raspored{" +
                "termini=" + termini +
                '}';
    }


    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public int getHourFrom() {
        return hourFrom;
    }

    public int getHourTo() {
        return hourTo;
    }
}
