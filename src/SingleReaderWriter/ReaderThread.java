package SingleReaderWriter;

public class ReaderThread extends Thread {
    SingleReaderWriter srw;
    public ReaderThread(SingleReaderWriter srw) {
        this.srw = srw;
    }
    public void run() {
        startReading();
        // insert real job here
        stopReading();
    }
}