package module10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    private static final String CURRENT_FOLDER = "src/module9";
    private static final String CITIES_FILENAME = "cities.txt";

    public static void main(String[] args) throws FileNotFoundException {
        /*

        The program
            (1) reads a list of American cities and their population from a file,
            (2) calculates combined population,
            (3) prints it.

        */

        try {
            Scanner scanner = new Scanner(new FileInputStream(new File(CURRENT_FOLDER, CITIES_FILENAME)));
            System.out.println("Combined population is: " + calculateCombinedPopulation(scanner));
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate the file, please try again. See details: " + e.getMessage());
        }
    }

    private static int calculateCombinedPopulation(Scanner scanner) {
        String line;
        int sum = 0;
        int lineCount = 0;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            lineCount++;

            try {
                sum += Integer.parseInt(line.split(" - ", 3)[1].replace(",", ""));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incorrect formatting: line #" + lineCount);
            }
        }

        return sum;
    }
}