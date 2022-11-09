import java.util.*;

public class Corrector {
    static Trigram myTrigram;
    public Corrector(Trigram myTrigram) {
       this.myTrigram = myTrigram;
    }
    //si le mot est dans le dictionnaire, il est correctement orthographié et le processus s’arrête
    public boolean isCorrect(String word) {
        for (Map.Entry<String, List<String>> string : myTrigram.dictionary.entrySet()) {
            if (string.getValue().contains(word)){
                System.out.println("The word is correct");
            } return true;

        }
        System.out.println(closestWords(word));
        return false;
    }

    public static Map<String, Integer> commonTrigram(String word){

        List<String> trigram = Trigram.trigram(word);//2. construire la liste des trigrammes du mot M
        Map<String,Integer> commonTrigram = new HashMap<>();

        // 3. Construire la liste L des mots qui ont au moins un trigramme commun avec M
        //4. pour chaque mot de L, calculer son nombre d’occurrences dans les listes de mots associées aux trigrammes de M
        for (Map.Entry<String, List<String>> listEntry : myTrigram.dictionary.entrySet()) {
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
