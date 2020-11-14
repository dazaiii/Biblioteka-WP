public class Ksiazka {
    public String tytul;
    public String autor;
    public String IBSN;
    public String wydawnictwo;

    public Ksiazka(String tytul, String autor, String IBSN, String wydawnictwo) {
        this.tytul = tytul;
        this.autor = autor;
        this.IBSN = IBSN;
        this.wydawnictwo = wydawnictwo;
    }

    public boolean status(String nazwa, String autor){
        // za pomocą select w bazie sprawdź dostępność książki
        // do tego celu można wykorzystać funkcję wyszukaj z klasy BazaKsiazek
        if (1 == 1){
            return true;
        }
        return false;
    }

    public Ksiazka wypozycz(String nazwa, String autor) throws Exception{
        if ( !status(nazwa,autor) ){
            throw new Exception("Wybrana książka nie istnije lub jest niedostępna");
        }
        // po sprawdzeniu że jest wyszukać ją w bazie zrobić update ilości i utworzyć obiekt do zwrócenia
        Ksiazka K = new Ksiazka(nazwa, tytul, "a", "A");

        return K;
    }

    public boolean rezerwuj(String nazwa, String autor){
        return true;
    }



}
