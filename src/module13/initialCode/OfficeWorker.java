package module13.initialCode;

public class OfficeWorker extends Thread {

    private final String name;
    private final int needsHoursOfSleep;
    private final int tasksPerHour;
    private int tasksCompleted;
    private final boolean isLazy;
    private static int tasksPool = 0;

    public OfficeWorker(String name, int needsHoursOfSleep, int tasksPerHour, boolean isLazy) {
        if (tasksPool <= 0) {
            String mes = "Нет смысла брать на работу " + name + ", т.к. заданий для выполнения нет.";
            throw new IllegalArgumentException(mes);
        }

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

    public int getTasksCompleted() {
        return tasksCompleted;
    }

    public static void setTasksPool(int tasksPool) {
        OfficeWorker.tasksPool = tasksPool;
    }

    @Override
    public void run() {
        while (tasksPool > 0) {
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
        int canCompleteCount = workDay * tasksPerHour;
        long timeToSleep = workDay;

        if (canCompleteCount < tasksPool) {
            tasksCompleted += canCompleteCount;
            tasksPool -= canCompleteCount;
        } else {
            tasksCompleted += tasksPool;
            timeToSleep = tasksPool / tasksPerHour;
            tasksPool = 0;
        }

        sleep(timeToSleep);
    }

    private void goHomeAndSleep(int needsHoursOfSleep) throws InterruptedException {
        sleep(needsHoursOfSleep);
    }
}
