import klase.Prostorija;
import specifikacija.ProveraTermina;

import java.time.LocalDateTime;

public class Imp1 implements ProveraTermina {


    @Override
    public boolean proveraZauzetosti(LocalDateTime pocetak, Prostorija prostorija) {
        return false;
    }

    @Override
    public boolean proveraZauzetosti(String dan, LocalDateTime datumOd, LocalDateTime datumDo) {
        return false;
    }
}
