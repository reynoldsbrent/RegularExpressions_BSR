import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NovelProcessor {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Run the program again and use the following format: NovelProcessor <novel_file> <pattern_file>");
            System.out.println("The following novel files are available: Frankenstein, Dracula, DrJekyllMrHyde, ThePictureOfDorianGray, TheCastleOfOtranto, and TheTurnOfTheScrew");
            System.out.println("Add the .txt extension to the novel file name");
            System.out.println("Example: java NovelProcessor Dracula.txt Patterns.txt");
            return;
        }

        String novelFileName = args[0];
        String patternFileName = args[1];

        // Read patterns from the pattern file
        HashMap<String, String> patterns = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(patternFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                patterns.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }




        // Using a for-each loop
for (Map.Entry<String, String> entry : patterns.entrySet()) {
    String word = entry.getKey();
    String regexPattern = entry.getValue();
    System.out.println("Word: " + word + ", Regular Expression: " + regexPattern);
}

 



        // Process the novel file
        HashMap<String, Integer> counts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(novelFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String pattern : patterns.keySet()) {
                    int count = counts.getOrDefault(pattern, 0);
                    count += countOccurrences(line, patterns.get(pattern)); // Pass the pattern from the map, not the key
                    counts.put(pattern, count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write counts to output file
        String outputFileName = novelFileName.substring(0, novelFileName.lastIndexOf('.')) + "_wc.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String pattern : counts.keySet()) {
                bw.write(pattern + "|" + counts.get(pattern) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Processing complete. Output written to " + outputFileName); 
    }
 
    private static int countOccurrences(String line, String pattern) {
        int count = 0;
        System.out.println("Pattern is: " + pattern); // Print pattern for debugging
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        while (m.find()) {
            count++;
        }
        return count;
        
    }
     
}