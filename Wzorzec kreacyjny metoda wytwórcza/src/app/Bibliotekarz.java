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
        System.out.println("Witaj " + imie + " " + nazwisko);
        System.out.println("1. Dodaj książkę");
        System.out.println("2. Aktualizuj dane książki");
        System.out.println("3. Usuń książkę");
        System.out.println("4. Wyszukaj książkę");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("Wybierz opcję: ");
            int wybor = scanner.nextInt();
            switch (wybor){
                case 1:{
                    BazaKsiazek bazaKsiazek = new BazaKsiazek();
                    bazaKsiazek.dodaj();
                    break;
                }
                case 2:{
                    BazaKsiazek bazaKsiazek = new BazaKsiazek();
                    bazaKsiazek.zaktualizuj();
                    break;
                }
                case 3:{
                    BazaKsiazek bazaKsiazek = new BazaKsiazek();
                    bazaKsiazek.usun();
                    break;
                }
                case 4:{
                    BazaKsiazek bazaKsiazek = new BazaKsiazek();
                    scanner.nextLine();
                    System.out.print("Podaj tytuł książki: ");
                    String tytul = scanner.nextLine();
                    System.out.print("Podaj autora książki: ");
                    String autor = scanner.nextLine();
                    String[] ksiazka = bazaKsiazek.wyszukaj(tytul,autor);
                    System.out.println("ISBN: " + ksiazka[2]);
                    System.out.println("Wydawnictwo: " + ksiazka[3]);
                    if (ksiazka[4].equals(0)){
                        System.out.println("Wypożycznona do " + ksiazka[5]);
                    }
                    else {
                        System.out.println("Niewypożyczona");
                    }
                    break;
                }
            }
        }

    }
}

