import klase.Prostorija;
import klase.Raspored;
import klase.Termin;
import specifikacija.DodelaTermina;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Imp1 implements DodelaTermina {

    @Override
    public boolean preklapanjeTermina(LocalDateTime pocetak1, LocalDateTime kraj1, LocalDateTime pocetak2, LocalDateTime kraj2) {
        if (pocetak1.isBefore(kraj2) && kraj1.isAfter(pocetak2)) {
            return true;
        }
        return false;
    }

    @Override
    public Prostorija dodavanjeProstorijaSaOsobinama(String naziv, int kapacitet) {
        return new Prostorija(naziv,kapacitet);
    }

    public boolean isProstorijaZauzeta(Prostorija prostorija, Raspored raspored){
        for(Termin t:raspored.getTermini()){
            if(t.getProstorija().equals(prostorija)){
                System.out.println("Prostorija je zauzeta");
                return true;
            }
        }
        System.out.println("Prostorija je slobodna");
        return false;
    }

    @Override // User is typing his start and end
    public Termin kreirajTerminUzPk(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija, Raspored raspored) {
        for(Termin t:raspored.getTermini()){

            if(!preklapanjeTermina(pocetak,kraj,t.getPocetak(),t.getKraj())){
                System.out.println("Termin je uspesno kreiran");
                raspored.getTermini().add(new Termin(pocetak, kraj, prostorija));
                return new Termin(pocetak, kraj, prostorija);
            }

        }
        System.out.println("Ovaj termin je zauzet, tako da termin u datim vrememnima ne moze biti kreiran");
        return null;
    }



    @Override
    public Termin kreirajTerminPt(LocalDateTime pocetak, int trajanje, Prostorija prostorija, Raspored raspored) {
        // ako se termini preklapaju onda ne moze da se kreira novi raposred
        // tada baciti exception sa porukicom
        for(Termin t:raspored.getTermini()){
            if(!preklapanjeTermina(pocetak,pocetak.plusHours(trajanje/60).minusMinutes(trajanje%60),t.getPocetak(),t.getKraj())){
                System.out.println("Termin je uspesno kreiran");
                return new Termin(pocetak,trajanje,prostorija);
            }
        }
        System.out.println("Ovaj termin je zauzet, tako da termin u datim vrememnima ne moze biti kreiran");
        return null;

    }

    @Override
    public boolean brisanjeTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored) {
        for(Termin t:raspored.getTermini()){
            if(preklapanjeTermina(pocetak,pocetak,t.getPocetak(),t.getKraj())){
                raspored.getTermini().remove(t);
                System.out.println("Termin je uspesno obrisan");
                return true;
            }
        }
        System.out.println("Termin nije obrisan");
        return false;
    }

    @Override
    public boolean premestajTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored) {

        for(Termin t:raspored.getTermini()){
            if(preklapanjeTermina(pocetak,pocetak,t.getPocetak(),t.getKraj())){
                t.setPocetak(pocetak);
                t.setKraj(kraj);
                System.out.println("Termin je uspesno promenjen");
                return true;
            }
        }
        System.out.println("Termin nije promenjen");
        return false;
    }

    @Override
    public void izlistavanjeSlobodniTermini(String kriterijum, Raspored raspored) {
        for(Termin t:raspored.getTermini()){
            if(!t.getDodatneStvari().containsValue(kriterijum)){
                System.out.println("Slobodno je");
            }
            else{
                System.out.println("Zauzeto je");
            }
        }
    }

    @Override
    public void pretrazivanjeVezaniPodaci(String podatak, Raspored raspored) {

        for(Termin t:raspored.getTermini()){
            if(t.getDodatneStvari().containsValue(podatak)){
                //postoji termin
                System.out.println(t.toString());
            }
            else{
                //pocetak 8:00 kraj 21:00
                //
                //slobodan termin
                System.out.println("Slodoan je");
            }
        }
    }

    @Override
    public boolean kreirajTerminUzPk(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda, Raspored r, Prostorija p, LocalTime start, LocalTime end) {
        return false;
    }

}
