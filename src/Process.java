public class Process {

    private int id;
    private int priority;
    private int executionTime;
    private int waitingTime;
    private Process next;
    public static final int MIN_PRIORITY = 32;
    public static final int MAX_PRIORITY = 1;


    Process(int id, int priority, int executionTime) {
        this.id = id;
        this.priority = priority;
        this.executionTime = executionTime;
        waitingTime = 0;
        next = null;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Process getNext() {
        return next;
    }

    public void setNext(Process next) {
        this.next = next;
    }

    public String toString() {
        return String.valueOf(id);
    }
}
