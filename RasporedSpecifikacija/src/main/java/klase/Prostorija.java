package klase;

import java.util.List;

public class Prostorija {

    private String naziv;

    private int kapacitet;


    public Prostorija(String naziv, int kapacitet) {
        this.naziv = naziv;
        this.kapacitet = kapacitet;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }


    @Override
    public String toString() {
        return naziv + " " + kapacitet;
    }
}
