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

    //si le mot est dans le dictionnaire, il est correctement orthographié et le processus s’arrête
    public static Map<String, Integer> checkCorrection(String word) {
        for (Map.Entry<String, List<String>> string : dictionary.entrySet()) {
            if (string.getValue().contains(word)) break;
        }
        return communTrigramme(word) ;//Sinon :
    }

    public static Map<String, Integer> communTrigramme(String word){

        List<String> trigrammes = trigramme(word);//2. construire la liste des trigrammes du mot M
        Map<String,Integer> communTrigramme = new HashMap<>();

        // 3. Construire la liste L des mots qui ont au moins un trigramme commun avec M
        //4. pour chaque mot de L, calculer son nombre d’occurrences dans les listes de mots associées aux trigrammes de M
        for (Map.Entry<String, List<String>> listEntry : dictionary.entrySet()) {
            for (String string : trigrammes) {
                if (Objects.equals(listEntry.getKey(), string)){
                    for(String string2 : listEntry.getValue()){
                        if(!communTrigramme.containsKey(string2)) communTrigramme.put(string2,1);
                        else {
                            communTrigramme.put(string2, communTrigramme.get(string2) + 1);
                        }

                    }
                }
          }
        }
        return communTrigramme;
    }

    //5. sélectionner les mots du dictionnaire qui ont le plus de trigrammes communs avec M
     public static Map<String, Integer> maxCommunTrigramme(String word){
         Map<String, Integer>communTrigramme = communTrigramme(word);
         Map<String, Integer> maxCommunTrigramme = new HashMap<>();
         int  max = 1;
         for (Map.Entry<String, Integer> string : communTrigramme.entrySet()) {
             if( string.getValue()>= max){ maxCommunTrigramme.put(string.getKey(), string.getValue());
                 max = string.getValue();
             }



         }
//         for(int nb =100; nb< communTrigramme.size(); nb++){
//             communTrigramme.remove(e);
//         }
         return maxCommunTrigramme;
     }

     public void closestWords(String word){
         Map<String, Integer> maxCommunTrigramme = maxCommunTrigramme(word);
         Map<String, Integer> distances = new HashMap<>();
         List<Integer> list = new ArrayList<>();
         int max = Integer.MAX_VALUE;
         for (Map.Entry<String, Integer> string : maxCommunTrigramme.entrySet()){
            int distance = Levenshtein.distance(word, String.valueOf(string));
            distances.put(string.getKey(),distance);
             list.add(string.getValue());
         }
         Collections.sort(list);

     }
}