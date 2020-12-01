public class KreatorKsiazek {
    public Ksiazka utworzEgzemplarz(String tytul, String autor, String ISBN, String wydawnictwo, String jezyk){
        if (jezyk.equals("polski")){
            return new KsiazkaPolskojezyczna(tytul,autor,ISBN,wydawnictwo);
        }
        else {
            return new KsiazkaObcojezyczna(tytul,autor,ISBN,wydawnictwo,jezyk);
        }
    }
    public Ksiazka utworzEgzemplarz(String jezyk){
        if (jezyk.equals("polski")){
            return new KsiazkaPolskojezyczna();
        }
        else {
            return new KsiazkaObcojezyczna();
        }
    }
}
