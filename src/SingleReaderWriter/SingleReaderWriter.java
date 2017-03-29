package SingleReaderWriter;

public class SingleReaderWriter {
	int n; // number of reader and write, 0 or 1
	public synchronized void startReading() throws InterruptedException {
		while (n != 0) {
			wait();
		}
		n = 1;
	}
	public synchronized void stopReading() {
		n = 0;
		notify();
	}
	public synchronized void startWriting() throws InterruptedException {
		while (n != 0) {
			wait();
		}
		n = 1;
	}
	public synchronized void stopWriting() {
		n = 0;
		notify();
	}
}