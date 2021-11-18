package module12.homework.solution;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<OfficeWorker> workers;

    public static void main(String[] args) throws InterruptedException {

        workers = new ArrayList<>();
        workers.add(new OfficeWorker("Коля", 8, 3, false));
        workers.add(new OfficeWorker("Петя", 6, 6, true));
        workers.add(new OfficeWorker("Витя", 4, 2, true));
        workers.add(new OfficeWorker("Женя", 12, 8, false));

        for (Thread worker : workers) {
            worker.start();
        }
        System.out.println("Все работники заступили на службу.");

        Thread.sleep(12000);
        System.out.println("Рабочая неделя подошла к концу.");

        for (OfficeWorker worker : workers) {
            worker.printCompletedTasks();
            worker.interrupt();
        }
        System.out.println("Все уволены.");
    }

}
