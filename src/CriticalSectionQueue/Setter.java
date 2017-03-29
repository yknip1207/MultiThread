package CriticalSectionQueue;
/***
 * 專門負責讀取使用者的需求,並把工作放到Queue裡面
 * @author Chrisyknip
 *
 */
public class Setter extends Thread{
	CircularQueue q;
	public Setter(CircularQueue q){this.q = q;}
	public void run(){
		while(true){
			try {
                Object data = null;
                // get user request
                 q.enQueue(data);
            } catch(Exception e) {
                // if we try to sleep here, user may feel slow response
                // if we do not sleep, CPU will be wasted
            }
		}
	}
}
