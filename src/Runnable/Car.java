package Runnable;

/***
 * 網址：http://programming.im.ncnu.edu.tw/J_Chapter9.htm
 * 啟動thread可以靠 
 *   1. extends Thread
 *   2. implements Runnable
 *   因為Java不允許多重繼承，所以如果同時要繼承parent class又要extends Thread是不行的，只能 extends parent class + implements Runnable
 *   
 * 產生Thread的方法:
 *   1. new Thread()：使用Thread()產生的Thread,其進入點為Thread裡的run()
 *   2. new Thread(Runnable)：使用Thread(Runnable)產生的Thread,其進入點為Runnable物件裡的run()
 *   當run()結束時,這個Thread也就結束了;這和main()結束有相同的效果 
 *   
 *    
 * Thread.setPriority(int)可以設定Thread的優先權,數字越大優先權越高(但優先權高的Thread其佔有CPU的機會比較高,但優先權低的也都會有機會執行到)
 * 	 public static final int MAX_PRIORITY 10
 *   public static final int MIN_PRIORITY 1
 *   public static final int NORM_PRIORITY 5
 *   
 * 其他Thread執行的相關method：
 *   1. yield():先讓給別的Thread執行
 *   2. sleep(int time):休息time mini second(1/1000秒)
 *   3. join():呼叫ThreadA.join()的執行緒會等到ThreadA結束後,才能繼續執行  
 *   
 *    
 */
public class Car implements Runnable {
	private String name;
	public Car(String name){this.name = name;}
	@Override
	public void run() {
		for(int i = 0; i < 5; i++)
			System.out.println(this.name +" is processing...");

	}

	
	
	public static void main(String[] args){
		Car car1 = new Car("Car 1");
		//呼叫一個Thread物件，把implement好的Runnable物件塞進去
		Thread thread1 = new Thread(car1);
		thread1.start();
		
		for(int i = 0; i < 5; i++)
			System.out.println("Main is processing...");
	}
}
