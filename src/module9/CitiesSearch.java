package module9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CitiesSearch {

    /*

    Напишите программу, которая будет выводить список городов, подходящих под критерии запроса пользователя.

    В файле cities.txt хранится информация о городах в США (название и население). Каждая новая строка - один город.
    В строке информация записана через - и выглядит следующим образом: <Город> - <Население>.

    Пользователь может через консоль задать числовой интервал (два числа через пробел).
    Программа должна вывести информацию о городах, население которых входит в указанный интервал.
    Порядок городов в выводе не важен.
    Если таких городов в файле нет, надо вернуть "В нашей базе нет городов, подходящих под запрос".

    Чтобы выйти из программы, надо написать "exit".
    Если формат ввода неверный программа должна вернуть "Неверный формат ввода. Введите два положительных числа и нажмите Enter".

     */

    private static final String CURRENT_FOLDER = "src/module9";
    private static final String CITIES_FILENAME = "cities.txt";

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("""
               Города США и их население.
               Команды отправляются программе с помощью Enter
               - Введите два положительных числа через пробел, чтобы увидеть города с населением в заданном диапазоне.
               - Введите 'exit', чтобы покинуть программу""");
        System.out.println();

        while (!(input = scanner.nextLine()).equals("exit")) {
            if (checkInput(input)) {
                int[] interval = getInterval(input);
                printCities(interval);
            } else {
                System.out.println("Неверный формат ввода. Введите два положительных числа и нажмите Enter");
            }
        }
    }

    private static boolean checkInput(String input) {
        return input.matches("\\d+ \\d+");
    }

    private static int[] getInterval(String input) {
        // parse input and get the start and the end points of the interval

        int[] result = {0,0};

        if (checkInput(input)) {
            int first = Integer.parseInt(input.split(" ")[0]);
            int second = Integer.parseInt(input.split(" ")[1]);

            if (first <= second) {
                result[0] = first;
                result[1] = second;
            } else {
                result[0] = second;
                result[1] = first;
            }
        }

        return result;
    }

    private static void printCities(int[] interval) throws FileNotFoundException  {
        boolean noFinds = true;

        Scanner scanner = new Scanner(new FileInputStream(new File(CURRENT_FOLDER, CITIES_FILENAME)));
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            int currentPopulation = Integer.parseInt(currentLine.split(" - ")[1].replace(",", ""));
            if (currentPopulation >= interval[0] && currentPopulation <= interval[1]) {
                System.out.println(currentLine);
                noFinds = false;
            }
        }

        if (noFinds) System.out.println("В нашей базе нет городов, подходящих под запрос");
    }
}
