public class Czasopismo extends EgzemplarzBasic{
    public void czytajArtykul(String nazwa){
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        String fragment = bazaKsiazek.wyszukaj(nazwa);
        System.out.println(fragment);
    }
}
