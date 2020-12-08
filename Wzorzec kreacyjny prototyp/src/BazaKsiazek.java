import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class BazaKsiazek {
    public void dodaj(Ksiazka ksiazka) {
        Scanner scanner = new Scanner(System.in);
        String autor = ksiazka.autor;
        String tytul = ksiazka.tytul;
        String wydawnictwo = ksiazka.wydawnictwo;
        String ISBN = ksiazka.IBSN;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO ksiazka(Autor, Tytul, ISBN, Wydawnictwo, Status) VALUES (" + "'" + autor + "','" + tytul + "','" + ISBN +"','" + wydawnictwo+"'," + "1)");
            con.close();
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void usun() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Usuwanie ksiazki");
        System.out.print("Podaj autora: ");
        String autor = scanner.nextLine();
        System.out.print("Podaj tytuł książki: ");
        String tytul = scanner.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM ksiazka WHERE ksiazka.autor = '" +  autor + "' AND ksiazka.tytul = '" + tytul + "';");
            con.close();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public String[] wyszukaj(String tytul, String autor){
        String[] ksiazka = new String[6];
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            String sql = "SELECT Tytul, Autor, ISBN, Wydawnictwo, Status, Data_wypozyczenia FROM Ksiazka WHERE Tytul = '";
            sql = sql + (tytul + "' AND Autor = '" + autor + "';");
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ksiazka[0] = rs.getString("Tytul");
                ksiazka[1] = rs.getString("Autor");
                ksiazka[2] = rs.getString("ISBN");
                ksiazka[3] = rs.getString("Wydawnictwo");
                ksiazka[4] = rs.getString("Status");
                if (ksiazka[4].equals("0")){
                    ksiazka[5] = rs.getDate("Data_wypozyczenia").toString();
                }
            }
            con.close();
            return ksiazka;
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public ArrayList<String> wyszukaj(String imie, String nazwisko, int status){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            String sql = "SELECT ksiazka.*\n" +
                    "FROM ksiazka \n" +
                    "\tLEFT JOIN uzytkownik ON ksiazka.`ID_uzytkownika` = uzytkownik.`ID_uzytkownika`\n" +
                    "WHERE uzytkownik.`Imie` = '" + imie + "' AND uzytkownik.Nazwisko = '" + nazwisko + "' AND ksiazka.status = '" + status + "'; ";
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<String> lista = new ArrayList<>();
            while(rs.next()) {
                lista.add(rs.getString("Tytul"));
                lista.add(rs.getString("Autor"));
                lista.add(rs.getString("ISBN"));
                lista.add(rs.getString("Wydawnictwo"));
            }
            con.close();
            return lista;
        }catch(Exception e){
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
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void zaktualizuj(String tytul, String autor, String imie, String nazwisko, int statusKsiazki) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                    "root",
                    "");
            Statement statement = con.createStatement();
            String sql = "UPDATE Ksiazka SET Ksiazka.Status = '" + statusKsiazki + "' WHERE Ksiazka.tytul = '" + tytul + "' AND Ksiazka.autor = '" + autor + "';";
            statement.executeUpdate(sql);
            if(statusKsiazki == 1){
                sql = "UPDATE Ksiazka SET ID_uzytkownika = NULL WHERE tytul = '" + tytul + "' AND autor = '" + autor + "';";
                statement.executeUpdate(sql);
                sql = "UPDATE Ksiazka SET Data_wypozyczenia = NULL WHERE tytul = '" + tytul + "' AND autor = '" + autor + "';";
                statement.executeUpdate(sql);

            }
            if(statusKsiazki == 0 || statusKsiazki == 2) {
                sql = "UPDATE Ksiazka SET ID_uzytkownika = (SELECT ID_uzytkownika FROM Uzytkownik WHERE imie = '"+imie+"' AND nazwisko = '"+nazwisko+"') WHERE tytul = '"+tytul+"' AND autor = '" + autor + "';";
                statement.executeUpdate(sql);
                if(statusKsiazki == 0) {
                    String sql2 = "SELECT Ksiazka.Data_wypozyczenia FROM Ksiazka WHERE Autor = '" + autor + "' AND tytul = '" + tytul + "';";
                    ResultSet rs = statement.executeQuery(sql2);
                    Date data = new Date(0000 - 00 - 00);
                    if (rs.next()) {
                        data = rs.getDate("Data_wypozyczenia");
                        if (!rs.wasNull()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(data);
                            calendar.add(Calendar.MONTH, 3);
                            Date data2 = new Date(calendar.getTimeInMillis());
                            String sql3 = "UPDATE Ksiazka SET Ksiazka.Data_wypozyczenia = '" + data2 + "' WHERE Ksiazka.Tytul = '" + tytul + "' AND Ksiazka.autor = '" + autor + "';";
                            statement.executeUpdate(sql3);
                        } else {
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MONTH, 3);
                            Date data2 = new Date(calendar.getTimeInMillis());

                            String sql3 = "UPDATE Ksiazka SET Ksiazka.Data_wypozyczenia = '" + data2 + "' WHERE Ksiazka.Tytul = '" + tytul + "' AND Ksiazka.autor = '" + autor + "';";
                            statement.executeUpdate(sql3);
                        }
                    }
                }
                con.close();
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}