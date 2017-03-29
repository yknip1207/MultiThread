package SingleReaderWriter;


public class WriterThread extends Thread {
    SingleReaderWriter srw;
    public WriterThread(SingleReaderWriter srw) {
        this.srw = srw;
    }
    public void run() {
        startWring();
        // insert real job here
        stopWriting();
    }
}