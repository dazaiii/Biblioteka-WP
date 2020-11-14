import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BazaKsiazek {
    public void dodaj(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj autora: ");
        String autor = scanner.next();
        System.out.print("Podaj tytuł książki: ");
        String tytul = scanner.next();
        System.out.print("Podaj IBSN");
        String IBSN = scanner.next();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql//localhost:3306/biblioteka",
                    "root",
                    "root");
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO ksiazka(Autor, Tytul, IBSN, Wydawnictwo, Status) VALUES (" + "'" + autor + "','" + tytul + "','" + IBSN +"'," + "1)");


        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void usun() {

    }

    public String[] wyszukaj(String tytul, String autor) throws Exception{
        String[] ksiazka = new String[5];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql//localhost:3306/bibliotekadb",
                    "root",
                    "root");
            Statement statement = con.createStatement();
            String sql = "SELECT Tytul, Autor, IBSN, Wydawnictwo, Status FROM Ksiazka WHERE Tytul = '";
            sql = sql + (tytul + "' AND Autor = '" + autor + "';");
            ResultSet rs = statement.executeQuery(sql);
            if (!rs.next())
                throw new Exception("Nie ma książki w bazie");
            while (rs.next()){
                ksiazka[0] = rs.getString(0);
                ksiazka[1] = rs.getString(1);
                ksiazka[2] = rs.getString(2);
                ksiazka[3] = rs.getString(3);
                ksiazka[4] = rs.getString(4);
            }
            return ksiazka;
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
