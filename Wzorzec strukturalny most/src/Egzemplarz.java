public interface Egzemplarz {
    void opis(String nazwa, String autor);
    boolean status(String nazwa, String autor);
    Ksiazka wypozycz(String nazwa, String autor, String imie, String nazwisko) throws Exception;
    boolean rezerwuj(String nazwa, String autor, String imie, String nazwisko) throws Exception;
    boolean zwroc(String nazwa, String autor, String imie, String nazwisko) throws Exception;
    boolean przedluzWypozyczenie(String nazwa, String autor, String imie, String nazwisko) throws Exception;
}
