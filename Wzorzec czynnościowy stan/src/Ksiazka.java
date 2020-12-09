public class Ksiazka {
    public String tytul;
    public String autor;
    public String IBSN;
    public String wydawnictwo;

    public Ksiazka(){

    }

    public Ksiazka(String tytul, String autor, String IBSN, String wydawnictwo) {
        this.tytul = tytul;
        this.autor = autor;
        this.IBSN = IBSN;
        this.wydawnictwo = wydawnictwo;
    }

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

    public Ksiazka wypozycz(String nazwa, String autor, String imie, String nazwisko) {
        Stan stan;
        if ( !status(nazwa,autor) ){
            stan = new Niewypozyczona();
        }
        else{
            stan = new Wypozyczona();
        }
        Ksiazka K = stan.wypozycz(nazwa, autor, imie, nazwisko);
        return K;
    }

    public boolean rezerwuj(String nazwa, String autor, String imie, String nazwisko) {
        Stan stan;
        if ( !status(nazwa,autor) ){
            stan = new Niewypozyczona();
        }
        else{
            stan = new Wypozyczona();
        }
        boolean zm = stan.rezerwuj(nazwa, autor, imie, nazwisko);
        return zm;
    }

    public boolean zwroc(String nazwa, String autor, String imie, String nazwisko) {
        Stan stan;
        if ( status(nazwa,autor) ){
            stan = new Niewypozyczona();
        }
        else{
            stan = new Wypozyczona();
        }
        boolean zm = stan.zwroc(nazwa, autor,imie, nazwisko);
        return zm;
    }

    public boolean przedluzWypozyczenie(String nazwa, String autor, String imie, String nazwisko) {
        Stan stan;
        if ( status(nazwa,autor) ){
            stan = new Niewypozyczona();
        }
        else{
            stan = new Wypozyczona();
        }
        boolean zm = stan.przedluzWypozyczenie(nazwa, autor, imie, nazwisko);
        return zm;
    }
}