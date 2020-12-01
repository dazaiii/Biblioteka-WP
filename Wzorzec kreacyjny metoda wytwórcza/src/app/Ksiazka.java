public interface Ksiazka {
    public void opis(String nazwa, String autor);
    public boolean status(String nazwa, String autor);
    public Ksiazka wypozycz(String nazwa, String autor, String imie, String nazwisko) throws Exception;
    public boolean rezerwuj(String nazwa, String autor, String imie, String nazwisko) throws Exception;
    public boolean zwroc(String nazwa, String autor, String imie, String nazwisko) throws Exception;
    public boolean przedluzWypozyczenie(String nazwa, String autor, String imie, String nazwisko) throws Exception;
}
