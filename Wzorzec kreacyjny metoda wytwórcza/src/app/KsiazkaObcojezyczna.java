public class KsiazkaObcojezyczna implements Ksiazka {
    public String tytul;
    public String autor;
    public String IBSN;
    public String wydawnictwo;
    public String jezyk;

    public KsiazkaObcojezyczna(){

    }

    public KsiazkaObcojezyczna(String tytul, String autor, String IBSN, String wydawnictwo, String jezyk) {
        this.tytul = tytul;
        this.autor = autor;
        this.IBSN = IBSN;
        this.wydawnictwo = wydawnictwo;
        this.jezyk = jezyk;
    }

    public void opis(String nazwa, String autor){
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        String[] ksiazka = bazaKsiazek.wyszukaj(nazwa,autor);
        System.out.println("Autorem książki " + ksiazka[0] + " jest " + ksiazka[1]);
        System.out.println("Jej ISBN to " + ksiazka[2]);
        System.out.println("Jest wydawana przez wydawnictwo: " + ksiazka[3]);
        System.out.println("Jezyk ksiazki to " + ksiazka[6]);
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

    public KsiazkaPolskojezyczna wypozycz(String nazwa, String autor, String imie, String nazwisko) throws Exception{
        if ( !status(nazwa,autor) ){
            throw new Exception("Wybrana książka nie istnije lub jest niedostępna");
        }
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        String[] ksiazka = bazaKsiazek.wyszukaj(nazwa,autor);
        KsiazkaPolskojezyczna K = new KsiazkaPolskojezyczna(ksiazka[0], ksiazka[1], ksiazka[2], ksiazka[3]);
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
