import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Uzytkownik {
    private String imie;
    private String nazwisko;
    private String pesel;
    private Konto konto;

    public Uzytkownik(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            String sql = "SELECT imie, nazwisko, pesel FROM Uzytkownik WHERE ID_uzytkownika = '" + id + "';";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                this.imie = rs.getString("Imie");
                this.nazwisko = rs.getString("Nazwisko");
                this.pesel = rs.getString("Pesel");
                this.konto = new Konto(imie,nazwisko);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void wyswietlMenu(){
        while (true) {
        System.out.println("MENU: ");
        System.out.println("1. Sprawdź wypożyczone książki");
        System.out.println("2. Sprzwdź zarezerwowane książki");
        System.out.println("3. Przedłuż ważność konta");
        System.out.println("4. Wyszukaj opis książki");
        System.out.println("5. Wypozycz książkę");
        System.out.println("6. Rezerwuj książkę");
        System.out.println("7. Zwróć książkę");
        System.out.println("8. Przedłuż wypożyczenie książki");
        System.out.println("9. Kup książkę lub czasopismo");
        System.out.println("10. Czytaj fragment");
        Ksiazka ksiazka1 = new Ksiazka();
        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();
            switch (wybor) {
                case 1: {
                    konto.sprawdzWypozyczone(imie, nazwisko);
                    break;
                }
                case 2: {
                    konto.sprawdzRezerwacje(imie, nazwisko);
                    break;
                }
                case 3: {
                    konto.przedluzWaznoscKonta(imie, nazwisko);
                    break;
                }
                case 4: {
                    String autor, tytul;
                    System.out.print("Podaj autora ksiazki: ");
                    Scanner a = new Scanner(System.in);
                    autor = a.nextLine();
                    System.out.println("Podaj tytul ksiazki: ");
                    Scanner t = new Scanner(System.in);
                    tytul = t.nextLine();
                    ksiazka1.opis(tytul, autor);
                    break;
                }
                case 5: {
                    String autor, tytul;
                    System.out.print("Podaj autora ksiazki: ");
                    Scanner a = new Scanner(System.in);
                    autor = a.nextLine();
                    System.out.println("Podaj tytul ksiazki: ");
                    Scanner t = new Scanner(System.in);
                    tytul = t.nextLine();
                    try {
                        ksiazka1.wypozycz(tytul, autor,imie,nazwisko);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;
                }
                case 6: {
                    String autor, tytul;
                    System.out.print("Podaj autora ksiazki: ");
                    Scanner a = new Scanner(System.in);
                    autor = a.nextLine();
                    System.out.println("Podaj tytul ksiazki: ");
                    Scanner t = new Scanner(System.in);
                    tytul = t.nextLine();
                    try {
                        ksiazka1.rezerwuj(tytul, autor,imie,nazwisko);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;
                }
                case 7: {
                    System.out.println("Twoje wypożyczone książki to:");
                    konto.sprawdzWypozyczone(imie, nazwisko);
                    System.out.println("Wprowadź dane książki, którą chcesz oddać");
                    String autor, tytul;
                    System.out.print("Podaj autora ksiazki: ");
                    Scanner a = new Scanner(System.in);
                    autor = a.nextLine();
                    System.out.println("Podaj tytul ksiazki: ");
                    Scanner t = new Scanner(System.in);
                    tytul = t.nextLine();
                    try {
                        ksiazka1.zwroc(tytul, autor,imie,nazwisko);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;
                }
                case 8: {
                    System.out.println("Twoje wypożyczone książki to:");
                    konto.sprawdzWypozyczone(imie, nazwisko);
                    System.out.println("Wprowadź dane książki, której datę wypożyczenia chcesz przedłużyć");
                    String autor, tytul;
                    System.out.print("Podaj autora ksiazki: ");
                    Scanner a = new Scanner(System.in);
                    autor = a.nextLine();
                    System.out.println("Podaj tytul ksiazki: ");
                    Scanner t = new Scanner(System.in);
                    tytul = t.nextLine();
                    try {
                        ksiazka1.przedluzWypozyczenie(tytul, autor,imie,nazwisko);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;
                }
                case 9: {
                    Zakup zakup = new Zakup();
                    zakup.kup();
                    System.out.println("Wybierz: \nFaktura\nParagon");
                    Scanner scanner1 = new Scanner(System.in);
                    String zm;
                    zm = scanner1.nextLine();
                    if(zm.toLowerCase().equals("faktura")){
                        zakup.wystawFakture();
                    } else if(zm.toLowerCase().equals("paragon")){
                        zakup.wystawParagon();
                    } else{
                        System.out.println("Podano niepoprawną opcję");
                    }
                    break;
                }
                case 10: {
                    System.out.println("Wybierz: \nKsiazka\nGazeta");
                    String autor, tytul;
                    Ksiazka ksiazka = new Ksiazka();
                    Czasopismo czasopismo = new Czasopismo();
                    Scanner scanner1 = new Scanner(System.in);
                    String zm;
                    zm = scanner1.nextLine();
                    if(zm.toLowerCase().equals("ksiazka")){
                        System.out.print("Podaj autora ksiazki: ");
                        Scanner a = new Scanner(System.in);
                        autor = a.nextLine();
                        System.out.println("Podaj tytul ksiazki: ");
                        Scanner t = new Scanner(System.in);
                        tytul = t.nextLine();
                        ksiazka.czytajFragment(tytul, autor);
                    } else if(zm.toLowerCase().equals("gazeta")){
                        System.out.println("Podaj tytul gazety: ");
                        Scanner t = new Scanner(System.in);
                        tytul = t.nextLine();
                        czasopismo.czytajArtykul(tytul);
                    } else {
                        System.out.println("Podano niepoprawną opcję");
                    }
                    break;
                }

                default: {
                    System.out.println("Podano niewłąściwą opcję");
                }
            }
        }
    }
}