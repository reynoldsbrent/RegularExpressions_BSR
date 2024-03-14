/**
 * NovelProcessor class processes a novel text file using patterns provided in the patterns file.
 * It counts occurrences of each pattern in the novel and writes the counts to an output file.
 * @author Brent Reynolds
 * @version 1.0
 * Assignment 4
 * CS322 - Compiler Construction
 * Spring 2024
 */
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
    /**
     * Main method to execute the novel processing.
     * @param args Command-line arguments: novel file name and pattern file name.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Run the program again and use the following format from the command line: java NovelProcessor novel_text.txt pattern_file.txt");
            System.out.println("Make sure to add the .txt extension to the novel file name");
            System.out.println("Example: java NovelProcessor Dracula.txt Patterns.txt");
            return;
        }

        String novelFileName = args[0];
        String patternFileName = args[1];

        // Read patterns from pattern file
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




        // For debugging 
        /*
for (Map.Entry<String, String> entry : patterns.entrySet()) {
    String word = entry.getKey();
    String regexPattern = entry.getValue();
    System.out.println("Word: " + word + ", Regular Expression: " + regexPattern); 
}
*/
 



        // Process novel files and count occurrences of each pattern
        HashMap<String, Integer> counts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(novelFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String pattern : patterns.keySet()) {
                    int count = counts.getOrDefault(pattern, 0);
                    count += countOfPatternsOccurence(line, patterns.get(pattern)); 
                    counts.put(pattern, count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write pattern counts to output file 
        String outputFileName = novelFileName.substring(0, novelFileName.lastIndexOf('.')) + "_wc.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String pattern : counts.keySet()) {
                bw.write(patterns.get(pattern) + " | " + counts.get(pattern) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Processing complete. Output written to " + outputFileName); 
    }
 
    /**
     * Counts the number of occurrences of a pattern in a line of text.
     * @param line The line to search for pattern occurrences.
     * @param pattern The pattern to search for.
     * @return The count of occurrences of the pattern in the line.
     */
    private static int countOfPatternsOccurence(String line, String pattern) {
        int count = 0;
        //System.out.println("Pattern is: " + pattern); // For debugging
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        while (m.find()) {
            count++;
        }
        return count;
        
    }
     
}