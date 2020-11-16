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

        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();
        while (true) {
            switch (wybor) {
                case 1: {

                }
                case 2: {

                }
                case 3: {

                }
                case 4: {

                }
                default: {
                    System.out.println("Podano niewłąściwą opcję");
                }
            }
        }

    }
}
