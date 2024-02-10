package file_handling;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ScoreHandler {

    private static final String FILE_NAME = "files/score.txt";

    public static void saveNumberToFile(int number) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(number);
            System.out.println("Number saved to file: " + number);
        } catch (IOException e) {
            System.err.println("Error saving number to file: " + e.getMessage());
        }
    }

    public static int readNumberFromFile() {
        int number = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                number = Integer.parseInt(line);
                System.out.println("Number read from file: " + number);
            } else {
                System.out.println("File is empty. Returning default number (0).");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading number from file: " + e.getMessage());
        }
        return number;
    }
}
