package klase;


import specifikacija.DodelaTermina;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Raspored  {

   private List<Termin> termini;


   public void addTermin(Termin t){
        termini.add(t);
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }

    @Override
    public String toString() {
        return "Raspored{" +
                "termini=" + termini +
                '}';
    }
}
