import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        double startTime = System.nanoTime();
        displayTrigram();
        double trigramTime = (System.nanoTime() - startTime)/ 1000000000;
        System.out.println("Time to build trigram is: "+ trigramTime + "s");
        correct();
    }

    public static void displayTrigram() throws FileNotFoundException {
        Trigram trigram = new Trigram(new File("dico.txt"));
        //   System.out.println("------------ Dictionnary--------------------\n" + trigram);
    }

    public static void correct() throws FileNotFoundException {
        Scanner file = new Scanner((new File("fautes.txt")));
        Trigram trigram = new Trigram(new File("dico.txt"));
        Corrector co = new Corrector(trigram);
        double startTime = System.nanoTime();
        try {
            while (file.hasNextLine()) {
                String word = file.nextLine();
                co.isCorrect(word);

            }
            file.close();
            double correctionTime = ((System.nanoTime() - startTime)/ 1000000000);
            System.out.println("Correction time:" + correctionTime +"s");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}