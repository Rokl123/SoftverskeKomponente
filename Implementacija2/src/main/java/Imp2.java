import klase.Prostorija;
import klase.Raspored;
import klase.Termin;
import specifikacija.DodelaTermina;

import java.time.LocalDateTime;

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
    public Termin kreirajTerminUzPk(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija, Raspored raspored) {
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
}
