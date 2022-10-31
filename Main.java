import java.io.File;
import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        displayTrigramme();
       //System.out.println(Trigramme.checkCorrection("abcèdert"));
      System.out.println("The words that have more commun trigrammes with the word are: \n" + Trigramme.maxCommunTrigramme("abcèdert"));
      System.out.println("\nThe distance between the two words is: " + Levenshtein.distance("niche","chiens"));

    }

    public static void displayTrigramme() throws FileNotFoundException {
        Trigramme trigramme = new Trigramme(new File("/amuhome/r20031646/Bureau/Licence 3/Algo2/correcteur-tp2algo2/dico.txt"));
       // System.out.println("------------ Dictionnary--------------------\n" + trigramme);
    }


}