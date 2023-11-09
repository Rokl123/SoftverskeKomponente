package specifikacija;

import klase.Prostorija;
import klase.Raspored;
import klase.Termin;

import java.time.LocalDateTime;

public interface DodelaTermina {

    boolean preklapanjeTermina(LocalDateTime pocetak1,LocalDateTime kraj1,LocalDateTime pocetak2,LocalDateTime kraj2);

    Prostorija dodavanjeProstorijaSaOsobinama(String naziv,int kapacitet);

    Termin kreirajTerminUzPk(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija, Raspored raspored);

    Termin kreirajTerminPt(LocalDateTime pocetak, int trajanje, Prostorija prostorija, Raspored raspored);

    boolean brisanjeTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored);

    boolean premestajTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored);

    void izlistavanjeSlobodniTermini(String kriterijum,Raspored raspored);

    void pretrazivanjeVezaniPodaci(String podatak, Raspored raspored);


}
