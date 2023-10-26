package klase;


import specifikacija.DodelaTermina;

import java.time.LocalDateTime;
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
        if (kraj1.isBefore(pocetak2) || kraj2.isAfter(pocetak1)) {
            return false;
        }
        return true;
    }

    @Override
    public Prostorija dodavanjeProstorijaSaOsobinama(String naziv, int kapacitet, List<PotrebneOprema> osobineProstorije) {
        return new Prostorija(naziv,kapacitet,osobineProstorije);
    }

    @Override
    public Termin kreirajTerminUzPk(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija) {
        // ako se termini preklapaju onda ne moze da se kreira novi raposred
        // tada baciti exception sa porukicom
        return new Termin(pocetak,kraj,prostorija);
    }

    @Override
    public Termin kreirajTerminPt(LocalDateTime pocetak, int trajanje, Prostorija prostorija) {
        // ako se termini preklapaju onda ne moze da se kreira novi raposred
        // tada baciti exception sa porukicom
        return new Termin(pocetak,trajanje,prostorija);
    }

    @Override
    public boolean brisanjeTermina(LocalDateTime pocetak, LocalDateTime kraj) {
        //ako postoji true, naic obrisano

        //ako ne onda false

        return true;
    }

    @Override
    public boolean premestajTermina(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija) {
        // ako postoji dati termin onda se on brise uz pomoc metode brisanjeTermina()
        // posle se kreira novi termin uz pomoc metode kreirajTermin()
        return true;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }
}
