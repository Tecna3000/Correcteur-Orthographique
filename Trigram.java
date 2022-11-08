import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trigram {

    private static Map<String, List<String>> dictionary;

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

    //si le mot est dans le dictionnaire, il est correctement orthographié et le processus s’arrête
    public  Map<String, Integer> checkCorrection(String word) {
        for (Map.Entry<String, List<String>> string : dictionary.entrySet()) {
            if (string.getValue().contains(word)) break;
        }
        return commonTrigram(word) ;//Sinon :
    }

    public static Map<String, Integer> commonTrigram(String word){

        List<String> trigram = trigram(word);//2. construire la liste des trigrammes du mot M
        Map<String,Integer> commonTrigram = new HashMap<>();

        // 3. Construire la liste L des mots qui ont au moins un trigramme commun avec M
        //4. pour chaque mot de L, calculer son nombre d’occurrences dans les listes de mots associées aux trigrammes de M
        for (Map.Entry<String, List<String>> listEntry : dictionary.entrySet()) {
            for (String string : trigram) {
                if (Objects.equals(listEntry.getKey(), string)){
                    for(String string2 : listEntry.getValue()){
                        if(!commonTrigram.containsKey(string2)) commonTrigram.put(string2,1);
                        else {
                            commonTrigram.put(string2, commonTrigram.get(string2) + 1);
                        }

                    }
                }
          }
        }
        return commonTrigram;
    }

    //5. sélectionner les mots du dictionnaire qui ont le plus de trigrammes communs avec M
     public static List<String> maxCommonTrigram(String word){
         Map<String, Integer>commonTrigram = commonTrigram(word);
         return commonTrigram.keySet().stream()
                 .sorted((o1, o2) -> commonTrigram.get(o2)-commonTrigram.get(o1))
                 .toList().subList(0,100);
     }

    //6. déterminer les cinq mots de la sélection les plus proches de M au sens de la distance
    //d’édition. L’utilisateur choisira parmis ces 5 mots celui qui lui convient.

     public static List<String> closestWords(String word){
         List<String> maxCommonTrigram = maxCommonTrigram(word);
         Map<String, Integer> distances = new HashMap<>();
         int max = Integer.MIN_VALUE;
         for (String string : maxCommonTrigram){
            int distance = Levenshtein.distance(word, String.valueOf(string));
            distances.put(string,distance);
         }
         return distances.keySet().stream()
                 .sorted(Comparator.comparingInt(distances::get))
                 .toList().subList(0,5);
         }

}