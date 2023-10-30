package klase;

import java.util.List;

public class Prostorija {

    private String naziv;

    private int kapacitet;

    private List<PotrebneOprema> potrebneStvariLista;

    public Prostorija(String naziv, int kapacitet, List<PotrebneOprema> potrebneStvariLista) {
        this.naziv = naziv;
        this.kapacitet = kapacitet;
        this.potrebneStvariLista = potrebneStvariLista;
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

    public List<PotrebneOprema> getPotrebneStvariLista() {
        return potrebneStvariLista;
    }

    public void setPotrebneStvariLista(List<PotrebneOprema> potrebneStvariLista) {
        this.potrebneStvariLista = potrebneStvariLista;
    }

    @Override
    public String toString() {
        return naziv + " " + kapacitet;
    }
}
