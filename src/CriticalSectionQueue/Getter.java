package CriticalSectionQueue;
/***
 * 專門由Queue裡抓取工作需求做進一步處理
 * @author Chrisyknip
 *
 */
public class Getter extends Thread{
	CircularQueue q;
	public Getter(CircularQueue q){this.q = q;}
	
	
	public void run(){
		while(true){
			try {
				Object data = q.deQueue();
			} catch (Exception e) {
				// if we try to sleep here, user may feel slow response
                // if we do not sleep, CPU will be wasted
			}
		}
	}
}
