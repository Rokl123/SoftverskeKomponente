package klase;

import java.time.LocalDateTime;
import java.util.List;

public class Termin {

    private LocalDateTime pocetak;

    private LocalDateTime kraj;

    private int trajanje;

    private Prostorija prostorija;

    public Termin(LocalDateTime pocetak, LocalDateTime kraj, Prostorija prostorija) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.prostorija = prostorija;
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
}
