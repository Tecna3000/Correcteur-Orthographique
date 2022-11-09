import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        double startTime1 = System.nanoTime();
        displayTrigram();
        double trigramTime = (System.nanoTime() - startTime1)/ 1000000000;
        double startTime2 = System.nanoTime();
        correct();
        double correctionTime = ((System.nanoTime() - startTime2)/ 1000000000) - trigramTime;
        System.out.println("Correction :" + correctionTime +"s");

    }

    public static void displayTrigram() throws FileNotFoundException {
        Trigram trigram = new Trigram(new File("dico.txt"));
     //   System.out.println("------------ Dictionnary--------------------\n" + trigram);
    }

    public static void correct() throws FileNotFoundException {
        Scanner file = new Scanner((new File("fautes.txt")));
        Trigram trigram = new Trigram(new File("dico.txt"));
        Corrector co = new Corrector(trigram);
        try {
            while (file.hasNextLine()) {
                String word = file.nextLine();
                co.isCorrect(word);

            }
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}