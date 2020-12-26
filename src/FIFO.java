import java.util.ArrayList;

public class FIFO {

    private int priority;
    private Process last;

    public void push(Process p) {
        if (last == null) {
            priority = p.getPriority();
            last = p;
        } else {
            p.setNext(last);
            last = p;
        }
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
            fifo += temp.toString() + " ";
            temp = temp.getNext();
        }
        fifo += temp.toString() + " ";
        return fifo;
    }
}
