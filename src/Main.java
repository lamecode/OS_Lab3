import java.util.ArrayList;
import java.util.Random;

public class Main {

    static ArrayList<Process> allProcesses;
    static ArrayList<FIFO> allQueues;

    public static void main(String[] args) {
        allProcesses = new ArrayList<>();
        allQueues = new ArrayList<>();
        for (int i = 0; i < 34; i++) {
            allProcesses.add(new Process(i,
                    Process.MIN_PRIORITY +
                            new Random().nextInt(Process.MAX_PRIORITY), Process.MIN_PRIORITY +
                    new Random().nextInt(Process.MAX_PRIORITY)));
        }
        for (int i = 0; i < allProcesses.size(); i++) {
            if (allQueues.size() == 0) {
                allQueues.add(new FIFO());
                allQueues.get(0).push(allProcesses.get(i));
            } else {
                for (int j = 0; j < allQueues.size(); j++) {
                    if (allProcesses.get(i).getPriority()
                            == allQueues.get(j).getPriority()) {
                        allQueues.get(j).push(allProcesses.get(i));
                        break;
                    }
                    if (j == allQueues.size() - 1) {
                        allQueues.add(new FIFO());
                        allQueues.get(j).push(allProcesses.get(i));
                        break;
                    }
                }

            }
        }

    }

    public static void sortFIFOs(ArrayList<FIFO> f) {
        FIFO temp;
        for (int i = 0; i < f.size(); i++) {
            for (int j = 0; j < f.size() - 1; j++) {
                if (f.get(j).getPriority() > f.get(j+1).getPriority()) {
                    temp = f.get(j);
                    f.set(j, f.get(j+1));
                    f.set(j + 1, temp);
                }
            }
        }
    }
}
