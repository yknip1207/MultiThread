package Sleep;

public class Car extends Thread {
	private String name;
	public Car(String name){
		this.name = name;
	}
	public void run(){
		for(int i = 0; i < 5; i++){
			
			
			try {
				sleep(1000);
				System.out.println(this.name+ " IS PROCESSING...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
