public class Niewypozyczona implements Stan {

    public Ksiazka wypozycz(String nazwa, String autor, String imie, String nazwisko){
        System.out.println("Wybrana książka nie istnieje lub jest niedostępna");
        return null;
    }

    public boolean rezerwuj(String nazwa, String autor, String imie, String nazwisko){
        System.out.println("Wybrana książka nie istnije lub jest niedostępna");
        return false;
    }

    public boolean zwroc(String nazwa, String autor, String imie, String nazwisko){
        System.out.println("Wybrana książka jest niewypozyczona lub jest zarezerwowana");
        return false;
    }

    public boolean przedluzWypozyczenie(String nazwa, String autor, String imie, String nazwisko){
        System.out.println("Wybrana książka jest niewypozyczona");
        return false;
    }
}
