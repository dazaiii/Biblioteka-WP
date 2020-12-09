public interface Stan {
    Ksiazka wypozycz(String nazwa, String autor, String imie, String nazwisko);
    boolean rezerwuj(String nazwa, String autor, String imie, String nazwisko);
    boolean zwroc(String nazwa, String autor, String imie, String nazwisko);
    boolean przedluzWypozyczenie(String nazwa, String autor, String imie, String nazwisko);
}
