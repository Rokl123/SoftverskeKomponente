package specifikacija;

import klase.Raspored;

public abstract class ImportExport {

    public abstract Raspored ucitajRasporedJson(String fileName);

    public abstract Raspored ucitajRasporedCsv(String fileName);

    public abstract void upisiRasporedUJson(String fileName,String path);

    public abstract void upisiRasporedUCsv(String fileName,String path);

    public abstract void upisiRasporedUPdf(String fileName,String path);

}
