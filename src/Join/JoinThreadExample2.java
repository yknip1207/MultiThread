package Join;


public class JoinThreadExample2 extends Thread{

	private String name;
	
	public JoinThreadExample2(String str){
		name=str;
	}
	
	public void run(){
		for(int i=0;i<3;i++){
			try{
				Thread.sleep((int)(1000*Math.random()));
			}
			catch(InterruptedException e){}
			System.out.println(name);
		}
	}

	/***
	 * 如果沒加 apple.join()，
	 * @author Chrisyknip
	 *
	 */	

	public static void main(String args[]){
		JoinThreadExample2 apple = new JoinThreadExample2("apple");
		JoinThreadExample2 pen = new JoinThreadExample2("pen");
		apple.start();
		
		
		
		//當有其他執行緒(apple)呼叫 join()，原來正執行的執行緒（或程式碼，這裡是main）會先暫停
		try {
			//在啟動 apple 執行緒後會先停留在 apple.join() 等待該執行緒完成，才會執行下一行程式（啟動pen執行緒）
			apple.join();
			
			//同理 pen 執行緒會停留在 pen.join() ，等到執行緒結束後就會將字串「main() 結束」字串印出
			pen.start();
			pen.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main() 結束");
	}

}
