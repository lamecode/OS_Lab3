public class FIFO {

    private int priority;
    private Process last;

    FIFO() {
        priority = 0;
        last = null;
    }

    public void push(Process p) {
        if (last == null) {
            priority = p.getPriority();
            last = p;
        } else {
            p.setNext(last);
            p.setWaitingTime(calculateWaitingTime());
            last = p;
        }
    }

    public Process getLast() {
        return last;
    }

    public int calculateWaitingTime() {
        int waitingTime = 0;
        Process temp = last;
        while (temp != null && temp.getNext() != null) {
            waitingTime += temp.getExecutionTime();
            temp = temp.getNext();
        }
        waitingTime += temp.getExecutionTime();
        return waitingTime;
    }

    public int getSize() {
        int size = 0;
        Process temp = last;
        while (temp != null && temp.getNext() != null) {
            size += 1;
            temp = temp.getNext();
        }
        size += 1;
        return size;
    }

    public void pop() {
        Process temp = last;
        while (temp.getNext().getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(null);
    }

    public int getPriority() {
        return priority;
    }

    public String convertToString(int time, long delay) {
        String fifo = "";
        Process temp = last;
        long start = 0;
        long end = 0;
        while (temp != null && temp.getNext() != null) {
            fifo += temp.toString();
            fifo += "Process waiting time: " + (temp.getWaitingTime() + time) + "\n";
            temp.setDelayTime(delay);
            start = System.currentTimeMillis();
            temp = temp.getNext();
            end = System.currentTimeMillis();
            delay += (end - start) / 1000F;
            fifo += "Delay time: " + (delay) + "\n";
            if (temp != null && temp.getNext() != null) {
                start = System.currentTimeMillis();
            }
        }
        end = System.currentTimeMillis();
        delay += (end - start) / 1000F;
        fifo += temp.toString();
        fifo += "Process waiting time: " + (temp.getWaitingTime() + time) + "\n";
        fifo += "Delay time: " + (delay) + "\n";
        temp.setDelayTime(delay);
        return fifo;
    }
}
