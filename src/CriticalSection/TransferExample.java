package CriticalSection;

public class TransferExample extends Thread {
    public static Object lock = new Object();
    //所有Object共用這一份
    public static int A = 1000;
    public static int B = 0;
    private int amount;
    public TransferExample(int x) {
        amount = x;
    }
    public void run() {
    	//這段要存取共同資料結構的程式碼時，該物件必須取得lock才有辦法access
        synchronized(lock) { // 取得lock,如果別的thread 已取得,則目前這個thread會等到thread 釋放該lock
            if (A >= amount) {
                A = A - amount;
                B = B + amount;
            }
        } // 離開synchronized區塊後,此thread會自動釋放lock
    }
    public static void main(String[] argv) {
    	Thread transaction1 = new TransferExample(100);
    	Thread transaction2 = new TransferExample(200);
        //可以呼叫什麼方法, 看 reference type   實際用哪個版本, 看 object type
        transaction1.start();
        transaction2.start();
    }
}