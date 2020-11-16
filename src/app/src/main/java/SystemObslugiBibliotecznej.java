import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SystemObslugiBibliotecznej {

    private String login;
    private String haslo;
    private int typ;

    public void zaloguj(){
        Scanner scanner = new Scanner(System.in);
        int ID_uzytkownika;
        int ID_bibliotekarza;
        while (true) {
            System.out.print("Podaj login: ");
            login = scanner.next();
            System.out.print("Podaj haslo: ");
            haslo = scanner.next();
            String loginBaza = new String();
            String hasloBaza = new String();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                        "root",
                        "");
                Statement statement = con.createStatement();
                String sql = "SELECT Login, Haslo, Typ_konta, ID_uzytkownika, ID_bibliotekarza FROM system WHERE login = '";
                sql = sql + (login + "' AND Haslo = '" + haslo + "';");
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    loginBaza = rs.getString("Login");
                    hasloBaza = rs.getString("Haslo");
                    typ = rs.getInt("Typ_konta");
                    ID_uzytkownika = rs.getInt("ID_uzytkownika");
                    ID_bibliotekarza = rs.getInt("ID_bibliotekarza");
                }
                else {
                    System.out.println("Podano niewłaściwe dane");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            if(login.equals(loginBaza) && haslo.equals(hasloBaza)){
                break;
            }
            else{
                System.out.println("Podany login lub hasło jest niepoptawne");
            }
        }
        if (typ == 0){
            System.out.println("Zalogowano bibliotekarza");
            /*
            Bibliotekarz bibliotekarz = new Bibliotekarz(ID_bibliotekarza);
            bibliotekarz.wyswietlMenu();
             */
        }
        if (typ == 1){
            System.out.println("Zalogowano użytkownika");
            /*
            Uzytkownik uzytkownik = new Uzytkownik(ID_uzytkownika);
            uzytkownik.wyswietlMenu();
             */
        }
    }

    public void wyloguj(){
        System.exit(0);
    }

    public void zarejestruj(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zarejestruj nowego użytkownika");
        System.out.print("Podaj imię: ");
        String imie = scanner.next();
        System.out.print("Podaj nazwisko: ");
        String nazwisko = scanner.next();
        System.out.print("Podaj pesel: ");
        String pesel = scanner.next();
        System.out.print("Podaj login: ");
        String login = scanner.next();
        System.out.print("Podaj haslo: ");
        String haslo = scanner.next();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            while (true) {
                String sql = "SELECT login FROM System WHERE login = '" + login + "';";
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    System.out.println("Istnieje konto o podanym loginie, podaj inny");
                    System.out.print("Podaj login: ");
                    login = scanner.next();
                } else {
                    break;
                }
            }
            String sql = "INSERT INTO uzytkownik (Imie, Nazwisko, Pesel, Status_konta) VALUES ('" + imie + "','" + nazwisko + "','" + pesel + "', 1);";
            statement.executeUpdate(sql);
            sql = "SELECT ID_Uzytkownika FROM uzytkownik WHERE IMIE = '" + imie + "' AND Nazwisko = '" + nazwisko + "';";
            ResultSet rs = statement.executeQuery(sql);
            int id = -1;
            if (rs.next())
                id = rs.getInt("ID_uzytkownika");
            sql = "INSERT INTO System (Login, Haslo, Typ_konta, ID_uzytkownika) VALUES ('" + login + "','" + haslo + "','1'," + id + ");";
            statement.executeUpdate(sql);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        System.out.println("Zarejestrowano pomyślnie");
    }

    public static void main(String[] args) {
        SystemObslugiBibliotecznej system = new SystemObslugiBibliotecznej();
        system.zarejestruj();
        system.zaloguj();
        system.wyloguj();
    }
}
