import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class searches a log file and stores unique IP addresses or usernames and their counts in a Hashmap.
 * It provides methods to return the size of each hashmap and print their contents.
 * @author Your Name
 * @version 1.0
 * Assignment 4
 * CS322 - Compiler Construction
 * Spring 2024
 */
public class LogFileProcessor {
    private HashMap<String, Integer> ipAddresses;
    private HashMap<String, Integer> usernames;

    /**
     * Constructor for LogFileProcessor class.
     * @param filename The name of the log file to process.
     * @param printFlag Flag to determine printing behavior.
     */
    public LogFileProcessor(String filename, int printFlag) {
        ipAddresses = new HashMap<>();
        usernames = new HashMap<>();
        parseLogFile(filename);
        printResults(printFlag);
    }

    /**
     * Parses the log file and extracts IP addresses and usernames.
     * @param filename The name of the log file to parse.
     */
    private void parseLogFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Regular expression for matching IP addresses
                String ipRegex = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";
                Pattern ipPattern = Pattern.compile(ipRegex);
                Matcher ipMatcher = ipPattern.matcher(line);

                while (ipMatcher.find()) {
                    String ipAddress = ipMatcher.group();
                    ipAddresses.put(ipAddress, ipAddresses.getOrDefault(ipAddress, 0) + 1);
                }

                // Regular expression for matching usernames
                String usernameRegex = "\\b[a-zA-Z0-9_-]+\\b";
                Pattern usernamePattern = Pattern.compile(usernameRegex);
                Matcher usernameMatcher = usernamePattern.matcher(line);

                while (usernameMatcher.find()) {
                    String username = usernameMatcher.group();
                    usernames.put(username, usernames.getOrDefault(username, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the results based on the printFlag.
     * @param printFlag Flag to determine printing behavior.
     */
    private void printResults(int printFlag) {
        switch (printFlag) {
            case 0:
                System.out.println(ipAddresses.size() + " unique IP addresses in the log.");
                System.out.println(usernames.size() + " unique users in the log.");
                break;
            case 1:
                for (String ipAddress : ipAddresses.keySet()) {
                    System.out.println(ipAddress + ": " + ipAddresses.get(ipAddress));
                }
                System.out.println(ipAddresses.size() + " unique IP addresses in the log.");
                System.out.println(usernames.size() + " unique users in the log.");
                break;
            case 2:
                for (String username : usernames.keySet()) {
                    System.out.println(username + ": " + usernames.get(username));
                }
                System.out.println(ipAddresses.size() + " unique IP addresses in the log.");
                System.out.println(usernames.size() + " unique users in the log.");
                break;
            default:
                System.out.println(ipAddresses.size() + " unique IP addresses in the log.");
                System.out.println(usernames.size() + " unique users in the log.");
                break;
        }
    }

    /**
     * Returns the size of the IP addresses hashmap.
     * @return The number of entries in the IP addresses hashmap.
     */
    public int getIpAddressesSize() {
        return ipAddresses.size();
    }

    /**
     * Returns the size of the usernames hashmap.
     * @return The number of entries in the usernames hashmap.
     */
    public int getUsernamesSize() {
        return usernames.size();
    }

    public static void main(String[] args) {
        String filename = args[0];
        int printFlag = Integer.parseInt(args[1]);

        LogFileProcessor processor = new LogFileProcessor(filename, printFlag);
    }
}

