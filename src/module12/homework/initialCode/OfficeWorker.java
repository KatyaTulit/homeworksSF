package module12.homework.initialCode;

public class OfficeWorker extends Thread {

    private final String name;
    private final int needsHoursOfSleep;
    private final int tasksPerHour;
    private int tasksCompleted;
    private final boolean isLazy;


    public OfficeWorker(String name, int needsHoursOfSleep, int tasksPerHour, boolean isLazy) {
        if (tasksPerHour <= 0) {
            String mes = "Работник должен уметь выполнять больше 0 заданий в час, иначе его не стоит брать на работу";
            throw new IllegalArgumentException(mes);
        }

        this.name = name;
        this.needsHoursOfSleep = needsHoursOfSleep;
        this.tasksPerHour = tasksPerHour;
        this.isLazy = isLazy;
        tasksCompleted = 0;
    }

    public void printCompletedTasks() {
        System.out.println(name + " успел выполнить " + tasksCompleted + " заданий.");
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                work(tasksPerHour, isLazy);
                goHomeAndSleep(needsHoursOfSleep);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    private void work(int tasksPerHour, boolean isLazy) throws InterruptedException {
        int workDay = isLazy ? 6 : 8;
        tasksCompleted += workDay * tasksPerHour;
        sleep(workDay * 100L);
    }

    private void goHomeAndSleep(int needsHoursOfSleep) throws InterruptedException {
        sleep(needsHoursOfSleep * 100L);
    }
}
