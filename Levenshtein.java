import static java.util.Collections.min;

public class Levenshtein {


    public static int Levenshtein(String word1, String word2) {
         return distance(word1.toCharArray(),word2.toCharArray());
    }

    public static int distance(char[] word1, char[] word2){
        int length1 =word1.length;
        int length2 = word2.length;
        int[][] distance = new int[length1 + 1][length2 + 1];
        int cost;

        for (int i = 0; i < length1 + 1 ; i++) //Initialiser la première colonne
            distance[i][0] = i;

        for (int i = 0 ; i < length2 + 1 ; i++)//et la première ligne
            distance[0][i] = i;

        for(int i = 1 ; i <= length1 ; i++){
            for(int j = 1 ; j <= length2 ; j++){
                if (word1[i-1] == word2[j-1])
                    cost = 0;
                else
                    cost = 1;

                distance[i][j] = min(
                        distance[i-1][j] +1,
                        distance[i][j-1]+1,
                        distance[i-1][j-1] + cost);

            }
        }

        return distance[length1][length1];


    }

    private static int min(int i, int i1, int i2) {
        return Math.min(Math.min(i, i1), i2);
    }
}
