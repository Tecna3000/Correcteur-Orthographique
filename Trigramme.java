import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trigramme {

    private static Map<String, List<String>> dictionary;

    public Trigramme(File filePath) throws FileNotFoundException {
        fillTrigramme(filePath);
    }

    public void fillTrigramme(File filePath) throws FileNotFoundException {
        Scanner file = new Scanner(filePath);
        dictionary = new HashMap<>();
        try {
            while (file.hasNextLine()) {
                String word = file.nextLine();
                List<String> trigrammes = trigramme(word);

                for (String tri : trigrammes) {
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
    public static List<String> trigramme(String word) {
        List<String> trigramme = new ArrayList<>();
        if (word.length() >= 3) {
            while (word.length() > 3) {
                String str = word.substring(0, 3);
                trigramme.add(str);
                word = word.substring(3);

            }
        }
        trigramme.add(word);
        return trigramme;
    }


    @Override
    public String toString() {
        StringBuilder myString = new StringBuilder(" ");
        for (Map.Entry<String, List<String>> string : dictionary.entrySet()) {
            myString.append("[").append(string.getKey()).append(",").append(string.getValue()).append("] \n\n");

        }
        return myString.toString();
    }

    public static Map<List<String>, Integer> checkCorrection(String word) {
        for (Map.Entry<String, List<String>> string : dictionary.entrySet()) {
            if (string.getValue().contains(word)) break;
        }
        List<String> trigrammes = trigramme(word);
        Map<List<String>,Integer> communTrigramme = new HashMap<>();
        for (Map.Entry<String, List<String>> string : dictionary.entrySet()) {
            for (String string2 : trigrammes) {
                if (Objects.equals(string.getKey(), string2) && ! communTrigramme.containsValue(string))
                    communTrigramme.put(string.getValue(),1);
            }
            nb++;
            communTrigramme.
            int nbOfOccurrences = 0;


        }
        return  communTrigramme;
    }
}