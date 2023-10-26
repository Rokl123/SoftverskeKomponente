package specifikacija;

public abstract class ImportExport {

    public abstract void  ucitajRasporedJson(String fileName);

    public abstract void ucitajRasporedCsv(String fileName);

    public abstract void upisiRasporedUJson(String fileName,String path);

    public abstract void upisiRasporedUCsv(String fileName,String path);

    public abstract void upisiRasporedUPdf(String fileName,String path);

}
