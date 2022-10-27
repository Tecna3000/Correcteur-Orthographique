import java.io.File;
import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        displayTrigramme();
       System.out.println(Trigramme.checkCorrection("abcèdert"));
      //System.out.println(Trigramme.maxCommunTrigramme("abcèdert"));
      System.out.println( Levenshtein.distance("niche","chiens"));

    }

    public static void displayTrigramme() throws FileNotFoundException {
        Trigramme trigramme = new Trigramme(new File("/amuhome/r20031646/Bureau/Licence 3/Algo2/correcteur-tp2algo2/dico.txt"));
       // System.out.println(trigramme);
    }


}