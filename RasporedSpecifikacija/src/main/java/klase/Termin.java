package klase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Termin {

    private LocalDateTime pocetak;

    private LocalDateTime kraj;

    private LocalDateTime pocetakPerioda;

    private LocalDateTime krajPerioda;
    private List<LocalDate> vremeOdrzavanja ;
    private int trajanje;


    private Prostorija prostorija;

    private Map<String,String> dodatneStvari;
    public Termin(LocalDateTime pocetak, int trajanje, Prostorija prostorija) {
        this.pocetak = pocetak;
        this.trajanje = trajanje;
        this.prostorija = prostorija;
    }
    public Termin(LocalDateTime pocetakPerioda,LocalDateTime krajPerioda,Prostorija p){
        this.pocetakPerioda = pocetakPerioda;
        this.krajPerioda=krajPerioda;
        vremeOdrzavanja = new ArrayList<>();
        this.prostorija=p;
        dodatneStvari = new HashMap<>(); dodatneStvari = new HashMap<>();
    }

    public Termin(Map<String, String> dodatneStvari) {
        this.dodatneStvari = dodatneStvari;
    }

    public Termin() {
        dodatneStvari = new HashMap<>();
    }

    public Termin(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija, Map<String, String> dodatneStvari) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.prostorija = prostorija;
        this.dodatneStvari = dodatneStvari;
    }

    public void setPocetakPerioda(LocalDateTime pocetakPerioda) {
        this.pocetakPerioda = pocetakPerioda;
    }

    public void setKrajPerioda(LocalDateTime krajPerioda) {
        this.krajPerioda = krajPerioda;
    }

    public void setVremeOdrzavanja(List<LocalDate> vremeOdrzavanja) {
        this.vremeOdrzavanja = vremeOdrzavanja;
    }




    public LocalDateTime getPocetakPerioda() {
        return pocetakPerioda;
    }

    public LocalDateTime getKrajPerioda() {
        return krajPerioda;
    }

    public List<LocalDate> getVremeOdrzavanja() {
        return vremeOdrzavanja;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public LocalDateTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalDateTime kraj) {
        this.kraj = kraj;
    }

    public LocalDateTime getPocetak() {
        return pocetak;
    }

    public Prostorija getProstorija() {
        return prostorija;
    }

    public void setProstorija(Prostorija prostorija) {
        this.prostorija = prostorija;
    }

    public void setPocetak(LocalDateTime pocetak) {
        this.pocetak = pocetak;
    }

    public Map<String, String> getDodatneStvari() {
        return dodatneStvari;
    }

    public void setDodatneStvari(Map<String, String> dodatneStvari) {
        this.dodatneStvari = dodatneStvari;
    }
    

    @Override
    public String toString() {
        return "Termin{" +
                "pocetak=" + pocetakPerioda +
                ", kraj=" + krajPerioda +
                ", trajanje=" + trajanje +
                ", prostorija=" + prostorija +
                ", dodatneStvari=" + dodatneStvari +
                '}';
    }
}
