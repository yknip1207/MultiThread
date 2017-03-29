package Join;

public class JoinThreadExample3 extends Thread {
    String myId;
    public JoinThreadExample3(String id) {
        myId = id;
    }
    public void run() { // overwrite Thread's run()
	for (int i=0; i < 50; i++) {
            System.out.println(myId+" Thread");
        }
    }
    public static void main(String[] argv) {
        Thread t1 = new JoinThreadExample3("T1"); // 產生Thread物件
        Thread t2 = new JoinThreadExample3("T2"); // 產生Thread物件
        t1.start(); // 開始執行t1.run()
        t2.start();
        try {
            t1.join(); // 等待t1結束
            t2.join(); // 等待t2結束
        } catch (InterruptedException e) {}
        for (int i=0;i < 5; i++) {
            System.out.println("Main Thread");
        }
    }
}