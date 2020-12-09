public class Zwroc implements OperacjeNaKsiazce {
    public boolean operacja(String nazwa, String autor, String imie, String nazwisko) {
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        bazaKsiazek.zaktualizuj(nazwa, autor,imie, nazwisko, 1);
        return true;
    }
}
