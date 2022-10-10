import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trigramme {

    private Map<String, List<String>> dictionary;




    public Trigramme(File filePath) throws FileNotFoundException {
        fillTrigramme(filePath);
    }

    public Trigramme(Map<String, List<String>> dictionary) {
        this.dictionary = dictionary;
    }

    public void fillTrigramme(File filePath) throws FileNotFoundException {
        Scanner file = new Scanner(filePath);
        dictionary = new HashMap<>();
        try {
            while (file.hasNextLine()) {
                String word = file.nextLine();
                List<String> trigrammes = trigramme(word);

                for(String tri : trigrammes){
                    List<String> words = new ArrayList<>();
                    words.add(word);
                    if(!dictionary.containsKey(tri))
                    {
                        dictionary.put(tri,words);
                    }
                    else {
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
    static List<String> trigramme(String word) {
        List<String> trigramme = new ArrayList<>();
        if (word.length() >= 3) {
            while (word.length() > 3) {
                        String str = word.substring(0,3);
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
        for(Map.Entry<String, List<String>> string : dictionary.entrySet()){
            myString.append("[").append(string.getKey()).append(",").append(string.getValue()).append("] \n\n");

        }
        return myString.toString();
    }
}