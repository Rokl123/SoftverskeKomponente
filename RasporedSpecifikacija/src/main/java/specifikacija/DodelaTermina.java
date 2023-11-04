package specifikacija;

import klase.Prostorija;
import klase.Raspored;
import klase.Termin;

import java.time.LocalDateTime;

public interface DodelaTermina {

    boolean preklapanjeTermina(LocalDateTime pocetak1,LocalDateTime kraj1,LocalDateTime pocetak2,LocalDateTime kraj2);

    Prostorija dodavanjeProstorijaSaOsobinama(String naziv,int kapacitet);

    Termin kreirajTerminUzPk(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija);

    Termin kreirajTerminPt(LocalDateTime pocetak, int trajanje, Prostorija prostorija);

    boolean brisanjeTermina(LocalDateTime pocetak, LocalDateTime kraj);

    boolean premestajTermina(LocalDateTime pocetak, LocalDateTime kraj);

    void izlistavanjeSlobodniTermini(String kriterijum);

    void pretrazivanjeVezaniPodaci(String podatak);


}
