import java.util.Random;

public class Zakup {
    public String wystawFakture(){
        Random random = new Random();
        int liczba = random.nextInt(4000);
        String nrFaktury = "FA" + liczba;
        System.out.println("Numer faktury to: " + nrFaktury);
        return nrFaktury;
    }
    public String wystawParagon(){
        Random random = new Random();
        int liczba = random.nextInt(4000);
        String nrParagonu= "PA" + liczba;
        System.out.println("Numer paragonu to: " + nrParagonu);
        return nrParagonu;
    }

    public void kup(){
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        bazaKsiazek.usun();
    }
}
