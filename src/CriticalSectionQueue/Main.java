package CriticalSectionQueue;

/***
 * 假設我們的執行環境是,某些Thread專門負責讀取使用者的需求,並把工作放到Queue裡面,某些Thread則專門由Queue裡抓取工作需求做進一步處理。
 * 這種架構的好處是,可以把慢速或不定速的輸入(如透過網路讀資料,連線速度可能差很多),和快速的處理分開,可使系統的反應速度更快,更節省資源。
 * 那麼以Exception來處理Queue空掉或爆掉的情況並不合適,因為使用Queue的人必須處理例外狀況,並不斷的消耗CPU資源
 * 
 * 
 * 
 * 為了解決這類資源分配的問題,Java Object提供了下面三個methods:
 *    1. wait():使呼叫此方法的Thread進入Blocking Mode,並設為等待該Object, 呼叫wait()時, 該Thread必須擁有該物件的lock。
 *       Blocking Mode下的Thread必須釋放所有手中的lock,並且無法使用CPU。
 *    2. notifyAll():讓等待該Object的所有Thread進入Runnable Mode。
 *    3. notify():讓等待該Object的某一個Thread進入Runnable Mode
 *    
 * Mode:
 *    1. Runnable Mode： Thread隨時可由作業系統分配CPU資源。
 *    2. Blocking Mode： Thread正在等待某個事件發生, OS不會讓這種Thread取得CPU資源
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircularQueue q = new CircularQueue(10);
		Getter r1 = new Getter(q);
        Getter r2 = new Getter(q);
        Setter w1 = new Setter(q);
        Setter w2 = new Setter(q);
        //r1這個getter想要從Queue裡面拿Obj出來，但如果已經沒東西好拿了，就會發生Exception，這樣不斷進入例外狀況，消耗CPU資源
        r1.start();
        r2.start();
        //w1這個getter想要加Obj到Queue裡面，但如果Queue已經滿了，就會發生Exception，這樣不斷進入例外狀況，消耗CPU資源
        w1.start();
        w2.start();
	}

}
