public class EgzemplarzBasic implements Egzemplarz {
    String tytul;
    public void opis(String nazwa, String autor){
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        String[] ksiazka = bazaKsiazek.wyszukaj(nazwa,autor);
        System.out.println("Autorem książki " + ksiazka[0] + " jest " + ksiazka[1]);
        System.out.println("Jej ISBN to " + ksiazka[2]);
        System.out.println("Jest wydawana przez wydawnictwo: " + ksiazka[3]);
        if(ksiazka[4].equals("1")){
            System.out.println("Książka jest aktualnie dostępna");
        }
        else {
            if(ksiazka[4].equals("0")) {
                System.out.println("Książka jest aktualnie niedostępna");
                System.out.println("Będzie dostępna od " + ksiazka[5]);
            }
            else
                System.out.println("Książka jest aktualnie niedostępna");
        }
    }

    public boolean status(String nazwa, String autor){
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        String[] wyn = bazaKsiazek.wyszukaj(nazwa,autor);
        if (wyn[4].equals("1")){
            return true;
        }
        return false;
    }

    public Ksiazka wypozycz(String nazwa, String autor, String imie, String nazwisko) throws Exception{
        if ( !status(nazwa,autor) ){
            throw new Exception("Wybrana książka nie istnije lub jest niedostępna");
        }
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        String[] ksiazka = bazaKsiazek.wyszukaj(nazwa,autor);
        Ksiazka K = new Ksiazka(ksiazka[0], ksiazka[1], ksiazka[2], ksiazka[3]);
        bazaKsiazek.zaktualizuj(nazwa, autor, imie, nazwisko, 0);
        return K;
    }

    public boolean rezerwuj(String nazwa, String autor, String imie, String nazwisko) throws Exception{
        if ( !status(nazwa,autor) ){
            throw new Exception("Wybrana książka nie istnije lub jest niedostępna");
        }
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        bazaKsiazek.zaktualizuj(nazwa, autor, imie, nazwisko,2);
        return true;
    }

    public boolean zwroc(String nazwa, String autor, String imie, String nazwisko) throws Exception{
        if ( status(nazwa,autor) ){
            throw new Exception("Wybrana książka jest niewypozyczona lub jest zarezerwowana");
        }
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        bazaKsiazek.zaktualizuj(nazwa, autor,imie, nazwisko, 1);
        return true;
    }

    public boolean przedluzWypozyczenie(String nazwa, String autor, String imie, String nazwisko) throws Exception{
        if ( status(nazwa,autor) ){
            throw new Exception("Wybrana książka jest niewypozyczona");
        }
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        bazaKsiazek.zaktualizuj(nazwa,autor,imie, nazwisko,0);
        return true;
    }
}
