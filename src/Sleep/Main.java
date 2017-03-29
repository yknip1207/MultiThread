package Sleep;

public class Main {

	public static void main(String[] args) {
		Car car1 = new Car("Car 1");
		Car car2 = new Car("Car 2");
		//只要call start()就可以啟動thread並且call run()作為第一個處理的工作
		car1.start();
		car2.start();


/***
 * Sleep用法
 */
		for(int i = 0 ;i<5;i++){
			try {
				Thread.sleep(1000);  //讓main() sleep 1秒
				System.out.println("main() is processing...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("main() is processing...");
		
		
		
	}

}
