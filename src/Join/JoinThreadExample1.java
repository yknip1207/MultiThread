package Join;

public class JoinThreadExample1 {
	/***
	 * 1. main.sysout("Main starts")
	 * 2. new thread2 -> 先sleep 1秒再 sysout("Thread 2 is running")
	 * 3. thread2.join()
	 * 4. main.sysout("Main ends")
	 * 
	 * 
	 * 
	 * 
	 * if thread2.join(): join入main thread
	 * 		(Main starts) -> (Thread 2 starts) -> (Thread 2 running) -> (Thread 2 ends) -> (Main ends) 
	 * if 沒放thread2.join()：Main會在 thread.sleep()的時候跑完
	 * 		(Main starts) -> (Main ends) -> (Thread 2 starts) -> (Thread 2 running) -> (Thread 2 ends) 
	 * @param args
	 */
	
	public static void main(String[] args) {
		// 程式啟動後main thread就開始，在main thread中您新建thread 2，並在啟動thread2後，
		// 將之加入（join）main thread的流程之中，thread2必須 先執行完畢，主執行緒才會再繼續它原本的流程， 執行結果如下
		System.out.println("Main thread starts...");
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				System.out.println("Thread 2 starts...");
				try {
					for(int i = 0; i <5;i++){
						Thread.sleep(1000);
						System.out.println("Thread 2 is running...");
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				System.out.println("Thread 2 ends...");
			}
		});
		
		thread2.start();


		
//		try {
//			//當有其他執行緒(thread2)呼叫 join()，原來正執行的執行緒（或程式碼，這裡就是main）會先暫停
//			// Thread 2 加入 Thread 1的流程裡！
//			//如果程式中沒有將thread 2使用join()將之加入main thread的流程中，
//			//則最後一行顯示"Thread 1 ends"的陳述會先執行完畢（因為thread2 使用了sleep()，這讓主執行緒有機會取得時間來執行）。
//			thread2.join();
//			
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("Main thread ends...");

	}

}
