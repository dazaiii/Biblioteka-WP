import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Bibliotekarz {
    private int IdentyfikatorPracownika;
    private String imie;
    private String nazwisko;
    private String pesel;

    public Bibliotekarz(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            String sql = "SELECT imie, nazwisko, pesel, ID_bibliotekarza FROM bibliotekarz";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                this.imie = rs.getString("Imie");
                this.nazwisko = rs.getString("Nazwisko");
                this.pesel = rs.getString("Pesel");
                this.IdentyfikatorPracownika = rs.getInt("ID_bibliotekarza");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void wyswietlMenu(){
        while(true) {
            System.out.println("Witaj " + imie + " " + nazwisko);
            System.out.println("1. Dodaj książkę");
            System.out.println("2. Aktualizuj dane książki");
            System.out.println("3. Usuń książkę");
            System.out.println("4. Wyszukaj książkę");
            Scanner scanner = new Scanner(System.in);

                System.out.print("Wybierz opcję: ");
                int wybor = scanner.nextInt();
                switch (wybor){
                    case 1:{
                        BazaKsiazek bazaKsiazek = new BazaKsiazek();
                        bazaKsiazek.dodaj();
                        System.out.println("Dodano książkę do bazy\n");
                        break;
                    }
                    case 2:{
                        BazaKsiazek bazaKsiazek = new BazaKsiazek();
                        bazaKsiazek.zaktualizuj();
                        System.out.println("Zaktualizowano dane książki\n");
                        break;
                    }
                    case 3:{
                        BazaKsiazek bazaKsiazek = new BazaKsiazek();
                        bazaKsiazek.usun();
                        System.out.println("Usunięto książkę\n");
                        break;
                    }
                    case 4:{
                        BazaKsiazek bazaKsiazek = new BazaKsiazek();
                        scanner.nextLine();
                        System.out.print("Podaj tytuł książki: ");
                        String tytul = scanner.nextLine();
                        System.out.print("Podaj autora książki: ");
                        String autor = scanner.nextLine();
                        Ksiazka k = new Ksiazka();
                        System.out.println();
                        k.opis(tytul,autor);
                        System.out.println();
                        break;
                    }
                }
            }
        }
}

