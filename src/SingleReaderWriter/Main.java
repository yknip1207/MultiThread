package SingleReaderWriter;

public class Main {
    public static void main(String[] argv) {
        SingleReaderWriter srw = new SingleReaderWriter();
        // create four threads
        (new WriterThread(srw)).start();
        (new WriterThread(srw)).start();
        (new ReaderThread(srw)).start();
        (new ReaderThread(srw)).start();
    }
}