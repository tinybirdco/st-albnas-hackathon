import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDistanceCalculator {

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

    public static double calculateJaroWinklerDistance(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        // Calculate the Jaro distance
        double jaroDistance = calculateJaroDistance(s1, s2);

        // Calculate the common prefix length (up to a maximum of 4)
        int prefixLength = 0;
        int maxPrefixLength = Math.min(4, Math.min(s1.length(), s2.length()));
        while (prefixLength < maxPrefixLength && s1.charAt(prefixLength) == s2.charAt(prefixLength)) {
            prefixLength++;
        }

        // Calculate Jaro-Winkler distance
        return jaroDistance + prefixLength * (1 - jaroDistance) * 0.1; // Winkler adjustment factor is fixed at 0.1
    }


    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
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
                    int distance = calculateDistance(inputString, extractedContent);
                    double jaroWriklerDistance = calculateJaroWinklerDistance(inputString, extractedContent);
                    double jaroDistance = calculateJaroDistance(inputString, extractedContent);
                    System.out.println("Distance between \"" + inputString + "\" and \"" + extractedContent + "\": " + distance + " / "+jaroDistance+ " / "+jaroWriklerDistance );
                    if (jaroWriklerDistance > threshold){
                        positives++;
                    }else{
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