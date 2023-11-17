package specifikacija;

import klase.Prostorija;
import klase.Raspored;
import klase.Termin;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface DodelaTermina {

    boolean preklapanjeTermina(LocalDateTime pocetak1,LocalDateTime kraj1,LocalDateTime pocetak2,LocalDateTime kraj2);

    Termin kreirajTerminUzPk(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija, Raspored raspored);

    boolean kreirajTerminUzPk(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda, Raspored r, Prostorija p, String dodatneStvari);

    Termin kreirajTerminPt(LocalDateTime pocetak, int trajanje, Prostorija prostorija, Raspored raspored);

    boolean brisanjeTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored);

    boolean premestajTermina(LocalDateTime pocetak, LocalDateTime kraj, Raspored raspored);
    boolean premestajTermina(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda, Raspored r, LocalTime start, LocalTime end);


    void izlistavanjeSlobodniTermini(String kriterijum,Raspored raspored);

    void izlisatavnjeZauzetihTermina(String podatak, Raspored raspored);

    void isProstorijaZauzeta(Prostorija prostorija, Raspored raspored);

    void isTerminSlobodan(LocalDateTime pocetak1,LocalDateTime kraj1, Raspored raspored);

    void isTerminSlobodan(DayOfWeek day, LocalDateTime pocetakPerioda, LocalDateTime krajPerioda);

}
