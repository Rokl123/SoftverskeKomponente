package specifikacija;

import klase.Raspored;

public abstract class ImportExport {

    public abstract Raspored ucitajRasporedJson(String fileName);

    public abstract Raspored ucitajRasporedCsv(String filepath,String ConfigFile) throws Exception;

    public abstract void upisiRasporedUJson(String fileName,String path,Raspored raspored);

    public abstract void upisiRasporedUCsv(String fileName,String path,Raspored raspored);

    public abstract void upisiRasporedUPdf(String fileName,String path,Raspored raspored);

}
