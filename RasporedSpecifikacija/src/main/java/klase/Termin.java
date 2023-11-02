package klase;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Termin {

    private LocalDateTime pocetak;

    private LocalDateTime kraj;

    private int trajanje;

    private Prostorija prostorija;

    private Map<String,String> dodatneStvari;

    public Termin(Map<String, String> dodatneStvari) {
        this.dodatneStvari = dodatneStvari;
    }

    public Termin() {
        dodatneStvari = new HashMap<>();
    }

    public Termin(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.prostorija = prostorija;
    }

    public Termin(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija, Map<String, String> dodatneStvari) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.prostorija = prostorija;
        this.dodatneStvari = dodatneStvari;
    }

    public Termin(LocalDateTime pocetak, int trajanje, Prostorija prostorija) {
        this.pocetak = pocetak;
        this.trajanje = trajanje;
        this.prostorija = prostorija;
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
                "pocetak=" + pocetak +
                ", kraj=" + kraj +
                ", trajanje=" + trajanje +
                ", prostorija=" + prostorija +
                ", dodatneStvari=" + dodatneStvari +
                '}';
    }
}
