import java.io.File;
import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
       // displayTrigram();
       //System.out.println(Trigram.checkCorrection("abcèdert"));
      System.out.println("The words that have more commun trigrammes with the word are: \n" + new Trigram(new File("/amuhome/r20031646/Bureau/Licence 3/Algo2/correcteur-tp2algo2/dico.txt")).maxCommonTrigram("addresse"));
      //System.out.println("\nThe distance between the two words is: " + Levenshtein.distance("niche","chiens"));
      System.out.println("\n the closest 5 wordds are" + Trigram.closestWords("addresse"));

    }

    public static void displayTrigram() throws FileNotFoundException {
        Trigram trigram = new Trigram(new File("/amuhome/r20031646/Bureau/Licence 3/Algo2/correcteur-tp2algo2/dico.txt"));
        System.out.println("------------ Dictionnary--------------------\n" + trigram);
    }


}