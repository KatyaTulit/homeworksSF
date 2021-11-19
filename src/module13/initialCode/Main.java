package module13.initialCode;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<OfficeWorker> workers;

    public static void main(String[] args) throws InterruptedException {

        // we have some work to do
        OfficeWorker.setTasksPool(500);

        // let's hire some workers to finish all the tasks
        workers = new ArrayList<>();
        workers.add(new OfficeWorker("Коля", 8, 3, false));
        workers.add(new OfficeWorker("Петя", 6, 6, true));
        workers.add(new OfficeWorker("Витя", 4, 2, true));
        workers.add(new OfficeWorker("Женя", 12, 8, false));

        for (Thread worker : workers) {
            worker.start();
        }
        System.out.println("Все работники заступили на службу.");

        // let's wait for the workers to finish all the tasks
        for (OfficeWorker worker : workers) {
            worker.join();

        }
        System.out.println("Все уволены.");

        // now let's print info about their efficiency
        int tasksCompleted = 0;
        for (OfficeWorker worker : workers) {
            tasksCompleted += worker.getTasksCompleted();
            worker.printCompletedTasks();
        }
        System.out.println("Всего было выполнено заданий: " + tasksCompleted);

    }
}
