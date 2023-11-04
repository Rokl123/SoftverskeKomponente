package klase;


import specifikacija.DodelaTermina;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Raspored implements DodelaTermina {

   private List<Termin> termini;


   public void addTermin(Termin t){
        termini.add(t);
    }

    // ovde pisemo pretragu

    //provera zauzetosti termina i sobe

    //izlistavanje slobodnih termina po kriterijumima


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

    public boolean isProstorijaZauzeta(Prostorija prostorija){
       for(Termin t:termini){
           if(t.getProstorija().equals(prostorija)){
               System.out.println("Prostorija je zauzeta");
               return true;
           }
       }
        System.out.println("Prostorija je slobodna");
       return false;
    }

    @Override
    public Termin kreirajTerminUzPk(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija) {
       for(Termin t:termini){
            if(!preklapanjeTermina(pocetak,kraj,t.getPocetak(),t.getKraj())){
                System.out.println("Termin je uspesno kreiran");
                return new Termin(pocetak, kraj, prostorija);
            }
        }
        System.out.println("Ovaj termin je zauzet, tako da termin u datim vrememnima ne moze biti kreiran");
        return null;
    }

    @Override
    public Termin kreirajTerminPt(LocalDateTime pocetak, int trajanje, Prostorija prostorija) {
        // ako se termini preklapaju onda ne moze da se kreira novi raposred
        // tada baciti exception sa porukicom
        for(Termin t:termini){
            if(!preklapanjeTermina(pocetak,pocetak.plusHours(trajanje/60).minusMinutes(trajanje%60),t.getPocetak(),t.getKraj())){
                System.out.println("Termin je uspesno kreiran");
                return new Termin(pocetak,trajanje,prostorija);
            }
        }
        System.out.println("Ovaj termin je zauzet, tako da termin u datim vrememnima ne moze biti kreiran");
        return null;

    }

    @Override
    public boolean brisanjeTermina(LocalDateTime pocetak, LocalDateTime kraj) {
        for(Termin t:termini){
            if(preklapanjeTermina(pocetak,pocetak,t.getPocetak(),t.getKraj())){
                termini.remove(t);
                System.out.println("Termin je uspesno obrisan");
                return true;
            }
        }
        System.out.println("Termin nije obrisan");
        return false;
    }

    @Override
    public boolean premestajTermina(LocalDateTime pocetak, LocalDateTime kraj) {

        for(Termin t:termini){
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
    public void izlistavanjeSlobodniTermini(String kriterijum) {

    }

    @Override
    public void pretrazivanjeVezaniPodaci(String podatak) {

       for(Termin t:termini){
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
}
