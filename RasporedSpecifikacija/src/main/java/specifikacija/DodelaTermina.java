package specifikacija;

import klase.PotrebneOprema;
import klase.Prostorija;
import klase.Termin;

import java.time.LocalDateTime;
import java.util.List;

public interface  DodelaTermina {

    boolean preklapanjeTermina(LocalDateTime pocetak1,LocalDateTime kraj1,LocalDateTime pocetak2,LocalDateTime kraj2);

    Prostorija dodavanjeProstorijaSaOsobinama(String naziv,int kapacitet, List<PotrebneOprema> osobineProstorije);

    Termin kreirajTerminUzPk(LocalDateTime pocetak,LocalDateTime kraj, Prostorija prostorija);

    Termin kreirajTerminPt(LocalDateTime pocetak,int trajanje, Prostorija prostorija);

    boolean brisanjeTermina(LocalDateTime pocetak,LocalDateTime kraj);

    boolean premestajTermina(LocalDateTime pocetak,LocalDateTime kraj, Prostorija prostorija);


}
