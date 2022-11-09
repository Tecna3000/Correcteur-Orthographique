import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
       // displayTrigram();

correct();
//
//      System.out.println("The words that have more common trigrams with the word are: \n" + Trigram.maxCommonTrigram("addresse"));
//      //System.out.println("\nThe distance between the two words is: " + Levenshtein.distance("niche","chiens"));
//      System.out.println("\n the closest 5 words are" + Trigram.closestWords("addresse"));

    }

    public static void displayTrigram() throws FileNotFoundException {
        Trigram trigram = new Trigram(new File("dico.txt"));
        System.out.println("------------ Dictionnary--------------------\n" + trigram);
    }

    public static void correct() throws FileNotFoundException {
        Scanner file = new Scanner((new File("fautes.txt")));
        try {
            while (file.hasNextLine()) {
                String word = file.nextLine();
                Trigram trigram = new Trigram(new File("dico.txt"));
                Corrector co = new Corrector(trigram);
                co.isCorrect(word);


            }
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}