package module11;

import java.io.*;
import java.util.*;

public class ProcessWaitingList {

    private static final String CURRENT_FOLDER = "src/module11";
    private static final String CURRENT_LIST_FILENAME = "current_list.txt";
    private static final String NEW_APPLICATIONS_FILENAME = "new_applications.txt";
    private static final String UPDATED_LIST_FILENAME = "updated_list.txt";

    public static void main(String[] args) {
        // create a list and populate it with the old version of the waiting list
        List<String> waitingList = new LinkedList<>();
        updateListFromFile(CURRENT_LIST_FILENAME, false, waitingList);

        // remove duplicates: according to new rules each person should appear only once
        Set<String> currentSet = new LinkedHashSet<>(waitingList); // we know that the first duplicated value will be left only
        waitingList.clear();
        waitingList.addAll(currentSet);

        // update the list by processing all new applications
        updateListFromFile(NEW_APPLICATIONS_FILENAME, true, waitingList);

        // write the waiting list to a new file
        writeListToFile(UPDATED_LIST_FILENAME, waitingList);
    }

    private static void updateListFromFile(String filename, boolean isNewApplication, List<String> loadTo) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(new File(CURRENT_FOLDER, filename)));
            while (scanner.hasNextLine()) {
                processOneLine(scanner.nextLine(), isNewApplication, loadTo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл-источник не доступен для чтения. " + e.getMessage());
        }
    }

    private static void processOneLine(String input, boolean isNewApplication, List<String> loadTo) {
        if (!isNewApplication) {
            loadTo.add(input);
        } else {
            processNewApplication(input, loadTo);
        }
    }

    private static void processNewApplication(String input, List<String> loadTo) {
        String[] names = input.split(";");
        if (!loadTo.contains(names[0])) {
            if (names.length > 1) {
                int friendIndx = loadTo.indexOf(names[1]);
                if (friendIndx != -1) {
                    loadTo.add(friendIndx+1, names[0]);
                } else {
                    loadTo.add(names[0]);
                }
            } else {
                loadTo.add(names[0]);
            }
        }
    }

    private static void writeListToFile(String filename, List<String> loadTo) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(new File(CURRENT_FOLDER, UPDATED_LIST_FILENAME)));
            for (String temp : loadTo) {
                output.write(temp);
                output.newLine();
            }
            output.close();
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи файла: " + e.getMessage());
        }
    }

}
