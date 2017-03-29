package CriticalSectionQueueImproved;

public class ReaderWriter extends Thread {

	public static final int READER = 1;
    public static final int WRITER = 2;
    private CircularQueue q;
    private int mode;
    private String name;
    public void run() {
    	System.out.println(this.name +" is running...");
        for (int i=0; i < 10; i++) {
            if (mode==READER) {
                q.deQueue();
                
            } else if (mode==WRITER) {
                q.enQueue(new Integer(i));
                
            }
        }
    }

    public ReaderWriter(CircularQueue q, int mode, String name) {
        this.q = q;
        this.mode = mode;
        this.name = name;
    }
    public static void main(String[] args) {
    	CircularQueue q = new CircularQueue(5);
        ReaderWriter r1, r2, w1, w2;
        (w1 = new ReaderWriter(q, WRITER, "w1")).start();
        (w2 = new ReaderWriter(q, WRITER, "w2")).start();
        (r1 = new ReaderWriter(q, READER, "r1")).start();
        (r2 = new ReaderWriter(q, READER, "r2")).start();
        try {
            w1.join(); // wait until w1 complete
            w2.join(); // wait until w2 complete
            r1.join(); // wait until r1 complete
            r2.join(); // wait until r2 complete
        } catch(InterruptedException epp) {
        }
    }
}
