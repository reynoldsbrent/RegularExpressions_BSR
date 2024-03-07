import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class WordCounter {
    public static void main(String[] args) {
        HashMap<String, Integer> totalCounts = new HashMap<>();

        for (String novel : args) {
            try (BufferedReader br = new BufferedReader(new FileReader(novel))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    String pattern = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    totalCounts.put(pattern, totalCounts.getOrDefault(pattern, 0) + count);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (String pattern : totalCounts.keySet()) {
            System.out.println(pattern + "|" + totalCounts.get(pattern));
        }
    }
}
