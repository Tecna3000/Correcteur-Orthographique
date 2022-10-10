import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trigramme {

    private Map<String, List<String>> dictionary;


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

                dictionary.put(word, trigrammes);

            }
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static List<String> trigramme(String word) {
        List<String> trigramme = new ArrayList<>();
        if (word.length() >= 3) {
            while (word.length() > 3) {
                        String str = word.substring(0,3);
                        System.out.println(str);
                        trigramme.add(str);
                        word = word.substring(3);

           }
      }
        trigramme.add(word);
        return trigramme;
    }

}