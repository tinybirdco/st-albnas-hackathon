import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDistanceCalculator {

    public static double calculateJaroDistance(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        int maxDistance = Math.max(s1.length(), s2.length()) / 2 - 1;

        // Count matching characters and transpositions
        int matches = 0;
        int transpositions = 0;
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            for (int j = Math.max(0, i - maxDistance); j < Math.min(s2.length(), i + maxDistance + 1); j++) {
                if (c1 == s2.charAt(j)) {
                    matches++;
                    if (i != j) {
                        transpositions++;
                    }
                    break;
                }
            }
        }

        if (matches == 0) {
            return 0.0;
        }

        double jaroSimilarity = (matches / (double) s1.length() + matches / (double) s2.length()
                + (matches - transpositions / 2.0) / matches) / 3.0;

        return jaroSimilarity;
    }

    public static int calculateDistance(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static double calculateCosineDistance(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            throw new IllegalArgumentException("Input strings cannot be null or empty");
        }

        Map<Character, Integer> frequency1 = calculateCharacterFrequency(s1);
        Map<Character, Integer> frequency2 = calculateCharacterFrequency(s2);

        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;

        // Calculate dot product and magnitude of each string's character frequency vector
        for (char ch : frequency1.keySet()) {
            if (frequency2.containsKey(ch)) {
                dotProduct += frequency1.get(ch) * frequency2.get(ch);
            }
            magnitude1 += Math.pow(frequency1.get(ch), 2);
        }

        for (char ch : frequency2.keySet()) {
            magnitude2 += Math.pow(frequency2.get(ch), 2);
        }

        // Calculate cosine distance
        double magnitudeProduct = Math.sqrt(magnitude1) * Math.sqrt(magnitude2);
        return 1 - (dotProduct / magnitudeProduct);
    }

    private static Map<Character, Integer> calculateCharacterFrequency(String s) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char ch : s.toCharArray()) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        return frequency;
    }

    public static int calculateDamerauLevenshteinDistance(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost));

                if (i > 1 && j > 1 && s1.charAt(i - 1) == s2.charAt(j - 2) && s1.charAt(i - 2) == s2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + cost);
                }
            }
        }

        return dp[n][m];
    }

    public static double calculateJaroWinklerDistance(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        // Calculate the Jaro distance
        double jaroDistance = calculateJaroDistance(s1, s2);

        // Calculate the common prefix length (up to a maximum of 4)
        int prefixLength = 0;
        int maxPrefixLength = Math.min(1, Math.min(s1.length(), s2.length()));
        while (prefixLength < maxPrefixLength && s1.charAt(prefixLength) == s2.charAt(prefixLength)) {
            prefixLength++;
        }

        // Calculate Jaro-Winkler distance
        return jaroDistance + prefixLength * (1 - jaroDistance) * 0.1; // Winkler adjustment factor is fixed at 0.1
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java StringDistanceCalculator <input_string> <file_path> <threshold>");
            return;
        }

        String inputString = args[0];
        String filePath = args[1];
        Double threshold = Double.parseDouble(args[2]);
        int positives = 0;
        int negatives = 0;
        int total=0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                Pattern pattern = Pattern.compile("'(.*?)'");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    // Append the extracted content to the result list
                    String extractedContent = matcher.group(1);
                    double jaroWriklerDistance = calculateJaroWinklerDistance(inputString, extractedContent);
                    double jaroDistance = calculateJaroDistance(inputString, extractedContent);
                    int distance = calculateDamerauLevenshteinDistance(inputString, extractedContent);
                    if (distance < threshold){
                       // System.out.println("Input: "+extractedContent+" output: "+inputString+" positive with score: "+distance +" cosine "+calculateCosineDistance(inputString, extractedContent)+" DLdistance "+calculateDamerauLevenshteinDistance(inputString, extractedContent));
                        System.out.println("Input: "+extractedContent+" output: "+inputString+" positive with score: "+distance);
                        positives++;
                    }else{
                        System.out.println("Input: "+extractedContent+" output: "+extractedContent+" negative with score: "+distance);
                        negatives++;
                    }
                    total++;
                }
            }
            System.out.println("Processed "+total+" inputs "+ "resulting in "+positives +" positives and "+negatives+" negatives.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}