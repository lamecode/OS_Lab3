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
                    Process.MAX_PRIORITY + new Random().nextInt(Process.MIN_PRIORITY),
                    new Random().nextInt(100)));
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
                        allQueues.get(j+1).push(allProcesses.get(i));
                        sortFIFOs(allQueues);
                        break;
                    }
                }

            }
        }
        ArrayList<Integer> averagePriorityTime = new ArrayList<>();
        ArrayList<Integer> sizes = new ArrayList<>();
        int j = 0;
        int time = 0;
        int summaryTime = 0;
        long delay = 0;
        while (allQueues.size() != 0) {
            long start1 = System.currentTimeMillis();
            System.out.println("Processes with priority " + allQueues.get(0).getPriority());
            long end1 = System.currentTimeMillis();
            delay += (end1 - start1) / 1000F;
            System.out.println(allQueues.get(0).convertToString(time, delay));
            long start2 = System.currentTimeMillis();
            int avPriorTime = allQueues.get(0).getLast().getWaitingTime();
            int size = allQueues.get(0).getSize();
            if (averagePriorityTime.size() == 0) {
                averagePriorityTime.add(avPriorTime);
            } else {
                averagePriorityTime.add(avPriorTime + averagePriorityTime.get(j - 1));
            }
            sizes.add(size);
            double result = (double) averagePriorityTime.get(j) / sizes.get(j);
            long end2 = System.currentTimeMillis();
            delay += (end2 - start2)/1000F + allQueues.get(0).getLast().getDelayTime();
            System.out.println("Average waiting time - " + result);
            System.out.println();
            time += avPriorTime + allQueues.get(0).getLast().getExecutionTime();
            j++;
            allQueues.remove(0);
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
