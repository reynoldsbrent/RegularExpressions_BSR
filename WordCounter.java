/**
 * WordCounter class counts the total occurrences of patterns across provided novel files using the novels' word count output file.
 * It reads the counts of pattern occurences from each novel's word count output file and sums the counts up.
 * @author Brent Reynolds
 * @version 1.0
 * Assignment 4
 * CS322 - Compiler Construction
 * Spring 2024
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class WordCounter {
    public static void main(String[] args) {
        // HashMap stores total counts of patterns in all novels
        HashMap<String, Integer> totalCounts = new HashMap<>();

        // Iterate through each novel provided
        // To run the program, use the following format from the command line: java WordCounter *wc.txt
        // Or alternatively, use the following format from the command line: java WordCounter novel1_wc.txt novel2_wc.txt novel3_wc.txt ... novel6_wc.txt
        for (String novel : args) {
            try (BufferedReader br = new BufferedReader(new FileReader(novel))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Split the line into two parts: pattern and count (separated by " | ")
                    String[] parts = line.split(" \\| ");
                    String pattern = parts[0]; // Get pattern
                    int count = Integer.parseInt(parts[1]); // Get count
                    // Update total count for the pattern in the totalCounts HashMap
                    totalCounts.put(pattern, totalCounts.getOrDefault(pattern, 0) + count);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Print the total counts for each pattern in all of the novels
        for (String pattern : totalCounts.keySet()) {
            System.out.println(pattern + " | " + totalCounts.get(pattern));
        }
    }
}
