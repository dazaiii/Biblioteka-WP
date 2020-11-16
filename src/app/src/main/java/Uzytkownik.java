import java.util.Scanner;

public class Uzytkownik {
    private String imie;
    private String nazwisko;
    private String pesel;
    private Konto konto;

    public Uzytkownik(int id) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    public void wyswietlMenu(){
        System.out.println("MENU: ");
        System.out.println("1. Sprawdź wypożyczone książki");
        System.out.println("2. Sprzwdź zarezerwowane książki");
        System.out.println("3. Przedłuż ważność konta");
        System.out.println("4. Wyszukaj ksiazke");
        Konto konto1 = new Konto(imie, nazwisko);
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();
        while (true) {
            switch (wybor) {
                case 1: {
                    konto1.sprawdzWypozyczone(imie, nazwisko);
                }
                case 2: {
                    konto1.sprawdzRezerwacje(imie, nazwisko);
                }
                case 3: {
                    konto1.przedluzWaznoscKonta(imie, nazwisko);
                }
                case 4: {
                    String autor, tytul;
                    System.out.print("Podaj autora ksiazki: ");
                    Scanner a = new Scanner(System.in);
                    autor = a.toString();
                    System.out.println("Podaj tytul ksiazki: ");
                    Scanner t = new Scanner(System.in);
                    tytul = t.toString();
                    bazaKsiazek.wyszukaj(autor, tytul);
                }
                default: {
                    System.out.println("Podano niewłąściwą opcję");
                }
            }
        }
    }
}