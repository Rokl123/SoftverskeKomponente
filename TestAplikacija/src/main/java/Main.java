import klase.Prostorija;
import klase.Raspored;
import specifikacija.ImportExport;
import specifikacija.LocalDateTimeTypeAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{

        Raspored raspored = new Raspored();
        ImportExport ie = new ImportExport(raspored.getHourFrom(),raspored.getHourTo());

        Scanner sc = new Scanner(System.in);
        System.out.println("Ucitavanje fajlova:");
        System.out.println("1. Ucitavanje preko csv:");
        System.out.println("2. Ucitavanje preko json-a:");

            switch (sc.nextLine()) {
                case "1":
                    System.out.println("Unesite putanju do fajla");
                    String fajl = sc.nextLine();
                    System.out.println("Unesite putanju do config fajla");
                    String config = sc.nextLine();
                    raspored.setTermini(ie.ucitajRasporedCsv(fajl,config));
                    break;
                case "2":
                    System.out.println("Unesite putanju do fajla");
                    String fajl1 = sc.nextLine();
                    raspored.setTermini(ie.ucitajRasporedJson(fajl1));
                    break;
            }
        List<Prostorija> prostorije = new ArrayList<>();

            System.out.println("Unesite ime fajla do prostorija");
            String fajl = sc.nextLine();
            prostorije = ie.ucitajProstorije(fajl);
        Imp1 imp1 = new Imp1();

        boolean flag = true;

        while(flag){
            System.out.println("Rad sa rasporedom:");
            System.out.println("1. Pretrazivanje rasporeda po kriterijumima:");
            System.out.println("2. Kreiranje termina:");
            System.out.println("3. Promena termina:");
            System.out.println("4. Obrisis termin:");
            System.out.println("5. Snimi fajl:");
            System.out.println("6. Izlistaj sve prostorije:");
            System.out.println("0. Zavrsetak programa:");

            switch (sc.nextLine()){
                case "1":
                    System.out.println("Unesite kriterijum po kome pretrazujete raspore: ");
                    String kriterijum = sc.nextLine();
                    System.out.println("Zauzeti termini");
                    imp1.izlisatavnjeZauzetihTermina(kriterijum,raspored);
                    System.out.println("Slobodni termini termini");
                    imp1.izlistavanjeSlobodniTermini(kriterijum,raspored);
                    break;
                case "2":
                    System.out.println("Unesite pocetak termina: ");
                    LocalDateTime pocetak = LocalDateTime.parse(sc.nextLine());
                    System.out.println("Unesite kraj termina: ");
                    LocalDateTime kraj = LocalDateTime.parse(sc.nextLine());
                    System.out.println("Unesite naziv prostorije ");
                    String naziv = sc.nextLine();
                    System.out.println("Unesite kapacitet prostorije ");
                    String kapacitet = sc.nextLine();
                    Prostorija prostorija = new Prostorija(naziv,Integer.parseInt(kapacitet));
                    imp1.kreirajTerminUzPk(pocetak,kraj,prostorija,raspored);
                    break;
                case "3":
                    System.out.println("Unesite pocetak termina trenutnog termina: ");
                    LocalDateTime pocetak1 = LocalDateTime.parse(sc.nextLine());
                    System.out.println("Unesite kraj termina trenutnog termina: ");
                    LocalDateTime kraj1 = LocalDateTime.parse(sc.nextLine());
                    imp1.premestajTermina(pocetak1,kraj1,raspored);
                    break;
                case "4":
                    System.out.println("Unesite pocetak termina za brisanje: ");
                    LocalDateTime pocetak2 = LocalDateTime.parse(sc.nextLine());
                    System.out.println("Unesite kraj termina za brisanje : ");
                    LocalDateTime kraj2 = LocalDateTime.parse(sc.nextLine());
                    imp1.brisanjeTermina(pocetak2,kraj2,raspored);
                    break;
                case "5":
                    System.out.println("1. Upisivanje preko csv-a:");
                    System.out.println("2. Upisivanje preko json-a:");
                    System.out.println("3. Upisivanje preko pdf-a:");

                    switch (sc.nextLine()) {
                        case "1":
                            System.out.println("Unesite kako hocete da vam se zove novi fajl ");
                            String imeF = sc.nextLine();
                            System.out.println("Unesite putanju gde zelite da sacuvate fajl ");
                            String fPath = sc.nextLine();
                            ie.upisiRasporedUCsv(imeF,fPath,raspored);
                     break;
                     case "2":
                         System.out.println("Unesite kako hocete da vam se zove novi fajl ");
                         String imeF1 = sc.nextLine();
                         System.out.println("Unesite putanju gde zelite da sacuvate fajl ");
                         String fPath1 = sc.nextLine();
                         ie.upisiRasporedUJson(imeF1,fPath1,raspored);
                         break;
                        case "3":
                            System.out.println("Unesite kako hocete da vam se zove novi fajl ");
                            String imeF2 = sc.nextLine();
                            System.out.println("Unesite putanju gde zelite da sacuvate fajl ");
                            String fPath2 = sc.nextLine();
                            ie.upisiRasporedUPdf(imeF2,fPath2,raspored);
                            break;
                        default:
                            System.out.println("Komanda nije na validna");
                    }
                    break;
                case "6":
                    for (Prostorija p:prostorije){
                        System.out.println(p.toString());
                    }
                    break;
                case "0":
                    flag = false;
                    break;

                default:
                    System.out.println("Komanda nije na validna");
            }
        }
    }
}
