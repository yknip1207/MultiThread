package MutexSemaphoreExample1;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/***
 * 
 * 
 * Mutex定義： 如果有一段critical section code是沒辦法被concurrently access的話，要用的thread就排好隊，由mutex obj來挑誰可以進入critical section
 *            其他排在後面的人就等該thread離開critical section後才可以使用 
 * 
 * Mutexes are typically used to serialise access to a section of re-entrant code that cannot be executed concurrently by more than one thread. 
 * A mutex object only allows one thread into a controlled section, 
 * forcing other threads which attempt to gain access to that section to wait until the first thread has exited from that section
 * 
 * 
 * Semaphore定義：
 * A semaphore restricts the number of simultaneous users of a shared resource up to a maximum number. 
 * Threads can request access to the resource (decrementing the semaphore), and can signal that they have finished using the resource (incrementing the semaphore)
 *
 *
 *網址：http://crunchify.com/what-is-java-semaphore-and-mutex-java-concurrency-multithread-explained-with-example/
 */


 
public class Main {
    static Object crunchifyLock = new Object();
    static LinkedList<String> crunchifyList = new LinkedList<String>();
    
    // Semaphore maintains a set of permits.
    // Each acquire blocks if necessary until a permit is available, and then takes it.
    // Each release adds a permit, potentially releasing a blocking acquirer.
    static Semaphore semaphore = new Semaphore(0);	//初始化的時候輸入一個permit number來決定同時間可以有多少個thread
    static Semaphore mutex = new Semaphore(1);		//當permit=1的時候其實就是mutex
    
    // I'll producing new Integer every time
    static class Producer extends Thread {
        public void run() {
            int counter = 1;
            try {
                while (true) {
                    String threadName = Thread.currentThread().getName() + counter++;
                    //當一個thread想使用資源的時候，要透過acquire()來取得permit，如果呼叫acquire()時已經沒有permit的話，就被block直到獲得permit為止
                    //cf: tryAcquite()則是non-blocking，即不論有無獲得permit，皆會繼續往下執行
                    mutex.acquire(); 
                    crunchifyList.add(threadName);
                    System.out.println("Producer is prdoucing new value: " + threadName);
                    
                    //使用完畢後，再呼叫release()來釋放所獲得的permit
                    mutex.release();
                    
                    // release lock
                    semaphore.release();
                    Thread.sleep(500);
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
    
    // I'll be consuming Integer every time
    static class Consumer extends Thread {
        String consumerName;
        
        public Consumer(String name) {
            this.consumerName = name;
        }
        
        public void run() {
            try {
                
                while (true) {
                    
                    // acquire lock. Acquires the given number of permits from this semaphore, blocking until all are available
                    // process stops here until producer releases the lock
                    semaphore.acquire();
                    
                    // Acquires a permit from this semaphore, blocking until one is available
                    mutex.acquire();
                    String result = "";
                    for (String value : crunchifyList) {
                        result = value + ",";
                    }
                    System.out.println(consumerName + " consumes value: " + result + "crunchifyList.size(): "
                            + crunchifyList.size() + "\n");
                    mutex.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new Producer().start();
        new Consumer("Crunchify").start();
        new Consumer("Google").start();
        new Consumer("Yahoo").start();
    }
}