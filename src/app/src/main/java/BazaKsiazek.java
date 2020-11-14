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
        System.out.print("Podaj wydawnictwo: ");
        String wydawnictwo = scanner.next();
        System.out.print("Podaj ISBN");
        String ISBN = scanner.next();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO ksiazka(Autor, Tytul, ISBN, Wydawnictwo, Status) VALUES (" + "'" + autor + "','" + tytul + "','" + wydawnictwo +"','" + ISBN +"'," + "1)");


        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void usun() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Usuwanie ksiazki");
        System.out.print("Podaj autora: ");
        String autor = scanner.next();
        System.out.print("Podaj tytuł książki: ");
        String tytul = scanner.next();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM ksiazka WHERE ksiazka.autor = '" +  autor + "' AND ksiazka.tytul = '" + tytul + "';");

        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public String[] wyszukaj(String tytul, String autor) throws Exception{
        String[] ksiazka = new String[5];
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            String sql = "SELECT Tytul, Autor, ISBN, Wydawnictwo, Status FROM Ksiazka WHERE Tytul = '";
            sql = sql + (tytul + "' AND Autor = '" + autor + "';");
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ksiazka[0] = rs.getString("Tytul");
                ksiazka[1] = rs.getString("Autor");
                ksiazka[2] = rs.getString("ISBN");
                ksiazka[3] = rs.getString("Wydawnictwo");
                ksiazka[4] = rs.getString("Status");
            }
            return ksiazka;
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public void zaktualizuj() {
        System.out.println("Co chcesz zaktualizowac?");
        System.out.println("1. Tytul");
        System.out.println("2. Autor");
        System.out.println("3. ISBN");
        System.out.println("4. Wydawnictwo");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj 1,2,3 lub 4");
            int wybor = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Wpisz co chcesz zmienić: ");
            String s = scanner.nextLine();
            System.out.print("Na co chcesz zmienić: ");
            String s2 = scanner.nextLine();
            switch(wybor) {
                case 1:
                    statement.executeUpdate("UPDATE ksiazka SET ksiazka.tytul = '" +  s2 + "' WHERE ksiazka.tytul = '" + s + "' ;");
                    break;
                case 2:
                    statement.executeUpdate("UPDATE ksiazka SET ksiazka.autor = '" + s2 + "' WHERE ksiazka.autor = '" + s + "' ;");
                    break;
                case 3:
                    statement.executeUpdate("UPDATE ksiazka SET ksiazka.ISBN = '" + s2 + "' WHERE ksiazka.ISBN = '" + s + "' ;");
                    break;
                case 4:
                    statement.executeUpdate("UPDATE ksiazka SET ksiazka.wydawnictwo = '" + s2 + "' WHERE ksiazka.wydawnictwo = '" + s + "' ;");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
