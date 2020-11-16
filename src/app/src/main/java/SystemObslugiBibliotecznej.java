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
            String login = scanner.next();
            System.out.print("Podaj haslo: ");
            String haslo = scanner.next();
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

    public static void main(String[] args) {
        SystemObslugiBibliotecznej system = new SystemObslugiBibliotecznej();

        system.zaloguj();
        system.wyloguj();
    }
}
