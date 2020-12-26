import java.util.ArrayList;

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

    public String toString() {
        String fifo = "";
        Process temp = last;
        while (temp != null && temp.getNext() != null) {
            fifo += temp.toString();
            temp = temp.getNext();
        }
        fifo += temp.toString() + " ";
        return fifo;
    }
}
