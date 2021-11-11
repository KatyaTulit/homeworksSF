package module10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class InitialCode {

    private static final String CURRENT_FOLDER = "src/module9";
    private static final String CITIES_FILENAME = "cities.txt";

    public static void main(String[] args) throws IOException {
        /*

        The program
            (1) reads a list of American cities and their population from a file,
            (2) calculates combined population,
            (3) prints it.

        */

        Scanner scanner = new Scanner(new FileInputStream(new File(CURRENT_FOLDER, CITIES_FILENAME)));
        String line;
        int sum = 0;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            sum += Integer.parseInt(line.split(" - ", 3)[1].replace(",", ""));
        }

        System.out.println("Combined population is: " + sum);

    }
}
