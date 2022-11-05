import java.io.File;
import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
       // displayTrigramme();
       //System.out.println(Trigramme.checkCorrection("abcèdert"));
      System.out.println("The words that have more commun trigrammes with the word are: \n" + new Trigram(new File("/amuhome/r20031646/Bureau/Licence 3/Algo2/correcteur-tp2algo2/dico.txt")).maxCommonTrigram("chiev"));
      //System.out.println("\nThe distance between the two words is: " + Levenshtein.distance("niche","chiens"));
      System.out.println("\n the closest 5 wordds are" + Trigram.closestWords("chien"));

    }

    public static void displayTrigramme() throws FileNotFoundException {
        Trigram trigramme = new Trigram(new File("/amuhome/r20031646/Bureau/Licence 3/Algo2/correcteur-tp2algo2/dico.txt"));
        System.out.println("------------ Dictionnary--------------------\n" + trigramme);
    }


}