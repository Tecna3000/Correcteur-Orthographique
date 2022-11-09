import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trigram {

    static Map<String, List<String>> dictionary;

    public Trigram(File filePath) throws FileNotFoundException {
        fillTrigram(filePath);
    }

    public void fillTrigram(File filePath) throws FileNotFoundException {
        Scanner file = new Scanner(filePath);
        dictionary = new HashMap<>();
        try {
            while (file.hasNextLine()) {
                String word = file.nextLine();
                List<String> trigrams = trigram(word);

                for (String tri : trigrams) {
                    List<String> words = new ArrayList<>();
                    words.add(word);
                    if (!dictionary.containsKey(tri)) //si le trigramme n'existe pas dans le dictionnaire, on l'ajoute et on ajoute le mot correspondant
                    {
                        dictionary.put(tri, words);
                    } else {                          //sinon on ajoute le mot à la liste appartenant au trigramme
                        dictionary.get(tri).add(word);
                    }
                }


            }
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // methode qui extrait les trigrammes d'un mot et les ajoute dans une liste
    public static List<String> trigram(String word) {
        List<String> trigram = new ArrayList<>();
        if (word.length() >= 3) {
            while (word.length() > 3) {
                String str = word.substring(0, 3);
                trigram.add(str);
                word = word.substring(3);

            }
        }
        trigram.add(word);
        return trigram;
    }


    @Override
    public String toString() {
        StringBuilder myString = new StringBuilder(" ");
        for (Map.Entry<String, List<String>> string : dictionary.entrySet()) {
            myString.append("[").append(string.getKey()).append(",").append(string.getValue()).append("] \n\n");

        }
        return myString.toString();
    }



}