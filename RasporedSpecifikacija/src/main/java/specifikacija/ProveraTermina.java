package specifikacija;


import klase.Prostorija;
import klase.Raspored;

import java.time.LocalDateTime;

public interface ProveraTermina {

    boolean proveraZauzetosti(LocalDateTime pocetak, Prostorija prostorija);

    boolean proveraZauzetosti(String dan, LocalDateTime datumOd,LocalDateTime datumDo);

}
