import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class WordCounter {
    public static void main(String[] args) {
        // Create a HashMap to store total counts of patterns across all novels
        HashMap<String, Integer> totalCounts = new HashMap<>();

        // Iterate through each novel provided as a command line argument
        // To run the program, use the following format from the command line: java WordCounter *wc.txt
        // Or alternatively, use the following format from the command line: java WordCounter novel1_wc.txt novel2_wc.txt novel3_wc.txt ... novel6_wc.txt
        for (String novel : args) {
            try (BufferedReader br = new BufferedReader(new FileReader(novel))) {
                String line;
                // Read each line from the novel file
                while ((line = br.readLine()) != null) {
                    // Split the line into two parts: pattern and count (separated by " | ")
                    String[] parts = line.split(" \\| ");
                    String pattern = parts[0]; // Extract the pattern
                    int count = Integer.parseInt(parts[1]); // Extract the count
                    // Update the total count for the pattern in the totalCounts map
                    totalCounts.put(pattern, totalCounts.getOrDefault(pattern, 0) + count);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Print the total counts for each pattern across all novels
        for (String pattern : totalCounts.keySet()) {
            System.out.println(pattern + " | " + totalCounts.get(pattern));
        }
    }
}
