package klase;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminPeriod extends Termin{

    private List<LocalDate> vremeOdrzavanja; //

    private LocalDateTime pocetakPerioda;

    private LocalDateTime krajPerioda;

    private Prostorija prostorija;

    private LocalTime pocetakTermina;

    private LocalTime krajTermina;

    private Map<String,String> dodatneStvari;

    public TerminPeriod(LocalDateTime pocetakPerioda,LocalDateTime krajPerioda,Prostorija p){
        this.pocetakPerioda = pocetakPerioda;
        this.krajPerioda=krajPerioda;
        vremeOdrzavanja = new ArrayList<>();
        this.prostorija=p;
        dodatneStvari = new HashMap<>();
    }


    public List<LocalDate> getVremeOdrzavanja() {
        return vremeOdrzavanja;
    }

    public LocalDateTime getPocetakPerioda() {
        return pocetakPerioda;
    }

    public LocalDateTime getKrajPerioda() {
        return krajPerioda;
    }

    public Prostorija getProstorija() {
        return prostorija;
    }

    public LocalTime getPocetakTermina() {
        return pocetakTermina;
    }

    public LocalTime getKrajTermina() {
        return krajTermina;
    }

    public Map<String, String> getDodatneStvari() {
        return dodatneStvari;
    }

    public void setVremeOdrzavanja(List<LocalDate> vremeOdrzavanja) {
        this.vremeOdrzavanja = vremeOdrzavanja;
    }

    public void setPocetakPerioda(LocalDateTime pocetakPerioda) {
        this.pocetakPerioda = pocetakPerioda;
    }

    public void setKrajPerioda(LocalDateTime krajPerioda) {
        this.krajPerioda = krajPerioda;
    }

    public void setProstorija(Prostorija prostorija) {
        this.prostorija = prostorija;
    }

    public void setPocetakTermina(LocalTime pocetakTermina) {
        this.pocetakTermina = pocetakTermina;
    }

    public void setKrajTermina(LocalTime krajTermina) {
        this.krajTermina = krajTermina;
    }

    public void setDodatneStvari(Map<String, String> dodatneStvari) {
        this.dodatneStvari = dodatneStvari;
    }

    @Override
    public String toString() {
        return "Termin{" +
                "pocetakPerioda=" + pocetakPerioda +
                ", krajPerioda=" + krajPerioda +
                ", prostorija=" + prostorija +
                ", dodatneStvari=" + dodatneStvari +
                ", vremeOdrzavanja=" + vremeOdrzavanja +
                ", pocetakTermina=" + pocetakTermina +
                ", krajTermina=" + krajTermina +
                '}';
    }
}
