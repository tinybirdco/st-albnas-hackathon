import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDistanceCalculator {

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
                    double distance = calculateDamerauLevenshteinDistance(inputString, extractedContent);
                    if (distance < threshold){
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