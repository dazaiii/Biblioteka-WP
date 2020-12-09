public class Wypozyczona implements Stan {

    public Ksiazka wypozycz(String nazwa, String autor, String imie, String nazwisko){
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        String[] ksiazka = bazaKsiazek.wyszukaj(nazwa,autor);
        Ksiazka K = new Ksiazka(ksiazka[0], ksiazka[1], ksiazka[2], ksiazka[3]);
        bazaKsiazek.zaktualizuj(nazwa, autor, imie, nazwisko, 0);
        return K;
    }

    public boolean rezerwuj(String nazwa, String autor, String imie, String nazwisko){
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        bazaKsiazek.zaktualizuj(nazwa, autor, imie, nazwisko,2);
        return true;
    }

    public boolean zwroc(String nazwa, String autor, String imie, String nazwisko) {
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        bazaKsiazek.zaktualizuj(nazwa, autor,imie, nazwisko, 1);
        return true;
    }

    public boolean przedluzWypozyczenie(String nazwa, String autor, String imie, String nazwisko){
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        bazaKsiazek.zaktualizuj(nazwa,autor,imie, nazwisko,0);
        return true;
    }
}
